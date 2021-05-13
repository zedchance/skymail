package org.csc133.a3.model;

/**
 * Automatically starts background music on instantiation
 */
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
