package org.csc133.a3.model;

/**
 * Objects that implement ISteerable can have their heading changed.
 */
public interface ISteerable
{
    /**
     * Allows a Movable to change its course by steering.
     * Input a negative degree to turn left,
     * input a positive degree to turn right.
     *
     * @param degrees amount to change
     */
    void changeDirection(int degrees);
}
