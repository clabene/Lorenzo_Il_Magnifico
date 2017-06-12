package network.server.rmi;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import logic.player.Player;
import network.ResponseCode;
import network.client.*;
import network.server.RemotePlayer;
import network.server.ServerInterface;

import java.rmi.RemoteException;
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
    public boolean dealWithVatican(int minFaithPoints, ) {
        return rmiclientInterface.dealWithVatican();
    }

    @Override
    public void selectCouncilFavour() {


    }

    @Override
    public void useSlaves(int quantity) {
        getGameRoom().useSlaves(quantity, getId());
    }

    @Override
    public void selectActionSpaceForExtraAction() {

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
