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
     * maximumSpeed is 50,
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

    /**
     * Helicopters can accelerate by a specific amount
     * up to the maximumSpeed
     */
    public void accelerate()
    {
        // TODO acceleration should be proportional to current fuel, damage, and max speed
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
        return damageLevel >= 100;
    }

    private void takeDamage(int amount)
    {
        // TODO change color when damage is taken
        if (damageLevel + amount >= 100)
        {
            damageLevel = 100;
        }
        else
        {
            damageLevel = damageLevel + amount + 1;
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

    public void resetAfterCollision()
    {
        setSpeed(0);
        damageLevel = 0;
        // TODO reset color
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
        // TODO consumption should be proportional to current speed
        fuelLevel = fuelLevel - fuelConsumptionRate;
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
     * Helicopters are both steerable and use fuel.
     * Each tick a Helicopter can turn 5 degrees toward
     * the stickAngle.
     * If the Helicopter crashes due to fuel starvation
     * the speed is set to 0.
     */
    @Override
    public void move()
    {
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
            consumeFuel();
        }
        else
        {
            setSpeed(0);
        }
    }

    @Override
    public void changeDirection(int degrees)
    {
        stickAngle = stickAngle + degrees;
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
