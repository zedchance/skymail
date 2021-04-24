package org.csc133.a3.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

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

    @Override
    public void paint(Graphics g)
    {
        final int COLOR_PAD = 1;

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing * digitWidth;

        float scaleFactor = Math.min(getInnerHeight() / (float) digitHeight,
                                     getInnerWidth() / (float) clockWidth);

        int displayDigitWidth = (int) (scaleFactor * digitWidth);
        int displayDigitHeight = (int) (scaleFactor * digitHeight);
        // num of digits showing minus one to account for darker ms color
        int displayClockWidth = displayDigitWidth * (numDigitsShowing - 1);

        int displayX = getX() + (getWidth() - displayClockWidth) / 4;
        int displayY = getY() + (getHeight() - displayDigitHeight) / 2;

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.setColor(ledColor);
        g.fillRect(displayX + COLOR_PAD,
                   displayY + COLOR_PAD,
                   displayClockWidth - COLOR_PAD * 2,
                   displayDigitHeight - COLOR_PAD * 2);
        // dark blue ms digit
        g.setColor(ColorUtil.rgb(50, 50, 255));
        g.fillRect(displayX + COLOR_PAD + (5 * displayDigitWidth),
                   displayY + COLOR_PAD,
                   displayDigitWidth - COLOR_PAD * 2,
                   displayDigitHeight - COLOR_PAD * 2);

        for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++)
        {
            g.drawImage(clockDigits[digitIndex],
                        displayX + digitIndex * displayDigitWidth,
                        displayY,
                        displayDigitWidth,
                        displayDigitHeight);
        }
    }
}
