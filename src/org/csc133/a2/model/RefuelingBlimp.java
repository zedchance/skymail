package org.csc133.a2.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Shape;

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
     * capacity is 5 times the size of the blimp.
     */
    public RefuelingBlimp()
    {
        Random rand = new Random();
        setSize(rand.nextInt(30) + 10);
        this.capacity = 5 * getSize();
        this.setColor(ColorUtil.blue(this.capacity));
        double startX = (double) rand.nextInt(925) + 50;
        double startY = (double) rand.nextInt(668) + 50;
        setLocation(startX, startY);
    }

    /**
     * Transfer fuel to a Helicopter, up to the rest
     * of the capacity of the Helicopter can hold it.
     * The color of the blimp fades as the fuel is
     * transferred.
     */
    public void transferFuel(Helicopter helo)
    {
        int fuelNeeded = 100 - (int) helo.getFuelLevel();
        if (isEmpty())
        {
            System.out.println("The blimp is empty");
        }
        else if (fuelNeeded < capacity)
        {
            helo.fuelUp(fuelNeeded);
            capacity = capacity - fuelNeeded;
        }
        else if (fuelNeeded > capacity)
        {
            helo.fuelUp(capacity);
            capacity = 0;
        }
        this.setColor(capacity);
    }

    public boolean isEmpty()
    {
        return capacity == 0;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        int x = (int) getX() + containerOrigin.getX();
        int y = (int) getY() + containerOrigin.getY();

        // TODO DRY
        // center object
        x = x - getSize() / 2;
        y = y + getSize() / 2;

        g.setColor(getColor());
        g.fillRoundRect(x, y, getSize() * 2, getSize(), 5, 5);

        g.setColor(ColorUtil.WHITE);
        g.drawString("C" + capacity, x, y);
    }

    @Override
    public String toString()
    {
        return "RefuelingBlimp\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY()) +
                " capacity=" + capacity;
    }
}
