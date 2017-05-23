package resources;

import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 09/05/2017.
 */
public abstract class Resource implements Losable, Gainable {

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

    public void resourceSpent(int quantity) throws NegativeResourceQuantityException{
        if(this.quantity < quantity) throw new NegativeResourceQuantityException();
        this.quantity -= quantity;
    }

    @Override
    public void gainedByPlayer(Player player){
        player.addResourcesToPlank(this);
    }

    @Override
    public void lostByPlayer(Player player) throws NegativeResourceQuantityException{
        player.removeResourcesFromPlank(this);
    }

}
