package org.csc133.a3.view;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;

public class DigitalDashComponent extends Component
{
    Image[] digitalImages = new Image[20];
    Image[] clockDigits;
    Image colonImage;
    int ledColor = ColorUtil.WHITE;
    int numDigitsShowing;
    double value = 0;

    public DigitalDashComponent()
    {
        initImages();
        numDigitsShowing = 1;
        clockDigits = new Image[numDigitsShowing];
        zeroOutDigits();
    }

    public DigitalDashComponent(int numDigitsShowing)
    {
        initImages();
        this.numDigitsShowing = numDigitsShowing;
        clockDigits = new Image[numDigitsShowing];
        zeroOutDigits();
    }

    private void initImages()
    {
        try
        {
            // first 10 is digits 0-9
            for (int i = 0; i < 10; i++)
            {
                digitalImages[i] = Image.createImage("/LED_digit_" + i + ".png");
            }
            // second 10 is digits 0-9 with decimal
            for (int i = 0; i < 10; i++)
            {
                digitalImages[i + 10] = Image.createImage("/LED_digit_" + i + "_with_dot.png");
            }
            colonImage = Image.createImage("/LED_colon.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void zeroOutDigits()
    {
        for (int i = 0; i < numDigitsShowing; i++)
        {
            clockDigits[i] = digitalImages[0];
        }
    }

    private void updateDash()
    {
        double valueCopy = value;
        for (int i = numDigitsShowing - 1; i >= 0; i--)
        {
            // extract rightmost digit, then chop off
            clockDigits[i] = digitalImages[(int) valueCopy % 10];
            valueCopy = valueCopy / 10;
        }
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public void setLedColor(int ledColor)
    {
        this.ledColor = ledColor;
    }

    public void start()
    {
        getComponentForm().registerAnimated(this);
    }

    public void stop()
    {
        getComponentForm().deregisterAnimated(this);
    }

    public boolean animate()
    {
        updateDash();
        return true;
    }

    public void laidOut()
    {
        this.start();
    }

    protected Dimension calcPreferredSize()
    {
        return new Dimension(colonImage.getWidth() * numDigitsShowing, colonImage.getHeight());
    }

    @Override
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
