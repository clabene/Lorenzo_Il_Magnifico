package userInterface.gui;

import javafx.fxml.Initializable;
import userInterface.GuiClient;

/**
 * Created by IBM on 15/06/2017.
 */
public abstract class Controller implements Initializable {

    private GuiClient guiClient;

    public GuiClient getGuiClient() {
        return guiClient;
    }

    public void setGuiClient(GuiClient guiClient) {
        this.guiClient = guiClient;
    }

}
