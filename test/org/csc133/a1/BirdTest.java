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

    @Test
    void testMove()
    {
        Bird bird = new Bird();
        bird.setLocation(0, 0);
        bird.setHeading(0);
        bird.setSpeed(10);
        assertEquals(0, bird.getX(), 0.001);
        assertEquals(0, bird.getY(), 0.001);
        bird.move();
        assertEquals(0, bird.getX(), 0.001);
        assertEquals(10, bird.getY(), 0.001);
        bird.setHeading(90);
        bird.move();
        assertEquals(10, bird.getX(), 0.001);
        assertEquals(10, bird.getY(), 0.001);
    }

    @Test
    void testToString()
    {
        Bird bird = new Bird();
        bird.setHeading(45);
        bird.setSpeed(3);
        bird.setLocation(0, 0);
        bird.setColor(30);
        String ret = "Bird{} MovableObject{heading=45, speed=3} GameObject{size=10, x=0.0, y=0.0, color=30}";
        assertEquals(ret, bird.toString());
    }
}
