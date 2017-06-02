package cards;

import exceptions.NegativePointsException;
import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import player.Player;
import pointsTracks.MilitaryPointsTrack;
import resources.SetOfResources;

/**
 * Created by IBM on 13/05/2017.
 */
public class CardCost implements Losable {

    /*
    * Right now this class can be easily replaced by Expandable objects. It will be worth not deleting if will encapsulate more functionality
    *
    * For example, it might turn useful when checking if a player has enough resources for taking a card from the tower
    *
    * */

    private SetOfResources resourcesCost;

    private MilitaryPointsTrack militaryPointsCost;
    private MilitaryPointsTrack necessaryMilitaryPointsTrack;

    public CardCost(SetOfResources resourcesCost){
        this.resourcesCost = resourcesCost;
    }
    public CardCost(MilitaryPointsTrack militaryPointsCost){
        this.militaryPointsCost = militaryPointsCost;
    }
    public CardCost(SetOfResources resourcesCost, MilitaryPointsTrack militaryPointsCost){
        this.resourcesCost = resourcesCost;
        this.militaryPointsCost = militaryPointsCost;
    }

    public SetOfResources getResourcesCost() {
        return resourcesCost;
    }

    public void setResourcesCost(SetOfResources resourcesCost) {
        this.resourcesCost = resourcesCost;
    }

    public MilitaryPointsTrack getMilitaryPointsCost() {
        return militaryPointsCost;
    }

    @Override
    public void lostByPlayer(Player player) throws NegativeResourceQuantityException, NegativePointsException {
        if(militaryPointsCost == null)
            resourcesCost.lostByPlayer(player);
        if(resourcesCost == null) {
            //todo check if enough points
            militaryPointsCost.lostByPlayer(player);
        }
        else {
            //todo
            // player selects what resourcesCost he prefers to pay
            // lostByPlayer(player) is called on the chosen resourcesCost
        }

    }


}
