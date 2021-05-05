package org.csc133.a3.model;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class HomeBase extends Fixed implements IDrawable
{
    private Image homeBaseImage;

    public HomeBase()
    {
        try
        {
            homeBaseImage = Image.createImage("/home.png").scaled(100, 100);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        // the home base is always at 100,00
        int x = 100 + containerOrigin.getX();
        int y = 100 + containerOrigin.getY();
        g.drawImage(homeBaseImage, x, y);
    }
}
