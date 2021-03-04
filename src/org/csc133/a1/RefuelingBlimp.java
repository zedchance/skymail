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
     * blimps start in a random location (at least 50 away from the edge),
     * capacity is 10 times the size of the blimp.
     */
    public RefuelingBlimp()
    {
        Random rand = new Random();
        setSize(rand.nextInt(10) + 10);
        this.capacity = 10 * getSize();
        this.setColor(ColorUtil.blue(this.capacity));
        double startX = (double) rand.nextInt(925) + 50;
        double startY = (double) rand.nextInt(668) + 50;
        setLocation(startX, startY);
    }

    public int getCapacity()
    {
        return capacity;
    }

    /**
     * Withdraw a specific amount from the blimp, when fuel
     * is withdrawn the color of the blimp fades.
     *
     * @param amount how much fuel to withdraw
     */
    public void withdrawFuel(int amount)
    {
        if (!isEmpty())
        {
            capacity = capacity - amount;
            this.setColor(capacity);
        }
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
