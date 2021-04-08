package org.csc133.a2.view;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a2.model.GameWorld;

public class ButtonControls extends Container
{
    GameWorld gw;
    Button leftButton;
    Button rightButton;
    Button upButton;
    Button downButton;

    public ButtonControls(GameWorld gw)
    {
        super(new GridLayout(1, 4));
        this.gw = gw;
        leftButton = new Button("←");
        rightButton = new Button("→");
        upButton = new Button("↑");
        downButton = new Button("↓");
        add(leftButton);
        add(rightButton);
        add(upButton);
        add(downButton);
    }
}
