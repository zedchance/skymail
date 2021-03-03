package org.csc133.a1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HelicopterTest is used to test all of the aspects of the player
 * controlled helicopter
 */
class HelicopterTest
{
    @Test
    void isDestroyed()
    {
        Helicopter helo = new Helicopter();
        assertFalse(helo.isDestroyed());
    }

    @Test
    void isFuelEmpty()
    {
        Helicopter helo = new Helicopter();
        assertFalse(helo.isFuelEmpty());
    }

    @Test
    void consumeFuel()
    {
    }

    @Test
    void move()
    {
    }

    //    @Test
    void testMove()
    {
        Helicopter helo = new Helicopter();
        helo.setLocation(0, 0);
        helo.setHeading(0);
        helo.setSpeed(10);
        assertEquals(0, helo.getX(), 0.001);
        assertEquals(0, helo.getY(), 0.001);
        helo.move();
        assertEquals(0, helo.getX(), 0.001);
        assertEquals(10, helo.getY(), 0.001);
        helo.setHeading(90);
        helo.move();
//        assertEquals(10, helo.getX(), 0.1);
//        assertEquals(10, helo.getY(), 0.1);
        // FIXME
    }

    @Test
    void changeDirection()
    {
    }

    @Test
    void testToString()
    {
    }
}