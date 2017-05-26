package actionSpaces;

import player.Player;
import resources.CouncilFavour;
import resources.Money;

/**
 * Created by IBM on 19/05/2017.
 */
public class CouncilActionSpace extends ActionSpace {

    public CouncilActionSpace(){
        super( (int) Double.POSITIVE_INFINITY, ActionSpaceType.COUNCIL, 1, new Money(1), new CouncilFavour(1));

    }

    public boolean action(Player player){
        return true;
    }

}
