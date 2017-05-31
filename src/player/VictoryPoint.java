package player;

import exceptions.NegativePointsException;
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
    @Override
    public void lostByPlayer(Player player) throws NegativePointsException{
        player.getPoints().loseVictoryPoints(this.getQuantity());
    }

}
