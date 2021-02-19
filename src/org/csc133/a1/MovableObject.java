package org.csc133.a1;

public abstract class MovableObject extends GameObject
{
    private int heading;
    private int speed;

    /**
     * By default, MovableObject's have these properties:
     * heading is 0,
     * speed is 0.
     */
    public MovableObject()
    {
        this(0, 0);
    }

    /**
     * Create a MovableObject with a specific heading and speed.
     *
     * @param heading the direction of travel
     * @param speed   the object's travel speed
     */
    public MovableObject(int heading, int speed)
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

    public void setHeading(int heading)
    {
        this.heading = heading;
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
        return "MovableObject{" +
                "heading=" + heading +
                ", speed=" + speed +
                "} " + super.toString();
    }
}
