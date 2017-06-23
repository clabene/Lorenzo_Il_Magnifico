package userInterface;

import logic.player.FamilyMember;
import network.ResponseCode;
import network.client.AbstractNetworkClient;
import network.client.ClientInterface;
import network.client.ClientView;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by IBM on 14/06/2017.
 */
public abstract class AbstractUserInterfaceClient implements Serializable{

    private transient ClientInterface clientController;
    private transient HashMap<ResponseCode, uiInnerInterface> map = new HashMap<>();


    public AbstractUserInterfaceClient(ClientInterface clientController){
        this.clientController = clientController;
        fillMap();
    }

    public ClientInterface getClientController() {
        return clientController;
    }

    public String getId(){
        return clientController.getId();
    }

    private void fillMap(){
        map.put(ResponseCode.LOGGED_IN, this::successfullyLoggedIn);
        map.put(ResponseCode.GAME_JOINED, this::successfullyJoinedGame);
        map.put(ResponseCode.ROOM_CREATED, this::successfullyCreatedRoom);
        map.put(ResponseCode.FAMILY_MEMBER_SELECTED, this::successfullySelectedFamilyMember);
        map.put(ResponseCode.ACTION_SPACE_SELECTED, this::successfullySelectedActionSpace);
        map.put(ResponseCode.SLAVES_USED, this::successfullyUsedSlaves);
        map.put(ResponseCode.GENERIC_ERROR, this::handleError);
        map.put(ResponseCode.OK, this::successfullyOperationFinished);
        map.put(ResponseCode.GAME_STARTED, this::successfullyGameStarted);


        //------------------------pinò
        map.put((ResponseCode.NOT_ENOUGH_PLAYERS),this::notEnoughPlayersError);
        map.put((ResponseCode.WAIT_YOUR_TURN),this::waitTurnError);
        map.put(ResponseCode.EXCOMMUNICATION_TAKEN, this::successfullyExcommunicationTaken);
    }


    protected abstract void successfullyLoggedIn();
    protected abstract void successfullyJoinedGame();
    protected abstract void successfullyCreatedRoom();
    protected abstract void successfullySelectedFamilyMember();
    protected abstract void successfullySelectedActionSpace();
    protected abstract void successfullyUsedSlaves();
    protected abstract void handleError();
    protected abstract void notEnoughPlayersError();
    protected abstract void waitTurnError();
    protected abstract void successfullyOperationFinished();
    protected abstract void successfullyExcommunicationTaken();
    protected abstract void successfullyGameStarted();
    public abstract void updateView(ClientView clientView);

    public abstract void go();

    public void logIn(){
        clientController.logIn();
    }
    public void createNewRoom(int numberOfPlayers){
        clientController.createNewRoom(numberOfPlayers);
    }
    public void joinGame(){
        clientController.joinGame();
    }
    public void selectFamilyMember(FamilyMember familyMember){
        clientController.selectFamilyMember(familyMember);
    }
    public void selectActionSpace(String actionSpaceId){
        clientController.selectActionSpace(actionSpaceId);
    }
    public void useSlaves(int quantity){
        clientController.useSlaves(quantity);
    }




    public void updateUi(ResponseCode rc) {
        uiInnerInterface handler = map.get(rc);
        if (handler != null) {
            handler.update();
        }
        else System.out.println("error");
    }

    @FunctionalInterface
    private interface uiInnerInterface {
        void update();
    }

}
