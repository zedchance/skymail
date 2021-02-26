package org.csc133.a1;

import java.util.Random;

public class Bird extends MovableObject
{
    /**
     * By default, Bird's have these properties:
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
