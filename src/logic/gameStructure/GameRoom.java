package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.exceptions.LimitedValueOffRangeException;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.CouncilFavour;
import logic.utility.CardSetupHandler;
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
    private Stack<Card> deck;

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
        initializeDeck();

        //todo board changes based on the number of players in the game
    }

    private void initializeDeck(){
        CardSetupHandler cartSetupHandler = new CardSetupHandler();
        this.deck = cartSetupHandler.readFromFile();
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

    public boolean checkIfPlayerHasAvailableAction(Player player){
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
        useExtraAction(player);

        //players.get(playerId).notifyRequestHandleOutcome(responseCode);

        if(responseCode == ResponseCode.OK) updatePlayersView();

    }

    private void useCouncilFavours(RemotePlayer remotePlayer) {
        Iterator iterator = remotePlayer.getPlank().getCouncilFavours().iterator();
        while(iterator.hasNext()) {
            remotePlayer.selectCouncilFavour(((CouncilFavour) iterator.next()).getNumberOfFavours() );
            iterator.remove();
        }
    }
    private void useExtraAction(RemotePlayer remotePlayer){
        if(remotePlayer.getExtraAction() == null) return;

        ActionSpace actionSpace = remotePlayer.selectActionSpaceForExtraAction(remotePlayer.getExtraAction().getActionSpaces());
        ResponseCode responseCode = game.playingExtraAction(remotePlayer, remotePlayer.getExtraAction().getFamilyMemberValue(), actionSpace);

        remotePlayer.setExtraAction(null);

        remotePlayer.notifyRequestHandleOutcome(responseCode);
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
            game.takeExcommunication(players.get(playerId), tassel, true);
            players.get(playerId).notifyRequestHandleOutcome();
        }
        else {
            players.get(playerId).dealWithVatican();
        }
    }

    public void takeExcomunication(Player player, ExcommunicationTassel tassel, boolean choice){
        game.takeExcommunication(player, tassel, choice);
    }

    public void changeTurn(){
        int i = 0;

        for(Player tmp: players.values()){
            if(checkIfPlayerHasAvailableAction(tmp) || tmp.getFamilyMembersAvailable().size() ==  0)
                i++;
        }

        if (i == players.size()) {
            if(TURN_NUMBER == 2){
                PERIOD_NUMBER++;
                Period Period = new Period();
                Turn turn = new Turn();
                TURN_NUMBER = 1;
            }
            else{
                TURN_NUMBER++;
                Turn turn = new Turn();
            }
        }
    }

    public void setCardsOnBoard(){
        board.setCardsOnBoard(deck);
    }




}
