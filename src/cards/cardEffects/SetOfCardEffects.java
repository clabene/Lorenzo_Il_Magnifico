package cards.cardEffects;

import player.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IBM on 03/06/2017.
 */
public class SetOfCardEffects implements CardEffect {

    private ArrayList<CardEffect> cardEffects = new ArrayList<>();

    public SetOfCardEffects(CardEffect ... cardEffects){
        Collections.addAll(this.cardEffects, cardEffects);
    }

    @Override
    public void activate(Player player) {
        for(CardEffect tmp : cardEffects)
            tmp.activate(player);
    }
}
