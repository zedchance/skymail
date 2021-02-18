package org.csc133.a1;

public abstract class GameObject
{
    private int size;
    private float x;
    private float y;
    private int color;

    public GameObject(int size, float x, float y, int color)
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

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    /**
     * Sets the location of the object in a (x, y) fashion.
     * The game board is restricted to 1024.0 x 768.0
     *
     * @param x coordinate as float
     * @param y coordinate as float
     */
    public void setLocation(float x, float y)
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
