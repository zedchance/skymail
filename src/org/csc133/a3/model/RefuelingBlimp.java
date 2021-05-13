package org.csc133.a3.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import org.csc133.a3.view.MapView;

import java.io.IOException;
import java.util.Random;

/**
 * RefuelingBlimps allow the player to refuel the Helicopter
 */
public class RefuelingBlimp extends Fixed
{
    private int capacity;
    private Image refuelingBlimpImage;

    /**
     * By default, RefuelingBlimp's have these properties:
     * size is random between 50 and 200,
     * blimps are blue,
     * blimps start in a random location (at least 50 away from the edge),
     * capacity is the size of the blimp.
     */
    public RefuelingBlimp()
    {
        Random rand = new Random();
        setSize(rand.nextInt(150) + 50);
        this.capacity = getSize();
        this.setColor(ColorUtil.blue(this.capacity));
        int startX = rand.nextInt(MapView.mapWidth) + MapView.WALL_PAD;
        int startY = rand.nextInt(MapView.mapHeight) + MapView.WALL_PAD;
        setLocation(new DoublePoint(startX, startY));
        initImage();
    }

    private void initImage()
    {
        try
        {
            refuelingBlimpImage = Image.createImage("/blimp.png").scaled(getSize(), getSize());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
        int x = (int) (getX() + containerOrigin.getX());
        int y = (int) (getY() + containerOrigin.getY());

        // TODO: 4/13/21 draw using blimp's color
        g.drawImage(refuelingBlimpImage, x, y);

        // TODO DRY
        // center for text
        x = x + getSize() / 4;
        y = y + getSize() / 2;

        g.setColor(ColorUtil.WHITE);
        // TODO: 4/13/21 center this text better
        g.drawString("" + capacity, x, y);
    }

    @Override
    public String toString()
    {
        return "RefuelingBlimp\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY()) +
                " capacity=" + capacity;
    }
}
