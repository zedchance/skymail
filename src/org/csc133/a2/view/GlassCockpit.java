package org.csc133.a2.view;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a2.controller.GameWorld;

import java.util.List;

public class GlassCockpit extends Container
{
    GameWorld gw;
    DigitalDashComponent fuelDash;
    DigitalDashComponent damageDash;
    DigitalDashComponent livesDash;
    DigitalDashComponent lastCheckpointReachedDash;
    DigitalDashComponent headingDash;

    public GlassCockpit(GameWorld gw)
    {
        super(new GridLayout(2, 6));
        this.gw = gw;

        // first row is header
        add(new Label("Game Time"));
        add(new Label("Fuel"));
        add(new Label("Damage"));
        add(new Label("Lives"));
        add(new Label("Last"));
        add(new Label("Heading"));

        // second row is digidash
        add(new GameClockComponent());

        fuelDash = new DigitalDashComponent(3);
        fuelDash.setValue(100);
        add(fuelDash);

        damageDash = new DigitalDashComponent(3);
        add(damageDash);

        livesDash = new DigitalDashComponent(1);
        livesDash.setValue(3);
        add(livesDash);

        lastCheckpointReachedDash = new DigitalDashComponent(1);
        lastCheckpointReachedDash.setValue(1);
        add(lastCheckpointReachedDash);

        headingDash = new DigitalDashComponent(3);
        add(headingDash);
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
        // TODO fix these hardcoded values
        List<Double> dashElements = gw.getDashElements();
        fuelDash.setValue(dashElements.get(0));
        damageDash.setValue(dashElements.get(1));
        livesDash.setValue(dashElements.get(2));
        lastCheckpointReachedDash.setValue(dashElements.get(3));
        headingDash.setValue(dashElements.get(4));
        return false;
    }
}