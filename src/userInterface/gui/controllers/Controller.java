package userInterface.gui.controllers;

import javafx.fxml.Initializable;
import userInterface.gui.GuiClient;
import userInterface.gui.Loader;

/**
 * Created by IBM on 15/06/2017.
 */
public abstract class Controller implements Initializable {

    private GuiClient guiClient;
    //private Loader loader;

    public GuiClient getGuiClient() {
        return guiClient;
    }

    public void setGuiClient(GuiClient guiClient) {
        this.guiClient = guiClient;
    }

    /*
    public Loader getLoader() {
        return loader;
    }


    public void setLoader(Loader loader) {
        this.loader = loader;
    }
    */

    public void buildErrorPopUp(){
        Loader.buildPopUp("Error", "An error occurred", "userInterface/gui/images/cancellami.jpg");
    }

}
