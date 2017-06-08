package logic.actionSpaces;

/**
 * Created by IBM on 08/06/2017.
 */
public class ActionSpaceId {

    private final char type;
    private final int column;
    private final int row;

    public ActionSpaceId(char type, int column, int row){
        this.type = type;
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public char getType() {
        return type;
    }

    @Override
    public boolean equals(Object o){
        if(o ==  null || !(o instanceof ActionSpaceId)) return false;

        ActionSpaceId actionSpaceId = (ActionSpaceId) o;

        return actionSpaceId.getType() == this.type
                && actionSpaceId.getColumn() == this.column
                && actionSpaceId.getRow() == this.row;

    }


}
