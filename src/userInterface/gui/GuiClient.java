package userInterface.gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.player.Player;
import network.ResponseCode;
import network.client.ClientInterface;
import userInterface.AbstractUserInterfaceClient;
import userInterface.gui.controllers.Controller;
import userInterface.gui.controllers.LobbyDaCancellareController;
import userInterface.gui.controllers.LogInDaCancellareController;
import userInterface.gui.controllers.MainViewController;

/**
 * Created by IBM on 14/06/2017.
 */
public class GuiClient extends AbstractUserInterfaceClient {

    private Controller controller;
    private Loader loader;
    private ViewUpdater viewUpdater = new ViewUpdater();


    public GuiClient(ClientInterface clientController) {
        super(clientController);
    }

    public void initializeLoader(Stage stage){
        loader = new Loader(this, stage);
    }

    /*
    @Override
    public ClientInterface getClientController() {
        return super.getClientController();
    }
    */

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void successfullyLoggedIn() {
        Platform.runLater( () -> loader.buildLobbyStage() );
    }

    @Override
    public void successfullyJoinedGame() {
        Platform.runLater( () -> loader.buildMainGameStage() );
        Platform.runLater( () -> ((MainViewController) controller).placeMyPlayer() );
    }

    @Override
    public void successfullyCreatedRoom() {
        Platform.runLater( () -> loader.buildMainGameStage() );
        Platform.runLater( () -> ((MainViewController) controller).placeMyPlayer() );
    }

    @Override
    public void successfullySelectedFamilyMember() {

    }

    @Override
    public void successfullySelectedActionSpace() {

    }

    @Override
    public void successfullyUsedSlaves() {

    }

    @Override
    public void handleError() {
        Platform.runLater( () -> controller.buildErrorPopUp());
    }

    @Override
    protected void notEnoughPlayersError() {

    }

    @Override
    protected void waitTurnError() {

    }

    @Override
    public void updateView() {
        //viewUpdater.updateView(getClientController().getView());
    }

    @Override
    public void go() {
        loader.buildLogInStage();
    }

    @Override
    public void updateUi(ResponseCode rc) {
        super.updateUi(rc);
    }
}
