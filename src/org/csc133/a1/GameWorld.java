package org.csc133.a1;

import java.util.ArrayList;
import java.util.List;

public class GameWorld
{
    private final List<GameObject> world = new ArrayList<>();
    private Helicopter player;
    private int clock, lives;

    public void init()
    {
        // Setup
        clock = 0;
        lives = 3;
        int startX = 100;
        int startY = 100;
        player = new Helicopter(startX, startY);

        // TODO skyscrapers, birds, blimps
        // Game objects
        world.add(player);
        world.add(new SkyScraper(startX, startY, 0));
    }

    public void exit()
    {
        // TODO print game over message, displaying whether the player won or not, and how many clock ticks
        System.out.println("Exiting after " + clock + " ticks");
    }

    // TODO commands for interacting with the game world

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
     * Advance game clock by 1 tick, moving any movable objects to
     * their new location.
     *
     * @return the current clock tick as an int
     */
    public int tick()
    {
        for (int i = 0; i < world.size(); i++)
        {
            if (world.get(i) instanceof MovableObject)
            {
                MovableObject mObj = (MovableObject) world.get(i);
                mObj.move();
            }
        }
        return ++clock;
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
}
