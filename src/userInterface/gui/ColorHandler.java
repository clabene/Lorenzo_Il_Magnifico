package userInterface.gui;

import java.util.HashMap;

/**
 * Created by Pinos on 25/06/2017.
 */
public class ColorHandler {
    private static HashMap<String, String> colorPlayers = new HashMap<>();





    public static String getPlayer(String colorId){
        return colorPlayers.get(colorId);
    }

    public void setPlayer(String colorId, String playerId){
        colorPlayers.put(colorId, playerId);
    }
}
