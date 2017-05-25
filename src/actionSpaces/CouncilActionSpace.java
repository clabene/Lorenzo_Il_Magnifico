package actionSpaces;

import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class CouncilActionSpace extends ActionSpace {

    public CouncilActionSpace(int MAX_NUMBER_OF_FAMILIARS){
        super(MAX_NUMBER_OF_FAMILIARS, ActionSpaceType.COUNCIL);

    }

    public boolean action(Player player){
        return true;
    }

}
