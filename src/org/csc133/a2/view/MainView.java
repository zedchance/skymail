package org.csc133.a2.view;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a2.Game;

public class MainView extends Form
{
    public MainView()
    {
        super("SkyMail 3000");
        setLayout(new BorderLayout());
        add(BorderLayout.SOUTH, new GlassCockpit());
        add(BorderLayout.CENTER, new MapView());

        // temporarily add old game to north slot
        add(BorderLayout.NORTH, new Game());
    }
}
