package org.csc133.a3.model.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.model.GameWorld;

public class RefuelCommand extends Command
{
    private GameWorld gw;

    public RefuelCommand(GameWorld gw)
    {
        super("Refuel");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        gw.refuel();
    }
}
