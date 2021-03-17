package org.csc133.a2.view;

import com.codename1.ui.Component;
import com.codename1.ui.Image;

import java.io.IOException;

public abstract class DigitalDashComponent<T> extends Component
{
    Image[] digitalImages = new Image[20];
    Image colonImage;
    Image[] dashDigits;
    T value;
    int ledColor;
    int numDigitsShowing;

    public DigitalDashComponent()
    {
        try
        {
            // first 10 is digits 0-9
            for (int i = 0; i < 10; i++)
            {
                digitalImages[i] = Image.createImage("/LED_digit_" + i + ".png");
            }
            // second 10 is digits 0-9 with decimal
            for (int i = 10; i < 20; i++)
            {
                digitalImages[i] = Image.createImage("/LED_digit_" + (i - 10) + "_with_dot.png");
            }
            colonImage = Image.createImage("/LED_colon.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public DigitalDashComponent(T value)
    {
        this();
        this.value = value;
    }

    // TODO constructor that takes ints
    // TODO constructor that takes doubles, and places decimal point

    public void setLedColor(int ledColor)
    {
        this.ledColor = ledColor;
    }
}
