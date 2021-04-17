package org.csc133.a2.model.strategy;

import org.csc133.a2.model.Helicopter;

public class CircleStrategy implements Strategy
{
    @Override
    public void invokeStrategy(Helicopter helo)
    {
        helo.setStickAngle(helo.getHeading() + 1);
    }
}
