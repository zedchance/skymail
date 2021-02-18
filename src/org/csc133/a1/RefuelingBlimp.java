package org.csc133.a1;

public class RefuelingBlimp extends GameObject
{
    private int capacity;

    public RefuelingBlimp()
    {
        // TODO capacity should be proportional to size, fix constructors to make this easy
        this(20, 0, 0, 200, 100);
    }

    public RefuelingBlimp(int size, double x, double y, int color, int capacity)
    {
        super(size, x, y, color);
        this.capacity = capacity;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void withdrawFuel(int amount)
    {
        capacity = capacity - amount;
        // TODO maybe return amount of fuel withdrawn?
    }

    public boolean isEmpty()
    {
        return capacity == 0;
    }

    @Override
    public String toString()
    {
        return "RefuelingBlimp{" +
                "capacity=" + capacity +
                "} " + super.toString();
    }
}
