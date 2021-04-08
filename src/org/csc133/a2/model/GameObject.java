package org.csc133.a2.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;

/**
 * All objects in game inherit from GameObject
 */
public abstract class GameObject implements IDrawable
{
    private int size;
    private double x;
    private double y;
    private int color;

    /**
     * By default, GameObject's have these properties:
     * size is randomly selected (between 5 and 25),
     * located at (0,0),
     * color value is 0.
     */
    public GameObject()
    {
        this(0, 0, 0);
    }

    /**
     * Create a new GameObject with set parameters
     *
     * @param x     horizontal position
     * @param y     vertical position
     * @param color what color the object is
     */
    public GameObject(double x, double y, int color)
    {
        Random rand = new Random();
        this.size = rand.nextInt(20) + 10;
        this.x = x;
        this.y = y;
        this.color = ColorUtil.rgb(0, color, 0);
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setLocation(double x, double y)
    {
        /* TODO this needs to take in the map size for starting locations
         *   but currently the map size isn't set until fully laid out */
        double defaultX = 1024;
        double defaultY = 768;
        setLocation(x, y, defaultX, defaultY);
    }

    /**
     * Sets the location of the object in a (x, y) fashion.
     *
     * @param x coordinate as double
     * @param y coordinate as double
     */
    public void setLocation(double x, double y, double maxX, double maxY)
    {
        if (x > maxX)
        {
            x = maxX;
        }
        else if (x < 0)
        {
            x = 0;
        }
        if (y > maxY)
        {
            y = maxY;
        }
        else if (y < 0)
        {
            y = 0;
        }
        this.x = x;
        this.y = y;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        int x = (int) getX() + containerOrigin.getX();
        int y = (int) getY() + containerOrigin.getY();

        // TODO DRY
        // center object
        x = x - size / 2;
        y = y + size / 2;

        g.setColor(getColor());
        g.fillRect(x, y, getSize(), getSize());
    }

    @Override
    public String toString()
    {
        return "GameObject{" +
                "size=" + size +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
