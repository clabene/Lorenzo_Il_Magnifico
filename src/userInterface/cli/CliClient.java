package userInterface.cli;

import network.ResponseCode;
import network.client.Client;
import network.client.ClientInterface;
import network.client.ClientView;
import userInterface.AbstractUserInterfaceClient;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by IBM on 23/06/2017.
 */
public class CliClient extends AbstractUserInterfaceClient implements Serializable {

    public CliClient(ClientInterface clientController) {
        super(clientController);

    }


    @Override
    public void successfullyLoggedIn() {
        System.out.println("log in successful");
    }

    @Override
    public void successfullyJoinedGame() {
        System.out.println("Join game successful");

    }

    @Override
    public void successfullyCreatedRoom() {
        System.out.println("Create room successful");
    }

    @Override
    public void successfullySelectedFamilyMember() {
        System.out.println("Selected Family Member successful");

    }

    @Override
    public void successfullySelectedActionSpace() {
        System.out.println("Selected Action Space successful");

    }

    @Override
    public void successfullyUsedSlaves() {
        System.out.println("Used slaves successful");


    }

    @Override
    public void handleError() {
        System.out.println("problem occurred");
    }

    @Override
    protected void notEnoughPlayersError() {
        System.out.println("There are not enough players to start the game!");
    }

    @Override
    protected void waitTurnError() {
        System.out.println("This is not your turn!");

    }

    @Override
    protected void successfullyOperationFinished() {
        System.out.println("Operation finished correctly");
    }

    @Override
    protected void successfullyExcommunicationTaken() {
        System.out.println("You don't have enough Faith Points, so you took excommunication from Vatican");
    }

    @Override
    protected void successfullyGameStarted() {
        System.out.println("Game started!!!!!!!!");
    }
/*
    @Override
    protected void successfullyPutFamilyMember() {
        System.out.println("Ok, put family member");
    }
*/

    @Override
    public void updateView(ClientView clientView) {
        clientView.printBoard(getId());
    }

    @Override
    public void updateUi(ResponseCode rc) {
        super.updateUi(rc);
    }

    public void loginMenu(){
        System.out.println("What do you want to do?\n 1)Join Game \n 2) Create new room \n");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                joinGame();
                break;
            case 2:
                System.out.println("How many players?");
                createNewRoom(scanner.nextInt());
                break;
        }
    }

    public boolean actionMenu(){
        System.out.println("What do you want to do?\n 1)Select Family Member \n 2)Select Action Space\n 3)Use slaves\n 4) Leave the game \n ");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()){
            case 1: familyMemberMenu();
                return false;
            //actionSpaceMenu();

            case 2: actionSpaceMenu();
                //familyMemberMenu();
                return false;
            case 3: slavesMenu();
                return false;

            case 4: leaveGame();
                return true;

            default: return false;
        }

    }


    public void familyMemberMenu(){
        System.out.println("Which family member do you want to select?");
        //getClientController().getView().printFamilyMembersAvailable(getId());

        getClientController().selectFamilyMember(getClientController().getView().printFamilyMembersAvailable(getId()));

    }

    public void actionSpaceMenu(){
        System.out.println("Which action space do you want to select?");
        getClientController().selectActionSpace(getClientController().getView().printActionSpaces(getId()));
    }



    public void slavesMenu(){
        System.out.println("How many slaves do you want to use?");
        getClientController().useSlaves(getClientController().getView().printSlaves(getId()));
    }

    public void leaveGame(){
        getClientController().leaveGame();
    }



    public void go(){
        Scanner scanner  = new Scanner(System.in);
        logIn();
        loginMenu();


        while(true){
            if(actionMenu())
                break;
        }


    }




}
