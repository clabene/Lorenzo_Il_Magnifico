package logic.gameStructure;

import logic.board.Board;
import logic.cards.Card;
import logic.exceptions.LimitedValueOffRangeException;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.utility.LimitedInteger;
import network.ResponseCode;
import network.server.RemotePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom {

    private Game game = new Game();
    private Board board = new Board();
    private Stack<Card> deck = new Stack();

    private HashMap<String, RemotePlayer> players; //key: playerId
    private int TURN_NUMBER;
    private int PERIOD_NUMBER;

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

    private boolean canPlaceFamilyMember() {
        return game.getSelectedActionSpace() != null && game.getSelectedFamilyMember() != null;
    }

    public void selectFamilyMember(FamilyMember familyMember, String playerId) {
        ResponseCode responseCode = game.selectionFamilyMember(familyMember, players.get(playerId));
        players.get(playerId).notifyRequestHandleOutcome(responseCode);

        if(canPlaceFamilyMember()) puttingFamilyMemberOnActionSpace(playerId);
    }

    public void selectActionSpace(String actionSpaceId, String playerId) {
        ResponseCode responseCode = game.selectionActionSpace(actionSpaceId, players.get(playerId), getBoard());

        players.get(playerId).notifyRequestHandleOutcome(responseCode);


        if(canPlaceFamilyMember()) puttingFamilyMemberOnActionSpace(playerId);
    }

    public void useSlaves(int quantity, String playerId) {
        ResponseCode responseCode = game.useSlaves(players.get(playerId),  quantity);

        players.get(playerId).notifyRequestHandleOutcome(responseCode);
    }

    private void puttingFamilyMemberOnActionSpace(String playerId) {

        RemotePlayer player = players.get(playerId);

        ResponseCode responseCode = game.puttingFamilyMemberOnActionSpace(player);
        useCouncilFavours(player);

        if(responseCode == ResponseCode.OK) updatePlayersView();

        players.get(playerId).notifyRequestHandleOutcome(responseCode);

    }

    private void useCouncilFavours(RemotePlayer remotePlayer) {
        Iterator iterator = remotePlayer.getPlank().getCouncilFavours().iterator();
        while(iterator.hasNext()) {
            remotePlayer.selectCouncilFavour();
            iterator.remove();
        }
    }

    private void updatePlayersView() {
        for(RemotePlayer tmp : players.values())
            tmp.updateView(board, players.values());
    }

    //todo use this to handle turn switching
    private ArrayList<Player> getNextTurnOrder() {
        return game.gettingNextTurnOrder( (ArrayList) players.values(), board);
    }

    public void dealWithVatican(String playerId, int minFaithPoints, ExcommunicationTassel tassel){
        if(!game.hasEnoughFaithPoints(players.get(playerId), minFaithPoints)){
            game.takeExcomunication(players.get(playerId), tassel, true);
            players.get(playerId).notifyRequestHandleOutcome();
        }
        else
            boolean choice = players.get(playerId).dealWithVatican();// devo scegliere cosa fare
            game.takeExcomunication(players.get(playerId), tassel, choice);

    }

    public void changeTurn(){
        int i = 0;

        for(Player tmp: players.values()){
            if(checkIfPlayerHasAvaialbeAction(tmp) || tmp.getFamilyMembersAvailable().size() ==  0)
                i++;
        }

        if (i == players.size()) {
            if(TURN_NUMBER == 2){
                PERIOD_NUMBER++;
                Period Period = new Period();
                Turn turn = new Turn;
                TURN_NUMBER = 1;
            }
            else{
                TURN_NUMBER++;
                Turn turn = new Turn;
            }
        }
    }

    public void setCardsOnBoard(){
        board.setCardsOnBoard(deck);
    }




}
