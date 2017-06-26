package userInterface.gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.interfaces.Gainable;
import logic.player.Player;
import logic.resources.Wood;
import network.ResponseCode;
import network.client.ClientInterface;
import network.client.ClientView;
import userInterface.AbstractUserInterfaceClient;
import userInterface.gui.controllers.Controller;
import userInterface.gui.controllers.MainViewController;

import java.util.ArrayList;


/**
 * Created by IBM on 14/06/2017.
 */
public class GuiClient extends AbstractUserInterfaceClient {

    private Controller controller;
    private Loader loader;
    private ViewUpdater viewUpdater = new ViewUpdater();
    private ViewBuilder viewBuilder = new ViewBuilder();

    public GuiClient(ClientInterface clientController) {
        super(clientController);
    }

    public void initializeLoader(Stage stage){
        loader = new Loader(this, stage);
    }

    public Controller getController(){
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void successfullyLoggedIn() {
        Platform.runLater( () -> loader.buildLobbyStage() );
    }

    @Override
    public void successfullyJoinedGame() {
        Platform.runLater( () ->{
            loader.buildMainGameStage();
            ((MainViewController) controller).placeMyPlayer();
            viewBuilder.setController((MainViewController) controller);
            updateView();
        });
        //updateView();
    }

    @Override
    public void successfullyCreatedRoom() {
        Platform.runLater( () -> {
            loader.buildMainGameStage();
            ((MainViewController) controller).placeMyPlayer();
            viewBuilder.setController((MainViewController) controller);
            updateView();
        });
        //updateView();
    }

    @Override
    public void successfullySelectedFamilyMember() {
        System.out.println("successfullySelectedFamilyMember()");
    }

    @Override
    public void successfullySelectedActionSpace() {
        System.out.println("successfullySelectedActionSpace()");
    }

    @Override
    public void successfullyUsedSlaves() {
        System.out.println("successfullyUsedSlaves()");
    }

    @Override
    public void handleError() {
        Platform.runLater( () -> controller.buildErrorPopUp());
    }

    @Override
    protected void notEnoughPlayersError() {
        Platform.runLater( () -> Loader.buildPopUp("Game yet to start", "Please wait for enough players", (Image) null));
    }

    @Override
    protected void waitTurnError() {
        Platform.runLater( () -> Loader.buildPopUp("Not your turn", "Please wait your turn", (Image) null));
    }

    @Override
    protected void successfullyOperationFinished() {
        //nop
    }

    @Override
    protected void successfullyExcommunicationTaken() {
        Platform.runLater( () -> Loader.buildPopUp("Excommunication received", "You received the excommunication from the Vatican", (Image) null) );
    }

    @Override
    protected void successfullyGameStarted() {
        updateView();
    }

    @Override
    public void updateView() {
        viewBuilder.setClientView(getClientController().getView());
        viewBuilder.buildView();
    }

    @Override
    public void go() {
        loader.buildLogInStage();
    }


    private boolean notSupportingVatican = true;
    private boolean canSend = false;


    //true: excommunication taken, false: excommunication not taken
    @Override
    public boolean dealWithVatican(int periodNumber) {
        Platform.runLater( () -> ((MainViewController) controller).dealWithVatican(periodNumber) ) ;

        //todo check this

        while(!canSend){
            canSend = ((MainViewController) controller).getCanSend();
            notSupportingVatican = ((MainViewController) controller).getNotSupporting();
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(".........................sending " + notSupportingVatican);
        canSend = false;
        return notSupportingVatican;
    }


    private Gainable[] gainables;

    @Override
    public Gainable[] selectCouncilFavour(int numberOfFavours) {
        Platform.runLater( () -> ((MainViewController) controller).selectCouncilFavour(numberOfFavours) ) ;

        //todo check this

        while(!canSend){
            canSend = ((MainViewController) controller).getCanSend();
            gainables = ((MainViewController) controller).getFavours();
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(".......................sending " + gainables);
        canSend = false;
        return gainables;
    }

    private ActionSpace actionSpace;
    @Override
    public ActionSpace selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        if(actionSpaces.size() == 1) return actionSpaces.get(0);

        Platform.runLater( () -> ((MainViewController) controller).selectActionSpaceForExtraAction(actionSpaces));

        //todo check this

        while(!canSend){
            canSend = ((MainViewController) controller).getCanSend();
            actionSpace = ((MainViewController) controller).getActionSpace();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(".......................sending " + actionSpace);
        canSend = false;
        return actionSpace;

    }

    @Override
    public void updateUi(ResponseCode rc) {
        super.updateUi(rc);
    }
}
