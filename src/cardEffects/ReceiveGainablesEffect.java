package cardEffects;

import interfaces.Gainable;
import player.Player;

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

    public void activate(Player player){
        player.gain( (Gainable[]) gainables.toArray() );
    }

}