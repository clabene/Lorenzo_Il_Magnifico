package userInterface.gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.player.FamilyMember;
import logic.player.Player;
import network.client.ClientView;
import network.server.RemotePlayer;
import userInterface.gui.components.ActionSpaceImageView;
import userInterface.gui.components.FamilyMemberImageView;
import userInterface.gui.components.PlayerTag;
import userInterface.gui.components.TowerActionSpaceImageView;
import userInterface.gui.controllers.MainViewController;

import java.util.ArrayList;


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
        if(checkForWinner()) return;
        checkIsMyTurn();
        buildBoard();
        buildPlayers();
    }

    private String currentPlayerId = "";

    private void checkIsMyTurn(){
        for( Player tmp : clientView.getPlayers() ) {
            if (!tmp.getId().equals(currentPlayerId) && controller.getGuiClient().getId().equals(tmp.getId()) && ((RemotePlayer) tmp).getCurrentPlayer()) {
                Platform.runLater(() -> Loader.buildPopUp("INFO", "Time to move for: " + controller.getPlayerFromId(tmp.getId()).getPlayerName(), (Image) null));
                currentPlayerId = tmp.getId();
            }
        }
     }

    private boolean checkForWinner(){
        for( Player tmp : clientView.getPlayers() ) {
            if(((RemotePlayer) tmp).getIsWinner()){
                Platform.runLater( () -> controller.showWinner(tmp.getId()));
                return true;
            }
        }
        return false;
    }

    private void buildBoard(){
        hideNotUsableActionSpaces();
        for(String tmp : clientView.getBoard().getHashMap().keySet()) {
            buildActionSpace(clientView.getBoard().getActionSpaceFromId(tmp), controller.getActionSpaceFromId(tmp));
            /*if(clientView.getBoard().getActionSpaceFromId(tmp) instanceof TowerActionSpace && ((TowerActionSpace)clientView.getBoard().getActionSpaceFromId(tmp)).getCard() != null){
                if(!(((TowerActionSpace)clientView.getBoard().getActionSpaceFromId(tmp)).getCard().getName().equals(((TowerActionSpaceImageView)controller.getActionSpaceFromId(tmp)).getCardName())))
                ((TowerActionSpaceImageView) controller.getActionSpaceFromId(tmp)).setCardImage("userInterface/gui/images/cards/"+((TowerActionSpace) clientView.getBoard().getActionSpaceFromId(tmp)).getCard().getName()+".png");
            }*/
        }

        //----
        Platform.runLater( () -> controller.setTassels(clientView.getBoard().getTasselFromIndex(0).getId(), clientView.getBoard().getTasselFromIndex(1).getId(),
                clientView.getBoard().getTasselFromIndex(2).getId()));

        Platform.runLater( () -> controller.setDice(clientView.getBoard().getBlackDice(),clientView.getBoard().getRedDice(), clientView.getBoard().getWhiteDice()));



    }

    private void hideNotUsableActionSpaces(){
        if(clientView.getPlayers().size() < 3) {
            controller.getActionSpaceFromId("AH2").setMouseTransparent(true);
            controller.getActionSpaceFromId("AP2").setMouseTransparent(true);
        }
        if(clientView.getPlayers().size() < 4){
            controller.getActionSpaceFromId("M3").setMouseTransparent(true);
            controller.getActionSpaceFromId("M4").setMouseTransparent(true);
        }
    }

    private void buildActionSpace(ActionSpace actionSpace, ActionSpaceImageView actionSpaceView){

        /*System.out.println("-------------------------------------------------"+actionSpaceView.getActionSpaceId());
        System.out.println("-------------------------------------------------"+actionSpace.getLastFamilyMemberAdded().getPlayerId());
        System.out.println("-------------------------------------------------"+actionSpace.getLastFamilyMemberAdded().getColor());
        System.out.println("-------------------------------------------------"+actionSpace.getLastFamilyMemberAdded().getValue());
*/

        if(actionSpace.getNumberOfFamilyMembers() < actionSpaceView.getNumberOfFamilyMembers() ){
            Platform.runLater(() -> controller.removeFamilyMemberFromActionSpace(actionSpaceView.getActionSpaceId()));
            while(actionSpaceView.getFamilyMembers().size()> 0){
                actionSpaceView.getFamilyMembers().remove(0);
            }
            //actionSpaceView.setFamilyMembers(new ArrayList<>());
            //if(actionSpaceView instanceof TowerActionSpaceImageView)
                //((TowerActionSpaceImageView) controller.getActionSpaceFromId(actionSpaceView.getActionSpaceId())).setCardImage("userInterface/gui/images/cards/" + ((TowerActionSpace) clientView.getBoard().getActionSpaceFromId(actionSpaceView.getActionSpaceId())).getCard().getName() + ".png");
            //    System.out.println("non entroooooooooooooooooooooooooooooooooo");
            //}

        }//else{
        if(actionSpace.getNumberOfFamilyMembers() > actionSpaceView.getNumberOfFamilyMembers())
                Platform.runLater(() -> controller.addFamilyMemberToActionSpace(actionSpaceView.getActionSpaceId(), actionSpace.getLastFamilyMemberAdded().getPlayerId(), actionSpace.getLastFamilyMemberAdded().getColor(), actionSpace.getLastFamilyMemberAdded().getValue()));

        if(actionSpace instanceof TowerActionSpace) {
            if (((TowerActionSpace) actionSpace).getCard() == null)
                ((TowerActionSpaceImageView) actionSpaceView).setCardToTaken();
            else{
                System.out.println("---------------------------------------"+((TowerActionSpace) actionSpace).getCard().getName());
                ((TowerActionSpaceImageView) actionSpaceView).setCardImage("userInterface/gui/images/cards/" + ((TowerActionSpace) actionSpace).getCard().getName() + ".png");

            }


        }
    }



    private void buildPlayers(){
        for(Player tmp : clientView.getPlayers())
            if(controller.getPlayerFromId(tmp.getId()) == null)
                Platform.runLater(() -> controller.addOpponent(tmp.getId()));
        else
                buildOpponent(tmp, controller.getPlayerFromId(tmp.getId()));
    }

    private void buildOpponent(Player player, PlayerTag playerTag){
        updateFamilyMembers(player, playerTag);
        updatePoints(player, playerTag);
        buildCards(player, playerTag);
        buildResources(player, playerTag);
    }
    private void updateFamilyMembers(Player player, PlayerTag playerTag){
        for(FamilyMember tmp : player.getFamilyMembers())
            if(tmp.getInActionSpace()) Platform.runLater( () -> playerTag.hideFamilyMember(tmp.getColor()));
            else Platform.runLater( () -> playerTag.updateFamilyMemberValue(tmp.getColor(), ((Integer)tmp.getValue()).toString()));
    }
    private void updatePoints(Player player, PlayerTag playerTag){
        Platform.runLater( () -> playerTag.updateFaithPointsQuantity( ((Integer)player.getFaithPoints().getTrackPosition().getValue()).toString() ) );
        Platform.runLater( () -> playerTag.updateMilitaryPointsQuantity( ((Integer)player.getMilitaryPoints().getTrackPosition().getValue()).toString() ));
        Platform.runLater( () -> playerTag.updateVictoryPointsQuantity( ((Integer) player.getPoints().getQuantity()).toString() ));
    }
    private void buildCards(Player player, PlayerTag playerTag){
        for(int i  = 0; i<6; i++){
            int a = i;
            if(player.getPlank().getCards().getLandCards()[i] != null)
                Platform.runLater( () -> playerTag.setLandCard(a, player.getPlank().getCards().getLandCards()[a].getName()));
            if(player.getPlank().getCards().getPersonCards()[i] != null)
                Platform.runLater(() -> playerTag.setPersonCard(a, player.getPlank().getCards().getPersonCards()[a].getName()));
            if(player.getPlank().getCards().getBuildingCards()[i] != null)
                Platform.runLater( () ->playerTag.setBuildingCard(a, player.getPlank().getCards().getBuildingCards()[a].getName()));
            if(player.getPlank().getCards().getVentureCards()[i] != null)
                Platform.runLater(()->playerTag.setVentureCard(a, player.getPlank().getCards().getVentureCards()[a].getName()));
        }
    }
    private void buildResources(Player player, PlayerTag playerTag){
        Platform.runLater( () -> playerTag.updateWoodQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfWood()).toString()));
        Platform.runLater( () -> playerTag.updateStoneQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfStone()).toString()));
        Platform.runLater( () -> playerTag.updateSlaveQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfSlaves()).toString()));
        Platform.runLater( () -> playerTag.updateMoneyQuantity(((Integer)player.getPlank().getSetOfResources().getQuantityOfMoney()).toString()));
    }

}
