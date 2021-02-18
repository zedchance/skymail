package org.csc133.a1;

public abstract class MovableObject extends GameObject
{
    private int heading;
    private int speed;

    public MovableObject(int size, float x, float y, int color, int heading, int speed)
    {
        super(size, x, y, color);
        this.heading = heading;
        this.speed = speed;
    }
}
