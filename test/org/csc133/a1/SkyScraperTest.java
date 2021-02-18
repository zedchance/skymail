package org.csc133.a1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void testToString()
    {
        SkyScraper sky = new SkyScraper();
        sky.setSize(30);
        sky.setLocation(5, 5);
        sky.setColor(200);
        String expected = "SkyScraper{sequenceNumber=1} GameObject{size=30, x=5.0, y=5.0, color=200}";
        assertEquals(expected, sky.toString());
    }
}
