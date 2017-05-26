package utility;

import actionSpaces.TowerActionSpace;
import interfaces.Gainable;
import pointsTrack.FaithPointsTrack;
import pointsTrack.MilitaryPointsTrack;
import resources.*;

/**
 * Created by IBM on 16/05/2017.
 */
public class StaticVariables {

    public static final int MIN_DICE_VALUE = 1; //smallest number on a dice face
    public static final int MAX_DICE_VALUE = 6; //greatest number on a dice face

    public static final int[] FAITH_TRACK_VICTORY_POINTS = { 0, 1, 2, 3, 4, 5, 7, 9, 11, 13, 15, 17, 19, 22, 25, 30 };
    public static final int[] PERSON_CARDS_VICTORY_POINTS = { 0, 1, 3, 6, 10, 15, 21 };
    public static final int[] LAND_CARDS_VICTORY_POINTS = { 0, 0, 0, 1, 4, 10, 20 };
    public static final int MILITARY_TRACK_POSITIONS = 30;

    public static final int FIRST_PLACE_MILITARY_BONUS = 5; //player with the most military points gets 5 victory points at the end of the game
    public static final int SECOND_PLACE_MILITARY_BONUS = 2; //player with the second most military points gets 2 victory points at the end of the game

    public static final int MAX_NUMBER_OF_CARDS_PER_TYPE_ON_PLANK = 6; //mas number of cards of the same type a player can have on their plank

    public static final Gainable[] COUNCIL_FAVOURS = {new SetOfResources(new Wood(), new Stone()),
            new Slave(2), new Money(2), new MilitaryPointsTrack(2), new FaithPointsTrack(1)};

    public static final TowerActionSpace[] TOWER_ACTION_SPACES = {new TowerActionSpace (7, new Wood(2)),
                                                                      new TowerActionSpace(7, new Stone(2)),
                                                                      new TowerActionSpace(7, new MilitaryPointsTrack(2)),
                                                                      new TowerActionSpace(7, new Money(2)),
                                                                      new TowerActionSpace (5, new Wood(1)),
                                                                      new TowerActionSpace(5, new Stone(1)),
                                                                      new TowerActionSpace(5, new MilitaryPointsTrack(1)),
                                                                      new TowerActionSpace(5, new Money(1)),
                                                                      new TowerActionSpace (3),
                                                                      new TowerActionSpace(3),
                                                                      new TowerActionSpace(3),
                                                                      new TowerActionSpace(3),
                                                                      new TowerActionSpace (1),
                                                                      new TowerActionSpace(1),
                                                                      new TowerActionSpace(1),
                                                                      new TowerActionSpace(1)};


    /*
    //max value requested to activate a land card or a building card when a family member is on the relative action spaces
    //Note that this is not necessarily the same as dice max value. Indeed, slaves can be used to increase a family member activation power
    public static final int MAX_ACTIVATION_VALUE= 6;
    public static final int MIN_ACTIVATION_VALUE= 1; //min value requested to activate a land card or a building card when a family member is on the relative action spaces
    */

    public static final int NUMBER_OF_CARD_TYPES = 4; //land, building, person, venture

}
