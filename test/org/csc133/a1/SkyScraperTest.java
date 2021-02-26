package org.csc133.a1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SkyScraperTest helps test GameObject's that are fixed in place
 */
class SkyScraperTest
{
    @Test
    void testSequenceNumber()
    {
        SkyScraper sky = new SkyScraper();
        assertEquals(1, sky.getSequenceNumber());
        sky.setSequenceNumber(4);
        assertEquals(4, sky.getSequenceNumber());
    }

    @Test
    void testLocation()
    {
        SkyScraper sky = new SkyScraper();
        sky.setLocation(500, 250);
        assertEquals(500, sky.getX());
        assertEquals(250, sky.getY());
    }

    @Test
    void testSize()
    {
        SkyScraper sky = new SkyScraper();
        sky.setSize(20);
        assertEquals(20, sky.getSize());
    }

    @Test
    void testColor()
    {
        SkyScraper sky = new SkyScraper();
        sky.setColor(135);
        assertEquals(135, sky.getColor());
        // TODO test for the CN1 color util conversion from int
    }
}
