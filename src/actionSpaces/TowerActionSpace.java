package actionSpaces;

import cards.Card;
import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class TowerActionSpace extends ActionSpace {

    private Card card;

    public TowerActionSpace(int minValueToPlaceFamiliar,  Gainable ... gainables){
        super(1, ActionSpaceType.TOWER, minValueToPlaceFamiliar, gainables);
        this.card = null;

    }

    public boolean action(Player player){
        if(card == null) return false;
        if(player.lose(card.getCardCost())){
            player.takeCard(card); //todo check
            card = null;
            return true;
        }
        return false;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return this.card;
    }

}
