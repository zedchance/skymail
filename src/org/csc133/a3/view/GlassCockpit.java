package org.csc133.a3.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a3.model.GameWorld;

import java.util.HashMap;

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

        fuelDash = new DigitalDashComponent(4);
        fuelDash.setValue(100);
        fuelDash.setLedColor(ColorUtil.MAGENTA);
        add(fuelDash);

        damageDash = new DigitalDashComponent(3);
        damageDash.setLedColor(ColorUtil.GREEN);
        add(damageDash);

        livesDash = new DigitalDashComponent(1);
        livesDash.setValue(3);
        livesDash.setLedColor(ColorUtil.YELLOW);
        add(livesDash);

        lastCheckpointReachedDash = new DigitalDashComponent(1);
        lastCheckpointReachedDash.setValue(1);
        lastCheckpointReachedDash.setLedColor(ColorUtil.CYAN);
        add(lastCheckpointReachedDash);

        headingDash = new DigitalDashComponent(3);
        headingDash.setLedColor(ColorUtil.BLUE);
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
        HashMap<String, Double> dashElements = gw.getDashElements();
        fuelDash.setValue(dashElements.get("fuel"));
        livesDash.setValue(dashElements.get("lives"));
        lastCheckpointReachedDash.setValue(dashElements.get("last"));
        headingDash.setValue(dashElements.get("heading"));

        // take care of damage level's led color
        double damageLevel = dashElements.get("damage");
        if (damageLevel < 50)
        {
            damageDash.setLedColor(ColorUtil.GREEN);
        }
        else if (damageLevel < 85)
        {
            damageDash.setLedColor(ColorUtil.YELLOW);
        }
        else
        {
            damageDash.setLedColor(ColorUtil.rgb(255, 0, 0));
        }
        damageDash.setValue(damageLevel);
        return false;
    }
}
