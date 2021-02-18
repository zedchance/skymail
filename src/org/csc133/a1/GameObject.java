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

    public float[] getLocation()
    {
        float[] ret = {x, y};
        return ret;
    }

    public void setLocation(float[] location)
    {
        x = location[0];
        y = location[1];
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}
