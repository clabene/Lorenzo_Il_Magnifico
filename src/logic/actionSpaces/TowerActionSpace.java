package logic.actionSpaces;

import logic.board.Board;
import logic.cards.Card;
import logic.cards.CardType;
import logic.exceptions.LimitedValueOffRangeException;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.Money;

import java.util.ArrayList;

/**
 * Created by IBM on 19/05/2017.
 */
public class TowerActionSpace extends ActionSpace {

    private Card card;
    private CardType cardType;

    public TowerActionSpace(int minValueToPlaceFamiliar, Gainable ... gainables){
        super(1, ActionSpaceType.TOWER, minValueToPlaceFamiliar, gainables);
        this.card = null;

    }

    //prendi la torre con carte del tipo cardType
    private ArrayList<TowerActionSpace> getTower(Board board){
        ArrayList<TowerActionSpace> toReturn = new ArrayList<>();
        for(ActionSpace tmp : board.getHashMap().values()){
            if(!(tmp instanceof TowerActionSpace)) continue;
            if(((TowerActionSpace) tmp).cardType != cardType) continue;
            toReturn.add((TowerActionSpace) tmp);
        }
        return toReturn;
    }

    private boolean towerAlreadyHasColoredOfSameType(Player player){
        for(TowerActionSpace tmp : getTower(player.getBoard())) {

                if(tmp.getLastFamilyMemberAdded()!= null &&
                        this.getLastFamilyMemberAdded().getPlayerId().equals(tmp.getLastFamilyMemberAdded().getPlayerId()) //tmp1 of same player as current family member
                        && tmp.getLastFamilyMemberAdded().getColor() != null  //tmp1 is not neutral
                        && tmp.getLastFamilyMemberAdded().getColor() != this.getLastFamilyMemberAdded().getColor() ){ //tmp1 is not current family member
                    System.out.println("You already put a not-neutral family member in the tower---------------------------");
                    return true;
                }
        }
        return false;
    }

    private boolean towerAlreadyHasNeutralOfSameType(Player player){

        for(TowerActionSpace tmp : getTower(player.getBoard())) {


            if (tmp.getLastFamilyMemberAdded()!= null &&
                    this.getLastFamilyMemberAdded().getPlayerId().equals(tmp.getLastFamilyMemberAdded().getPlayerId()) //tmp1 of same player as current family member
                    && tmp.getLastFamilyMemberAdded() != this.getLastFamilyMemberAdded() //tmp1 is not current family member
                    && tmp.getLastFamilyMemberAdded().getColor() == null) { //tmp1 is neutral
                System.out.println("You already put a not-neutral family member in the tower");
                return true;
            }
        }
        return false;
    }

    private boolean playerPaidCoins(Player player) {
        if(towerAlreadyHasNeutralOfSameType(player))
            return player.lose(new Money(3));
        return true;
    }

    public boolean action(Player player){

        if(this.card == null) return false;

        if(towerAlreadyHasColoredOfSameType(player)) return false;

        if(! playerPaidCoins(player)) return false;

        if(player.lose(card.getCardCost())){ //true: player can pay the card
            try{
                player.tryToTakeCard(card);
            } catch(IndexOutOfBoundsException | LimitedValueOffRangeException e) { //goes here if player has 6 cards of the given type already
                System.out.println("You already have 6 cards of type "+card.getCardType());
                player.gain( (Gainable) card.getCardCost().getSelectedCost()); //could not take the card but paid for it -> takes what paid back
                return false;
            }
            if(card.getImmediateEffect() != null) card.getImmediateEffect().activate(player);
            this.card = null;
            return true;
        }
        return false;
    }

    public void setCard(Card card){
        if(card != null) this.cardType = card.getCardType(); //todo this is quite brutal
        this.card = card;
    }

    public Card getCard(){
        return this.card;
    }



}
