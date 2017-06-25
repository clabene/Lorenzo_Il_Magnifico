package logic.board;


import logic.actionSpaces.*;
import logic.cards.Card;
import logic.cards.LandCard;
import logic.exceptions.ActionSpaceCoveredException;
import logic.exceptions.FamilyMemberSelectionException;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.gameStructure.Game;
import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.*;
import logic.utility.CardSetupHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

/**
 * Created by IBM on 09/05/2017.
 */
public class Board implements Serializable{

    private final int MAX_NUMBER_OF_PLAYERS; //use LimitedInteger

    public Board(int MAX_NUMBER_OF_PLAYERS){
        this.MAX_NUMBER_OF_PLAYERS = MAX_NUMBER_OF_PLAYERS;
        this.setHashMap(MAX_NUMBER_OF_PLAYERS);
        inizializeExcommunicationTassels();
    }

    private int redDice;
    private int whiteDice;
    private int blackDice;

    private ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];

    private HashMap<String, ActionSpace> actionSpaceHashMap = new HashMap<>();

    /**
     * first letter: T = tower ; C = council; A = activation; M = market.
     * second letter: L = land; P = person; B = building; V = venture; P = production; H = harvest.
     *
     */

    private void setHashMap(int numberOfPlayers){

        actionSpaceHashMap.put("TL1", new TowerActionSpace(1) );
        actionSpaceHashMap.put("TL2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TL3", new TowerActionSpace(5, new Wood(1)));
        actionSpaceHashMap.put("TL4", new TowerActionSpace(7, new Wood(2)));
        actionSpaceHashMap.put("TP1", new TowerActionSpace(1));
        actionSpaceHashMap.put("TP2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TP3", new TowerActionSpace(5, new Stone(1)));
        actionSpaceHashMap.put("TP4", new TowerActionSpace(7, new Stone(2)));
        actionSpaceHashMap.put("TB1", new TowerActionSpace(1));
        actionSpaceHashMap.put("TB2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TB3", new TowerActionSpace(5, new MilitaryPointsTrack(1)));
        actionSpaceHashMap.put("TB4", new TowerActionSpace(7, new MilitaryPointsTrack(2)));
        actionSpaceHashMap.put("TV1", new TowerActionSpace(1));
        actionSpaceHashMap.put("TV2", new TowerActionSpace(3));
        actionSpaceHashMap.put("TV3", new TowerActionSpace(5, new Money(1)));
        actionSpaceHashMap.put("TV4", new TowerActionSpace(7, new Money(2)));
        actionSpaceHashMap.put("C", new CouncilActionSpace());
        actionSpaceHashMap.put("AP1", new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION));
        if(numberOfPlayers > 2) actionSpaceHashMap.put("AP2", new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.PRODUCTION ));
        actionSpaceHashMap.put("AH1", new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST));
        if(numberOfPlayers > 2) actionSpaceHashMap.put("AH2", new ActivationActionSpace(Integer.MAX_VALUE, ActivationActionSpaceType.HARVEST ));
        actionSpaceHashMap.put("M1", new MarketActionSpace(new Money(5)));
        actionSpaceHashMap.put("M2", new  MarketActionSpace(new Slave(5)));
        if(numberOfPlayers > 3) actionSpaceHashMap.put("M3", new MarketActionSpace(new Money(2), new MilitaryPointsTrack(3)));
        if(numberOfPlayers > 3) actionSpaceHashMap.put("M4", new MarketActionSpace(new CouncilFavour(2)));
    }

    public ActionSpace tryToSelectActionSpace(String key) throws ActionSpaceCoveredException{
        ActionSpace actionSpace = actionSpaceHashMap.get(key);
        if(actionSpace.getCovered()) throw new ActionSpaceCoveredException();
        return actionSpace;
    }


    public int getMAX_NUMBER_OF_PLAYERS(){
        return MAX_NUMBER_OF_PLAYERS;
    }

    public ArrayList<String> getTurnOrder(){
        CouncilActionSpace a;
        a = (CouncilActionSpace) actionSpaceHashMap.get("C");
        return a.getFamilyMemberArrayList();
    }

    public HashMap<String, ActionSpace> getHashMap(){
        return actionSpaceHashMap;
    }

    public ActionSpace getActionSpaceFromId(String actionSpaceId) {
        return actionSpaceHashMap.get(actionSpaceId);
    }

    public ExcommunicationTassel getTasselFromIndex(int i){
        return tassels[i];
    }


    public void setCardsOnBoard(Stack<Card> deck){
        ((TowerActionSpace)this.getHashMap().get("TL1")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TL2")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TL3")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TL4")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TP1")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TP2")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TP3")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TP4")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TB1")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TB2")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TB3")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TB4")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TV1")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TV2")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TV3")).setCard(deck.pop());
        ((TowerActionSpace)this.getHashMap().get("TV4")).setCard(deck.pop());


        /*
        int i = 0;
        for(ActionSpace tmp: actionSpaceHashMap.values()){




            ((TowerActionSpace) tmp).setCard(deck.pop());
            i++;
            if(i == 16)  break;
        }*/
    }

    private void inizializeExcommunicationTassels(){
        CardSetupHandler cartSetupHandler = new CardSetupHandler();
        this.tassels = cartSetupHandler.tesselExcomunication();

    }


    public ExcommunicationTassel[] getTassels() {
        return tassels;
    }

    public void setDice(){
        Random randomGenerator = new Random();
        redDice = randomGenerator.nextInt(6)+1;
        whiteDice = randomGenerator.nextInt(6)+1;
        blackDice = randomGenerator.nextInt(6)+1;
    }

    public int getBlackDice() {
        return blackDice;
    }

    public int getRedDice() {
        return redDice;
    }

    public int getWhiteDice() {
        return whiteDice;
    }
/*
    public static void main(String[] args) {

            Board board = new Board(4);
            ArrayList<Player> players = new ArrayList<>();
            CardSetupHandler cartSetupHandler = new CardSetupHandler();
            Stack<Card> deck = new Stack<>();
            deck =  cartSetupHandler.prova();
            //board.setCardsOnBoard(deck);
            //LandCard land = (LandCard) deck.pop();
            //((TowerActionSpace)board.getHashMap().get("TL1")).setCard(land);

            board.setCardsOnBoard(deck);


            players.add(new Player(new Wood()));
            players.add(new Player(new Wood(3)));
            players.add(new Player(new Wood(5)));

            FamilyMember familyMember = new FamilyMember(Color.BLACK, 3);

                //players.get(0).tryToSelectFamilyMember(familyMember);
                board.getHashMap().get("C").familyMemberAdded(familyMember);


            for (ActionSpace tmp:
                    board.getHashMap().values()) {
                System.out.println(tmp+ "\t");
            }

        String s = "\t\t\t\t\t\t\t\t\t\t\t";
        String yellow = "\u001B[33m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        String green = "\u001B[32m";
        String end = "\u001B[0m";
        System.out.println(red);
        System.out.println("LAND TOWER"+s+"PERSON TOWER"+s+"BUILDING TOWER"+s+"VENTURE TOWER");
        System.out.println(end);
        System.out.println(yellow);
        System.out.println("1) Tower_1"+s+"2)Tower_1"+s+"3)Tower_1"+s+"4)Tower_1");
        System.out.println(end);
        System.out.println(blue);
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getName()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getCardCost()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getActivationValue()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getImmediateEffect()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getPermanentEffect()
        );
        System.out.println(end);
        System.out.println(green);
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL4")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TP4")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TB4")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TV4")).getLastFamilyMemberAdded()
        );
        System.out.println(end);
        System.out.println(yellow);
        System.out.println("5) Tower_2"+s+"6)Tower_2"+s+"7)Tower_2"+s+"8)Tower_2");
        System.out.println(end);
        System.out.println(blue);
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getName()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getCardCost()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getActivationValue()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getImmediateEffect()
        );
        ;
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getPermanentEffect()
        );
        System.out.println(end);
        System.out.println(green);
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL3")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TP3")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TB3")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TV3")).getLastFamilyMemberAdded()
        );
        System.out.println(end);
        System.out.println("9) Tower_3"+s+"10)Tower_3"+s+"11)Tower_3"+s+"12)Tower_3");
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getName()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getCardCost()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getActivationValue()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getImmediateEffect()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getPermanentEffect()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL2")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TP2")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TB2")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TV2")).getLastFamilyMemberAdded()
        );

        System.out.println("13) Tower_4"+s+"14)Tower_4"+s+"15)Tower_4"+s+"16)Tower_4");
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getName()+s+
                ((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getName()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getCardCost()+s+
                ((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getCardCost()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getActivationValue()+s+
                ((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getActivationValue()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getImmediateEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getImmediateEffect()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getPermanentEffect()+s+
                ((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getPermanentEffect()
        );
        System.out.println(((TowerActionSpace)board.getHashMap().get("TL1")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TP1")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TB1")).getLastFamilyMemberAdded()+s+
                ((TowerActionSpace)board.getHashMap().get("TV1")).getLastFamilyMemberAdded()
        );

        //----------------------------------fine torri----------------------------------

        System.out.println("17)COUNCIL");
        System.out.println(board.getHashMap().get("C").toString());
        System.out.println(board.getHashMap().get("C").getMinValueToPlaceFamiliar());


        //----------------fine consiglio

        System.out.println("MARKET");
        System.out.println("18)Space_1"+s+"19)Space_2"+s+"Space_3"+s+"Space_4");
        System.out.println(board.getHashMap().get("M1").getBonus()+s+
                board.getHashMap().get("M2").getBonus()+s+
                board.getHashMap().get("M3").getBonus()+s+
                board.getHashMap().get("M4").getBonus()
        );
        System.out.println(board.getHashMap().get("M1").getLastFamilyMemberAdded()+s+
                board.getHashMap().get("M2").getLastFamilyMemberAdded()+s+
                board.getHashMap().get("M3").getLastFamilyMemberAdded()+s+
                board.getHashMap().get("M4").getLastFamilyMemberAdded()
        );
        System.out.println(board.getHashMap().get("M1").getMinValueToPlaceFamiliar()+s+
                board.getHashMap().get("M2").getMinValueToPlaceFamiliar()+s+
                board.getHashMap().get("M3").getMinValueToPlaceFamiliar()+s+
                board.getHashMap().get("M4").getMinValueToPlaceFamiliar()
        );
        //------------------------fine market



        System.out.println("PRODUCTION_1"+s+s+"HARVEST_1");
        System.out.println(board.getHashMap().get("AP1").getMinValueToPlaceFamiliar()+s+s+board.getHashMap().get("AH1").getMinValueToPlaceFamiliar());
        System.out.println(board.getHashMap().get("AP1").getLastFamilyMemberAdded()+s+s+board.getHashMap().get("AH1").getLastFamilyMemberAdded());


        //------------------------------------------fine 1 place

        System.out.println("PRODUCTION_2");
        System.out.println(board.getHashMap().get("AP2").getMinValueToPlaceFamiliar());
        System.out.println(board.getHashMap().get("AP2").getFamilyMembers());

        System.out.println("HARVEST_2");
        System.out.println(board.getHashMap().get("AH2").getMinValueToPlaceFamiliar());
        System.out.println(board.getHashMap().get("AH2").getFamilyMembers());





    }*/
}
