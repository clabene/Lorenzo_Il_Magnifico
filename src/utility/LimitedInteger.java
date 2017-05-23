package utility;

import exceptions.LimitedValueOffRangeException;

/**
 * Created by IBM on 16/05/2017.
 */
public class LimitedInteger {

    private final int MAX_VALUE;
    private final int MIN_VALUE;
    private int value = 0;

    public LimitedInteger(int MAX_VALUE, int MIN_VALUE, int value) throws LimitedValueOffRangeException{
        if(value > MAX_VALUE || value < MIN_VALUE) throw new LimitedValueOffRangeException();
        this.MAX_VALUE = MAX_VALUE;
        this.MIN_VALUE = MIN_VALUE;
        this.value = value;
    }


    @Deprecated
    //todo delete this constructor, value is 0 and no check concerning MIN_VALUE and MAX_VALUE is done
    public LimitedInteger(int MAX_VALUE, int MIN_VALUE) {
        this.MAX_VALUE = MAX_VALUE;
        this.MIN_VALUE = MIN_VALUE;
    }

    public void increment() throws LimitedValueOffRangeException{
        if(value >= MAX_VALUE) throw new LimitedValueOffRangeException();
        else value++;
    }

    public void decrement() throws LimitedValueOffRangeException{
        if(value <= MIN_VALUE) throw new LimitedValueOffRangeException();
        else value--;
    }

    public int getValue() {
        return value;
    }

    public int getMAX_VALUE() {
        return MAX_VALUE;
    }

    public int getMIN_VALUE() {
        return MIN_VALUE;
    }

    public void setValue(int value) throws LimitedValueOffRangeException {
        if(value > MAX_VALUE || value < MIN_VALUE) throw new LimitedValueOffRangeException();
        this.value = value;
    }
}
