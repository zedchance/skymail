package org.csc133.a3.model.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.model.Bird;
import org.csc133.a3.model.GameWorld;

public class BirdCollisionCommand extends Command
{
    private GameWorld gw;

    public BirdCollisionCommand(GameWorld gw)
    {
        super("Bird collision");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        // FIXME: 4/24/21 TEMP
        gw.birdCollision(new Bird());
    }
}
