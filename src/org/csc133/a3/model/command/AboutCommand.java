package org.csc133.a3.model.command;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command
{
    public AboutCommand()
    {
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        Dialog.show("SkyMail 3000", "About text goes here", "Ok", null);
    }
}
