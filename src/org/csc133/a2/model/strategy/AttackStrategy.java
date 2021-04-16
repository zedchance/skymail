package org.csc133.a2.model.strategy;

import org.csc133.a2.model.Helicopter;
import org.csc133.a2.model.PlayerHelicopter;

public class AttackStrategy implements Strategy
{
    @Override
    public void invokeStrategy(Helicopter helo)
    {
        System.out.println("Performing attack strat");
        Helicopter target = PlayerHelicopter.getPlayer();
        int currentHeading = helo.getHeading();
    }
}
