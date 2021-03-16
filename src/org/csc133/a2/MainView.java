package org.csc133.a2;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

public class MainView extends Form
{
    public MainView()
    {
        super("SkyMail 3000");
        setLayout(new BorderLayout());
        add(BorderLayout.SOUTH, new GlassCockpit());
        add(BorderLayout.CENTER, new MapView());
    }
}
