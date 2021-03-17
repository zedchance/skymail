package org.csc133.a2.view;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

public class GlassCockpit extends Form
{
    public GlassCockpit()
    {
        super(new GridLayout(2, 6));

        // first row is header
        add(new Label("Game Time"));
        add(new Label("Fuel"));
        add(new Label("Damage"));
        add(new Label("Lives"));
        add(new Label("Last"));
        add(new Label("Heading"));

        // second row is digidash
        // TODO add digidashes
        add(new GameClockComponent());
        add(new Label("100"));
        add(new Label("0"));
        add(new Label("3"));
        add(new Label("1"));
        add(new Label("0 degrees"));
    }
}
