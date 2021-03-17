package org.csc133.a2.model;

/**
 * Objects in game that have a movable position
 */
public abstract class Movable extends GameObject
{
    private int heading;
    private int speed;

    /**
     * By default, Movable's have these properties:
     * heading is 0,
     * speed is 0.
     */
    public Movable()
    {
        this(0, 0);
    }

    /**
     * Create a Movable with a specific heading and speed.
     *
     * @param heading the direction of travel
     * @param speed   the object's travel speed
     */
    public Movable(int heading, int speed)
    {
        super();
        this.heading = heading;
        this.speed = speed;
    }

    public int getHeading()
    {
        return heading;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * The heading is limited between 0 and 359, and acts
     * the same way as a compass heading (0 is north).
     *
     * @param heading compass heading to set
     */
    public void setHeading(int heading)
    {
        this.heading = Math.floorMod(heading, 360);
    }

    /**
     * Move the object to a new location based on its current heading and speed
     */
    public void move()
    {
        double theta = 90 - heading;
        double deltaX = Math.cos(Math.toRadians(theta)) * speed;
        double deltaY = Math.sin(Math.toRadians(theta)) * speed;
        setLocation(getX() + deltaX, getY() + deltaY);
    }

    @Override
    public String toString()
    {
        return "Movable{" +
                "heading=" + heading +
                ", speed=" + speed +
                "} " + super.toString();
    }
}
