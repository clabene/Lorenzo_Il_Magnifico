package network.server.rmi;

import logic.actionSpaces.ActionSpace;
import logic.gameStructure.GameRoom;
import logic.player.FamilyMember;
import network.server.RemotePlayer;

/**
 * Created by Pinos on 06/06/2017.
 */
public class RMIPlayer extends RemotePlayer {

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
    public void dealWithVatican() {

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
    public void notifyRequestHandleOutcome(String responseCode) {


    }
}
