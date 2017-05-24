import player.Player;
import pointsTrack.FaithPointsTrack;
import pointsTrack.LandCardsPointsTrack;
import pointsTrack.MilitaryPointsTrack;
import resources.*;

/**
 * Created by IBM on 09/05/2017.
 */
public class Main {


    public static void main(String args[]){

        Player player = new Player();

        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("vitory "+player.getVictoryPoints());

        player.gain(new SetOfResources(new Money(3), new Stone(7)), new MilitaryPointsTrack(27),
                new FaithPointsTrack(3), new FaithPointsTrack(1), new LandCardsPointsTrack(7),
                new VictoryPoint(21), new Wood(3));

        System.out.println("---------------------------------------------------");


        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("victory "+player.getVictoryPoints());

        player.lose(new Stone(4), new MilitaryPointsTrack(3), new SetOfResources(new Money(1), new Stone(4)),
                new Money(2), new VictoryPoint(11));

        System.out.println("---------------------------------------------------");


        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("victory "+player.getVictoryPoints());


    }

}
