package player;

import interfaces.Losable;
import interfaces.Gainable;

/**
 * Created by IBM on 23/05/2017.
 */
public class VictoryPoint implements Gainable, Losable {

    private int victoryPoints = 1;

    public VictoryPoint(int numberOfPoints){
        this.victoryPoints = numberOfPoints;
    }


    public int getQuantity() {
        return victoryPoints;
    }

    public void setQuantity(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void addVictoryPoints(int quantity){
        this.victoryPoints += quantity;
    }
    public void loseVictoryPoints(int quantity){
        this.victoryPoints -= quantity;
    }

    @Override
    public void gainedByPlayer(Player player) {
        player.getPoints().addVictoryPoints(this.getQuantity());
    }


    /*
    * Player can have negative victory points: no exceptions concerning negative victory points are thrown
    * */
    @Override
    public void lostByPlayer(Player player){
        player.getPoints().loseVictoryPoints(this.getQuantity());
    }

}
