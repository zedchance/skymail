package org.csc133.a3.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

/**
 * SkyScrapers act as checkpoints for the player's flight path
 */
public class SkyScraper extends Fixed
{
    private int sequenceNumber;
    private Image skyScraperImage;

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
        super.setColor(ColorUtil.green(100));
        initImage();
    }

    private void initImage()
    {
        try
        {
            skyScraperImage = Image.createImage("/skyscraper.png").scaled(100, 100);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public SkyScraper(int x, int y, int sequenceNumber)
    {
        this(sequenceNumber);
        setLocation(new DoublePoint(x, y));
    }

    @Override
    public void setColor(int color)
    {
        System.out.println("SkyScrapers cannot change color once created.");
    }

    public int getSequenceNumber()
    {
        return sequenceNumber;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        int x = (int) getX() + containerOrigin.getX();
        int y = (int) getY() + containerOrigin.getY();

        // center object
        // TODO DRY
        x = x - getSize() / 2;
        y = y + getSize() / 2;

        g.drawImage(skyScraperImage, x, y);

        g.setColor(ColorUtil.WHITE);
        g.drawString(" " + sequenceNumber, x, y);
    }

    @Override
    public String toString()
    {
        return "SkyScraper " + sequenceNumber + "\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY());
    }
}
