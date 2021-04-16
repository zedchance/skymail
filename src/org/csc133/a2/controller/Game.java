package org.csc133.a2.controller;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a2.model.GameWorld;
import org.csc133.a2.model.command.*;
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
    private boolean isSpawned = false;

    public Game()
    {
        super("SkyMail 3000");
        setLayout(new BorderLayout());

        gw = new GameWorld();

        // map and cockpit views
        handleViews();

        // setup key listeners
        handleKeys();

        // timer continually calls run method
        UITimer.timer(REFRESH_RATE, true, this, this);
    }

    private void handleViews()
    {
        add(BorderLayout.NORTH, new GlassCockpit(gw));
        add(BorderLayout.CENTER, new MapView(gw.getWorld()));
        add(BorderLayout.SOUTH, new ButtonControls(gw));
        // TODO add commands in overflow menu
    }

    private void handleKeys()
    {
        // key commands
        Command accelerateCommand = new AccelerateCommand(gw);
        addKeyListener('a', accelerateCommand);

        Command brakeCommand = new BrakeCommand(gw);
        addKeyListener('b', brakeCommand);

        Command leftCommand = new LeftCommand(gw);
        addKeyListener('l', leftCommand);

        Command rightCommand = new RightCommand(gw);
        addKeyListener('r', rightCommand);

        Command helicopterCollisionCommand = new HelicopterCollisionCommand(gw);
        addKeyListener('c', helicopterCollisionCommand);

        Command refuelCommand = new RefuelCommand(gw);
        addKeyListener('e', refuelCommand);

        Command birdCollisionCommand = new BirdCollisionCommand(gw);
        addKeyListener('g', birdCollisionCommand);

        // TODO 's' key for skyscraper collision, with Dialog

        // prompt for exit
        addKeyListener('x', this::askToExit);

        // arrow keys for flight
        final int LEFT = 2;
        final int RIGHT = 5;
        final int UP = 1;
        final int DOWN = 6;
        addGameKeyListener(LEFT, leftCommand);
        addGameKeyListener(RIGHT, rightCommand);
        addGameKeyListener(UP, accelerateCommand);
        addGameKeyListener(DOWN, brakeCommand);
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
        // spawn world once everything is laid out
        spawnWorld();

        // check if the game needs to be reinitialized
        gw.checkIfReset();

        // check if game is over
        if (gw.isGameOver())
        {
            gw.exit();
        }
        gw.tick();
    }

    private void spawnWorld()
    {
        if (!isSpawned)
        {
            gw.init();
            isSpawned = true;
        }
    }
}
