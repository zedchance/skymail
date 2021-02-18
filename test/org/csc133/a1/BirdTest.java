package org.csc133.a1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BirdTest
{
    @org.junit.jupiter.api.Test
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
    }

    @Test
    void testToString()
    {
        Bird bird = new Bird();
        String ret = "Bird{} MovableObject{heading=45, speed=3} GameObject{size=10, x=0.0, y=0.0, color=30}";
        assertEquals(ret, bird.toString());
    }
}
