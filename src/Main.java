import javafx.print.PageLayout;
import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.ActivationActionSpaceType;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.cards.LandCard;
import logic.cards.cardEffects.ExchangeGainablesEffect;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.gameStructure.ActionPhase;
import logic.gameStructure.Game;
import logic.gameStructure.PeriodNumber;
import logic.interfaces.Losable;
import logic.player.Plank;
import logic.player.Player;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.Money;
import logic.resources.Slave;
import logic.resources.Wood;

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

        Player p = new Player();
        p.gain(new MilitaryPointsTrack(7));
        System.out.println(p.lose(new MilitaryPointsTrack(10)));

        ArrayList<Plank> planks = new ArrayList<>();
        planks.add(new Plank());
        System.out.println(planks.get(planks.size()-1));
        */

        Board b = new Board();

        Player cla = new Player(new Slave(2), new Money(5));

        cla.setBoard(b);

        cla.getFamilyMembers()[0].incrementFamilyMemberValue(100);
        try {
            cla.tryToTakeCard(new LandCard("ciao", PeriodNumber.FIRST, 3, new ReceiveGainablesEffect(new Slave(2)), new ExchangeGainablesEffect(new Money(2), new Wood(4))));
        } catch (Exception e){}

        System.out.println(cla.getPlank().getSetOfResources());
        System.out.println(cla.getPlank().getCards());

        //TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Wood(2));

        //TowerActionSpace towerActionSpace = (TowerActionSpace) b.getArea(0).getActionSpace(0);
        //towerActionSpace.setCard(new LandCard("ww", PeriodNumber.FIRST, 0, new ReceiveGainablesEffect(new Slave(2)), new ReceiveGainablesEffect(new Wood(2))));

        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);

        ActionPhase a = new ActionPhase();
        a.putFamilyMemberOnActionSpace(cla, cla.getFamilyMembers()[0], activationActionSpace);

        System.out.println(cla.getPlank().getSetOfResources());

    }

}
