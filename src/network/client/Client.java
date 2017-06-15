package network.client;

import com.sun.corba.se.spi.orbutil.fsm.FSM;
import logic.actionSpaces.ActionSpace;
import logic.player.FamilyMember;
import userInterface.AbstractUserInterfaceClient;
import logic.board.Board;
import logic.player.Player;
import network.ResponseCode;
import network.client.socket.SocketClient;
import userInterface.CliClient;
import userInterface.GuiClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by IBM on 06/06/2017.
 */
public class Client implements ClientInterface {

    private String id = UUID.randomUUID().toString();

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;

    private AbstractNetworkClient networkClient ;
    private AbstractUserInterfaceClient uiClient;

    private ClientView clientView = new ClientView();

    @Override
    public String getId() {
        return id;
    }

    public Client(){
        setNetworkType();
        this.networkClient.connect();
        setUserInterface();
        uiClient.go();
    }

    private int selectNetworkType(){
        System.out.println("What network is preferred?");
        System.out.println("1 : RMI");
        System.out.println("other : Socket");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    private void setNetworkType(){
        if(selectNetworkType() == 1) networkClient = new RMIClient(this,RMI_PORT);
        else  networkClient = new SocketClient(this,SOCKET_PORT);
    }
    private int selectUserInterfaceType(){
        System.out.println("What ui is preferred?");
        System.out.println("1 : CLI");
        System.out.println("other : GUI");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    private void setUserInterface(){
        if(selectUserInterfaceType() == 1) uiClient = new CliClient(this);
        else uiClient = new GuiClient(this);
    }

    /*
    private AbstractNetworkClient getNetworkClient() {
        return networkClient;
    }

    public AbstractUserInterfaceClient getUiClient() {
        return uiClient;
    }
    */

    @Override
    public void logIn() {
        networkClient.tryToLogIn();
    }

    @Override
    public void createNewRoom(int numberOfPlayers) {
        networkClient.tryToCreateRoom(numberOfPlayers);
    }

    @Override
    public void joinGame() {
        networkClient.tryToJoinGame();
    }

    @Override
    public void selectFamilyMember(FamilyMember familyMember) {
        networkClient.selectFamilyMember(familyMember);
    }

    @Override
    public void selectActionSpace(String actionSpaceId) {
        networkClient.selectActionSpace(actionSpaceId);
    }

    @Override
    public void useSlaves(int quantity) {
        networkClient.useSlaves(quantity);
    }



    @Override
    public void showOutcome(ResponseCode outcomeCode) {
        uiClient.updateUi(outcomeCode);
    }

    @Override
    public ClientView getView(){
        return clientView;
    }

    @Override
    public void updateView(Board board, Collection<Player> players) {
        clientView.setBoard(board);
        clientView.setPlayers((ArrayList<Player>) players);
        uiClient.updateView();
    }


    public static void main(String[] args){
        Client client = new Client();
    }

}
