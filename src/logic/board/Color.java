package logic.board;

import java.io.Serializable;

/**
 * Created by IBM on 09/05/2017.
 */
public enum Color implements Serializable{

    RED("Red"), BLACK("Black"), WHITE("White");

    private String colorString;

    Color(String colorString){
        this.colorString = colorString;
    }

    @Override
    public String toString(){
        return colorString;
    }

}
