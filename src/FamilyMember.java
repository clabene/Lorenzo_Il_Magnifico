/**
 * Created by IBM on 18/05/2017.
 */
public class FamilyMember {

    private Color color; //null iff neutral
    private int value;
    private boolean inActionSpace;

    public FamilyMember(Color color, int value){
        this.color = color;
        this.value = value;
        this.inActionSpace = false;
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

    public static void main(String[] a){
        System.out.println("CIAONE tanto non è vero che va");
    }

}
