package resources;

/**
 * Created by IBM on 09/05/2017.
 */
public class Wood extends Resource {

    public Wood(){
        super();
    }

    public Wood(int quantity){
        super(quantity);
    }

    @Override
    public String toString(){
        return getQuantity()+" woods";
    }

}
