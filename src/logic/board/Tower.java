package logic.board;

import logic.actionSpaces.TowerActionSpace;
import logic.player.FamilyMember;

import java.util.ArrayList;

/**
 * Created by Pinos on 30/05/2017.
 */
public class Tower {

    TowerActionSpace[] spaces;

    public Tower (TowerActionSpace[] spaces){
        this.spaces = spaces;
    }

    public TowerActionSpace[] getSpaces(){
        return this.spaces;
    }

    public ArrayList<FamilyMember> getFamilyMembers(){
        ArrayList<FamilyMember> familyMembers = new ArrayList<>();
        for(TowerActionSpace tmp: this.spaces){
            if(tmp.getCovered())
                familyMembers.add(tmp.getLastFamilyMemberAdded());
        }
        return familyMembers;
    }

}
