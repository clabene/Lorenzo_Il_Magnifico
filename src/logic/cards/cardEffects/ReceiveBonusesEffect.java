package logic.cards.cardEffects;

import logic.bonuses.Bonus;
import logic.player.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IBM on 30/05/2017.
 */
public class ReceiveBonusesEffect implements CardEffect {

    private ArrayList<Bonus> bonuses = new ArrayList<>();

    public ReceiveBonusesEffect(Bonus ... bonuses) {
        Collections.addAll(this.bonuses, bonuses);
    }

    @Override
    public void activate(Player player){
        player.getBonuses().addAll(bonuses);
    }

}
