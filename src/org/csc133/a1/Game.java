package org.csc133.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * Sky Mail 3000
 */
public class Game extends Form
{
    private GameWorld gw;
    private boolean isUserExiting = false;

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
                        gw.helicopterCollision();
                        break;
                    case 'E':
                    case 'e':
                        System.out.println("Simulating refuel");
                        gw.refuel();
                        break;
                    case 'G':
                    case 'g':
                        System.out.println("Simulating bird collision");
                        gw.birdCollision();
                        break;
                    case 'T':
                    case 't':
                        System.out.println("Advancing tick to " + gw.tick());
                        break;
                    case 'D':
                    case 'd':
                        System.out.println("Display");
                        gw.display();
                        break;
                    case 'M':
                    case 'm':
                        System.out.println("Map");
                        gw.map();
                        break;
                    case 'x':
                        System.out.println("Exit? y/N");
                        isUserExiting = true;
                        break;
                    case 'Y':
                    case 'y':
                        if (isUserExiting) gw.exit();
                        break;
                    case 'N':
                    case 'n':
                        if (isUserExiting)
                        {
                            System.out.println("Okay, we'll keep playing");
                            isUserExiting = false;
                        }
                        break;
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        gw.landOnSkyScraperCheckpoint(Integer.parseInt(sCommand));
                        break;
                }
                if (gw.isGameOver())
                {
                    gw.exit();
                }
            }
        });
    }
}
