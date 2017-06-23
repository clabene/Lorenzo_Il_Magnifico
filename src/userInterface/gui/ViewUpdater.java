package userInterface.gui;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.player.FamilyMember;
import logic.player.Plank;
import logic.player.Player;
import network.client.ClientView;

import java.util.HashMap;

/**
 * Created by IBM on 22/06/2017.
 */
public class ViewUpdater {

    private ClientView oldClientView;
    private ClientView newClientView;

    public ViewUpdater(){
        oldClientView = new ClientView();
        oldClientView.setBoard(new Board(0));
    }

    public void updateView(ClientView newClientView){
        this.newClientView = newClientView;

        updateBoard();
        updatePlayers();

        oldClientView = newClientView;
        this.newClientView = null;

    }

    private void updateBoard(){
        if(oldClientView.getBoard().getMAX_NUMBER_OF_PLAYERS() == 0){ //if this is first update
            //todo show excommunication tassels
            //todo hide not-usable action spaces
        }

        updateDice(newClientView.getBoard().getBlackDice(),
                newClientView.getBoard().getRedDice(),
                newClientView.getBoard().getWhiteDice()
        );

        updateActionSpaces();


    }
    private void updateDice(int newBlack, int newRed, int newWhite){
        if(newBlack != oldClientView.getBoard().getBlackDice()) ; //todo update black dice
        if(newRed != oldClientView.getBoard().getRedDice()) ; //todo update red dice
        if(newWhite != oldClientView.getBoard().getWhiteDice()) ; //todo update white dice
    }
    private void updateActionSpaces(){
        HashMap<String, ActionSpace> oldActionSpaces = oldClientView.getBoard().getHashMap();
        HashMap<String, ActionSpace> newActionSpaces = newClientView.getBoard().getHashMap();
        for(String tmp : newClientView.getBoard().getHashMap().keySet()){
            updateSingleActionSpace(oldActionSpaces.get(tmp), newActionSpaces.get(tmp) );
        }
    }
    private void updateSingleActionSpace(ActionSpace oldActionSpace, ActionSpace newActionSpace){
        if( newActionSpace.getLastFamilyMemberAdded() == null && oldActionSpace.getLastFamilyMemberAdded() != null )
            ;//todo empty up the action space

        else if( ! familyMembersAreEqual(oldActionSpace.getLastFamilyMemberAdded(), newActionSpace.getLastFamilyMemberAdded()) )
            ;//todo add new family member to action space
    }
    private boolean familyMembersAreEqual(FamilyMember oldFamilyMember, FamilyMember newFamilyMember){
        return oldFamilyMember.getPlayerId().equals(newFamilyMember.getPlayerId())
                && oldFamilyMember.getValue() == newFamilyMember.getValue()
                && ((oldFamilyMember.getColor() == null && newFamilyMember.getColor() == null)
                        || (oldFamilyMember.getColor() == newFamilyMember.getColor())) //both neutral or same color
                ;
    }
    private void updatePlayers(){
        for(Player tmp : newClientView.getPlayers()){
            Player oldPlayer = getOldPlayerFromId(tmp.getId());
            if(oldPlayer == null) ;//todo add new opponent then call updateSinglePlayer with that and tmp as parameters
            else updateSinglePlayer(oldPlayer, tmp);
        }
    }
    private Player getOldPlayerFromId(String id){
        for(Player tmp : oldClientView.getPlayers())
            if(tmp.getId().equals(id))
                return tmp;
        return null;
    }
    private void updateSinglePlayer(Player oldPlayer, Player newPlayer){
        updatePlanks(oldPlayer.getPlank(), newPlayer.getPlank(), newPlayer.getId());
        updatePoints(oldPlayer, newPlayer);
        updateFamilyMembers(oldPlayer, newPlayer);
    }
    private void updatePlanks(Plank oldPlank, Plank newPlank, String playerId){
        for(int i = 0; i < 4; i++)
            if(oldPlank.getSetOfResources().getResources()[i].getQuantity() != newPlank.getSetOfResources().getResources()[i].getQuantity() )
                ; //todo update resource quantity

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getLandCards()[i] == null ) break; //no more land cards to check
            if( oldPlank.getCards().getLandCards()[i] == null) ;//todo add card
        }

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getPersonCards()[i] == null ) break; //no more person cards to check
            if( oldPlank.getCards().getPersonCards()[i] == null) ;//todo add card
        }

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getBuildingCards()[i] == null ) break; //no more building cards to check
            if( oldPlank.getCards().getBuildingCards()[i] == null) ;//todo add card
        }

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getVentureCards()[i] == null ) break; //no more ventures cards to check
            if( oldPlank.getCards().getVentureCards()[i] == null) ;//todo add card
        }
    }
    private void updatePoints(Player oldPlayer, Player newPlayer){
        if(oldPlayer.getFaithPoints().getTrackPosition().getValue() != newPlayer.getFaithPoints().getTrackPosition().getValue())
            ; //todo update points value
        if(oldPlayer.getMilitaryPoints().getTrackPosition().getValue() != newPlayer.getMilitaryPoints().getTrackPosition().getValue())
            ; //todo update points value
        if(oldPlayer.getPoints().getQuantity() != newPlayer.getPoints().getQuantity())
            ; //todo update points value
    }
    private void updateFamilyMembers(Player oldPlayer, Player newPlayer) {
        for(int i = 0; i<4; i++) {
            if( newPlayer.getFamilyMembers()[i].getInActionSpace()
                    && !oldPlayer.getFamilyMembers()[i].getInActionSpace() )
                ;//todo hide family member
            else if( !newPlayer.getFamilyMembers()[i].getInActionSpace()
                    && oldPlayer.getFamilyMembers()[i].getInActionSpace() )
                ;//todo show family member
            if( oldPlayer.getFamilyMembers()[i].getValue() != newPlayer.getFamilyMembers()[i].getValue() )
                ;//todo update family member's value
        }
    }

}
