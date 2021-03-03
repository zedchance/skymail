package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;

/**
 * RefuelingBlimps allow the player to refuel the Helicopter
 */
public class RefuelingBlimp extends Fixed
{
    private int capacity;

    /**
     * By default, RefuelingBlimp's have these properties:
     * size is random between 10 and 20,
     * blimps are blue,
     * capacity is 10 times the size of the blimp.
     */
    public RefuelingBlimp()
    {
        Random rand = new Random();
        setSize(rand.nextInt(10) + 10);
        this.capacity = capacity * getSize();
        this.setColor(ColorUtil.blue(100));
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
