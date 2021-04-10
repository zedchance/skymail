package org.csc133.a2.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;

/**
 * Birds fly randomly, and can collide with the player
 */
public class Bird extends Movable
{
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
        double startX = rand.nextInt(2000);
        double startY = rand.nextInt(2000);
        setLocation(startX, startY);
        setSpeed(rand.nextInt(5) + 10);
        setHeading(rand.nextInt(360));
        super.setColor(ColorUtil.rgb(100, 100, 0));
    }

    @Override
    public void setColor(int color)
    {
        System.out.println("Birds cannot change color.");
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        int x = (int) getX() + containerOrigin.getX();
        int y = (int) getY() + containerOrigin.getY();

        // TODO DRY
        // center object
        x = x - getSize() / 2;
        y = y + getSize() / 2;

        g.setColor(getColor());
        g.fillRoundRect(x, y, getSize() * 2, getSize(), 5, 5);

        g.setColor(ColorUtil.WHITE);
        g.drawString("BIRD", x, y);
    }

    /**
     * Bird change their heading randomly +- 5 degrees
     * from current heading each tick
     */
    @Override
    public void move()
    {
        Random rand = new Random();
        setHeading(getHeading() + (rand.nextInt(11) - 5));
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
