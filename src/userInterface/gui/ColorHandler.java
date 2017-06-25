package userInterface.gui;

import userInterface.PlayerColor;

import java.util.HashMap;

/**
 * Created by Pinos on 25/06/2017.
 */
public class ColorHandler {
    private static HashMap<String, PlayerColor> colorPlayers = new HashMap<>();


    public static PlayerColor getColor(String playerId){
        return colorPlayers.get(playerId);
    }

    public static void addPlayer(String playerId){
        PlayerColor playerColor;
        if( colorPlayers.values().size() == 0 ) playerColor = PlayerColor.RED;
        else if( colorPlayers.values().size() == 1 ) playerColor = PlayerColor.GREEN;
        else if( colorPlayers.values().size() == 2 ) playerColor = PlayerColor.BLUE;
        else playerColor = PlayerColor.YELLOW;
        colorPlayers.put(playerId, playerColor);
    }

}
