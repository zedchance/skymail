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

    @Test
    void changeDirection()
    {
    }

    @Test
    void testToString()
    {
    }
}