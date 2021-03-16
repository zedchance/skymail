package org.csc133.a2;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

public class GlassCockpit extends Form
{
    public GlassCockpit()
    {
        super(new GridLayout(1, 6));

        add(new Label("Game Time"));
        add(new Label("Fuel"));
        add(new Label("Damage"));
        add(new Label("Lives"));
        add(new Label("Last"));
        add(new Label("Heading"));
    }
}
