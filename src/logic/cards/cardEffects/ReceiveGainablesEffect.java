package logic.cards.cardEffects;

import logic.interfaces.Gainable;
import logic.player.Player;
import logic.pointsTracks.FaithPointsTrack;
import logic.resources.Wood;

import java.util.ArrayList;

/**
 * Created by IBM on 26/05/2017.
 */
public class ReceiveGainablesEffect implements CardEffect {

    private ArrayList<Gainable> gainables = new ArrayList<>();

    public ReceiveGainablesEffect(Gainable ... gainables){
        for(Gainable tmp : gainables)
            this.gainables.add(tmp);
    }

    @Override
    public void activate(Player player){
        player.gain(gainables.toArray(new Gainable[gainables.size()]));
    }

    @Override
    public String toString() {
        return "receive gainables";
    }

    /*
    public static void main(String[] a){
        Player p = new Player("");
        ReceiveGainablesEffect r = new ReceiveGainablesEffect(new FaithPointsTrack(2), new Wood(3));
        r.activate(p);
        System.out.println(p.getFaithPoints());
        System.out.println(p.getPlank().getSetOfResources());
    }
*/
}