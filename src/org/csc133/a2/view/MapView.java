package org.csc133.a2.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a2.model.GameObject;
import org.csc133.a2.model.GameWorld;
import org.csc133.a2.model.Helicopter;

public class MapView extends Container
{
    public static final int WALL_PAD = 40;
    public static int mapWidth;
    public static int mapHeight;
    private GameWorld gw;

    public MapView(GameWorld gw)
    {
        super(new BorderLayout());
        this.gw = gw;
    }

    public void start()
    {
        getComponentForm().registerAnimated(this);
    }

    public void stop()
    {
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut()
    {
        this.start();
        mapWidth = getWidth() - (4 * WALL_PAD);
        mapHeight = getHeight() - (4 * WALL_PAD);
    }

    public boolean animate()
    {
        return true;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        // bg and wall
        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(ColorUtil.LTGRAY);
        g.fillRect(getX() + WALL_PAD,
                   getY() + WALL_PAD,
                   getWidth() - WALL_PAD * 2,
                   getHeight() - WALL_PAD * 2);

        // draw all world objects
        Point originOfMap = new Point(getX(), getY());
        for (GameObject item : gw.getWorld())
        {
            item.draw(g, originOfMap);
//            System.out.println(item);
        }
    }
}
