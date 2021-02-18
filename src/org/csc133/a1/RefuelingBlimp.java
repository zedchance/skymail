package org.csc133.a1;

public class RefuelingBlimp extends GameObject
{
    private int capacity;

    public RefuelingBlimp()
    {
        this(20, 0, 0, 200, 100);
    }

    public RefuelingBlimp(int size, float x, float y, int color, int capacity)
    {
        super(size, x, y, color);
        this.capacity = capacity;
    }

    public void withdrawFuel()
    {
        // TODO
    }

    @Override
    public String toString()
    {
        return "RefuelingBlimp{" +
                "capacity=" + capacity +
                "} " + super.toString();
    }
}
