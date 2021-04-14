package org.csc133.a2.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Random;

/**
 * Birds fly randomly, and can collide with the player
 */
public class Bird extends Movable
{
    private int headingChangeCounter = 0;
    Image birdImage;

    /**
     * By default, Bird's have these properties:
     * color is yellow,
     * a random start location,
     * a random speed,
     * a random heading.
     */
    public Bird()
    {
        super();
        Random rand = new Random();
        int startX = rand.nextInt(2000);
        int startY = rand.nextInt(2000);
        setLocation(new DoublePoint(startX, startY));
        setSpeed(rand.nextInt(5) + 10);
        setHeading(rand.nextInt(360));
        super.setColor(ColorUtil.rgb(100, 100, 0));
        setSize(rand.nextInt(100) + 50);
        initImage();
    }

    @Override
    public void setColor(int color)
    {
        System.out.println("Birds cannot change color.");
    }

    public void initImage()
    {
        try
        {
            birdImage = Image.createImage("/bird.png").scaled(getSize(), getSize());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        int x = (int) (getX() + containerOrigin.getX());
        int y = (int) (getY() + containerOrigin.getY());

        // TODO DRY
        // center of bird
        int centerX = x + (getSize() / 2);
        int centerY = y + (getSize() / 2);

        // rotate graphics plane
        float amountToRotate = (float) Math.toRadians(getHeading());
        g.rotateRadians(-1 * amountToRotate, centerX, centerY);

        g.drawImage(birdImage, x, y);

        g.rotateRadians(amountToRotate, centerX, centerY);
    }

    /**
     * Bird change their heading randomly +- 5 degrees
     * from current heading every 20 ticks
     */
    @Override
    public void move()
    {
        // slow down heading change to every 5 ticks
        if (headingChangeCounter++ % 5 == 0)
        {
            Random rand = new Random();
            setHeading(getHeading() + (rand.nextInt(11) - 5));
        }
        super.move();
    }

    @Override
    public String toString()
    {
        return "Bird\t\t\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY()) +
                " speed=" + getSpeed() +
                " heading=" + getHeading();
    }
}
