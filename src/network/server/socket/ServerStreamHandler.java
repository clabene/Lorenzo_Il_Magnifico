package network.server.socket;

import logic.player.FamilyMember;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by IBM on 09/06/2017.
 */
public class ServerStreamHandler {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private SocketPlayer player;

    private HashMap<String, ResponseInnerInterface> responseMap = new HashMap<>();

    public ServerStreamHandler(ObjectOutputStream output, ObjectInputStream input, SocketPlayer player){
        this.input = input;
        this.output = output;
        this.player = player;
        fillMap();
    }

    private void fillMap(){
        //todo use constants (make an Enum maybe) instead of String
        responseMap.put("LOG_IN_REQUEST", this::tryToLogInClient);
        responseMap.put("JOIN_GAME_REQUEST", player::tryToJoinGame);
        responseMap.put("CREATE_NEW_ROOM_REQUEST", this::tryToCreateNewRoom);
        responseMap.put("FAMILY_MEMBER_SELECTION_REQUEST", this::familyMemberSelectionRespond);
        responseMap.put("ACTION_SPACE_SELECTION_REQUEST", this::actionSpaceSelectionRespond);
        responseMap.put("USE_SLAVES_REQUEST", this::useSlaveRespond);
        responseMap.put("LEAVE_GAME_REQUEST",this::tryToLeaveGame);

    }

    public void tryToLeaveGame(){
        player.leaveGame();
    }

    public void tryToLogInClient(){
        try {
            String id = (String) input.readObject();
            player.tryToLogInClient(id);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not handle LOG_IN_REQUEST");
        }
    }

    public void tryToCreateNewRoom(){
        try {
            int NUMBER_OF_PLAYERS = (Integer) input.readObject(); //check if boxing needed
            player.tryToJoinNewRoom(NUMBER_OF_PLAYERS);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not handle CREATE_NEW_ROOM_REQUEST");
        }
    }

    public void familyMemberSelectionRespond(){
        try {
            FamilyMember familyMember = (FamilyMember) input.readObject();
            player.selectFamilyMember(familyMember);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not handle FAMILY_MEMBER_SELECTION_REQUEST");
        }
    }

    public void actionSpaceSelectionRespond(){
        try {
            String actionSpaceId = (String) input.readObject();
            player.selectActionSpace(actionSpaceId);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not handle ACTION_SPACE_SELECTION_REQUEST");
        }
    }

    public void useSlaveRespond() {
        try {
            int quantity = (Integer) input.readObject();
            player.useSlaves(quantity);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not handle USE_SLAVES_REQUEST");
        }
    }


    public void respond(String s) {
        ResponseInnerInterface handler = responseMap.get(s);
        if (handler != null) {
            handler.operate();
        }
        else System.out.println("error");
    }


    @FunctionalInterface
    public interface ResponseInnerInterface {
        void operate();
    }



}
