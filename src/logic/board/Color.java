package logic.board;

/**
 * Created by IBM on 09/05/2017.
 */
public enum Color {

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
