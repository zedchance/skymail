package org.csc133.a3.model.strategy;

import org.csc133.a3.model.Helicopter;

public class CircleStrategy implements Strategy
{
    @Override
    public void invokeStrategy(Helicopter helo)
    {
        helo.setStickAngle(helo.getHeading() + 1);
    }
}
