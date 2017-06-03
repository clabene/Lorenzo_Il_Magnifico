package actionSpaces;

import board.Tower;
import cards.Card;
import exceptions.LimitedValueOffRangeException;
import interfaces.Gainable;
import player.FamilyMember;
import player.Player;
import resources.Money;

/**
 * Created by IBM on 19/05/2017.
 */
public class TowerActionSpace extends ActionSpace {

    private Card card;

    public TowerActionSpace(int minValueToPlaceFamiliar, Gainable ... gainables){
        super(1, ActionSpaceType.TOWER, minValueToPlaceFamiliar, gainables);
        this.card = null;

    }

    private boolean towerAlreadyHasColoredOfSameType(Player player){
        for(Tower tmp : player.getBoard().getTowerArea().getTowers()) {
            if (!tmp.getFamilyMembers().contains(this.getLastFamilyMemberAdded())) continue;
            //we are in the same tower of the action space
            for(FamilyMember tmp1 : tmp.getFamilyMembers())
                if(this.getLastFamilyMemberAdded().getPlayerId().equals(tmp1.getPlayerId()) //tmp1 of same player as current family member
                        && tmp1 != this.getLastFamilyMemberAdded() //tmp1 is not current family member
                        && tmp1.getColor() != null) { //tmp1 is not neutral
                    System.out.println("You already put a not-neutral family member in the tower");
                    return false;
                }
        }
        return true;
    }

    private boolean towerAlreadyHasNeutralOfSameType(Player player){
        for(Tower tmp : player.getBoard().getTowerArea().getTowers()) {
            if (!tmp.getFamilyMembers().contains(this.getLastFamilyMemberAdded())) continue;
            //we are in the same tower of the action space
            for(FamilyMember tmp1 : tmp.getFamilyMembers())
                if(this.getLastFamilyMemberAdded().getPlayerId().equals(tmp1.getPlayerId()) //tmp1 of same player as current family member
                        && tmp1 != this.getLastFamilyMemberAdded() //tmp1 is not current family member
                        && tmp1.getColor() == null) { //tmp1 is neutral
                    return false;
                }
        }
        return true;
    }

    private boolean playerPaidCoins(Player player){
        if(towerAlreadyHasNeutralOfSameType(player))
            return player.lose(new Money(3));
        else return true;
    }

    public boolean action(Player player){

        if(card == null || towerAlreadyHasColoredOfSameType(player)) return false;

        if(! playerPaidCoins(player)) return false;

        if(player.lose(card.getCardCost())){ //true: player can pay the card
            try{
                player.tryToTakeCard(card);
            } catch(IndexOutOfBoundsException | LimitedValueOffRangeException e) { //goes here if player has 6 cards of the given type already
                System.out.println("You already have 6 cards of type "+card.getCardType());
                player.gain( (Gainable) card.getCardCost().getSelectedCost()); //could not take the card but paid for it -> takes what paid back
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
