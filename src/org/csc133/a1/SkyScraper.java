package org.csc133.a1;

public class SkyScraper extends GameObject
{
    private int sequenceNumber;

    public SkyScraper()
    {
        this(30, 0, 0, 100, 1);
    }

    public SkyScraper(int size, float x, float y, int color, int sequenceNumber)
    {
        super(size, x, y, color);
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString()
    {
        return "SkyScraper{" +
                "sequenceNumber=" + sequenceNumber +
                "} " + super.toString();
    }
}
