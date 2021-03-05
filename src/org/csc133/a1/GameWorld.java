package org.csc133.a1;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameWorld has all game objects, and commands to interact with the objects
 */
public class GameWorld
{
    private final int TOTAL_CHECKPOINTS = 5;

    private final List<GameObject> world = new ArrayList<>();
    private Helicopter player;
    private int clock = 0;
    private int lives = 3;

    // this initial level design has hard coded values for all objects
    public void init()
    {
        // setup
        int startX = 100;
        int startY = 100;
        world.clear();

        // player starts at first SkyScraper
        player = new Helicopter(startX, startY);
        world.add(player);
        world.add(new SkyScraper(startX, startY, 1));

        // the rest of the checkpoints to reach
        world.add(new SkyScraper(100, 200, 2));
        world.add(new SkyScraper(100, 300, 3));
        world.add(new SkyScraper(250, 500, 4));
        world.add(new SkyScraper(800, 500, 5));

        // refuel blimps
        placeBlimps(2);

        // 2 birds
        placeBirds(2);
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
    }

    public void exit()
    {
        if (lives == 0)
        {
            System.out.println("You have run out of lives!");
        }
        else if (player.getLastSkyscraperReached() == TOTAL_CHECKPOINTS)
        {
            System.out.println("You have completed all checkpoints!");
        }
        System.out.println("Exiting after " + clock + " ticks");
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
        player.changeDirection(-5);
    }

    /**
     * The player can turn right 5 degrees at a time,
     * issue multiple commands for more
     */
    public void right()
    {
        player.changeDirection(5);
    }

    /**
     * Simulate a collision with another Helicopter
     */
    public void helicopterCollision()
    {
        // TODO actually collide with another helo object
        player.collide();

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
    public void refuel()
    {
        // TODO check if this is the closest blimp, for now its breaking after the first blimp
        for (GameObject obj : world)
        {
            if (obj instanceof RefuelingBlimp)
            {
                RefuelingBlimp blimp = (RefuelingBlimp) obj;
                if (!blimp.isEmpty())
                {
                    blimp.transferFuel(player);
                    if (blimp.isEmpty()) placeBlimps(1);
                    break;
                }
            }
        }
    }

    /**
     * Collide with a bird, after the collision
     * the bird is removed from the world, and a new
     * is added.
     */
    public void birdCollision()
    {
        // TODO this collides with the first bird in the list, check for closest
        for (GameObject obj : world)
        {
            if (obj instanceof Bird)
            {
                Bird bird = (Bird) obj;
                player.collideWithBird();
                world.remove(bird);
                placeBirds(1);
                break;
            }
        }
    }

    /**
     * Advance game clock by 1 tick, moving any movable objects to
     * their new location.
     *
     * @return the current clock tick as an int
     */
    public int tick()
    {
        for (int i = 0; i < world.size(); i++)
        {
            if (world.get(i) instanceof Movable)
            {
                Movable mObj = (Movable) world.get(i);
                mObj.move();
            }
        }
        return ++clock;
    }

    /**
     * Generate a basic text HUD
     */
    public void display()
    {
        System.out.printf("You are at (%.1f, %.1f)\n", player.getX(), player.getY());
        System.out.printf("Speed: %2d/%2d    Heading: %5d\n", player.getSpeed(), player.getMaximumSpeed(), player.getHeading());
        System.out.printf("Fuel: %6.0f    Damage: %6d\n", player.getFuelLevel(), player.getDamageLevel());
        System.out.printf("Lives: %5d    Clock: %7d\n", lives, clock);
        System.out.printf("Highest SkyScraper reached: %2d\n", player.getLastSkyscraperReached());
    }

    /**
     * Print out a text representation of the map
     */
    public void map()
    {
        for (GameObject item : world)
        {
            System.out.println(item);
        }
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

    private void placeBlimps(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            world.add(new RefuelingBlimp());
        }
    }

    private void placeBirds(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            world.add(new Bird());
        }
    }
}
