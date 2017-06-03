package logic.actionSpaces;

import logic.player.Player;
import logic.resources.CouncilFavour;
import logic.resources.Money;

import java.util.ArrayList;

/**
 * Created by IBM on 19/05/2017.
 */
public class CouncilActionSpace extends ActionSpace {
    ArrayList<String> familyMemberArrayList = new ArrayList<>();

    public CouncilActionSpace(){
        super( (int) Double.POSITIVE_INFINITY, ActionSpaceType.COUNCIL, 1, new Money(1), new CouncilFavour(1));

    }

    public boolean action(Player player){
        if(!familyMemberArrayList.contains(player.getId()))
            familyMemberArrayList.add(player.getId());

        return true;
    }

    public ArrayList<String> getFamilyMemberArrayList(){
        return this.familyMemberArrayList;
    }



}
