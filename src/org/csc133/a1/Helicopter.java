package org.csc133.a1;

public class Helicopter extends MovableObject implements ISteerable
{
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
     * fuelConsumptionRate is 5,
     * damageLevel is 0,
     * lastSkyScraperReached is 0,
     * speed is 0,
     * size is 10.
     */
    public Helicopter()
    {
        this(0, 50, 100, 5, 0, 0);
        setSpeed(0);
        setSize(10);
    }

    public Helicopter(int x, int y)
    {
        this();
        setLocation(x, y);
    }

    private Helicopter(int stickAngle, int maximumSpeed, double fuelLevel, double fuelConsumptionRate, int damageLevel, int lastSkyscraperReached)
    {
        super();
        this.stickAngle = stickAngle;
        this.maximumSpeed = maximumSpeed;
        this.fuelLevel = fuelLevel;
        this.fuelConsumptionRate = fuelConsumptionRate;
        this.damageLevel = damageLevel;
        this.lastSkyscraperReached = lastSkyscraperReached;
    }

    /**
     * Helicopters can accelerate by a specific amount
     * up to the maximumSpeed
     */
    public void accelerate()
    {
        int accelerationSpeed = 10;
        if (getSpeed() >= maximumSpeed)
        {
            setSpeed(maximumSpeed);
        }
        else
        {
            setSpeed(getSpeed() + accelerationSpeed);
        }
    }

    /**
     * Helicopters can decelerate by a specific amount,
     * down to 0, where they hover.
     */
    public void decelerate()
    {
        int decelerationSpeed = 10;
        if (getSpeed() <= 0)
        {
            setSpeed(0);
        }
        else
        {
            setSpeed(getSpeed() - decelerationSpeed);
        }
    }

    /**
     * Helicopters are destroyed when their damageLevel is 100
     *
     * @return true if destroyed
     */
    public boolean isDestroyed()
    {
        return damageLevel == 100;
    }

    /**
     * Helicopters are empty when their fuelLevel has reached 0
     *
     * @return true if fuel is empty
     */
    public boolean isFuelEmpty()
    {
        return fuelLevel == 0;
    }

    /**
     * Helicopters use fuel each time they move
     */
    public void consumeFuel()
    {
        // TODO consumption should be proportional to current speed
        fuelLevel = fuelLevel - fuelConsumptionRate;
    }

    @Override
    public void move()
    {
        // helicopters can only turn 5 degrees each tick
        if (stickAngle > getHeading())
        {
            setHeading(getHeading() + 5);
        }
        else if (stickAngle < getHeading())
        {
            setHeading(getHeading() - 5);
        }

        if (!isFuelEmpty() && !isDestroyed())
        {
            super.move();
        }
        consumeFuel();
    }

    @Override
    public void changeDirection(int degrees)
    {
        stickAngle = stickAngle + degrees;
    }

    @Override
    public String toString()
    {
        return "Helicopter{" +
                "stickAngle=" + stickAngle +
                ", maximumSpeed=" + maximumSpeed +
                ", fuelLevel=" + fuelLevel +
                ", fuelConsumptionRate=" + fuelConsumptionRate +
                ", damageLevel=" + damageLevel +
                ", lastSkyscraperReached=" + lastSkyscraperReached +
                "} " + super.toString();
    }
}
