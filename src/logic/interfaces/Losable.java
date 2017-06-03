package logic.interfaces;

import logic.exceptions.NegativePointsException;
import logic.exceptions.NegativeResourceQuantityException;
import logic.player.Player;

/**
 * Created by IBM on 09/05/2017.
 */
public interface Losable {
    //can be spent.

    void lostByPlayer(Player player) throws NegativeResourceQuantityException, NegativePointsException;

}
