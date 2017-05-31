package cardEffects;

import actionSpaces.ActionSpace;
import actionSpaces.ActivationActionSpace;
import player.FamilyMember;
import player.Player;

import java.util.ArrayList;

/**
 * Created by IBM on 30/05/2017.
 */
public class PlayExtraActionPhaseEffect implements CardEffect{

    private ArrayList<ActionSpace> actionSpaces = new ArrayList<>();
    FamilyMember familyMember;
    int[] towersIndexes = new int[4];

    public PlayExtraActionPhaseEffect( int valueOfFamilyMember, ActivationActionSpace activationActionSpace){
        this.familyMember = new FamilyMember(null, valueOfFamilyMember);
        this.actionSpaces.add(activationActionSpace);
    }

    public PlayExtraActionPhaseEffect(int valueOfFamilyMember, int ... towersIndexes){
        this.familyMember = new FamilyMember(null, valueOfFamilyMember);
        this.towersIndexes = towersIndexes;
    }

    /*
    public PlayExtraActionPhaseEffect(Tower tower, int valueOfFamilyMemeber){
        for(ActionSpace tmp : tower.getSpaces())
            this.actionSpaces.add(tmp);

        this.familyMember = new FamilyMember(null, valueOfFamilyMemeber);
    }

    public PlayExtraActionPhaseEffect(TowerArea towerArea, int valueOfFamilyMemeber){

        for(Tower towerTmp : towerArea.getTowers())
            for( ActionSpace actionSpaceTmp : towerTmp.getSpaces() )
                this.actionSpaces.add(actionSpaceTmp);

        this.familyMember = new FamilyMember(null, valueOfFamilyMemeber);
    }
    */

    @Override
    public void activate(Player player) {
        // controllo se playable (??)
        // selezione spazio azione
        // sacrificio schiavi
        // attivazione bonus
        // familiare messo
        //  false -> restore di board -> repeat
        //  true -> familiare = null;
    }








}
