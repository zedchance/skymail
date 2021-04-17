package org.csc133.a2.model;

public class PlayerHelicopter
{
    private static Helicopter player;

    /**
     * A singleton pattern for the player's Helicopter
     *
     * @return single instance of a Helicopter
     */
    public static synchronized Helicopter getPlayer()
    {
        if (player == null)
        {
            player = new Helicopter();
        }
        return player;
    }

    @Override
    public String toString()
    {
        return "Player " + super.toString();
    }
}
