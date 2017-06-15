package userInterface;

import network.ResponseCode;
import network.client.ClientInterface;

/**
 * Created by IBM on 14/06/2017.
 */
public class GuiClient extends AbstractUserInterfaceClient {

    public GuiClient(ClientInterface clientController) {
        super(clientController);
    }

    /*
    @Override
    public ClientInterface getClientController() {
        return super.getClientController();
    }
    */
    @Override
    public void successfullyLoggedIn() {

    }

    @Override
    public void successfullyJoinedGame() {

    }

    @Override
    public void successfullyCreatedRoom() {

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

    }

    @Override
    public void updateView() {

    }

    @Override
    public void updateUi(ResponseCode rc) {
        super.updateUi(rc);
    }
}
