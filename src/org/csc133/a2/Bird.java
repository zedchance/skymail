package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;

/**
 * Birds fly randomly, and can collide with the player
 */
public class Bird extends Movable
{
    /**
     * By default, Bird's have these properties:
     * color is yellow,
     * a random start location,
     * a random speed,
     * a random heading.
     */
    public Bird()
    {
        super();
        Random rand = new Random();
        double startX = (double) rand.nextInt(1025);
        double startY = (double) rand.nextInt(768);
        setLocation(startX, startY);
        setSpeed(rand.nextInt(11));
        setHeading(rand.nextInt(360));
        super.setColor(ColorUtil.rgb(100, 100, 0));
    }

    @Override
    public void setColor(int color)
    {
        System.out.println("Birds cannot change color.");
    }

    /**
     * Bird change their heading randomly +- 5 degrees
     * from current heading each tick
     */
    @Override
    public void move()
    {
        Random rand = new Random();
        setHeading(rand.nextInt(11) - 5);
        super.move();
    }

    @Override
    public String toString()
    {
        return "Bird\t\t\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY()) +
                " speed=" + getSpeed() +
                " heading=" + getHeading();
    }
}
