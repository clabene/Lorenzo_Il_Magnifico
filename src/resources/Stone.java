package resources;

/**
 * Created by IBM on 09/05/2017.
 */
public class Stone extends Resource {

    public Stone(){
        super();
    }

    public Stone(int quantity){
        super(quantity);
    }

    @Override
    public String toString(){
        return getQuantity()+" stones";
    }
}
