package actionSpaces;

import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class ActivationActionSpace extends ActionSpace {

    public ActivationActionSpace(int MAX_NUMBER_OF_FAMILIARS){
        super(MAX_NUMBER_OF_FAMILIARS, ActionSpaceType.ACTIVATION);

    }

    public boolean action(Player player){
        return true;
    }

}
