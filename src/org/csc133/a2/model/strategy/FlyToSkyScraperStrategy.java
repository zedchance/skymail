package org.csc133.a2.model.strategy;

import com.codename1.util.MathUtil;
import org.csc133.a2.model.*;

public class FlyToSkyScraperStrategy implements Strategy
{
    private GameObjectCollection world;

    public FlyToSkyScraperStrategy(GameObjectCollection world)
    {
        this.world = world;
    }

    @Override
    public void invokeStrategy(Helicopter helo)
    {
        SkyScraper target = null;
        int currentSkyScraper = helo.getLastSkyscraperReached();

        // loop and find the next target
        for (GameObject item : world)
        {
            if (item instanceof SkyScraper)
            {
                if (((SkyScraper) item).getSequenceNumber() == currentSkyScraper + 1)
                {
                    target = (SkyScraper) item;
                    break;
                }
            }
        }

        // fly toward target
        helo.setSpeed(1);
        double currentX = helo.getX();
        double currentY = helo.getY();
        double targetX = target.getX();
        double targetY = target.getY();
        double deltaX = currentX - targetX;
        double deltaY = currentY - targetY;
        double targetHeading = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
        helo.setStickAngle((int) (90 - targetHeading));

        // check if at target
        final int COLLISION_PAD = 10;
        if (helo.getX() >= target.getX() - COLLISION_PAD && helo.getX() <= target.getX() + COLLISION_PAD &&
                helo.getY() >= target.getY() - COLLISION_PAD && helo.getY() <= target.getY() + COLLISION_PAD)
        {
            helo.landAtSkyScraper(target.getSequenceNumber());
        }
    }
}
