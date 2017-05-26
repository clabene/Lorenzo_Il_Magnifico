package cardEffects;

import interfaces.Gainable;
import player.Player;

import java.util.ArrayList;

/**
 * Created by IBM on 26/05/2017.
 */
public class ReceiveGainablesForMilitaryPointsEffect implements CardEffect {

    private ArrayList<Gainable> gainables = new ArrayList<>();

    public ReceiveGainablesForMilitaryPointsEffect(Gainable ... gainables){
        for(Gainable tmp : gainables)
            this.gainables.add(tmp);
    }

    public void activate(Player player){
        for(int i = player.getMilitaryPoints().getTrackPosition().getValue()/2; i>=0; i--)
            player.gain( (Gainable[]) gainables.toArray());
    }


}

