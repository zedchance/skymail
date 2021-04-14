package org.csc133.a2.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Random;

public class NonPlayerHelicopter extends Helicopter
{
    private Image nonPlayerHeloImage;

    public NonPlayerHelicopter()
    {
        super();
        Random rand = new Random();
        int startX = rand.nextInt(2000);
        int startY = rand.nextInt(2000);
        setLocation(new DoublePoint(startX, startY));
        startMoving();
        // TODO: 4/10/21 fix stick angle and heading difference
        setStickAngle(rand.nextInt(360) + 1);
        setColor(ColorUtil.CYAN);
        setFuelLevel(1000);
        setSize(rand.nextInt(120) + 60);
        initImage();
    }

    private void initImage()
    {
        try
        {
            nonPlayerHeloImage = Image.createImage("/npc_helo.png").scaled(getSize(), getSize());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void startMoving()
    {
        Random rand = new Random();
        setSpeed(rand.nextInt(20) + 5);
    }

    @Override
    public void draw(Graphics g, com.codename1.ui.geom.Point containerOrigin)
    {
        // top left of helo
        int x = (int) getX() + containerOrigin.getX();
        int y = (int) getY() + containerOrigin.getY();

        // center of helo
        int centerX = x + (getSize() / 2);
        int centerY = y + (getSize() / 2);

        // rotate graphics plane
        float amountToRotate = (float) Math.toRadians(getHeading());
        g.rotateRadians(-1 * amountToRotate, centerX, centerY);

        g.drawImage(nonPlayerHeloImage, x, y);

        g.rotateRadians(amountToRotate, centerX, centerY);

        // TODO: 4/13/21 draw image with color
    }

    @Override
    public void wallStrategy()
    {
        super.wallStrategy();
        setHeading(getHeading() + 30);
        startMoving();
    }

    // TODO: 4/10/21 strategies
}
