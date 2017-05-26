package resources;

import interfaces.Gainable;
import player.Player;
import utility.StaticVariables;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IBM on 24/05/2017.
 */
public class CouncilFavour implements Gainable {

    private int numberOfFavours = 1;

    private Gainable[] favours = StaticVariables.COUNCIL_FAVOURS;

    public CouncilFavour(int numberOfFavours){
        if(numberOfFavours >= favours.length) numberOfFavours = favours.length;
        this.numberOfFavours = numberOfFavours;
    }

    @Override
    public void gainedByPlayer(Player player){

        Scanner input = new Scanner(System.in);

        while(numberOfFavours > 0){
            //favours are printed
            System.out.println(toString());

            //player selects favour
            int chosenIndex = input.nextInt()-1; //todo controls are needed here

            if(favours[chosenIndex] == null){
                System.out.println("Not valid input");
                continue;
            }

            //favour gained by player (calling gain??)
            player.gain(favours[chosenIndex]);

            //remove favour from ArrayList
            favours[chosenIndex] = null;

            numberOfFavours--;
        }

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
