package logic.actionSpaces;

import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IBM on 18/05/2017.
 **/
public abstract class ActionSpace implements Serializable{

    private  ArrayList<Gainable> bonus = new ArrayList<>();

    private  ActionSpaceType actionSpaceType;

    private boolean covered = false;

    private int minValueToPlaceFamiliar;
    private final int MAX_NUMBER_OF_FAMILIARS; //= (int) Double.POSITIVE_INFINITY; if no limit
    //private LimitedInteger numberOfFamiliars;

    private  ArrayList<FamilyMember> familyMembers = new ArrayList<>();

    public ActionSpace( int MAX_NUMBER_OF_FAMILIARS, ActionSpaceType actionSpaceType, int minValueToPlaceFamiliar, Gainable ... bonuses){
        this.MAX_NUMBER_OF_FAMILIARS = MAX_NUMBER_OF_FAMILIARS;
        //initializeNumberOfFamiliars();
        this.minValueToPlaceFamiliar = minValueToPlaceFamiliar;

        for(Gainable tmp : bonuses)
            this.bonus.add(tmp);

        this.actionSpaceType = actionSpaceType;
    }

    public void resetActionSpace(){
        while(familyMembers.size() >0){
            familyMembers.remove(0);
        }

        //familyMembers.removeAll(familyMembers);

        //familyMembers.clear();//todo
    }

    public void setCovered(boolean covered){
        this.covered = covered;
    }

    public void familyMemberAdded(FamilyMember familyMember) {
        if(covered) return;
        this.familyMembers.add(familyMember);
        if(familyMembers.size() == MAX_NUMBER_OF_FAMILIARS) covered = true;
    }
    public void familyMemberRemoved(FamilyMember familyMember) {
        this.familyMembers.remove(familyMember);
        if(familyMembers.size() < MAX_NUMBER_OF_FAMILIARS) covered = false;
    }
    public FamilyMember getLastFamilyMemberAdded() {
        if(familyMembers.size() != 0)
            return familyMembers.get(familyMembers.size()-1);
        return null;
    }

    public ArrayList<FamilyMember> getFamilyMembers(){
        return familyMembers;
    }

    public int getNumberOfFamilyMembers(){
        return familyMembers.size();
    }

    /*
    private void initializeNumberOfFamiliars(){
        try{
            numberOfFamiliars = new LimitedInteger(MAX_NUMBER_OF_FAMILIARS, 0, 0);
        } catch (LimitedValueOffRangeException e){
            System.out.println("Can not put your familiar here, this action space is full already");
        }
    }
    */

    /*
     * New familiar is added to the action space
     *
     * Returns Gainable object "bonus" that current player will have to gain. It was avoided this way to put a Player object
     * in this method, which would not have been very fancy.
     *
     **/
    /*
    public Gainable[] familiarAdded(){
        try{
            numberOfFamiliars.increment();
        } catch (LimitedValueOffRangeException e){
            System.out.println("Cannot put your familiar here, this action space is full already");
        }
        return bonus.toArray(new Gainable[bonus.size()]);
    }
    */


    public ActionSpaceType getActionSpaceType() {
        return actionSpaceType;
    }

    public void decreaseMinValueRequested(int number){
        this.minValueToPlaceFamiliar -= number ;
    }

    public boolean getCovered(){
        return this.covered;
    }
    public int getMinValueToPlaceFamiliar(){
        return this.minValueToPlaceFamiliar;
    }

    public void setBonus(ArrayList<Gainable> bonus) {
        this.bonus = bonus;
    }
    public ArrayList<Gainable> getBonus() {
        return bonus;
    }

    //this method is the one to call when the action space is activated
    //action will be so implemented by subclasses:
    // tower -> (try to) take card
    // market -> null
    // activation -> do the activation
    // council -> set order for next turn
    //returns true if familiar was correctly added
    public abstract boolean action(Player player);

    @Override
    public String toString() {
        return "\u001B[31m"+actionSpaceType +"\u001B[0m"+
                "\n" +"\u001B[34m"+ bonus.toString() +"\u001B[0m" + "\n" + "------------------"+familyMembers.toString() +"------------------";
    }
}
