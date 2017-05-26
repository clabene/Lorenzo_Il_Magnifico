package actionSpaces;

import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class ActivationActionSpace extends ActionSpace {

    private ActivationActionSpaceType activationType;

    public ActivationActionSpace(int MAX_NUMBER_OF_FAMILIARS, ActivationActionSpaceType activationType ){
        super(MAX_NUMBER_OF_FAMILIARS, ActionSpaceType.ACTIVATION, 1, null);
        this.activationType = activationType;

    }

    public boolean action(Player player){
        return true;
    }

}
