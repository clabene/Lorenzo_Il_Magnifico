package network.client;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.player.FamilyMember;
import network.client.rmi.RMIClient;
import userInterface.AbstractUserInterfaceClient;
import logic.board.Board;
import logic.player.Player;
import network.ResponseCode;
import network.client.socket.SocketClient;
import userInterface.cli.CliClient;
import userInterface.gui.GuiClient;

import java.util.Collection;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by IBM on 06/06/2017.
 */
public class Client extends Application implements ClientInterface {

    private  String id = UUID.randomUUID().toString();

    private final int RMI_PORT = 6789;
    private final int SOCKET_PORT = 9876;

    private AbstractNetworkClient networkClient ;
    private AbstractUserInterfaceClient uiClient;

    //private ClientView clientView = new ClientView();

    @Override
    public String getId() {
        return id;
    }

    public Client(){
        //setNetworkType();
        //networkClient.connect();

        //setUserInterfaceAndStart();
        ////uiClient.go();
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
    private void setUserInterfaceAndStart(){
        if(selectUserInterfaceType() == 1){
            uiClient = new CliClient(this);

            setNetworkType();
            networkClient.connect();

            uiClient.go();
        }
        else{
            launch(); //uiClient = new GuiClient(this);
        }
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
    public void leaveGame() {
        networkClient.leaveGame();
    }


    @Override
    public void showOutcome(ResponseCode outcomeCode) {
        uiClient.updateUi(outcomeCode);
    }

    //todo delete this
    @Override
    public ClientView getView(){
        return new ClientView();//clientView;
    }

    @Override
    public void updateView(Board board, Collection<Player> players) {
        uiClient.updateView(new ClientView(players, board));

        //clientView.setBoard(board);
        //clientView.setPlayers(players);
    }


    public static void main(String[] args){
        Client c = new Client();
        c.setUserInterfaceAndStart();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        uiClient = new GuiClient(this);

        setNetworkType();
        networkClient.connect();

        ((GuiClient)uiClient).initializeLoader(primaryStage);

        uiClient.go();
    }
}