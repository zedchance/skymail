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
        String aboutText = "The object of the game" +
                "\n\n" +
                "The player controls a helicopter and attempts to fly to each sky scraper in order, while maintaining" +
                " a balance between fuel and damage." +
                " Other helicopters compete against the player and attempt to do the same, or do directly damage the" +
                " player." +
                " The player must navigate between other moving objects such as birds, and must make decisions on" +
                " when to deviate from the flight path to refuel." +
                "\n\n" +
                "The player starts with 3 lives, and loses a life when their helicopter runs out of fuel, or if" +
                " they take too much damage." +
                " If the player loses all 3 lives, or if the opponent helicopter finishes the flight path before" +
                " the player, it is game over and the player loses." +
                " If the player can successfully land at each sky scraper in order, while both maintaining adequate" +
                " fuel and damage levels, the player wins." +
                " If the player wins, the elapsed time that it took to win is essentially the score at the end of" +
                " the game." +
                "\n\n" +
                "How to play" +
                "\n\n" +
                "Use the flight controls below to control the helicopter.\n" +
                "\n" +
                "`a` or up arrow to accelerate\n" +
                "`b` or down arrow to decelerate\n" +
                "`l` or left arrow to turn left\n" +
                "`r` or right arrow to turn right\n" +
                "\n" +
                "Fly over sky scrapers to land at them." +
                " You  must land at each SkyScraper in order.\n" +
                "\n" +
                "Fly over refueling blimps to refuel your helicopter.\n" +
                "\n" +
                "If your helicopter becomes too damaged, you will lose a life and the game will restart." +
                " If you lose all 3 lives, it is game over.";
        Dialog.show("SkyMail 3000", aboutText, "Ok", null);
    }
}
