package logic.resources;

import logic.exceptions.NegativeResourceQuantityException;
import logic.interfaces.Losable;
import logic.interfaces.Gainable;
import logic.player.Player;

import java.io.Serializable;

/**
 * Created by IBM on 09/05/2017.
 */
public abstract class Resource implements Losable, Gainable, Serializable {

    private int quantity;

    public Resource(int quantity){
        this.quantity = quantity;
    }
    public Resource(){
        this(1);
    }

    public void resourceAdded(int quantity){
        if(quantity < 0) quantity = 0;
        this.quantity += quantity;
    }

    public void resourceSpent(int quantity) throws NegativeResourceQuantityException{
        if(this.quantity < quantity) throw new NegativeResourceQuantityException();
        this.quantity -= quantity;
    }

    public int getQuantity() {
        return quantity;
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
