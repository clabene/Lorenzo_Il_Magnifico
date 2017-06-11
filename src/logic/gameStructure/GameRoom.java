package logic.gameStructure;

import logic.board.Board;
import logic.exceptions.LimitedValueOffRangeException;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.utility.LimitedInteger;
import network.ResponseCode;
import network.server.RemotePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom {

    private Game game = new Game();
    private Board board = new Board();
    private HashMap<String, RemotePlayer> players; //key: playerId

    private final int NUMBER_OF_PLAYERS;

    private LimitedInteger numberOfPlayers;

    public GameRoom(int NUMBER_OF_PLAYERS) {
        this.NUMBER_OF_PLAYERS = NUMBER_OF_PLAYERS;
        try {
            numberOfPlayers = new LimitedInteger(NUMBER_OF_PLAYERS, 1, 1);
        } catch (LimitedValueOffRangeException e){
            System.out.println("Could not initialize numberOfPlayers");
        }

        //todo board changes based on the number of players in the game
    }

    public void addPlayerToRoom(RemotePlayer remotePlayer) throws LimitedValueOffRangeException{
        numberOfPlayers.increment();

        remotePlayer.setGameRoom(this);
        remotePlayer.setBoard(board);
        players.put(remotePlayer.getId(), remotePlayer);
    }

    public Board getBoard() {
        return board;
    }

    public boolean checkIfPlayerHasAvaialbeAction(Player player){
        return game.checkingIfPlayable(player);
    }

    private boolean canPlaceFamilyMember(){
        return game.getSelectedActionSpace() != null && game.getSelectedFamilyMember() != null;
    }

    public void selectFamilyMember(FamilyMember familyMember, String playerId){
        ResponseCode responseCode = game.selectionFamilyMember(familyMember, players.get(playerId));
        players.get(playerId).notifyRequestHandleOutcome(responseCode);

        if(canPlaceFamilyMember()) puttingFamilyMemberOnActionSpace(playerId);
    }

    public void selectActionSpace(String actionSpaceId, String playerId) {
        ResponseCode responseCode = game.selectionActionSpace(actionSpaceId, players.get(playerId), getBoard());

        players.get(playerId).notifyRequestHandleOutcome(responseCode);


        if(canPlaceFamilyMember()) puttingFamilyMemberOnActionSpace(playerId);
    }

    public void useSlaves(FamilyMember familyMember, int quantity, String playerId){
        ResponseCode responseCode = game.useSlaves(players.get(playerId), familyMember,  quantity);

        players.get(playerId).notifyRequestHandleOutcome(responseCode);
    }

    private void puttingFamilyMemberOnActionSpace(String playerId){

        RemotePlayer player = players.get(playerId);

        ResponseCode responseCode = game.puttingFamilyMemberOnActionSpace(player);
        useCouncilFavours(player);

        players.get(playerId).notifyRequestHandleOutcome(responseCode);

    }

    private void useCouncilFavours(RemotePlayer remotePlayer){
        Iterator iterator = remotePlayer.getPlank().getCouncilFavours().iterator();
        while(iterator.hasNext()) {
            remotePlayer.selectCouncilFavour();
            iterator.remove();
        }
    }

    //todo use this to handle turn switching
    private ArrayList<Player> getNextTurnOrder() {
        return game.gettingNextTurnOrder( (ArrayList) players.values(), board);
    }


}
