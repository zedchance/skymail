package org.csc133.a1;

public class Helicopter extends MovableObject implements ISteerable
{
    private int stickAngle;
    private int maximumSpeed;
    private int fuelLevel;
    private float fuelConsumptionRate;
    private int damageLevel;
    private int lastSkyscraperReached;

    public Helicopter(int size, float x, float y, int color, int heading, int speed, int stickAngle, int maximumSpeed, int fuelLevel, float fuelConsumptionRate, int damageLevel, int lastSkyscraperReached)
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
        // TODO
    }
}
