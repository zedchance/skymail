package org.csc133.a3.model.command;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class VersionCommand extends Command
{
    public VersionCommand()
    {
        super("Version");
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        Dialog.show("SkyMail 3000", "Version 3\nCreated by Zed Chance", "Ok", null);
    }
}
