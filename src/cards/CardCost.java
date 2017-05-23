package cards;

import interfaces.Losable;

/**
 * Created by IBM on 13/05/2017.
 */
public class CardCost {

    /*
    * Right now this class can be easily replaced by Expandable objects. It will be worth not deleting if will encapsulate more functionality
    *
    * For example, it might turn useful when checking if a player has enough resources for taking a card from the tower
    *
    * */

    private Losable cost;

    public CardCost(Losable cost){
        this.cost = cost;
    }

    public Losable getCost() {
        return cost;
    }


}
