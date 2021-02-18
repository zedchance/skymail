package org.csc133.a1;

public abstract class GameObject
{
    private int size;
    private double x;
    private double y;
    private int color;

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
        if (x > 1024.0)
        {
            x = 1024.0f;
        }
        if (y > 768.0)
        {
            y = 768.0f;
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
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
