package org.csc133.a3.model;

/**
 * Objects that implement ICollider can both check if they collide with another object,
 * and handle those collisions.
 */
public interface ICollider
{
    boolean collidesWith(GameObject otherObject);

    void handleCollision(GameObject otherObject, GameWorld gw);
}
