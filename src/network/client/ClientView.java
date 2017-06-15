package network.client;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.exceptions.FamilyMemberSelectionException;
import logic.player.FamilyMember;
import logic.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IBM on 11/06/2017.
 */
public class ClientView {

    private ArrayList<Player> players = new ArrayList<>();


    private Board board;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



    public FamilyMember printFamilyMembersAvailable(String id){
        System.out.println(id);

        for(Player tmp: players){
            if(tmp.getId().equals(id)){
                System.out.println(tmp);
                System.out.println(tmp.getFamilyMembersAvailable().toString());
                Scanner scanner = new Scanner(System.in);
                return null;
                //return tmp.getFamilyMembersAvailable().get(scanner.nextInt());
            }
        }
        return null;
    }

    public String printActionSpaces(){
        printBoard();
        Scanner scanner = new Scanner(System.in);
        String key = scanner.next();
        return key;
    }

    public int printSlaves(String id){
        for(Player tmp: players){
            if(tmp.getId().equals(id)){
                System.out.println("You have only "+ tmp.getPlank().getSetOfResources().getQuantityOfSlaves()+"slaves");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            }
        }
        return 0;
    }

    public void printPlayers(){
        String s = "\t\t\t\t\t\t\t\t\t\t\t";
        for(int i = 0; i < players.size(); i++){
            System.out.print("Player_"+i+s);
        }

        for(Player tmp: players){
            tmp.getPlank().getSetOfResources().getResources();
            tmp.getFamilyMembers();
            tmp.getPlank().getCards();
            tmp.getFaithPoints();
            tmp.getMilitaryPoints();
            tmp.getPoints();
            //todo chiedere a claudio com printare

        }

    }

    //todo funzione che printa la mia situazione


    public void printBoard(){

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



    }
}
