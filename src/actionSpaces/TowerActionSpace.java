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
        /*

        //todo check if 3 coins are to be paid
        //todo is the number of cards to be controlled here? max 6 per player

        if(card == null) return false;
        if(player.lose(card.getCardCost())){
            try{
                player.takeCard(card);
            } catch(IndexOutOfBoundsException | LimitedValueOffRangeException e){
                System.out.println("You already have 6 cards of this type");
                player.gain(card.getCardCost()); //what if card has two costs?
                return false;
            }
            card = null;
            return true;
        }
        return false;
        */
        return true;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return this.card;
    }

}
