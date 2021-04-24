package org.csc133.a3.view;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a3.model.GameWorld;
import org.csc133.a3.model.command.AccelerateCommand;
import org.csc133.a3.model.command.BrakeCommand;
import org.csc133.a3.model.command.LeftCommand;
import org.csc133.a3.model.command.RightCommand;

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
        Command leftCommand = new LeftCommand(gw);
        leftButton.addActionListener(leftCommand);

        Command rightCommand = new RightCommand(gw);
        rightButton.addActionListener(rightCommand);

        Command accelerateCommand = new AccelerateCommand(gw);
        upButton.addActionListener(accelerateCommand);

        Command brakeCommand = new BrakeCommand(gw);
        downButton.addActionListener(brakeCommand);
    }

    private void addButtons()
    {
        add(leftButton);
        add(rightButton);
        add(upButton);
        add(downButton);
    }
}
