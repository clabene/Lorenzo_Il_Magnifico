package cardEffects;


import cardEffects.bonuses.Bonus;
import player.Player;

/**
 * Created by IBM on 30/05/2017.
 */
public class ReceiveBonusEffect implements CardEffect {

    private Bonus bonus; //todo array??

    public ReceiveBonusEffect(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    public void activate(Player player){
        player.getBonuses().add(bonus);
    }

}
