package userInterface;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.board.Color;
import logic.exceptions.FamilyMemberSelectionException;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.Wood;
import network.ResponseCode;
import network.client.Client;
import network.client.ClientInterface;

import java.util.Scanner;

/**
 * Created by IBM on 14/06/2017.
 */
public class CliClient extends AbstractUserInterfaceClient {

    public CliClient(ClientInterface clientController) {
        super(clientController);
    }


    @Override
    public void successfullyLoggedIn() {
        System.out.println("log in successful");
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

    public void loginMenu(){
        System.out.println("What do you want to do?\n 1)Join Game \n 2) Create new room \n");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                joinGame();
            case 2:
                System.out.println("How many players?");
                createNewRoom(scanner.nextInt());

        }



    }

    public void actionMenu(){
        System.out.println("What do you want to do?\n 1)Select Family Member \n 2)Select Action Space\n 3)Use slaves\n ");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()){
            case 1: familyMemberMenu();
                break;
                    //actionSpaceMenu();
            case 2: actionSpaceMenu();
                    familyMemberMenu();
            case 3: slavesMenu();

        }

    }


    public void familyMemberMenu(){
        System.out.println("Which family member do you want to select?");
        getClientController().getView().printFamilyMembersAvailable(getId());

        //getClientController().selectFamilyMember(getClientController().getView().printFamilyMembersAvailable(getId()));

    }

    public void actionSpaceMenu(){
        System.out.println("Which action space do you want to select?");
        getClientController().selectActionSpace(getClientController().getView().printActionSpaces());

    }

    public void vaticanMenu(){
        System.out.println();
    }

    public void slavesMenu(){
        System.out.println("How many slaves do you want to use?");
        getClientController().useSlaves(getClientController().getView().printSlaves(getId()));
    }



    public void go(){
        Scanner scanner  = new Scanner(System.in);
        logIn();
        loginMenu();

        while(true){
            actionMenu();
        }


    }



}
