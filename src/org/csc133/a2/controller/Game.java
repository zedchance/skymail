package org.csc133.a2.controller;

import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a2.model.GameWorld;
import org.csc133.a2.view.ButtonControls;
import org.csc133.a2.view.GlassCockpit;
import org.csc133.a2.view.MapView;

/**
 * Sky Mail 3000
 */
public class Game extends Form implements Runnable
{
    public static final int REFRESH_RATE = 15;

    private final GameWorld gw;

    public Game()
    {
        super("SkyMail 3000");
        setLayout(new BorderLayout());

        gw = new GameWorld();
        gw.init();

        // map and cockpit views
        handleViews();

        // setup key listeners
        handleKeys();

        // timer continually calls run method
        UITimer.timer(REFRESH_RATE, true, this, this);
    }

    private void handleViews()
    {
        System.out.println("Game.handleViews");
        add(BorderLayout.NORTH, new GlassCockpit(gw));
        add(BorderLayout.CENTER, new MapView(gw));
        add(BorderLayout.SOUTH, new ButtonControls(gw));
        // TODO add commands in overflow menu
    }

    private void handleKeys()
    {
        // key commands
        addKeyListener('a', evt -> gw.accelerate());
        addKeyListener('b', evt -> gw.brake());
        addKeyListener('l', evt -> gw.left());
        addKeyListener('r', evt -> gw.right());
        addKeyListener('c', evt -> gw.helicopterCollision());
        addKeyListener('e', evt -> gw.refuel());
        addKeyListener('g', evt -> gw.birdCollision());
        addKeyListener('x', this::askToExit);
        // TODO 's' key for skyscraper collision, with Dialog

        // arrow keys for flight
        final int LEFT = 2;
        final int RIGHT = 5;
        final int UP = 1;
        final int DOWN = 6;
        addGameKeyListener(LEFT, evt -> gw.left());
        addGameKeyListener(RIGHT, evt -> gw.right());
        addGameKeyListener(UP, evt -> gw.accelerate());
        addGameKeyListener(DOWN, evt -> gw.brake());
    }

    private void askToExit(ActionEvent evt)
    {
        if (Dialog.show("Exit?", "Are you sure you want to exit?", "Yes", "No"))
        {
            gw.exit();
        }
    }

    @Override
    public void run()
    {
        // check if the game needs to be reinitialized
        gw.checkIfReset();

        // check if game is over
        if (gw.isGameOver())
        {
            gw.exit();
        }
        gw.tick();
    }
}
