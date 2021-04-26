package org.csc133.a3.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import org.csc133.a3.model.strategy.Strategy;
import org.csc133.a3.view.MapView;

import java.io.IOException;
import java.util.Random;

public class NonPlayerHelicopter extends Helicopter
{
    private Image nonPlayerHeloImage;
    private Strategy strategy;

    public NonPlayerHelicopter()
    {
        super();
        Random rand = new Random();
        int startX = rand.nextInt(MapView.mapWidth);
        int startY = rand.nextInt(MapView.mapHeight);
        setLocation(new DoublePoint(startX, startY));
        setSpeed(1);
        // TODO: 4/10/21 fix stick angle and heading difference
        setStickAngle(rand.nextInt(360) + 1);
        setColor(ColorUtil.CYAN);
        setFuelLevel(1000);
        setSize(rand.nextInt(100) + 60);
        initImage();
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
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

    @Override
    public void draw(Graphics g, Point containerOrigin)
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
    public void move()
    {
        strategy.invokeStrategy(this);
        super.move();
    }

    @Override
    public void wallBehavior()
    {
        setStickAngle(0);
    }

    @Override
    public String toString()
    {
        return "NPH " + super.toString();
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw)
    {
        // TODO: 4/24/21 nph's collisions
    }
}
