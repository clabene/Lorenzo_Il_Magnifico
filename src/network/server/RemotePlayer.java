package network.server;


import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import logic.player.Player;

/**
 * Created by Pinos on 06/06/2017.
 */
public abstract class RemotePlayer extends Player{

    private GameRoom gameRoom;


    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

    /*
    * Client selects a family member from theirs
    * */
    public abstract void selectFamilyMember(FamilyMember familyMember, RemotePlayer player);

    /*
    * Client selects an area from the board
    * */
    public abstract void selectBoardArea();

    /*
    * Client selects an action space from the board
    * */
    public abstract void selectActionSpace();

    /*
    * Client decides if they want to support the Vatican or not.
    * This method is triggered by the server side.
    * */
    public abstract void dealWithVatican();
    /*
    * Client decides what bonus they will get form a council favour.
    * This method is triggered by the server side.
    * */
    public abstract void selectCouncilFavour();
    /*
    * Client sends request to use slaves to increment selected family member value
    * */
    public abstract void useSlaves();

}
