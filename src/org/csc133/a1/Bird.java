package org.csc133.a1;

public class Bird extends MovableObject
{
    public Bird()
    {
        // TODO birds should start in random locations with random headings
        this(10, 0, 0, 30, 45, 3);
    }

    public Bird(int size, double x, double y, int color, int heading, int speed)
    {
        super(size, x, y, color, heading, speed);
    }

    @Override
    public String toString()
    {
        return "Bird{} " + super.toString();
    }
}
