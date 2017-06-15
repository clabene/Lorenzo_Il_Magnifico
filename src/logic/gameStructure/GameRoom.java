package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.cards.Card;
import logic.exceptions.LimitedValueOffRangeException;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.interfaces.Gainable;
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

    private Board board;

    private ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];

    private WinnerElector winnerElector = new WinnerElector();

    private Stack<Card> deck;

    private HashMap<String, RemotePlayer> players; //key: playerId

    private String currentPlayer ;

    private int turnNumber = 1;
    private int periodNumber = 1;

    ArrayList<String> turnOrder = new ArrayList<>();




    //private final int NUMBER_OF_PLAYERS;

    private LimitedInteger numberOfPlayers;

    public GameRoom(int NUMBER_OF_PLAYERS) {
        //this.NUMBER_OF_PLAYERS = NUMBER_OF_PLAYERS;
        initializeDeck();
        board = new Board(NUMBER_OF_PLAYERS);
        board.setCardsOnBoard(deck);
        try {
            numberOfPlayers = new LimitedInteger(NUMBER_OF_PLAYERS, 1, 1);
        } catch (LimitedValueOffRangeException e){
            System.out.println("Could not initialize numberOfPlayers");
        }
        initializeDeck();
    }

    private void initializeDeck(){
        CardSetupHandler cartSetupHandler = new CardSetupHandler();
        //this.deck = cartSetupHandler.readFromFile();
        this.deck = cartSetupHandler.prova();
    }

    public void addPlayerToRoom(RemotePlayer remotePlayer) throws LimitedValueOffRangeException{
        numberOfPlayers.increment();

        remotePlayer.setGameRoom(this);
        remotePlayer.setBoard(board);
        players.put(remotePlayer.getId(), remotePlayer);

        updatePlayersView();

    }

    public Board getBoard() {
        return board;
    }

    private boolean checkIfPlayerHasAvailableAction(Player player){
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

        if(turnNumber == 2)
            dealWithVatican(currentPlayer, periodNumber + 2, tassels[periodNumber-1]);
        changeCurrentPlayer();
        changeTurn();

        //----------------------

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
        remotePlayer.selectActionSpaceForExtraAction(remotePlayer.getExtraAction().getActionSpaces());
    }

    public void doExtraAction(RemotePlayer remotePlayer, ActionSpace actionSpace){
        ResponseCode responseCode = game.playingExtraAction(remotePlayer, remotePlayer.getExtraAction().getFamilyMemberValue(), actionSpace);

        if(responseCode == ResponseCode.GENERIC_ERROR) restoreArea(actionSpace);

        remotePlayer.setExtraAction(null);
    }
    private void restoreArea(ActionSpace actionSpace) {
        Card card = ( (TowerActionSpace) actionSpace).getCard();
        actionSpace = new TowerActionSpace(actionSpace.getMinValueToPlaceFamiliar(), actionSpace.getBonus().toArray(new Gainable[actionSpace.getBonus().size()]));
        ((TowerActionSpace)actionSpace).setCard(card);
    }


    private void updatePlayersView() {
        for(RemotePlayer tmp : players.values())
            tmp.updateView(board, players.values());
    }

    //todo use this to handle turn switching
    private void getNextTurnOrder() {
        game.gettingNextTurnOrder(turnOrder, board);
    }

    public void dealWithVatican(String playerId, int minFaithPoints, ExcommunicationTassel tassel){
        if(!game.hasEnoughFaithPoints(players.get(playerId), minFaithPoints)){
            game.takeExcommunication(players.get(playerId), tassel, true);
        }
        else {
            players.get(playerId).dealWithVatican(game.getCurrentPeriodNumber());
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.OK);
        }
    }

    public void takeExcommunication(Player player, boolean notSupporting){
        game.takeExcommunication(player, board.getTassels()[game.getCurrentPeriodNumber()], notSupporting);
    }


    private void changeTurn(){

        boolean toChangeTurn = true;


        for(Player tmp: players.values())
            if( !checkIfPlayerHasAvailableAction(tmp) )
                toChangeTurn = false;


        if(toChangeTurn){
            if(turnNumber == 2){
                periodNumber++;
                turnNumber = 0;
            }
            game.changeTurn();
            turnNumber++;
            for(Player tmp: players.values())
                game.takingBackFamilyMembers(tmp, board);
            getNextTurnOrder();
            setCardsOnBoard();
            game.throwDice(board);
        }

    }

    public void changeCurrentPlayer(){
        for(int i = 0; i < turnOrder.size()-1; i++){
            if(currentPlayer.equals(turnOrder.get(i))){
                currentPlayer = turnOrder.get(i+1);
                return;
            }
        }
        currentPlayer = turnOrder.get(0);
        return;
    }


    public void setCardsOnBoard(){
        board.setCardsOnBoard(deck);
    }












}
