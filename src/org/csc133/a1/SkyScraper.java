package org.csc133.a1;

public class SkyScraper extends GameObject
{
    private int sequenceNumber;

    /**
     * By default, SkyScraper's have these properties:
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
                "location=(" + getX() + "," + getY() + ")";
    }
}
