package org.csc133.a2.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.util.Calendar;

public class GameClockComponent extends DigitalDashComponent
{
    private static final int MS_COLON_IDX = 2;
    long startTime;

    public GameClockComponent()
    {
        super(6);
        startTime = Calendar.getInstance().getTimeInMillis();
        // timer starts as a green color
        setLedColor(ColorUtil.GREEN);
        clockDigits[MS_COLON_IDX] = colonImage;
    }

    private void setTime(int m, int s, int ms)
    {
        clockDigits[0] = digitalImages[m / 10];
        clockDigits[1] = digitalImages[m % 10];
        // 2 is a colon
        clockDigits[3] = digitalImages[s / 10];
        // + 10 for digits with dot
        clockDigits[4] = digitalImages[s % 10 + 10];
        clockDigits[5] = digitalImages[ms % 10];
        // TODO make the rightmost digit darker in color
    }

    private void setElapsedTime()
    {
        long rightNow = Calendar.getInstance().getTimeInMillis();
        long elapsedTime = rightNow - startTime;
        int tenthsOfSeconds = (int) elapsedTime / 100;
        int seconds = (int) elapsedTime / 1000;
        int minutes = seconds / 60;
        // TODO handle this somewhere else
        if (minutes > 10) setLedColor(ColorUtil.rgb(255, 0, 0));
        setTime(minutes, seconds % 60, tenthsOfSeconds);
    }

    @Override
    public boolean animate()
    {
        setElapsedTime();
        return true;
    }
}
