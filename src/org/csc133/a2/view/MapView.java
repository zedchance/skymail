package org.csc133.a2.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a2.model.GameObject;

import java.util.List;

public class MapView extends Container
{
    private List<GameObject> world;

    public MapView(List<GameObject> world)
    {
        super(new BorderLayout());
        this.world = world;
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
    }

    public boolean animate()
    {
        return true;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        // bg
        g.setColor(ColorUtil.LTGRAY);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        // draw all world objects
        Point originOfMap = new Point(getX(), getY());
        for (GameObject item : world)
        {
            item.draw(g, originOfMap);
        }
    }
}
