package userInterface.gui;

import logic.actionSpaces.ActionSpace;
import logic.player.FamilyMember;
import logic.player.Player;
import network.client.ClientView;
import userInterface.PlayerColor;
import userInterface.gui.component.PlayerTag;
import userInterface.gui.controllers.MainViewController;

import java.util.Collection;

/**
 * Created by IBM on 23/06/2017.
 */
public class ViewBuilder {

    private ClientView clientView;
    private MainViewController controller;

    public ViewBuilder(ClientView clientView, MainViewController controller){
        this.clientView = clientView;
        this.controller = controller;
    }
    public ViewBuilder(){

    }

    public void setController(MainViewController controller) {
        this.controller = controller;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void buildView(){
        if(controller == null) return;
        buildBoard();
        buildPlayers();
    }

    private void buildBoard(){

    }

    private void buildPlayers(){
        for(Player tmp : clientView.getPlayers())
            if(controller.getPlayerFromId(tmp.getId()) == null) controller.addOpponent(PlayerColor.RED, tmp.getId(), "name");
            else buildOpponent(tmp, controller.getPlayerFromId(tmp.getId()));
    }

    private void buildOpponent(Player player, PlayerTag playerTag){
        updateFamilyMembers(player, playerTag);
        updatePoints(player, playerTag);
        buildCards(player, playerTag);
        buildResources(player, playerTag);
    }
    private void updateFamilyMembers(Player player, PlayerTag playerTag){
        for(FamilyMember tmp : player.getFamilyMembers())
            if(tmp.getInActionSpace()) ;//todo hide tmp
            else playerTag.updateFamilyMemberValue(tmp.getColor(), ((Integer)tmp.getValue()).toString());
    }
    private void updatePoints(Player player, PlayerTag playerTag){
        playerTag.updateFaithPointsQuantity( ((Integer)player.getFaithPoints().getTrackPosition().getValue()).toString() );
        playerTag.updateMilitaryPointsQuantity( ((Integer)player.getMilitaryPoints().getTrackPosition().getValue()).toString() );
        playerTag.updateFaithPointsQuantity( ((Integer) player.getPoints().getQuantity()).toString() );
    }
    private void buildCards(Player player, PlayerTag playerTag){
        for(int i  = 0; i<6; i++){
            if(player.getPlank().getCards().getLandCards()[i] != null) playerTag.setLandCard(i, player.getPlank().getCards().getLandCards()[i].getName());
            if(player.getPlank().getCards().getPersonCards()[i] != null) playerTag.setPersonCard(i, player.getPlank().getCards().getPersonCards()[i].getName());
            if(player.getPlank().getCards().getBuildingCards()[i] != null) playerTag.setBuildingCard(i, player.getPlank().getCards().getBuildingCards()[i].getName());
            if(player.getPlank().getCards().getVentureCards()[i] != null) playerTag.setVentureCard(i, player.getPlank().getCards().getVentureCards()[i].getName());
        }
    }
    private void buildResources(Player player, PlayerTag playerTag){
        playerTag.updateWoodQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfWood()).toString());
        playerTag.updateStoneQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfStone()).toString());
        playerTag.updateSlaveQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfSlaves()).toString());
        playerTag.updateMoneyQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfMoney()).toString());
    }

}
