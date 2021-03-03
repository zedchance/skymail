package org.csc133.a1;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameWorld has all game objects, and commands to interact with the objects
 */
public class GameWorld
{
    private final List<GameObject> world = new ArrayList<>();
    private Helicopter player;
    private int clock, lives;

    // this initial level design has hard coded values for all objects
    public void init()
    {
        // setup
        clock = 0;
        lives = 3;
        int startX = 100;
        int startY = 100;

        // player starts at first SkyScraper
        player = new Helicopter(startX, startY);
        SkyScraper sky1 = new SkyScraper(startX, startY, 1);

        // the rest of the checkpoints to reach
        SkyScraper sky2 = new SkyScraper(100, 200, 2);
        SkyScraper sky3 = new SkyScraper(100, 300, 3);
        SkyScraper sky4 = new SkyScraper(250, 500, 4);
        SkyScraper sky5 = new SkyScraper(800, 500, 5);

        // refuel blimps
        RefuelingBlimp blimp1 = new RefuelingBlimp(250, 350);
        RefuelingBlimp blimp2 = new RefuelingBlimp(600, 400);

        // 2 birds
        Bird bird1 = new Bird();
        Bird bird2 = new Bird();

        // add game objects to world
        world.add(player);
        world.add(sky1);
        world.add(sky2);
        world.add(sky3);
        world.add(sky4);
        world.add(sky5);
        world.add(blimp1);
        world.add(blimp2);
        world.add(bird1);
        world.add(bird2);
    }

    public void exit()
    {
        // TODO print game over message, displaying whether the player won or not, and how many clock ticks
        System.out.println("Exiting after " + clock + " ticks");
        if (lives == 0)
        {
            System.out.println("You have run out of lives!");
        }
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
        player.collide();
        if (player.isDestroyed())
        {
            lives--;
            player.resetAfterCollision();
            System.out.printf("You crashed!\nRemaining lives: %3d\n", lives);
        }
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
        // TODO take in an amount from a blimp
        player.fuelUp(100);
    }

    public void birdCollision()
    {
        player.collideWithBird();
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
        System.out.printf("Speed: %5d    Heading: %5d\n", player.getSpeed(), player.getHeading());
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
}
