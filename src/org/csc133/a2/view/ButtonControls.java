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
        addButtons();
        addListeners(gw);
    }

    private void addListeners(GameWorld gw)
    {
        leftButton.addActionListener(evt -> gw.left());
        rightButton.addActionListener(evt -> gw.right());
        upButton.addActionListener(evt -> gw.accelerate());
        downButton.addActionListener(evt -> gw.brake());
    }

    private void addButtons()
    {
        add(leftButton);
        add(rightButton);
        add(upButton);
        add(downButton);
    }
}
