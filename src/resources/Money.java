package resources;

/**
 * Created by IBM on 09/05/2017.
 */
public class Money extends Resource {

    public Money(){
        super();
    }

    public Money(int quantity){
        super(quantity);
    }

    @Override
    public String toString(){
        return "Money(quantity of "+getQuantity()+")";
    }
}
