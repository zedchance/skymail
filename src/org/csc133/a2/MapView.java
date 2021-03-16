package org.csc133.a2;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;

public class MapView extends Form
{
    public MapView()
    {
        super(new BorderLayout());
        add(BorderLayout.CENTER, new Label("MAP GOES HERE"));
    }
}
