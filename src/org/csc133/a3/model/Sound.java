package org.csc133.a3.model;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.IOException;

public class Sound
{
    private Media m;

    public Sound(String fileName)
    {
        try
        {
            m = MediaManager.createMedia(Display.getInstance().getResourceAsStream(getClass(), "/" + fileName),
                                         "audio/mp3");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void play()
    {
        play(0);
    }

    public void play(int startTime)
    {
        m.setVolume(50);
        m.setTime(startTime);
        m.play();
    }
}
