package actionSpaces;

import exceptions.LimitedValueOffRangeException;
import interfaces.Gainable;
import player.Player;
import utility.LimitedInteger;

import java.util.ArrayList;

/**
 * Created by IBM on 18/05/2017.
 **/
public abstract class ActionSpace {

    private ArrayList<Gainable> bonus = new ArrayList<>();

    private ActionSpaceType actionSpaceType; //might wanna delete this attribute

    //Some action spaces are only available when enough players are playing the game
    //Note that MAX_NUMBER_OF_FAMILIARS = 0 is not a better solution than this boolean. Indeed, M_N_O_F is a final value
    //and therefore not changeable, while the number of players might have to be allowed to dynamically change throughout the game
    //(which causes some action spaces to be covered and uncovered more times during the game, not just at the beginning)
    private boolean covered = false;

    private int minValueToPlaceFamiliar;
    private final int MAX_NUMBER_OF_FAMILIARS; //= (int) Double.POSITIVE_INFINITY; if no limit
    private LimitedInteger numberOfFamiliars;

    public ActionSpace(int MAX_NUMBER_OF_FAMILIARS, ActionSpaceType actionSpaceType, int minValueToPlaceFamiliar, Gainable ... bonuses){
        this.MAX_NUMBER_OF_FAMILIARS = MAX_NUMBER_OF_FAMILIARS;
        initializeNumberOfFamiliars();
        this.minValueToPlaceFamiliar = minValueToPlaceFamiliar;

        for(Gainable tmp : bonuses)
            this.bonus.add(tmp);

        this.actionSpaceType = actionSpaceType;
    }


    private void initializeNumberOfFamiliars(){
        try{
            numberOfFamiliars = new LimitedInteger(MAX_NUMBER_OF_FAMILIARS, 0, 0);
        } catch (LimitedValueOffRangeException e){
            System.out.println("Can not put your familiar here, this action space is full already");
        }
    }

    /*
        * New familiar is added to the action space
        *
        * Returns Gainable object "bonus" that current player will have to gain. It was avoided this way to put a Player object
        * in this method, which would not have been very fancy.
        *
        * */
    public Gainable[] familiarAdded(){
        try{
            numberOfFamiliars.increment();
        } catch (LimitedValueOffRangeException e){
            System.out.println("Cannot put your familiar here, this action space is full already");
        }

        return (Gainable[]) bonus.toArray();
    }


    //this method is the one to call when the action space is activated
    //action will be so implemented by subclasses:
    // tower -> (try to) take card
    // market -> null (??* check bonus2 attribute)
    // activation -> do the activation
    // council -> set order for next turn
    //returns true if familiar was correctly added
    public abstract boolean action(Player player);

}
