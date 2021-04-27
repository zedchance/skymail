package org.csc133.a3.model;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;
import java.util.Random;

/**
 * The player controls the Helicopter
 */
public class Helicopter extends Movable implements ISteerable
{
    private final int MAX_SPEED = 100;
    private int stickAngle;
    private int maximumSpeed;
    private double fuelLevel;
    private double fuelConsumptionRate;
    private int damageLevel;
    private int lastSkyscraperReached;
    private Image heloImage;

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
     * size is 100.
     */
    public Helicopter()
    {
        this(0, 10, 100, 0.01, 0, 0);
        Random rand = new Random();
        setSpeed(rand.nextInt(2) + 1);
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
        setLocation(new DoublePoint(x, y));
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
        setSize(100);
        initImage();
    }

    private void initImage()
    {
        try
        {
            heloImage = Image.createImage("/helo.png").scaled(getSize(), getSize());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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

    public void setStickAngle(int stickAngle)
    {
        this.stickAngle = stickAngle;
    }

    public void setFuelLevel(double fuelLevel)
    {
        this.fuelLevel = fuelLevel;
    }

    /**
     * Helicopters can accelerate by a specific amount
     * up to the maximumSpeed. Causing a Helicopter to accelerate
     * also spends 1 unit of fuel.
     */
    public void accelerate()
    {
        int accelerationSpeed = 10;
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
        int decelerationSpeed = 5;
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
     * equal to an eight of the sum of their speeds + 1
     */
    public void collide(Helicopter otherHelicopter)
    {
        takeDamage(((getSpeed() / 8) + (otherHelicopter.getSpeed() / 8) / 8) + 1);
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

    public void resetHelicopter()
    {
        damageLevel = 0;
        fuelLevel = 100;
        setSpeed(0);
        setHeading(0);
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
        // TODO: 4/17/21 fix the stick angle and heading difference to obey rules
        setHeading(stickAngle);

        // check if able to move
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
        // top left of helo
        int x = (int) (getX() + containerOrigin.getX());
        int y = (int) (getY() + containerOrigin.getY());

        // center of helo
        int centerX = x + (getSize() / 2);
        int centerY = y + (getSize() / 2);

        // rotate graphics plane
        float amountToRotate = (float) Math.toRadians(getHeading());
        g.rotateRadians(-1 * amountToRotate, centerX, centerY);

        g.drawImage(heloImage, x, y);

        g.rotateRadians(amountToRotate, centerX, centerY);

        // TODO: 4/13/21 draw image with color
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

    @Override
    public void wallBehavior()
    {
        takeDamage(10);
        setSpeed(0);
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw)
    {
        if (otherObject instanceof SkyScraper)
        {
            SkyScraper s = (SkyScraper) otherObject;
            gw.landOnSkyScraperCheckpoint(s.getSequenceNumber());
        }
        else if (otherObject instanceof RefuelingBlimp)
        {
            RefuelingBlimp r = (RefuelingBlimp) otherObject;
            gw.refuel(r);
        }
        else if (otherObject instanceof NonPlayerHelicopter)
        {
            NonPlayerHelicopter nph = (NonPlayerHelicopter) otherObject;
            // TODO: 4/26/21 this feels too circular, consider refactor
            gw.helicopterCollision(this);
        }
        else if (otherObject instanceof Bird)
        {
            // helos only take 1 damage when they hit a bird
            takeDamage(1);
        }
    }
}
