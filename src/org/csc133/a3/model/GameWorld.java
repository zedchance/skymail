package org.csc133.a3.model;

import com.codename1.ui.Dialog;
import org.csc133.a3.model.strategy.AttackStrategy;
import org.csc133.a3.model.strategy.CircleStrategy;
import org.csc133.a3.model.strategy.FlyToSkyScraperStrategy;
import org.csc133.a3.model.strategy.Strategy;
import org.csc133.a3.view.MapView;

import java.util.HashMap;
import java.util.Random;

/**
 * The GameWorld has all game objects, and commands to interact with the objects
 */
public class GameWorld
{
    private final int TOTAL_CHECKPOINTS = 9;
    private final Random rand = new Random();

    private final GameObjectCollection world = new GameObjectCollection();
    private final GameObjectCollection toBeSpawned = new GameObjectCollection();
    private final GameObjectCollection toBeDespawned = new GameObjectCollection();
    private final Helicopter player = PlayerHelicopter.getPlayer();
    private NonPlayerHelicopter nph3;
    private int clock = 0;
    private int lives = 3;

    public void init()
    {
        // setup
        int startX = 100;
        int startY = 100;
        world.clear();

        // player starts at first SkyScraper, only one PlayerHelicopter can exist at once
        player.setLocation(new DoublePoint(startX, startY));
        player.resetHelicopter();
        player.landAtSkyScraper(1);

        // player is first item in world
        world.add(player);

        // add initial objects to be spawned
        spawnSkyScrapers(startX, startY);
        spawnBlimps(2);
        spawnBirds(2);
        spawnNonPlayerHelicopters();
        handleSpawns();
    }

    public GameObjectCollection getWorld()
    {
        return world;
    }

    public Helicopter getPlayer()
    {
        return player;
    }

    /**
     * Dash elements to be displayed on GlassCockpit
     *
     * @return HashMap of fuel, damage, lives, last, heading
     */
    public HashMap<String, Double> getDashElements()
    {
        HashMap<String, Double> dashElements = new HashMap<>();
        dashElements.put("fuel", player.getFuelLevel());
        dashElements.put("damage", (double) player.getDamageLevel());
        dashElements.put("lives", (double) lives);
        dashElements.put("last", (double) player.getLastSkyscraperReached());
        dashElements.put("heading", (double) player.getHeading());
        return dashElements;
    }

    public void checkIfReset()
    {
        if (player.isDestroyed())
        {
            System.out.printf("You crashed!\nRemaining lives: %3d\n", --lives);
            init();
        }
        else if (player.isFuelEmpty())
        {
            System.out.printf("You have run out of fuel!\nRemaining lives: %3d\n", --lives);
            init();
        }
        else if (player.getLastSkyscraperReached() == TOTAL_CHECKPOINTS)
        {
            exit();
        }
        else if (nph3.getLastSkyscraperReached() == TOTAL_CHECKPOINTS)
        {
            exit();
        }
    }

    public void exit()
    {
        String endOfGameDisplay = "";
        if (isGameOver())
        {
            endOfGameDisplay += "You have run out of lives!\n";
        }
        else if (player.getLastSkyscraperReached() == TOTAL_CHECKPOINTS)
        {
            endOfGameDisplay += "You have completed all checkpoints!\n";
        }
        else if (nph3.getLastSkyscraperReached() == TOTAL_CHECKPOINTS)
        {
            endOfGameDisplay += "The opponent helicopter has completed all checkpoints before you!\n";
        }
        endOfGameDisplay += "Exiting after " + clock + " ticks";

        Dialog.show(null, endOfGameDisplay, "Ok", null);
        System.exit(0);
    }

    /**
     * Accelerate the player's helicopter, up to the maximum
     * speed
     */
    public void accelerate()
    {
        player.accelerate();
    }

    /**
     * Brakes the player's helicopter, down to 0
     */
    public void brake()
    {
        player.decelerate();
    }

    /**
     * The player can turn left 5 degrees at a time,
     * issue multiple commands for more
     */
    public void left()
    {
        player.changeDirection(5);
    }

    /**
     * The player can turn right 5 degrees at a time,
     * issue multiple commands for more
     */
    public void right()
    {
        player.changeDirection(-5);
    }

    /**
     * Simulate a collision with another Helicopter
     */
    public void helicopterCollision(Helicopter otherHelicopter)
    {
        player.collide(otherHelicopter);
    }

    public void landOnSkyScraperCheckpoint(int n)
    {
        System.out.printf("Attempting to land on SkyScraper %d\n", n);
        if (player.landAtSkyScraper(n))
        {
            System.out.printf("Landing successful, you have reached SkyScraper %d\n", n);
        }
        else
        {
            System.out.println("You cannot land here, you must reach the SkyScrapers in order.");
        }
    }

    /**
     * Refuel the player's Helicopter
     */
    public void refuel(RefuelingBlimp blimp)
    {
        if (!blimp.isEmpty())
        {
            blimp.transferFuel(player);
            if (blimp.isEmpty()) spawnBlimps(1);
        }
    }

    /**
     * Collide with a bird, after the collision
     * the bird is removed from the world, and a new
     * is added.
     */
    public void birdCollision(Bird bird)
    {
        player.collideWithBird();
        toBeDespawned.add(bird);
        spawnBirds(1);
    }

    /**
     * Advance game clock by 1 tick, moving any movable objects to
     * their new location.
     *
     * @return the current clock tick as an int
     */
    public int tick()
    {
        for (GameObject thisObject : world)
        {
            if (thisObject instanceof Movable)
            {
                Movable mObj = (Movable) thisObject;
                mObj.move();
            }
            // detect collision
            for (GameObject thatObject : world)
            {
                if (thisObject.collidesWith(thatObject))
                {
                    System.err.println("COLLISION: " + thisObject.getClass().getSimpleName()
                                               + " -> "
                                               + thatObject.getClass().getSimpleName());
                    thisObject.handleCollision(thatObject, this);
                    thatObject.handleCollision(thisObject, this);
                }
            }
        }
        handleSpawns();
        return ++clock;
    }

    /**
     * Spawns new objects to world,
     * despawns old objects from world.
     */
    private void handleSpawns()
    {
        // spawn new objects
        world.addAll(toBeSpawned);
        toBeSpawned.clear();

        // despawn objects
        world.removeAll(toBeDespawned);
        toBeDespawned.clear();
    }

    /**
     * The game is over when the player runs out of lives
     *
     * @return true if game is over
     */
    public boolean isGameOver()
    {
        return lives == 0;
    }

    private void spawnNonPlayerHelicopters()
    {
        // TODO: 4/27/21 spawn n amount of NPH with a random strategy each, despawn on death
        // TODO: 4/23/21 allow change of strategies with a button
        Strategy circleStrategy = new CircleStrategy();
        NonPlayerHelicopter nph1 = new NonPlayerHelicopter();
        nph1.setStrategy(circleStrategy);
        toBeSpawned.add(nph1);

        Strategy attackStrategy = new AttackStrategy();
        NonPlayerHelicopter nph2 = new NonPlayerHelicopter();
        nph2.setStrategy(attackStrategy);
        nph2.setStickAngle(0);
        toBeSpawned.add(nph2);

        Strategy flyToSkyScraperStrategy = new FlyToSkyScraperStrategy(world);
        nph3 = new NonPlayerHelicopter();
        nph3.setStrategy(flyToSkyScraperStrategy);
        toBeSpawned.add(nph3);
    }

    private void spawnSkyScrapers(int startX, int startY)
    {
        // first sky scraper is at helo's start location
        toBeSpawned.add(new SkyScraper(startX, startY, 1));

        // the rest of the checkpoints to reach
        for (int i = 2; i <= TOTAL_CHECKPOINTS; i++)
        {
            int randX = rand.nextInt(MapView.mapWidth);
            int randY = rand.nextInt(MapView.mapHeight);
            toBeSpawned.add(new SkyScraper(randX, randY, i));
        }
    }

    private void spawnBlimps(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            toBeSpawned.add(new RefuelingBlimp());
        }
    }

    private void spawnBirds(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            toBeSpawned.add(new Bird());
        }
    }
}
