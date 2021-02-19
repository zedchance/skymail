package org.csc133.a1;

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
        // TODO birds should have a random start location, random speed, random heading
    }

    @Override
    public String toString()
    {
        return "Bird{} " + super.toString();
    }
}
