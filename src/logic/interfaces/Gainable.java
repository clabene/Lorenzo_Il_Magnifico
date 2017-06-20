package logic.interfaces;

import logic.player.Player;

import java.io.Serializable;

/**
 * Created by IBM on 14/05/2017.
 */
public interface Gainable extends Serializable{
    //can be gained

    void gainedByPlayer(Player player);

}
