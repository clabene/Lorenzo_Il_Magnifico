package network.client;

import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.player.FamilyMember;
import logic.player.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by IBM on 11/06/2017.
 */
public class ClientView implements Serializable{

    private Collection<Player> players = new ArrayList<>();

    private Board board ;

    public Collection<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



    public FamilyMember printFamilyMembersAvailable(String id){

        for(Player tmp: players){
            if(tmp.getId().equals(id)){
                System.out.println(tmp);
                System.out.println(tmp.getFamilyMembersAvailable().toString());
                Scanner scanner = new Scanner(System.in);
                //return null;
                return tmp.getFamilyMembersAvailable().get(scanner.nextInt());
            }
        }
        return null;
    }

    public String printActionSpaces(){
        printBoard();
        System.out.println("Insert the KEY of the Action space that you want to select");
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

    //todo funzione che printa la mia situazione


    private void printBoard(){
        String s = "                                                                                                               ";
        String purple = "\u001B[35m";
        String yellow = "\u001B[33m";
        String cyan = ("\u001B[36m");
        String blue = "\u001B[34m";
        String green = "\u001B[32m";
        String end = "\u001B[0m";
        String red = "\u001B[31m";
        int k = 25;
        int g = 20;
        String a = cyan+"* ";


        String h = "--------------------------------------------------";
        String j = "***********************************************";
        System.out.println(cyan);
        System.out.println(j+j+j+j);
        System.out.println(a+("LAND TOWER"+s).substring(0,k)+("PERSON TOWER"+s).substring(0,k)+
                ("BUILDING TOWER"+s).substring(0,k)+("VENTURE TOWER"+s).substring(0,k)
                +("YOU"+s).substring(0,k)+("MARKET"+s).substring(0,k)
        );

        System.out.println(cyan+j+j+j+j+end);
        System.out.print(a+green+("TL4)"+((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getName()+s).substring(0,k)+a+
                blue+("TP4)"+((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getName()+s).substring(0,k)+a+
                yellow+("TB4)"+((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getName()+s).substring(0,k)+a+
                purple+("TV4)"+((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getName()+s).substring(0,k)+a+a+
                red+("M1)Space_1"+s).substring(0,g)+a+a+end+("AP1)PRODUCTION_1"+s).substring(0,g)+a+a+yellow+("C)COUNCIL"+s).substring(0,g)+a
        );
        /*
        for (Player tmp: players)
            System.out.print(("Money: "+tmp.getPlank().getSetOfResources().getResources()[0]+s).substring(0,k));*/

        System.out.print("\n");

        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getCardCost()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getCardCost()+s).substring(0,k)+end+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getCardCost()+s).substring(0,k)+end+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getCardCost()+s).substring(0,k)+a+a+
                red+(board.getHashMap().get("M1").getBonus()+s).substring(0,g)+a+a+end+(board.getHashMap().get("AP1").getMinValueToPlaceFamiliar()+s).substring(0,g)+a+a+
                yellow+("Gain: Council Favour"+s+s).substring(0,g)+a
        );
        /*for (Player tmp: players)
            System.out.print(("Stone: "+tmp.getPlank().getSetOfResources().getResources()[1]+s).substring(0,k));*/
        System.out.print("\n");
        System.out.print(a+green+("Act val: "+((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getActivationValue()+s).substring(0,k)+a+
                blue+("Act val: "+((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getActivationValue()+s).substring(0,k)+a+
                yellow+("Act val: "+((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getActivationValue()+s).substring(0,k)+a+
                purple+("Act val: "+((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getActivationValue()+s).substring(0,k)+a+a+
                red+(board.getHashMap().get("M1").getLastFamilyMemberAdded()+s).substring(0,g)+a+a+end+(board.getHashMap().get("AP1").getLastFamilyMemberAdded()+s).substring(0,g)+a+a+
                yellow+(board.getHashMap().get("C").getMinValueToPlaceFamiliar()+s).substring(0,g)+a
        );
        /*
        for (Player tmp: players)
            System.out.print(("Wood: "+tmp.getPlank().getSetOfResources().getResources()[2]+s).substring(0,k));*/
        System.out.print("\n");
        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getImmediateEffect()+s).substring(0,k)+a+a+
                red+(board.getHashMap().get("M1").getMinValueToPlaceFamiliar()+s).substring(0,g)+a+a+
                (s+s).substring(0,g)+a+a+ yellow+("Num FM: "+board.getHashMap().get("C").getFamilyMembers().size()+s).substring(0,g)+a



        );
        /*
        for (Player tmp: players)
            System.out.print(("Slaves: "+tmp.getPlank().getSetOfResources().getResources()[3]+s).substring(0,k));*/
        System.out.print("\n");
        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL4")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP4")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB4")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV4")).getCard().getPermanentEffect()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a+a+(s+s).substring(0,g)+a
        );
        /*
        for (Player tmp: players)
            System.out.print(("Council Favour: "+tmp.getPlank().getCouncilFavours().size()+s).substring(0,k));*/
        System.out.print("\n");

        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL4")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP4")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB4")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV4")).getLastFamilyMemberAdded()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a+a+(s+s).substring(0,g)+a
        );
        /*
        for (Player tmp: players)
            System.out.print(("FP: "+tmp.getFaithPoints().getTrackPosition().getValue()+s).substring(0,k));*/
        System.out.print("\n");
        System.out.println(cyan+j+j+j+j+end);
        //todo System.out.println(red+h+h+a+end);
        System.out.print(a+green+("TL3)"+((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getName()+s).substring(0,k)+a+
                blue+("TP3)"+((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getName()+s+s).substring(0,k)+a+
                yellow+("TB3)"+((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getName()+s+s).substring(0,k)+a+
                purple+("TV3)"+((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getName()+s).substring(0,k)+a+a+
                red+("M2)Space_2"+s).substring(0,g)+a+a+end+("AH1)HARVEST_1"+s).substring(0,g)+a
        );
        /*
        for (Player tmp: players)
            System.out.print(("VP: "+tmp.getPoints().getQuantity()+s).substring(0,k));*/
        System.out.print("\n");
        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getCardCost()+s+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getCardCost()+s+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getCardCost()+s+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getCardCost()+s).substring(0,k)+a+a+
                red+(board.getHashMap().get("M2").getBonus()+s).substring(0,g)+a+a+end+(board.getHashMap().get("AH1").getMinValueToPlaceFamiliar()+s).substring(0,g)
        );
        /*
        for (Player tmp: players)
            System.out.print(("MP: "+tmp.getMilitaryPoints().getTrackPosition().getValue()+s).substring(0,k));*/
        System.out.print("\n");

        System.out.println(a+green+("Act val: "+((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                blue+("Act val: "+((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                yellow+("Act val: "+((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                purple+("Act val: "+((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getActivationValue()+s).substring(0,k)+a+a+
                red+(board.getHashMap().get("M2").getLastFamilyMemberAdded()+s).substring(0,g)+a+a+end+(board.getHashMap().get("AH1").getLastFamilyMemberAdded()+s).substring(0,g)
        );
        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getImmediateEffect()+s).substring(0,k)+a+a+
                red+(board.getHashMap().get("M2").getMinValueToPlaceFamiliar()+s).substring(0,g)+a
        );
        ;
        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL3")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP3")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB3")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV3")).getCard().getPermanentEffect()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a
        );

        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL3")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP3")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB3")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV3")).getLastFamilyMemberAdded()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a
        );
        System.out.println(cyan+j+j+j+j+end);
        //todo System.out.println(red+h+h+a+end);
        System.out.print(a+green+("TL2)"+((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getName()+s+s).substring(0,k)+a+
                blue+("TP2)"+((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getName()+s+s).substring(0,k)+a+
                yellow+("TB2)"+((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getName()+s+s).substring(0,k)+a+
                purple+("TV2)"+((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getName()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+("M3)Space_3"+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);

        if(board.getMAX_NUMBER_OF_PLAYERS() >2)
            System.out.print(end+("AP2)PRODUCTION_2"+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");

        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getCardCost()+s+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getCardCost()+s+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getCardCost()+s+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getCardCost()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+(board.getHashMap().get("M3").getBonus()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        if(board.getMAX_NUMBER_OF_PLAYERS() >2)
            System.out.print(end+(board.getHashMap().get("AP2").getMinValueToPlaceFamiliar()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);

        System.out.print("\n");
        System.out.print(a+green+("Act val: "+((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                blue+("Act val: "+((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                yellow+("Act val: "+((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                purple+("Act val: "+((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getActivationValue()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+(board.getHashMap().get("M3").getLastFamilyMemberAdded()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        if(board.getMAX_NUMBER_OF_PLAYERS() >2)
            System.out.print(end+(board.getHashMap().get("AP2").getFamilyMembers()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");
        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getImmediateEffect()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+(board.getHashMap().get("M3").getMinValueToPlaceFamiliar()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");
        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL2")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP2")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB2")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV2")).getCard().getPermanentEffect()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a+end+(s+s).substring(0,g)+a
        );
        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL2")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP2")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB2")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV2")).getLastFamilyMemberAdded()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a+end+(s+s).substring(0,g)+a
        );
        System.out.println(cyan+j+j+j+j+end);
        //todo System.out.println(red+h+h+a+end);
        System.out.print(a+green+("TL1)"+((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getName()+s+s).substring(0,k)+a+
                blue+("TP1)"+((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getName()+s+s).substring(0,k)+a+
                yellow+("TB1)"+((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getName()+s+s).substring(0,k)+a+
                purple+("TL1)"+((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getName()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+("M4)Space_4"+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        if(board.getMAX_NUMBER_OF_PLAYERS() >2)
            System.out.print(end+("AH2)HARVEST_2"+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");
        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getCardCost()+s+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getCardCost()+s+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getCardCost()+s+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getCardCost()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+(board.getHashMap().get("M4").getBonus()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        if(board.getMAX_NUMBER_OF_PLAYERS() >2)
            System.out.print(end+(board.getHashMap().get("AH2").getMinValueToPlaceFamiliar()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");
        System.out.print(a+green+("Act val: "+((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                blue+("Act val: "+((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                yellow+("Act val: "+((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getActivationValue()+s+s).substring(0,k)+a+
                purple+("Act val: "+((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getActivationValue()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+(board.getHashMap().get("M4").getLastFamilyMemberAdded()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);

        if(board.getMAX_NUMBER_OF_PLAYERS() >2)
            System.out.print(end+(board.getHashMap().get("AH2").getFamilyMembers()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");
        System.out.print(a+green+(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getImmediateEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getImmediateEffect()+s).substring(0,k)+a+a
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(red+(board.getHashMap().get("M4").getMinValueToPlaceFamiliar()+s).substring(0,g)+a);
        else
            System.out.println((s+s).substring(0,g)+a);
        System.out.print("\n");
        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL1")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP1")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB1")).getCard().getPermanentEffect()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV1")).getCard().getPermanentEffect()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a

        );
        System.out.println(a+green+(((TowerActionSpace)board.getHashMap().get("TL1")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                blue+(((TowerActionSpace)board.getHashMap().get("TP1")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                yellow+(((TowerActionSpace)board.getHashMap().get("TB1")).getLastFamilyMemberAdded()+s).substring(0,k)+a+
                purple+(((TowerActionSpace)board.getHashMap().get("TV1")).getLastFamilyMemberAdded()+s).substring(0,k)+a+a+
                (s+s).substring(0,g)+a
        );
        System.out.println(cyan+j+j+j+j+end);
        System.out.println(cyan+j+j+j+j+end);

        //----------------------------------fine torri----------------------------------
        //System.out.println(red+h+h+end);
        System.out.println("C)COUNCIL");
        System.out.println("Gain: Council Favour");
        System.out.println(board.getHashMap().get("C").getMinValueToPlaceFamiliar());
        System.out.println("Num FM: "+board.getHashMap().get("C").getFamilyMembers().size());


        //----------------fine consiglio
        System.out.println(red+h+h+end);
        System.out.println("MARKET");
        System.out.print(("M1)Space_1"+s).substring(0,g)+("M2)Space_2"+s).substring(0,g));
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(("M3)Space_3"+s).substring(0,g)+("M4)Space_4"+s).substring(0,g));
        System.out.print(("AP1)PRODUCTION_1"+s).substring(0,g)+("AH1)HARVEST_1"+s).substring(0,g));
        System.out.print("\n");


        System.out.print((board.getHashMap().get("M1").getBonus()+s).substring(0,g)+
                (board.getHashMap().get("M2").getBonus()+s).substring(0,g)
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print((
                    board.getHashMap().get("M3").getBonus()+s).substring(0,g)+
                    (board.getHashMap().get("M4").getBonus()+s).substring(0,g));
        System.out.print((board.getHashMap().get("AP1").getMinValueToPlaceFamiliar()+s).substring(0,g)+
                    (board.getHashMap().get("AH1").getMinValueToPlaceFamiliar()+s).substring(0,g));
        System.out.print("\n");

        System.out.print((board.getHashMap().get("M1").getLastFamilyMemberAdded()+s).substring(0,g)+
                (board.getHashMap().get("M2").getLastFamilyMemberAdded()+s).substring(0,g)
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print((
                    board.getHashMap().get("M3").getLastFamilyMemberAdded()+s).substring(0,g)+
                    (board.getHashMap().get("M4").getLastFamilyMemberAdded()+s).substring(0,g));

        System.out.println((board.getHashMap().get("AP1").getLastFamilyMemberAdded()+s).substring(0,g)+
                    (board.getHashMap().get("AH1").getLastFamilyMemberAdded()+s).substring(0,g));
        System.out.print("\n");

        System.out.print((board.getHashMap().get("M1").getMinValueToPlaceFamiliar()+s).substring(0,g)+
                (board.getHashMap().get("M2").getMinValueToPlaceFamiliar()+s).substring(0,g)
        );
        if(board.getMAX_NUMBER_OF_PLAYERS() >3)
            System.out.print(
                    (board.getHashMap().get("M3").getMinValueToPlaceFamiliar()+s).substring(0,k)+
                    (board.getHashMap().get("M4").getMinValueToPlaceFamiliar()+s).substring(0,k));

        System.out.print("\n");
        //------------------------fine market
/*
        System.out.println(red+h+h+end);
        System.out.println("AP1)PRODUCTION_1"+s+s+s+s+"AH1)HARVEST_1");
        System.out.println(board.getHashMap().get("AP1").getMinValueToPlaceFamiliar()+s+s+board.getHashMap().get("AH1").getMinValueToPlaceFamiliar());
        System.out.println(board.getHashMap().get("AP1").getLastFamilyMemberAdded()+s+s+board.getHashMap().get("AH1").getLastFamilyMemberAdded());
*/
        //------------------------------------------fine 1 place
        if(board.getMAX_NUMBER_OF_PLAYERS() >2) {
            System.out.println("AP2)PRODUCTION_2");
            System.out.println(board.getHashMap().get("AP2").getMinValueToPlaceFamiliar());
            System.out.println(board.getHashMap().get("AP2").getFamilyMembers());

            System.out.println("AH2)HARVEST_2");
            System.out.println(board.getHashMap().get("AH2").getMinValueToPlaceFamiliar());
            System.out.println(board.getHashMap().get("AH2").getFamilyMembers());
        }


    }
}
