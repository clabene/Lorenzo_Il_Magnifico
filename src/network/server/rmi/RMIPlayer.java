package network.server.rmi;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.gameStructure.GameRoom;
import logic.interfaces.Gainable;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;
import network.client.*;
import network.server.RemotePlayer;
import network.server.ServerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

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
    public void selectCouncilFavour(int numberOfFavours) {
        Gainable[] favours =  rmiclientInterface.selectCouncilFavour(numberOfFavours);
        gain(favours);

    }
    @Override
    public void dealWithVatican(int periodNumber) {
        boolean choice = false;
        try {
            choice = rmiclientInterface.dealWithVatican(periodNumber);
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
         ActionSpace actionSpace = rmiclientInterface.selectActionSpaceForExtraAction(actionSpaces);
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
        rmiclientInterface.updateView(board, (Collection<Player>) players);
    }


}
