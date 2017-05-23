import interfaces.Losable;
import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 23/05/2017.
 */
public class VictoryPoint implements Gainable, Losable {

    private int victoryPoints = 1;

    public VictoryPoint(int numberOfPoints){
        this.victoryPoints = numberOfPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    @Override
    public void gainedByPlayer(Player player) {
        player.addVictoryPoints(getVictoryPoints());
    }

    @Override
    public void lostByPlayer(Player player) {
        player.loseVictoryPoints(getVictoryPoints());
    }

}
