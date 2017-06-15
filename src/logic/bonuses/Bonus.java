package logic.bonuses;

import logic.actionSpaces.ActionSpace;

import java.io.Serializable;

/**
 * Created by IBM on 30/05/2017.
 */
public interface Bonus extends Serializable{

    /*
    * this interface tags those classes that represent a bonus effect. This are given by person cards
    *
    * This bonuses are given to the player, which has a List of Bonus
    *
    * Right before the placement of the family member in the action spaces all of the player's bonus are activated,
    * see ActionPhase class
    *
    * */

    void activateBonus(ActionSpace actionSpace);

}
