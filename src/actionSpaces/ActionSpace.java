package actionSpaces;

import exceptions.LimitedValueOffRangeException;
import interfaces.Gainable;
import utility.LimitedInteger;

    /**
     * Created by IBM on 18/05/2017.
     */
    public abstract class ActionSpace {

        private int activationCost;

        private Gainable bonus;
        private Gainable bonus2 = null; //second council favour in market (??*)
        //Note that might just wanna have a collection of type Gainable

    private ActionSpaceType actionSpaceType; //might wanna delete this attribute

    //Some action spaces are only available when enough players are playing the game
    //Note that MAX_NUMBER_OF_FAMILIARS = 0 is not a better solution than this boolean. Indeed, M_N_O_F is a final value
    //and therefore not changeable, while the number of players might have to be allowed to dynamically change throughout the game
    //(which causes some action spaces to be covered and uncovered more times during the game, not just at the beginning)
    private boolean covered = false;

    private int minValueToPlaceFamiliar;
    private final int MAX_NUMBER_OF_FAMILIARS;
    private LimitedInteger numberOfFamiliars;


    //todo complete this
    public ActionSpace(int MAX_NUMBER_OF_FAMILIARS, ActionSpaceType actionSpaceType){
        this.MAX_NUMBER_OF_FAMILIARS = MAX_NUMBER_OF_FAMILIARS;
        initializeNumberOfFamiliars();

        this.actionSpaceType = actionSpaceType;
    }

    private void initializeNumberOfFamiliars(){
        try{
            numberOfFamiliars = new LimitedInteger(MAX_NUMBER_OF_FAMILIARS, 0, 0);
        } catch (LimitedValueOffRangeException e){
            System.out.println("Can not put your familiar here, this action space is full already");
        }
    }



    public void familiarAdded(){
        try{
            numberOfFamiliars.increment();
        } catch (LimitedValueOffRangeException e){
            System.out.println("Cannot put your familiar here, this action space is full already");
        }
    }

    //this method is the one to call when the action space is activated
    //action will be so implemented by subclasses:
    // tower -> (try to) take card
    // market -> null (??* check bonus2 attribute)
    // activation -> do the activation
    // council -> set order for next turn
    public abstract void action();



}
