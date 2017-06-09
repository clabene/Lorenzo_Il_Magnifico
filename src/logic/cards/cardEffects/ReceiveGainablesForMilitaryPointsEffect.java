package logic.cards.cardEffects;

import logic.player.Player;
import logic.player.VictoryPoint;
import logic.pointsTracks.MilitaryPointsTrack;


/**
 * Created by IBM on 26/05/2017.
 */
public class ReceiveGainablesForMilitaryPointsEffect extends ReceiveGainablesEffect {

    public ReceiveGainablesForMilitaryPointsEffect(){
        super(new VictoryPoint(1));
    }

    @Override
    public void activate(Player player){
        for(int i = player.getMilitaryPoints().getTrackPosition().getValue()/2; i>0; i--)
            super.activate(player);
    }

/*
    public static void main(String[] args){
        Player p = new Player();
        p.gain(new MilitaryPointsTrack(11));
        ReceiveGainablesForMilitaryPointsEffect r = new ReceiveGainablesForMilitaryPointsEffect();
        r.activate(p);
        System.out.println(p.getPlank().getSetOfResources());
        System.out.println(p.getFaithPoints());
    }

*/

}

