package logic.resources;

import logic.interfaces.Gainable;
import logic.player.Player;
import logic.utility.StaticVariables;

import java.util.Scanner;

/**
 * Created by IBM on 24/05/2017.
 */
public class CouncilFavour implements Gainable {

    private int numberOfFavours = 1;

    private Gainable[] favours = StaticVariables.COUNCIL_FAVOURS;

    public CouncilFavour(){
    }

    public CouncilFavour(int numberOfFavours){
        if(numberOfFavours >= favours.length) numberOfFavours = favours.length;
        this.numberOfFavours = numberOfFavours;
    }

    public int getNumberOfFavours() {
        return numberOfFavours;
    }


    @Override
    public void gainedByPlayer(Player player){
        player.getPlank().getCouncilFavours().add(this);
    }

    @Override
    public String toString(){
        int i = 0;
        String toReturn = "";
        for(Gainable tmp : favours) {
            i++;
            if(tmp != null)
                toReturn += i + " " + tmp.toString() + "\n";
        }
        return toReturn;
    }





}
