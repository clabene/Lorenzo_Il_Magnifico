package cards;

import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import player.Player;
import resources.SetOfResources;

/**
 * Created by IBM on 13/05/2017.
 */
public class CardCost implements interfaces.Losable {

    /*
    * Right now this class can be easily replaced by Expandable objects. It will be worth not deleting if will encapsulate more functionality
    *
    * For example, it might turn useful when checking if a player has enough resources for taking a card from the tower
    *
    * */

    private SetOfResources cost;
    private interfaces.Losable cost2 = null;

    public CardCost(SetOfResources cost){
        this.cost = cost;
    }

    public CardCost(SetOfResources cost, interfaces.Losable cost2){
        this.cost = cost;
        this.cost2 = cost2;
    }

    public SetOfResources getCost() {
        return cost;
    }

    public void setCost(SetOfResources cost) {
        this.cost = cost;
    }

    public interfaces.Losable getCost2(){
        return cost2;
    }


    @Override
    public void lostByPlayer(Player player) throws NegativeResourceQuantityException {
        if(cost2 == null)
            cost.lostByPlayer(player);
        else {
            //todo
            // player selects what cost he prefers to pay
            // lostByPlayer(player) is called on the chosen cost
        }

    }


}
