package org.csc133.a3.model.strategy;

import com.codename1.util.MathUtil;
import org.csc133.a3.model.*;

public class FlyToSkyScraperStrategy implements Strategy
{
    private final GameObjectCollection world;

    public FlyToSkyScraperStrategy(GameObjectCollection world)
    {
        this.world = world;
    }

    @Override
    public void invokeStrategy(Helicopter helo)
    {
        // TODO: 4/16/21 target becomes null when nph finishes entire flight path

        // loop and find the next target
        SkyScraper target = null;
        int currentSkyScraper = helo.getLastSkyscraperReached();
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

        // calculate new heading
        double currentX = helo.getX();
        double currentY = helo.getY();
        double targetX = target.getX();
        double targetY = target.getY();
        double deltaX = currentX - targetX;
        double deltaY = currentY - targetY;
        double targetHeading = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
        System.out.println("helo.getHeading() = " + helo.getHeading());
        System.out.println("targetHeading = " + Math.floorMod((int) ((int) 90 - targetHeading), 360));

        // turn toward target
        helo.setSpeed(1);
        helo.setStickAngle((int) (90 - targetHeading));

        // check if at target
        final int COLLISION_PAD = target.getSize();
        if (helo.getX() >= target.getX() - COLLISION_PAD && helo.getX() <= target.getX() + COLLISION_PAD &&
                helo.getY() >= target.getY() - COLLISION_PAD && helo.getY() <= target.getY() + COLLISION_PAD)
        {
            helo.landAtSkyScraper(target.getSequenceNumber());
        }
    }
}
