package org.csc133.a1;

public abstract class MovableObject extends GameObject
{
    private int heading;
    private int speed;

    public MovableObject(int size, double x, double y, int color, int heading, int speed)
    {
        super(size, x, y, color);
        this.heading = heading;
        this.speed = speed;
    }

    public void move()
    {
        // TODO figure out new position based on speed and heading (compass)
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
