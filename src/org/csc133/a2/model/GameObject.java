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
    private Point location;
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
    public GameObject(int x, int y, int color)
    {
        Random rand = new Random();
        this.size = rand.nextInt(20) + 10;
        location = new Point(x, y);
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
        return location.getX();
    }

    public double getY()
    {
        return location.getY();
    }

    /**
     * Sets the location of the object in a (x, y) fashion.
     */
    public void setLocation(Point location)
    {
        this.location = location;
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
    public void draw(Graphics g, com.codename1.ui.geom.Point containerOrigin)
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
                ", x=" + getX() +
                ", y=" + getY() +
                ", color=" + color +
                '}';
    }
}
