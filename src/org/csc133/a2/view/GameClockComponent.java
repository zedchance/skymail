package org.csc133.a2.view;

import com.codename1.charts.util.ColorUtil;

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
    }

    private void setElapsedTime()
    {
        long rightNow = Calendar.getInstance().getTimeInMillis();
        long elapsedTime = rightNow - startTime;
        int tenthsOfSeconds = (int) elapsedTime / 100;
        int seconds = (int) elapsedTime / 1000;
        int minutes = seconds / 60;
        checkIfColorChange(minutes);
        setTime(minutes, seconds % 60, tenthsOfSeconds);
    }

    private void checkIfColorChange(int minutes)
    {
        if (minutes > 10) setLedColor(ColorUtil.rgb(255, 0, 0));
    }

    // TODO: 4/10/21 resetElapsedTime(), startElapsedTime(), stopElapsedTime, and getElapsedTime()

    @Override
    public boolean animate()
    {
        setElapsedTime();
        return true;
    }

    // TODO: 4/9/21 override paint, rightmost digit should be darker
}
