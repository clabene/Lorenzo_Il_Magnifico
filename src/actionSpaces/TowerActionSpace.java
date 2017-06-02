package actionSpaces;

import cards.Card;
import exceptions.LimitedValueOffRangeException;
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

        //todo check if 3 coins are to be paid

        if(card == null) return false;
        if(player.lose(card.getCardCost())){ //true: player can pay the card
            try{
                player.tryToTakeCard(card);
            } catch(IndexOutOfBoundsException | LimitedValueOffRangeException e) { //goes here if player has 6 cards of the given type already
                System.out.println("You already have 6 cards of type "+card.getCardType());
                //todo player.gain(card.getCardCost()); Also, what if card has two costs?
                return false;
            }
            this.card = null;
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
