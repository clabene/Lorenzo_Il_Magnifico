package userInterface.gui;

import javafx.application.Platform;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.player.FamilyMember;
import logic.player.Plank;
import logic.player.Player;
import logic.resources.*;
import network.client.ClientView;
import userInterface.PlayerColor;
import userInterface.gui.controllers.MainViewController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IBM on 22/06/2017.
 */
public class ViewUpdater {

    private ClientView oldClientView;
    private ClientView newClientView;
    private MainViewController controller;

    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    public ViewUpdater(){
        oldClientView = new ClientView();
        oldClientView.setBoard(new Board(0));
        oldClientView.setPlayers(new ArrayList<>());
    }

    public void updateView(ClientView newClientView){
        if(controller == null) return;

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
            ;//todo empty up the action space & return
        if(newActionSpace.getLastFamilyMemberAdded() == null && oldActionSpace.getLastFamilyMemberAdded() == null )
            return;
        if( ! familyMembersAreEqual(oldActionSpace.getLastFamilyMemberAdded(), newActionSpace.getLastFamilyMemberAdded()) )
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

            System.out.println(newClientView);
            System.out.println(oldClientView);
            System.out.println("new player: " + tmp);
            System.out.println("old player: " + oldPlayer);

            if(oldPlayer == null) Platform.runLater( () -> controller.addPlayer(PlayerColor.RED, tmp.getId(), "name"));
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
            if(oldPlank.getSetOfResources().getResources()[i].getQuantity() != newPlank.getSetOfResources().getResources()[i].getQuantity() ) {
                //update resource quantity
                Resource r = newPlank.getSetOfResources().getResources()[i];
                if(r instanceof Wood)
                    Platform.runLater( () ->  controller.updateWoodQuantity(playerId, ((Integer) r.getQuantity()).toString()));
                if(r instanceof Stone)
                    Platform.runLater(() -> controller.updateStoneQuantity(playerId, ((Integer) r.getQuantity()).toString()));
                if(r instanceof Slave)
                    Platform.runLater(() -> controller.updateSlaveQuantity(playerId, ((Integer) r.getQuantity()).toString()));
                if(r instanceof Money)
                    Platform.runLater(() -> controller.updateMoneyQuantity(playerId, ((Integer) r.getQuantity()).toString()));
            }


        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getLandCards()[i] == null ) break; //no more land cards to check
            if( oldPlank.getCards().getLandCards()[i] == null)
                Platform.runLater(() -> controller.addLandCard(playerId, "name"));//add card
        }

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getPersonCards()[i] == null ) break; //no more person cards to check
            if( oldPlank.getCards().getPersonCards()[i] == null)
                Platform.runLater(() -> controller.addPersonCard(playerId, "name"));//add card
        }

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getBuildingCards()[i] == null ) break; //no more building cards to check
            if( oldPlank.getCards().getBuildingCards()[i] == null)
                Platform.runLater(() -> controller.addBuildingCard(playerId, "name"));//add card
        }

        for(int i = 0; i < 6; i++){
            if( newPlank.getCards().getVentureCards()[i] == null ) break; //no more ventures cards to check
            if( oldPlank.getCards().getVentureCards()[i] == null)
                Platform.runLater(() -> controller.addVentureCard(playerId, "name"));//add card
        }
    }
    private void updatePoints(Player oldPlayer, Player newPlayer){
        if(oldPlayer.getFaithPoints().getTrackPosition().getValue() != newPlayer.getFaithPoints().getTrackPosition().getValue())
            Platform.runLater( () -> controller.updateFaithPointsQuantity(newPlayer.getId(), ((Integer) newPlayer.getFaithPoints().getTrackPosition().getValue()).toString())); //update points value
        if(oldPlayer.getMilitaryPoints().getTrackPosition().getValue() != newPlayer.getMilitaryPoints().getTrackPosition().getValue())
            Platform.runLater( () -> controller.updateMilitaryPointsQuantity(newPlayer.getId(), ((Integer) newPlayer.getMilitaryPoints().getTrackPosition().getValue()).toString())); //update points value
        if(oldPlayer.getPoints().getQuantity() != newPlayer.getPoints().getQuantity())
            Platform.runLater( () -> controller.updateVictoryPointsQuantity(newPlayer.getId(), ((Integer) newPlayer.getPoints().getQuantity()).toString())); //update points value
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
