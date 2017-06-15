package logic.player;

import logic.board.Board;
import logic.board.Color;
import logic.cards.LandCard;
import logic.exceptions.FamilyMemberSelectionException;
import logic.gameStructure.PeriodNumber;
import logic.bonuses.Bonus;
import logic.exceptions.LimitedValueOffRangeException;
import logic.exceptions.NegativePointsException;
import logic.exceptions.NegativeResourceQuantityException;
import logic.interfaces.Losable;
import logic.pointsTracks.FaithPointsTrack;
import logic.pointsTracks.LandCardsPointsTrack;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.pointsTracks.PersonCardsPointsTrack;
import logic.cards.Card;
import logic.interfaces.Gainable;
import logic.resources.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by IBM on 14/05/2017.
 */
public class Player implements Serializable{

    private String id;

    private VictoryPoint points = new VictoryPoint(0);
    private MilitaryPointsTrack militaryPoints = new MilitaryPointsTrack();
    private FaithPointsTrack faithPoints = new FaithPointsTrack();
    private PersonCardsPointsTrack personCardsPoints = new PersonCardsPointsTrack();
    private LandCardsPointsTrack landCardsPoints = new LandCardsPointsTrack();

    private Plank plank = new Plank();

    private final int BLACK_FAMILY_MEMBER_INDEX = 0;
    private final int RED_FAMILY_MEMBER_INDEX = 1;
    private final int WHITE_FAMILY_MEMBER_INDEX = 2;
    private final int NEUTRAL_FAMILY_MEMBER_INDEX = 3;


    private FamilyMember[] familyMembers = new FamilyMember[4];

    private ArrayList<Bonus> bonuses = new ArrayList<>();

    private Board board;

    private ExtraAction extraAction;

    private final SetOfResources initialResources = new SetOfResources(); //todo

    public Player(Resource ... resources /*initialResources*/){
        this.plank = new Plank(resources /*initialResources*/);

    }

    //this has to be called as first method after constructor
    public void setId(String id) {
        this.id = id;
        initializeFamilyMembers();
    }

    private void initializeFamilyMembers(){
        FamilyMember[] familyMembers = {new FamilyMember(Color.BLACK, 0, this.id), new FamilyMember(Color.RED, 0, this.id),
                new FamilyMember(Color.WHITE, 0, this.id), new FamilyMember(null, 0, this.id)};
        this.familyMembers = familyMembers;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    /*
    * Players gains all the points and all the resources given as parameters
    * */
    public void gain(Gainable ... gainables) {
        for (Gainable tmp : gainables) {
            if(tmp == null) return;
            tmp.gainedByPlayer(this);
        }
    }

    /*
    * Player (tries to) lose all the points and all the resources given as parameters.
    *
    * If player is asked to lose more than what they actually have, the output changes based on the type of losable:
    *
    * - Resource/SetOfResources : player will not lose any of that resource/set of resources and a NegativeResourceQuantityException
    *  exception is thrown, and false is returned.
    *  Observation: if the player is asked to lose SetOfResources(new Wood(10), new Stone(10)) and has enough wood but not
    *  enough stone, they will lose neither wood nor stone. On the other hand, if the same player is asked to lose Wood(10)
    *  and Stone(10), they will lose 10 woods but no stone.
    *
    * - PointsTrack : player will lose all of their points, a NegativePointsException is thrown and false is returned.
    *
    * - VictoryPoint : player can have a negative amount of victory points.
    *
    *
    * Player loses all the losable objects that can lose without an exception to be thrown. The catching of an Exception does
    * not cause the method to stop.
    *
    * True: Losable parameters were all completely lost (no exception was thrown).
    *
    * */
    public boolean lose(Losable... losables) {
        boolean toReturn = true;
        for (Losable tmp : losables)
            try {
                if(tmp == null) return true;
                tmp.lostByPlayer(this);
            } catch (NegativeResourceQuantityException | NegativePointsException e) {
                toReturn = false;
            }
        return toReturn;
    }

    public static void main(String[] a){
        Player player = new Player(new Slave(21));
        player.gain(new FaithPointsTrack(10));
        boolean b = player.lose(new FaithPointsTrack(15), new Slave(22));
        System.out.println("fede "+ player.getFaithPoints().getTrackPosition().getValue());
        System.out.println(player.getPlank().getSetOfResources().toString());
        System.out.println(b);

        for(int i = 7; i >= 0; i--)
            try{
                player.tryToTakeCard(new LandCard("sapa", PeriodNumber.SECOND, 1, null, null));
            } catch (ArrayIndexOutOfBoundsException | LimitedValueOffRangeException e){
                System.out.println("assgbarnhahnahnagaedsh");
            }
    }

    public FamilyMember tryToSelectFamilyMember(FamilyMember familyMember) throws FamilyMemberSelectionException{
        if(!familyMember.getPlayerId().equals(id)) throw new FamilyMemberSelectionException();
        for(FamilyMember tmp : familyMembers)
            if(tmp.getColor().equals(familyMember.getColor()) && !tmp.getInActionSpace())
                return tmp;
        throw new FamilyMemberSelectionException();
    }

    public void tryToTakeCard(Card card) throws IndexOutOfBoundsException, LimitedValueOffRangeException{
        plank.getCards().cardAdded(card);
    }

    public void addResourcesToPlank(Resource ... resources){
        plank.getSetOfResources().resourcesAdded(resources);
    }

    public void removeResourcesFromPlank(Resource ... resources) throws NegativeResourceQuantityException {
        plank.getSetOfResources().resourcesSpent(resources);
    }

    public void setExtraAction(ExtraAction extraAction) {
        this.extraAction = extraAction;
    }

    public ExtraAction getExtraAction() {
        return extraAction;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Bonus> getBonuses() {
        return bonuses;
    }

    public String getId(){
        return this.id;
    }

    public Plank getPlank() {
        return plank;
    }

    public MilitaryPointsTrack getMilitaryPoints() {
        return militaryPoints;
    }

    public FaithPointsTrack getFaithPoints() {
        return faithPoints;
    }

    public PersonCardsPointsTrack getPersonCardsPoints() {
        return personCardsPoints;
    }

    public LandCardsPointsTrack getLandCardsPoints() {
        return landCardsPoints;
    }

    public VictoryPoint getPoints() {
        return points;
    }


    /*
    public int getNumberOfFamilyMembersAvailable(){
        int i = 0;
        for(FamilyMember tmp: familyMembers){
            if(!tmp.getInActionSpace()){
                i++;
            }
        }
        return i;
    }
    */

    /*
    return the family members of the player that are not on any action space
    */
    public ArrayList<FamilyMember> getFamilyMembersAvailable(){
        ArrayList<FamilyMember> available = new ArrayList<>();
        for(FamilyMember tmp: familyMembers){
            if(!tmp.getInActionSpace()){
                available.add(tmp);

            }
        }
        return available;
    }

    public FamilyMember[] getFamilyMembers(){
        return this.familyMembers;
    }

    public void excommunicationDecision(){
        System.out.println("What do you want to do? \n 0: take excommunication \n 1: lose you faithPoints");
        Scanner input = new Scanner(System.in);
        int decision = input.nextInt();
        if(decision == 0){


        }else if(decision == 1){
            this.gain(this.getFaithPoints().calculateVictoryPointsFromPosition());
            this.lose(this.getFaithPoints());

        }else{
            excommunicationDecision();
        }
    }

    //todo method to take the excommunication
}
