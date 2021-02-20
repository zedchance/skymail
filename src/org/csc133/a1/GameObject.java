package org.csc133.a1;

public abstract class GameObject
{
    private int size;
    private double x;
    private double y;
    private int color;

    /**
     * By default, GameObject's have these properties:
     * size is 10,
     * located at (0,0),
     * color value is 0.
     */
    public GameObject()
    {
        this(10, 0, 0, 0);
    }

    /**
     * Create a new GameObject with set parameters
     *
     * @param size  how big the object is
     * @param x     horizontal position
     * @param y     vertical position
     * @param color what color the object is
     */
    public GameObject(int size, double x, double y, int color)
    {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = color;
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

    /**
     * Sets the location of the object in a (x, y) fashion.
     * The game board is restricted to 1024.0 x 768.0
     *
     * @param x coordinate as double
     * @param y coordinate as double
     */
    public void setLocation(double x, double y)
    {
        // TODO can the player wrap around? astroids style? (modulo)
        if (x > 1024.0)
        {
            x = 1024.0;
        }
        else if (x < 0)
        {
            x = 0;
        }
        if (y > 768.0)
        {
            y = 768.0;
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
    public String toString()
    {
        return "GameObject{" +
                "size=" + size +
                ", x=" + x +  // TODO pretty print
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
