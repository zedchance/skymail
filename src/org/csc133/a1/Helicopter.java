package org.csc133.a1;

public class Helicopter extends MovableObject implements ISteerable
{
    private int stickAngle;
    private int maximumSpeed;
    private int fuelLevel;
    private double fuelConsumptionRate;
    private int damageLevel;
    private int lastSkyscraperReached;

    public Helicopter()
    {
        // TODO setup default params to follow doc
        this(25, 0, 0, 150, 45, 0, 45, 10, 100, 5, 0, 0);
    }

    public Helicopter(int size, double x, double y, int color, int heading, int speed, int stickAngle, int maximumSpeed, int fuelLevel, double fuelConsumptionRate, int damageLevel, int lastSkyscraperReached)
    {
        super(size, x, y, color, heading, speed);
        this.stickAngle = stickAngle;
        this.maximumSpeed = maximumSpeed;
        this.fuelLevel = fuelLevel;
        this.fuelConsumptionRate = fuelConsumptionRate;
        this.damageLevel = damageLevel;
        this.lastSkyscraperReached = lastSkyscraperReached;
    }

    @Override
    public void changeDirection(int degrees)
    {
        // TODO change heading based on stickAngle and degrees
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
