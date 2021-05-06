package org.csc133.a3.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import org.csc133.a3.view.MapView;

import java.io.IOException;
import java.util.Random;

/**
 * Birds fly randomly, and can collide with the player
 */
public class Bird extends Movable
{
    private int headingChangeCounter = 0;
    private Image[] birdSprites = new Image[3];
    private int currentSprite;
    private int spriteWaitTime;

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
        int startX = rand.nextInt(MapView.mapWidth);
        int startY = rand.nextInt(MapView.mapHeight);
        setLocation(new DoublePoint(startX, startY));
        setSpeed(rand.nextInt(5) + 10);
        setHeading(rand.nextInt(360));
        super.setColor(ColorUtil.rgb(100, 100, 0));
        setSize(rand.nextInt(100) + 50);
        initImage();
        currentSprite = 0;
        spriteWaitTime = 0;
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
            birdSprites[0] = Image.createImage("/bird1.png").scaled(getSize(), getSize());
            birdSprites[1] = Image.createImage("/bird2.png").scaled(getSize(), getSize());
            birdSprites[2] = Image.createImage("/bird3.png").scaled(getSize(), getSize());
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

        // bird changes sprite every 30 draws
        spriteWaitTime = ++spriteWaitTime % 30;
        if (spriteWaitTime == 0)
        {
            currentSprite = ++currentSprite % 3;
        }
        g.drawImage(birdSprites[currentSprite], x, y);

        g.rotateRadians(amountToRotate, centerX, centerY);
    }

    /**
     * Bird change their heading randomly +- 5 degrees
     * from current heading every 20 ticks
     */
    @Override
    public void move()
    {
        // slow down heading change to every 10 ticks
        if (headingChangeCounter++ % 10 == 0)
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

    @Override
    public void wallBehavior()
    {
        setHeading(getHeading() + 30);
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw)
    {
        // birds don't collide with skyscrapers or blimps
        if (otherObject instanceof Fixed) return;
        Helicopter helo = (Helicopter) otherObject;
        gw.birdCollision(helo, this);
    }
}
