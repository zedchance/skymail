package org.csc133.a2.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.util.Calendar;

public class GameClockComponent extends DigitalDashComponent
{
    private static final int MS_COLON_IDX = 2;
    private static final int numDigitsShowing = 6;
    Image[] clockDigits = new Image[numDigitsShowing];
    long startTime;

    public GameClockComponent()
    {
        super();
        setLedColor(ColorUtil.GREEN);
        // clock shows all 0s at first
        for (int i = 0; i < numDigitsShowing; i++)
        {
            clockDigits[i] = digitalImages[0];
        }
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
        setTime(minutes, seconds % 60, tenthsOfSeconds);
    }

    public void start()
    {
        startTime = Calendar.getInstance().getTimeInMillis();
        getComponentForm().registerAnimated(this);
    }

    public void stop()
    {
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut()
    {
        this.start();
    }

    public boolean animate()
    {
        setElapsedTime();
        return true;
    }

    protected Dimension calcPreferredSize()
    {
        return new Dimension(colonImage.getWidth() * numDigitsShowing, colonImage.getHeight());
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing * digitWidth;

        float scaleFactor = Math.min(getInnerHeight() / (float) digitHeight,
                                     getInnerWidth() / (float) clockWidth);

        int displayDigitWidth = (int) (scaleFactor * digitWidth);
        int displayDigitHeight = (int) (scaleFactor * digitHeight);
        int displayClockWidth = displayDigitWidth * numDigitsShowing;

        int displayX = getX() + (getWidth() - displayClockWidth) / 2;
        int displayY = getY() + (getHeight() - displayDigitHeight) / 2;

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.setColor(ledColor);
        g.fillRect(displayX + COLOR_PAD,
                   displayY + COLOR_PAD,
                   displayClockWidth - COLOR_PAD * 2,
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
