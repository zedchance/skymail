package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

/**
 * SkyScrapers act as checkpoints for the player's flight path
 */
public class SkyScraper extends Fixed
{
    private int sequenceNumber;

    /**
     * By default, SkyScraper's have these properties:
     * color is green,
     * sequenceNumber is 1.
     */
    public SkyScraper()
    {
        this(1);
    }

    /**
     * Create a SkyScraper with a specific sequence number.
     *
     * @param sequenceNumber the number in which this skyScraper should be visited
     */
    public SkyScraper(int sequenceNumber)
    {
        super();
        this.sequenceNumber = sequenceNumber;
        setColor(ColorUtil.green(100));
    }

    public SkyScraper(double x, double y, int sequenceNumber)
    {
        this(sequenceNumber);
        setLocation(x, y);
    }

    public int getSequenceNumber()
    {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString()
    {
        return "SkyScraper " + sequenceNumber + "\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY());
    }
}
