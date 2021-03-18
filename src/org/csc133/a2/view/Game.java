package org.csc133.a2.view;

import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a2.controller.GameWorld;

/**
 * Sky Mail 3000
 */
public class Game extends Form
{
    private GameWorld gw;

    public Game()
    {
        super("SkyMail 3000");
        setLayout(new BorderLayout());

        gw = new GameWorld();
        gw.init();

        // map and cockpit views
        add(BorderLayout.SOUTH, new GlassCockpit(gw));
        add(BorderLayout.CENTER, new MapView(gw.getWorld()));

        // setup key listeners
        handleKeys();
    }

    public void handleKeys()
    {
        addKeyListener('a', evt -> gw.accelerate());
        addKeyListener('b', evt -> gw.brake());
        addKeyListener('l', evt -> gw.left());
        addKeyListener('r', evt -> gw.right());
        addKeyListener('c', evt -> gw.helicopterCollision());
        addKeyListener('e', evt -> gw.refuel());
        addKeyListener('g', evt -> gw.birdCollision());
        addKeyListener('x', evt -> {
            if (Dialog.show("Exit?", "Are you sure you want to exit?", "Yes", "No"))
            {
                System.exit(0);
            }
        });
        // TODO nums 1-9 for checkpoints
    }

    public void start()
    {
        getComponentForm().registerAnimated(this);
    }

    public void stop()
    {
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut()
    {
        this.start();
    }

    @Override
    public boolean animate()
    {
        // check if the game needs to be reinitialized
        gw.checkIfReset();

        // check if game is over
        if (gw.isGameOver())
        {
            gw.exit();
        }
        gw.tick();
        return false;
    }
}
