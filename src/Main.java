import player.Plank;
import player.Player;
import pointsTracks.MilitaryPointsTrack;

import java.util.ArrayList;

/**
 * Created by IBM on 09/05/2017.
 */
public class Main {


    public static void main(String args[]){
        /*
        Player player = new Player();

        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("vitory "+player.getQuantity());

        player.gain(new Losable(new Money(3), new Stone(7)), new MilitaryPointsTrack(27),
                new FaithPointsTrack(3), new FaithPointsTrack(1), new LandCardsPointsTrack(7),
                new VictoryPoint(21), new Wood(3));

        System.out.println("---------------------------------------------------");


        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("victory "+player.getQuantity());

        player.lose(new Stone(4), new MilitaryPointsTrack(3), new Losable(new Money(1), new Stone(4)),
                new Money(2), new VictoryPoint(31));

        System.out.println("---------------------------------------------------");


        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("victory "+player.getQuantity());
        */
        /*
        Gainable g = new FaithPointsTrack(2);
        System.out.println(g instanceof Stone);
        System.out.println(g instanceof Wood);
        System.out.println(g instanceof Resource);
        System.out.println(g instanceof PointsTrack);
        System.out.println(g instanceof FaithPointsTrack);
        System.out.println(g instanceof MilitaryPointsTrack);

        ActionSpaceType a = ActionSpaceType.ACTIVATION;
        System.out.println(ActionSpaceType.ACTIVATION == a);
        */
        /*
        Plank p = new Plank();
        Plank p2 = p;
        System.out.println(p);
        System.out.println(p2);
        p = new Plank();
        System.out.println(p);
        System.out.println(p2);
        */
        /*
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3; j++){
                if(j == 2) continue;
                System.out.println("a");
            }
        }
        */
        Player p = new Player();
        p.gain(new MilitaryPointsTrack(7));
        System.out.println(p.lose(new MilitaryPointsTrack(10)));

        ArrayList<Plank> planks = new ArrayList<>();
        planks.add(new Plank());
        System.out.println(planks.get(planks.size()-1));

    }

}
