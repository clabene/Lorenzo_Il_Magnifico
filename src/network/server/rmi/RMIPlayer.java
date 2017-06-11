package network.server.rmi;

import logic.actionSpaces.ActionSpace;
import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import network.client.*;
import network.server.RemotePlayer;
import network.server.ServerInterface;

import java.rmi.RemoteException;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIPlayer extends RemotePlayer {

    private transient RMIClientInterface rmiclientInterface;
    private transient ServerInterface serverController;

    @Override
    public GameRoom getGameRoom() {
        return super.getGameRoom();
    }

    @Override
    public void setGameRoom(GameRoom gameRoom) {
        super.setGameRoom(gameRoom);
    }


    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        getGameRoom().selectFamilyMember(familyMember, getId());
    }

    @Override
    public void selectActionSpace(String actionSpaceId) {
        getGameRoom().selectActionSpace(actionSpaceId, getId());
    }

    @Override
    public void dealWithVatican(int minFaithPoints, ) {
        getGameRoom().dealWithVatican(getId(), minFaithPoints,  );


    }

    @Override
    public void selectCouncilFavour() {


    }

    @Override
    public void useSlaves(FamilyMember familyMember, int quantity) {
        getGameRoom().useSlaves(familyMember, quantity,getId());
    }

    @Override
    public void selectActionSpaceForExtraAction() {

    }

    @Override
    public void notifyRequestHandleOutcome(String responseCode) throws RemoteException {
        try {
            rmiclientInterface.notifyRequestHandleOutcome(responseCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void tryToLogInClient(String clientId) {
        setId(clientId);
        serverController.tryToLogIn(clientId, this);
    }

    public void tryToJoinGame() throws RemoteException {
        try {
            serverController.tryToJoinGame(getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void tryToJoinNewRoom(Integer NUMBER_OF_PLAYERS) throws RemoteException {
        try {
            serverController.tryToCreateRoom(getId(), NUMBER_OF_PLAYERS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
