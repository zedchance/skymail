package org.csc133.a3.model;

public class BGSound extends Sound implements Runnable
{
    public BGSound(String fileName)
    {
        super(fileName);
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        this.play();
    }
}
