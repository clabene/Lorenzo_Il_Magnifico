package logic.interfaces;

import logic.exceptions.NegativePointsException;
import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;

import java.io.Serializable;

/**
 * Created by IBM on 09/05/2017.
 */
public interface Losable extends Serializable{
    //can be spent.

    void lostByPlayer(Player player) throws NegativeResourceQuantityException, NegativePointsException;

}
