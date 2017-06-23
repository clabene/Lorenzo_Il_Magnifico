package network.server.rmi;

import com.google.gson.Gson;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.gameStructure.GameRoom;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;
import network.client.rmi.RMIClientInterface;
import network.server.RemotePlayer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIPlayer extends RemotePlayer {

    public RMIPlayer(RMIClientInterface rmiClientInterface, String clientId){
        this.rmiclientInterface = rmiClientInterface;
        setId(clientId);
    }

    private  transient RMIClientInterface rmiclientInterface;


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
    public void selectCouncilFavour(int numberOfFavours) {
        Gainable[] favours = new Gainable[0];
        try {
            favours = rmiclientInterface.selectCouncilFavour(numberOfFavours);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        gain(favours);

    }
    @Override
    public void dealWithVatican(int periodNumber) {
        boolean choice = false;
        try {
            choice = rmiclientInterface.dealWithVatican(periodNumber);
            this.setExcommunications(periodNumber-1, choice);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        getGameRoom().takeExcommunication(this, choice);
    }

    @Override
    public void useSlaves(int quantity) {
        getGameRoom().useSlaves(quantity, getId());
    }

    @Override
    public void selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        ActionSpace actionSpace = null;
        try {
             actionSpace = rmiclientInterface.selectActionSpaceForExtraAction(actionSpaces);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        getGameRoom().doExtraAction(this, actionSpace);
        notifyRequestHandleOutcome(ResponseCode.OK);
    }

    @Override
    public void notifyRequestHandleOutcome(ResponseCode responseCode) {
        try {

            rmiclientInterface.notifyRequestHandleOutcome(responseCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <P extends Player> void updateView(Board board, Collection<P> players) {
        try {
            ArrayList<Player> players1 = new ArrayList<>();
            players1.addAll(players);

            rmiclientInterface.updateView(board,  players1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaveGame() {
        getGameRoom().leaveGame(getId());
    }


}
