package org.csc133.a3.model;

public interface ICollider
{
    boolean collidesWith(GameObject otherObject);

    void handleCollision(GameObject otherObject, GameWorld gw);
}
