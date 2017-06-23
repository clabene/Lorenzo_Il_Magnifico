package logic.cards.cardEffects;

import logic.player.Player;

import java.io.Serializable;

/**
 * Created by IBM on 14/05/2017.
 */
public interface CardEffect extends Serializable {
    void activate(Player player);
}
