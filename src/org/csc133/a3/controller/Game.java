package org.csc133.a3.controller;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a3.model.GameWorld;
import org.csc133.a3.model.command.*;
import org.csc133.a3.view.ButtonControls;
import org.csc133.a3.view.GlassCockpit;
import org.csc133.a3.view.MapView;

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
        setupViews();

        // setup key listeners and overflow commands
        setupCommands();

        // timer continually calls run method
        UITimer.timer(REFRESH_RATE, true, this, this);

        // show main Game form
        this.show();

        // init sounds when world is spawned, after Game is shown
        new Thread(gw::initSounds).start();
    }

    private void setupViews()
    {
        add(BorderLayout.NORTH, new GlassCockpit(gw));
        add(BorderLayout.CENTER, new MapView(gw));
        add(BorderLayout.SOUTH, new ButtonControls(gw));
    }

    private void setupCommands()
    {
        // commands
        Command accelerateCommand = new AccelerateCommand(gw);
        Command brakeCommand = new BrakeCommand(gw);
        Command leftCommand = new LeftCommand(gw);
        Command rightCommand = new RightCommand(gw);
        Command exitCommand = new ExitCommand(gw);
        Command aboutCommand = new AboutCommand();
        Command versionCommand = new VersionCommand();

        // key commands
        addKeyListener('a', accelerateCommand);
        addKeyListener('b', brakeCommand);
        addKeyListener('l', leftCommand);
        addKeyListener('r', rightCommand);
        addKeyListener('x', exitCommand);

        // arrow keys for flight
        final int LEFT = 2;
        final int RIGHT = 5;
        final int UP = 1;
        final int DOWN = 6;
        addGameKeyListener(LEFT, leftCommand);
        addGameKeyListener(RIGHT, rightCommand);
        addGameKeyListener(UP, accelerateCommand);
        addGameKeyListener(DOWN, brakeCommand);

        // overflow menu commands
        getToolbar().addCommandToOverflowMenu(exitCommand);
        getToolbar().addCommandToOverflowMenu(aboutCommand);
        getToolbar().addCommandToOverflowMenu(versionCommand);
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
