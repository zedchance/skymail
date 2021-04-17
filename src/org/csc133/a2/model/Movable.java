package org.csc133.a2.model;

import com.codename1.ui.geom.Point;
import org.csc133.a2.controller.Game;
import org.csc133.a2.view.MapView;

import java.util.Map;

/**
 * Objects in game that have a movable position
 */
public abstract class Movable extends GameObject implements IWallStrategy
{
    private int heading;
    private int speed;

    /**
     * By default, Movable's have these properties:
     * heading is 0,
     * speed is 0.
     */
    public Movable()
    {
        this(0, 0);
    }

    /**
     * Create a Movable with a specific heading and speed.
     *
     * @param heading the direction of travel
     * @param speed   the object's travel speed
     */
    public Movable(int heading, int speed)
    {
        super();
        this.heading = heading;
        this.speed = speed;
    }

    public int getHeading()
    {
        return heading;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * The heading is limited between 0 and 359, and acts
     * the same way as a compass heading (0 is north).
     *
     * @param heading compass heading to set
     */
    public void setHeading(int heading)
    {
        this.heading = Math.floorMod(heading, 360);
    }

    private boolean checkIfOnWall(DoublePoint location)
    {
        double newX = location.getX();
        double newY = location.getY();
        return  newX > MapView.mapWidth ||
                newX < MapView.WALL_PAD ||
                newY > MapView.mapHeight ||
                newY < MapView.WALL_PAD;
    }

    private DoublePoint checkIfMovingOutOfBounds(DoublePoint location)
    {
        double newX = location.getX();
        double newY = location.getY();
        // TODO: 4/9/21 I don't love these static values
        newX = Math.min(newX, MapView.mapWidth);
        newX = Math.max(newX, MapView.WALL_PAD);
        newY = Math.min(newY, MapView.mapHeight);
        newY = Math.max(newY, MapView.WALL_PAD);
        return new DoublePoint(newX, newY);
    }

    /**
     * Move the object to a new location based on its current heading and speed
     */
    public void move()
    {
        double theta = 90 - heading;
        double speed = getSpeed();
        double deltaX = Math.cos(Math.toRadians(theta)) * (speed / 2) / Game.REFRESH_RATE;
        double deltaY = Math.sin(Math.toRadians(theta)) * (speed / 2) / Game.REFRESH_RATE;
        double newX = getX() + deltaX;
        double newY = getY() + deltaY;
        DoublePoint newLocation = new DoublePoint(newX, newY);
        // check if the object is contacting the wall
        if (checkIfOnWall(newLocation))
        {
            wallStrategy();
        }
        // set new location, and check if moving out of bounds
        setLocation(checkIfMovingOutOfBounds(newLocation));
    }

    @Override
    public String toString()
    {
        return "Movable{" +
                "heading=" + heading +
                ", speed=" + speed +
                "} " + super.toString();
    }
}
