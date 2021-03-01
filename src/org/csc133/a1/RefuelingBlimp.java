package org.csc133.a1;

public class RefuelingBlimp extends GameObject
{
    private int capacity;

    /**
     * By default, RefuelingBlimp's have these properties:
     * capacity is proportional to size of blimp.
     */
    public RefuelingBlimp()
    {
        // TODO capacity should be proportional to size, fix constructors to make this easy
        this(100);
    }

    public RefuelingBlimp(int capacity)
    {
        super();
        this.capacity = capacity;
    }

    public RefuelingBlimp(int x, int y)
    {
        super();
        setLocation(x, y);
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
        return "RefuelingBlimp\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY()) +
                " capacity=" + capacity;
    }
}
