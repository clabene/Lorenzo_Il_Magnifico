package resources;

import interfaces.Expendable;
import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 09/05/2017.
 */
public abstract class Resource implements Expendable, Gainable {

    private int quantity;

    public Resource(int quantity){
        this.quantity = quantity;
    }
    public Resource(){
        this(1);
    }

    public int getQuantity() {
        return quantity;
    }

    public void resourceAdded(int quantity){
        this.quantity += quantity;
    }
    //todo must throw exception if quantity goes below 0
    public void resourceSpent(int quantity){
        this.quantity -= quantity;
    }

    @Override
    public void gainedByPlayer(Player player){
        player.addResourcesToPlank(this);
    }

}
