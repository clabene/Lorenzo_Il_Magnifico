package cards.cardEffects;

import bonuses.Bonus;
import player.Player;

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
