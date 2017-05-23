import utility.LimitedInteger;
import utility.StaticVariables;

/**
 * Created by IBM on 14/05/2017.
 */
public class DiceValue extends LimitedInteger {

    /*
    * Inheritance paradigm as used here is not great for 2 reasons. First, DiceValue should not be able to call increment and decrement methods
    * and secondly dice value is now initialized to 0, which is not a value a dice should be able to have.
    *
    * Two possible solutions seem reasonable:
    * Either the utters can be overridden in order to make no operation,
    * or a more general class can be made, with no increment and decrement methods, for both DiceValue and LimitedInteger to extend
    * The second proposed solution would make the code over-complicated, but the first one is bad programming.
    *
    * Also, see Note 5 in file "notes.txt".
    * */

    public DiceValue(){
        super(StaticVariables.MAX_DICE_VALUE, StaticVariables.MIN_DICE_VALUE);
    }

    public int getRandomDiceValue(){
        return 0; //todo
    }



}




