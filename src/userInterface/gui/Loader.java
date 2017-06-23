package userInterface.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import userInterface.gui.component.PopUp;
import userInterface.gui.controllers.Controller;



/**
 * Created by IBM on 16/06/2017.
 */
public class Loader {

    //this might become a singleton

    private GuiClient guiClient;
    private Stage window;

    public Loader(GuiClient guiClient, Stage stage) {
        this.guiClient = guiClient;
        window = stage;
    }

    private void buildStage(String title, String fxmlUrl, int width, int height) {
        window.setTitle(title);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlUrl));
            Parent root = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            controller.setGuiClient(guiClient);
            System.out.println(controller.getGuiClient());
            //controller.setLoader(this);
            guiClient.setController(controller);
            window.setScene(new Scene(root, width, height));
        } catch (Exception e) {
            e.printStackTrace();
        }

        window.show();
    }

    public static void buildPopUp(String title, String message, String imageUrl){
        PopUp popUp = new PopUp(message, imageUrl);
        popUp.setTitle(title);
        popUp.show();
    }
    public static void buildPopUp(String title, String message, Image image){
        PopUp popUp = new PopUp(message, image);
        popUp.setTitle(title);
        popUp.show();
    }
/*
    private void buildPopUp(String title, String fxmlUrl, int width, int height) {
        alertBox = new Stage();
        alertBox.setTitle(title);
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlUrl));
            Parent root = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            controller.setGuiClient(guiClient);
            //cannot call: guiClient.setController(controller); otherwise, once the pop up is closed, guiClient will have
            //no connection to the "real" stage no more
            alertBox.setScene(new Scene(root, width, height));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertBox.show();
    }

    public void closePopUp() {
        if (alertBox != null) alertBox.close();
        alertBox = null;
    }
*/
    public void buildLogInStage() {
        buildStage("LogIn", "fxml/logInDaCancellare.fxml", 400, 300);
    }
    public void buildLobbyStage() {
        buildStage("Lobby", "fxml/lobbyDaCancellare.fxml", 400, 300);
    }
    public void buildMainGameStage(){
        buildStage("LORENZO IL MAGNIFICO", "fxml/mainView.fxml", 1200, 800);
    }
}

