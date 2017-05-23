package resources;

/**
 * Created by IBM on 09/05/2017.
 */
public class Slave extends Resource {

    public Slave(){
        super();
    }

    public Slave (int quantity){
        super(quantity);
    }

    @Override
    public String toString(){
        return "Slave(quantity of "+getQuantity()+")";
    }


}
