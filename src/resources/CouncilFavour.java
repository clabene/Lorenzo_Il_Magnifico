package resources;

import interfaces.Gainable;
import player.Player;
import utility.StaticVariables;

import java.util.ArrayList;

/**
 * Created by IBM on 24/05/2017.
 */
public class CouncilFavour implements Gainable {

    private int numberOfFavours = 1;

    private ArrayList<Gainable> favours = new ArrayList<>();

    public CouncilFavour(int numberOfFavours){
        for(Gainable tmp : StaticVariables.COUNCIL_FAVOURS)
            favours.add(tmp);
        if(numberOfFavours >= favours.size()) numberOfFavours = favours.size();
        this.numberOfFavours = numberOfFavours;
    }

    @Override
    public void gainedByPlayer(Player player){

        while(numberOfFavours > 0){
            //favours are printed
            //player selects favour
            //favour gained by player (calling gain??)
            //remove favour from ArrayList
        }

    }





}
