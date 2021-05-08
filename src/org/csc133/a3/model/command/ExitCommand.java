package org.csc133.a3.model.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.model.GameWorld;

public class ExitCommand extends Command
{
    private GameWorld gw;

    public ExitCommand(GameWorld gw)
    {
        super("Exit");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        gw.exit();
    }
}
