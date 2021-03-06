package org.csc133.a3.model;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Objects that implement IDrawable can be drawn in MapView
 */
public interface IDrawable
{
    /**
     * Enables game object to be drawn
     *
     * @param g               graphics to draw to
     * @param containerOrigin center of object
     */
    void draw(Graphics g, Point containerOrigin);
}
