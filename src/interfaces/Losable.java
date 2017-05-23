package interfaces;

import exceptions.NegativePointsException;
import exceptions.NegativeResourceQuantityException;
import player.Player;

/**
 * Created by IBM on 09/05/2017.
 */
public interface Losable {
    //can be spent.

    void lostByPlayer(Player player) throws NegativeResourceQuantityException, NegativePointsException;

}
