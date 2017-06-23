package userInterface.gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.interfaces.Gainable;
import logic.player.Player;
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
        });
    }

    @Override
    public void successfullyCreatedRoom() {
        Platform.runLater( () -> {
            loader.buildMainGameStage();
            ((MainViewController) controller).placeMyPlayer();
            viewBuilder.setController((MainViewController) controller);
        });
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

    }

    @Override
    protected void successfullyExcommunicationTaken() {

    }

    @Override
    protected void successfullyGameStarted() {

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

    @Override
    public boolean dealWithVatican(int periodNumber) {
        return false;
    }

    @Override
    public Gainable[] selectCouncilFavour(int numberOfFavours) {
        return new Gainable[0];
    }

    @Override
    public ActionSpace selectActionSpaceForExtraAction(ArrayList<ActionSpace> actionSpaces) {
        return null;
    }

    @Override
    public void updateUi(ResponseCode rc) {
        super.updateUi(rc);
    }
}
