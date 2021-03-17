package org.csc133.a2.view;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a2.model.GameObject;

import java.util.List;

public class MapView extends Container
{
    private List<GameObject> world;
    int test = 0;
    Label testLabel;

    public MapView(List<GameObject> world)
    {
        super(new BorderLayout());
        this.world = world;
        testLabel = new Label("MAP GOES HERE: " + test);
        add(BorderLayout.CENTER, testLabel);
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
        testLabel.setText("MAP: " + test++);
        return false;
    }
}
