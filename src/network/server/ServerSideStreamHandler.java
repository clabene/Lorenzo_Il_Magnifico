package network.server;

import logic.player.FamilyMember;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by IBM on 09/06/2017.
 */
public class ServerSideStreamHandler {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private SocketPlayer player;

    private HashMap<String, ResponseInnerInterface> responseMap = new HashMap<>();

    public ServerSideStreamHandler(ObjectOutputStream output, ObjectInputStream input, SocketPlayer player){
        this.input = input;
        this.output = output;
        this.player = player;
        fillMap();
    }

    private void fillMap(){
        responseMap.put("LOG_IN_REQUEST", this::tryToLogInClient);
        responseMap.put("FAMILY_MEMBER_SELECTION_REQUEST", this::familyMemberSelectionRespond);

    }


    public void tryToLogInClient(){
        try {
            String name = (String) input.readObject();
            String id = (String) input.readObject();
            player.tryToLogInClient(id);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not handle FAMILY_MEMBER_SELECTION_REQUEST");
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


    public void respond(String s) {
        ResponseInnerInterface handler = responseMap.get(s);
        if (handler != null) {
            handler.operate();
        }
        else System.out.println("error");
    }


    @FunctionalInterface
    private interface ResponseInnerInterface {
        void operate();
    }



}
