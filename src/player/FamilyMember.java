package player;

import java.awt.*;

/**
 * Created by IBM on 18/05/2017.
 */
public class FamilyMember {

    private Color color; //null iff neutral
    private int value;
    private boolean inActionSpace;
    private String id;

    public FamilyMember(Color color, int value){
        this.color = color;
        this.value = value;
        this.inActionSpace = false;
        this.id = null;
    }

    public FamilyMember(Color color, int value, String id, boolean inActionSpace){
        this.color = color;
        this.value = value;
        this.inActionSpace = inActionSpace;
        this.id = id;
    }

    public int getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }

    public void valueIncremented(){ //player can exchange slaves for increasing the value of their family members
        value++;
    }
    public void setInActionSpace(boolean inActionSpace) {
        this.inActionSpace = inActionSpace;
    }
    public boolean getInActionSpace(){return this.inActionSpace;};

    public String toString(){
        return this.color + " Family Member " + " value: " +this.value;
    }


}
