package org.csc133.a2.model.strategy;

import com.codename1.util.MathUtil;
import org.csc133.a2.model.Helicopter;
import org.csc133.a2.model.PlayerHelicopter;

public class AttackStrategy implements Strategy
{
    @Override
    public void invokeStrategy(Helicopter helo)
    {
        helo.setSpeed(1);
        double currentX = helo.getX();
        double currentY = helo.getY();
        Helicopter target = PlayerHelicopter.getPlayer();
        double targetX = target.getX();
        double targetY = target.getY();
        double deltaX = currentX - targetX;
        double deltaY = currentY - targetY;
        double targetHeading = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
        helo.setStickAngle((int) (90 - targetHeading));
    }
}
