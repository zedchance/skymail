package org.csc133.a1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BirdTest helps test MovableObjects
 */
class BirdTest
{
    @Test
    void generalBirdTest()
    {
        Bird bird = new Bird();
        bird.setColor(10);
        assertEquals(10, bird.getColor());
        bird.setSize(50);
        assertEquals(50, bird.getSize());
        bird.setLocation(10, 5);
        assertEquals(10, bird.getX());
        assertEquals(5, bird.getY());

        // can't move out of bounds
        bird.setLocation(2000, 1000);
        assertEquals(1024.0, bird.getX());
        assertEquals(768.0, bird.getY());
    }
}
