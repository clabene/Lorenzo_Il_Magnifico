package actionSpaces;

import player.Player;

/**
 * Created by IBM on 19/05/2017.
 */
public class TowerActionSpace extends ActionSpace {

    public TowerActionSpace(){
        super(1, ActionSpaceType.TOWER);

    }

    public boolean action(Player player){
        return true;
    }

}
