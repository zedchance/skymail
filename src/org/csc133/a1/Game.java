package org.csc133.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form
{
    private GameWorld gw;

    public Game()
    {
        System.out.println("New game started");
        gw = new GameWorld();
        gw.init();
        play();
    }

    private void play()
    {
        Label lblCommand = new Label("Enter a command: ");
        addComponent(lblCommand);
        final TextField txtInput = new TextField();
        addComponent(txtInput);
        this.show();

        // accept and execute user command
        txtInput.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                String sCommand = txtInput.getText().toString();
                if (sCommand == null || sCommand.equals(""))
                {
                    return;
                }
                txtInput.clear();
                switch (sCommand.charAt(0))
                {
                    case 'A':
                    case 'a':
                        System.out.println("Accelerating");
                        gw.accelerate();
                        break;
                    case 'B':
                    case 'b':
                        System.out.println("Braking");
                        gw.brake();
                        break;
                    case 'L':
                    case 'l':
                        System.out.println("Turning left");
                        gw.left();
                        break;
                    case 'R':
                    case 'r':
                        System.out.println("Turning right");
                        gw.right();
                        break;
                    case 'C':
                    case 'c':
                        System.out.println("Simulating helicopter collision");
                        break;
                    case 'E':
                    case 'e':
                        System.out.println("Simulating refuel");
                        break;
                    case 'G':
                    case 'g':
                        System.out.println("Simulating bird collision");
                        break;
                    case 'T':
                    case 't':
                        System.out.println("Advancing tick to " + gw.tick());
                        break;
                    case 'D':
                    case 'd':
                        System.out.println("Generating display");
                        break;
                    case 'M':
                    case 'm':
                        System.out.println("Showing map");
                        gw.map();
                        break;
                    case 'x':
                        System.out.println("Exit?");
                        // TODO ask y/N
                        gw.exit();
                        break;
                    // TODO numbers 1-9 will pretend that the helicopter has collided with skyscraper x
                }
            }
        });
    }
}
