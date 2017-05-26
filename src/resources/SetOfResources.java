package resources;

import exceptions.NegativeResourceQuantityException;
import interfaces.Losable;
import interfaces.Gainable;
import player.Player;

/**
 * Created by IBM on 09/05/2017.
 */
public class SetOfResources implements Losable, Gainable {

    private Resource[] resources = new Resource[4];
    private final int WOOD_INDEX = 0;
    private final int STONE_INDEX = 1;
    private final int SLAVE_INDEX = 2;
    private final int MONEY_INDEX = 3;

    public SetOfResources(){
        initializeResources();
    }

    public SetOfResources(Resource ... resources){
        initializeResources();
        resourcesAdded(resources);
    }

    private void initializeResources(){
        resources[WOOD_INDEX] = new Wood(0);
        resources[STONE_INDEX] = new Stone(0);
        resources[SLAVE_INDEX] = new Slave(0);
        resources[MONEY_INDEX] = new Money(0);
    }

    public Resource[] getResources() {
        return resources;
    }

    private void oneResourceAdded(Resource resource){
        if(resource instanceof Wood) resources[WOOD_INDEX].resourceAdded(resource.getQuantity());
        else if(resource instanceof Stone) resources[STONE_INDEX].resourceAdded(resource.getQuantity());
        else if(resource instanceof Slave) resources[SLAVE_INDEX].resourceAdded(resource.getQuantity());
        else if(resource instanceof Money) resources[MONEY_INDEX].resourceAdded(resource.getQuantity());
    }

    public void resourcesAdded(Resource ... resources){
        for(Resource tmp : resources){
            oneResourceAdded(tmp);
        }
    }

    private void oneResourceSpent(Resource resource) throws NegativeResourceQuantityException {
        if(resource instanceof Wood) resources[WOOD_INDEX].resourceSpent(resource.getQuantity());
        else if(resource instanceof Stone) resources[STONE_INDEX].resourceSpent(resource.getQuantity());
        else if(resource instanceof Slave)  resources[SLAVE_INDEX].resourceSpent(resource.getQuantity());
        else if(resource instanceof Money) resources[MONEY_INDEX].resourceSpent(resource.getQuantity());
    }

    //true if NegativeResourceQuantityException is not to be thrown
    private boolean enoughResourcesQuantity(Resource resource) {
        if(resource instanceof Wood && resources[WOOD_INDEX].getQuantity() < resource.getQuantity())
                return false;
        else if(resource instanceof Stone&& resources[STONE_INDEX].getQuantity() < resource.getQuantity())
            return false;
        else if(resource instanceof Slave && resources[SLAVE_INDEX].getQuantity() < resource.getQuantity())
                return false;
        else if(resource instanceof Money && resources[MONEY_INDEX].getQuantity() < resource.getQuantity())
                return false;
        return true;
    }


    public void resourcesSpent(Resource ... resources) throws NegativeResourceQuantityException {
        for(Resource tmp : resources)
            if(!enoughResourcesQuantity(tmp))
                throw new NegativeResourceQuantityException();
        for (Resource tmp : resources)
            oneResourceSpent(tmp);
    }

    @Override
    public String toString(){
        String toReturn = "";
        for(Resource tmp : resources)
            if(tmp.getQuantity() != 0)
                toReturn += tmp.toString()+"  ";
        if(toReturn.equals(""))
            return "No resource found";
        return toReturn;
        //return resources[WOOD_INDEX]+"; "+ resources[STONE_INDEX]+"; "+ resources[SLAVE_INDEX]+"; "+ resources[MONEY_INDEX];
    }

    @Override
    public void gainedByPlayer(Player player){
        player.addResourcesToPlank(this.getResources());
    }

    @Override
    public void lostByPlayer(Player player) throws NegativeResourceQuantityException {
        player.removeResourcesFromPlank(this.getResources());
    }


}