package org.csc133.a3.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Rectangle;

import java.util.HashSet;
import java.util.Random;

/**
 * All objects in game inherit from GameObject
 */
public abstract class GameObject implements IDrawable, ICollider
{
    private final HashSet<GameObject> collisionSet = new HashSet<>();
    private int size;
    private DoublePoint location;
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
        location = new DoublePoint(x, y);
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
    public void setLocation(DoublePoint location)
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
                ", x=" + getX() +
                ", y=" + getY() +
                ", color=" + color +
                '}';
    }

    /**
     * Returns a bounding Rectangle for the object, used to check collision.
     *
     * @return Rectangle of the object size, centered at the object's location
     */
    private Rectangle getBoundingRectangle()
    {
        return new Rectangle((int) getX(), (int) getY(), getSize(), getSize());
    }

    /**
     * Checks if this collides with another object using bounding rectangles.
     * Objects that do collide are kept track of in the collision set so multiple
     * collisions don't occur.
     *
     * @param otherObject object to check collision with
     * @return true if object collide
     */
    @Override
    public boolean collidesWith(GameObject otherObject)
    {
        if (this == otherObject || otherObject == null) return false;
        if (this.getBoundingRectangle().intersects(otherObject.getBoundingRectangle()))
        {
            // initial collision
            if (!collisionSet.contains(otherObject))
            {
                collisionSet.add(otherObject);
                otherObject.collisionSet.add(this);
                return true;
            }
        }
        else
        {
            // done colliding
            if (collisionSet.contains(otherObject))
            {
                collisionSet.remove(otherObject);
                otherObject.collisionSet.remove(this);
            }
        }
        // already collided
        return false;
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw)
    {
        // by default nothing happens on collision
    }
}
