package org.csc133.a2.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;

/**
 * The player controls the Helicopter
 */
public class Helicopter extends Movable implements ISteerable
{
    private final int MAX_SPEED = 50;
    private int stickAngle;
    private int maximumSpeed;
    private double fuelLevel;
    private double fuelConsumptionRate;
    private int damageLevel;
    private int lastSkyscraperReached;

    /**
     * By default, Helicopters have these properties:
     * stickAngle is 0,
     * maximumSpeed is 10,
     * fuelLevel is 100,
     * fuelConsumptionRate is 0.05,
     * damageLevel is 0,
     * lastSkyScraperReached is 0,
     * color is red,
     * speed is random between 1 and 3,
     * size is 20.
     */
    public Helicopter()
    {
        this(0, 10, 100, 0.05, 0, 0);
        Random rand = new Random();
        setSpeed(rand.nextInt(2) + 1);
        setSize(20);
    }

    /**
     * Create a helicopter at a specific position
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    public Helicopter(int x, int y)
    {
        this();
        setLocation(x, y);
    }

    /**
     * Create a helicopter with specific parameters
     *
     * @param stickAngle            the direction that the helicopter wants to fly
     * @param maximumSpeed          the fastest the helicopter can fly
     * @param fuelLevel             the amount of fuel, empty at 0
     * @param fuelConsumptionRate   amount of fuel used per tick
     * @param damageLevel           amount of damage taken, up to 100
     * @param lastSkyscraperReached last SkyScraper checkpoint reached
     */
    private Helicopter(int stickAngle, int maximumSpeed, double fuelLevel, double fuelConsumptionRate, int damageLevel, int lastSkyscraperReached)
    {
        super();
        this.stickAngle = stickAngle;
        this.maximumSpeed = maximumSpeed;
        this.fuelLevel = fuelLevel;
        this.fuelConsumptionRate = fuelConsumptionRate;
        this.damageLevel = damageLevel;
        this.lastSkyscraperReached = lastSkyscraperReached;
        setColor(ColorUtil.rgb(255, 0, 0));
    }

    public double getFuelLevel()
    {
        return fuelLevel;
    }

    public int getDamageLevel()
    {
        return damageLevel;
    }

    public int getLastSkyscraperReached()
    {
        return lastSkyscraperReached;
    }

    public int getMaximumSpeed()
    {
        return maximumSpeed;
    }

    /**
     * Helicopters can accelerate by a specific amount
     * up to the maximumSpeed. Causing a Helicopter to accelerate
     * also spends 1 unit of fuel.
     */
    public void accelerate()
    {
        int accelerationSpeed = 1;
        setSpeed(getSpeed() + accelerationSpeed);
        checkSpeed();
        consumeFuel(1);
    }

    /**
     * Helicopters can decelerate by a specific amount,
     * down to 0, where they hover.
     */
    public void decelerate()
    {
        int decelerationSpeed = 2;
        setSpeed(Math.max(getSpeed() - decelerationSpeed, 0));
    }

    /**
     * Calculate the maximum speed that the helicopter
     * can travel.
     * Lighter helicopters travel faster, so the less fuel
     * in the tank the faster the speed (up to 25% increase).
     * Damaged helicopters travel slower
     * (15% faster when not damaged).
     */
    private void calculateMaximumSpeed()
    {
        double newSpeed = maximumSpeed;
        double fuelFactor = 1 + (-0.0025 * fuelLevel + 0.25);
        newSpeed = newSpeed * fuelFactor;
        double damageFactor = 1 + (-0.0015 * damageLevel + 0.15);
        newSpeed = newSpeed * damageFactor;
        maximumSpeed = Math.min(MAX_SPEED, (int) newSpeed);
    }

    /**
     * Check to make sure the helicopter is not travelling faster
     * than the maximum speed
     */
    private void checkSpeed()
    {
        if (getSpeed() > maximumSpeed)
        {
            setSpeed(maximumSpeed);
        }
    }

    /**
     * Helicopters are destroyed when their damageLevel is 100
     *
     * @return true if destroyed
     */
    public boolean isDestroyed()
    {
        return damageLevel >= 100;
    }

    /**
     * Helicopters can take damage up until they are destroyed.
     * Helicopters start as being red and fade with the amount
     * of damage taken. Maximum speed is limited when damage is taken.
     *
     * @param amount damage to be applied
     */
    private void takeDamage(int amount)
    {
        if (damageLevel + amount >= 100)
        {
            damageLevel = 100;
        }
        else
        {
            damageLevel = damageLevel + amount + 1;
            setColor(ColorUtil.rgb(100 - damageLevel, 0, 0));
            maximumSpeed = maximumSpeed - (amount / 2);
            calculateMaximumSpeed();
        }
    }

    /**
     * Helicopters take damage when colliding with other objects
     * equal to their current speed + 1
     */
    public void collide()
    {
        // TODO take in another Helicopter as parameter, use their speed as a factor to damage taken
        takeDamage(getSpeed() + 1);
    }

    /**
     * Helicopters take damage equal to 1/5 of current speed + 1
     * when colliding with a Bird
     */
    public void collideWithBird()
    {
        takeDamage(getSpeed() / 5 + 1);
    }

    /**
     * Helicopters are empty when their fuelLevel has reached 0
     *
     * @return true if fuel is empty
     */
    public boolean isFuelEmpty()
    {
        return fuelLevel <= 0;
    }

    /**
     * Helicopters use fuel each time they move, proportional
     * to current speed
     */
    private void consumeFuel()
    {
        consumeFuel(fuelConsumptionRate);
    }

    private void consumeFuel(double amount)
    {
        fuelLevel = fuelLevel - amount;
    }

    /**
     * Helicopters can hold 100 units of fuel
     *
     * @param amount how much fuel to add to tank
     */
    public void fuelUp(int amount)
    {
        if (fuelLevel + amount >= 100)
        {
            fuelLevel = 100;
        }
        else
        {
            fuelLevel = fuelLevel + amount;
        }
    }

    /**
     * Attempts to land the Helicopter at a specific
     * SkyScraper. Helicopters can only land in order,
     * and cannot skip the SkyScraper checkpoints.
     *
     * @param n SkyScraper number to request landing
     * @return true if landing was successful, false if not
     */
    public boolean landAtSkyScraper(int n)
    {
        if (lastSkyscraperReached == (n - 1))
        {
            lastSkyscraperReached++;
            return true;
        }
        return false;
    }

    /**
     * Helicopters are both steerable and use fuel.
     * Each tick a Helicopter can turn 5 degrees toward
     * the stickAngle.
     * If the Helicopter crashes due to fuel starvation
     * the speed is set to 0.
     */
    @Override
    public void move()
    {
        // TODO this isn't obeying the A1 rules for stick angle / heading anymore
        setHeading(stickAngle);

        if (!isFuelEmpty() && !isDestroyed())
        {
            calculateMaximumSpeed();
            checkSpeed();
            super.move();
            consumeFuel();
        }
        else
        {
            setSpeed(0);
        }
    }

    /**
     * Helicopters can turn a maximum of a 40 degree
     * difference on the stick angle compared to the
     * current heading
     *
     * @param degrees amount of requested direction change
     */
    @Override
    public void changeDirection(int degrees)
    {
        if (Math.abs(stickAngle - getHeading()) <= 35)
        {
            stickAngle = Math.floorMod(stickAngle + degrees, 360);
        }
    }

    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        // center of helo
        int x = (int) getX() + containerOrigin.getX();
        int y = (int) getY() + containerOrigin.getY();

        // nose of helo
        double theta = 90 - getHeading();
        int noseX = x + (int) (Math.cos(Math.toRadians(theta)) * getSize());
        int noseY = y + (int) (Math.sin(Math.toRadians(theta)) * getSize());

        // left and right tails of helo
        int leftTailX = x + (int) (Math.cos(Math.toRadians(theta + 90)) * getSize());
        int leftTailY = y + (int) (Math.sin(Math.toRadians(theta + 90)) * getSize());
        int rightTailX = x + (int) (Math.cos(Math.toRadians(theta - 90)) * getSize());
        int rightTailY = y + (int) (Math.sin(Math.toRadians(theta - 90)) * getSize());

        g.setColor(getColor());
        g.fillTriangle(noseX, noseY, leftTailX, leftTailY, rightTailX, rightTailY);
    }

    @Override
    public String toString()
    {
        return "Helicopter\t\t" +
                String.format("location=(%5.1f, %5.1f)", getX(), getY()) +
                " lastSkyscraperReached=" + lastSkyscraperReached +
                " stickAngle=" + stickAngle +
                " maximumSpeed=" + maximumSpeed +
                " fuelLevel=" + fuelLevel +
                " fuelConsumptionRate=" + fuelConsumptionRate +
                " damageLevel=" + damageLevel;
    }
}
