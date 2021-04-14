package org.csc133.a2.model;

public class PlayerHelicopter
{
    private static Helicopter player;

    /**
     * A singleton pattern to create a single instance
     * of the player's Helicopter
     *
     * @param x coordinate
     * @param y coordinate
     * @return Helicopter object
     */
    public static synchronized Helicopter getPlayer(int x, int y)
    {
        if (player == null)
        {
            player = new Helicopter(x, y);
        }
        return player;
    }
}
