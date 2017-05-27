package cardEffects;

import interfaces.Gainable;
import player.Player;
import pointsTrack.FaithPointsTrack;
import pointsTrack.MilitaryPointsTrack;
import resources.SetOfResources;
import resources.Slave;
import resources.Wood;


/**
 * Created by IBM on 26/05/2017.
 */
public class ReceiveGainablesForMilitaryPointsEffect extends ReceiveGainablesEffect {

    public ReceiveGainablesForMilitaryPointsEffect(Gainable ... gainables){
        super(gainables);
    }

    @Override
    public void activate(Player player){
        for(int i = player.getMilitaryPoints().getTrackPosition().getValue()/2; i>0; i--)
            super.activate(player);
    }


    public static void main(String[] args){
        Player p = new Player();
        p.gain(new MilitaryPointsTrack(11));
        ReceiveGainablesForMilitaryPointsEffect r = new ReceiveGainablesForMilitaryPointsEffect(new SetOfResources(new Wood(2), new Slave(3)), new FaithPointsTrack(2));
        r.activate(p);
        System.out.println(p.getPlank().getSetOfResources());
        System.out.println(p.getFaithPoints());
    }



}

