package org.csc133.a2.model;

public interface ISteerable
{
    /**
     * Allows a Movable to change its course by steering.
     * Input a negative degree to turn left,
     * input a positive degree to turn right.
     *
     * @param degrees
     */
    public void changeDirection(int degrees);
}
