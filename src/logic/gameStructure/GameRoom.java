package logic.gameStructure;

import logic.actionSpaces.ActionSpace;
import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.board.Color;
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

import java.io.Serializable;
import java.util.*;

/**
 * Created by IBM on 06/06/2017.
 */
public class GameRoom implements Serializable{

    private transient Game game = new Game();

    private transient Board board;

    private transient ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];

    private transient WinnerElector winnerElector = new WinnerElector();

    private transient Stack<Card> deck;

    private transient HashMap<String, RemotePlayer> players = new HashMap<>(); //key: playerId

    private transient String currentPlayer = null;

    private transient int turnNumber = 1;
    private transient int periodNumber = 1;

    private boolean gameStarted = false;

    transient ArrayList<String> turnOrder = new ArrayList<>();
    private int i = 0;




    //private final int NUMBER_OF_PLAYERS;

    private transient LimitedInteger numberOfPlayers;

    public GameRoom(int NUMBER_OF_PLAYERS) {
        //this.NUMBER_OF_PLAYERS = NUMBER_OF_PLAYERS;
        initializeDeck();

        board = new Board(NUMBER_OF_PLAYERS);
        board.setCardsOnBoard(deck);
        try {
            numberOfPlayers = new LimitedInteger(NUMBER_OF_PLAYERS, 0, 0);
        } catch (LimitedValueOffRangeException e){
            System.out.println("Could not initialize numberOfPlayers");
        }
        //initializeDeck();
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

            if (numberOfPlayers.getValue() == board.getMAX_NUMBER_OF_PLAYERS()) {
                setValueOfFamilyMembers();
                initializeTurnOrder();
                currentPlayer = turnOrder.get(0);
                gameStarted = true;
            }



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
        if(currentPlayer == null){
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.NOT_ENOUGH_PLAYERS);
            return;
        }

        if(!currentPlayer.equals(playerId)) {
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.WAIT_YOUR_TURN);
            return;
        }
        ResponseCode responseCode = game.selectionFamilyMember(familyMember, players.get(playerId));
        players.get(playerId).notifyRequestHandleOutcome(responseCode);
        if (canPlaceFamilyMember()) puttingFamilyMemberOnActionSpace(playerId);
    }

    public void selectActionSpace(String actionSpaceId, String playerId) {
        if(currentPlayer == null){
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.NOT_ENOUGH_PLAYERS);
            return;
        }

        if(!currentPlayer.equals(playerId)) {
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.WAIT_YOUR_TURN);
            return;
        }
        ResponseCode responseCode = game.selectionActionSpace(actionSpaceId, players.get(playerId), getBoard());
        players.get(playerId).notifyRequestHandleOutcome(responseCode);
        if(canPlaceFamilyMember()) puttingFamilyMemberOnActionSpace(playerId);


    }

    public void useSlaves(int quantity, String playerId) {
        if(currentPlayer == null){
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.NOT_ENOUGH_PLAYERS);
            return;
        }

        if(!currentPlayer.equals(playerId)) {
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.WAIT_YOUR_TURN);
            return;
        }
        ResponseCode responseCode = game.useSlaves(players.get(playerId),  quantity);
        players.get(playerId).notifyRequestHandleOutcome(responseCode);


    }

    private void puttingFamilyMemberOnActionSpace(String playerId) {

        RemotePlayer player = players.get(playerId);

        ResponseCode responseCode = game.puttingFamilyMemberOnActionSpace(player);
        useCouncilFavours(player);
        useExtraAction(player);
        updatePlayersView();

        //players.get(playerId).notifyRequestHandleOutcome(responseCode);

        if(responseCode == ResponseCode.OK) updatePlayersView();
/*//todo leva il commento dopo avere sistemato i turni ( gestire vaticano)
        if(turnNumber == 2)
            dealWithVatican(currentPlayer, periodNumber + 2, tassels[periodNumber-1]);*/
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
        for(RemotePlayer tmp : players.values()){
            tmp.updateView(board, players.values());
        }

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
            setValueOfFamilyMembers();

        }
    }

    public void changeCurrentPlayer(){
        for(int i = 0; i < turnOrder.size(); i++){
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

    public void initializeTurnOrder(){
        for (Player tmp: players.values()) {
            turnOrder.add(tmp.getId());
        }
    }
    public boolean getGameStarted(){
        return gameStarted;
    }

    public void setValueOfFamilyMembers(){
        game.throwDice(board);

        for(Player tmp: players.values()){
            for(FamilyMember tmp1: tmp.getFamilyMembers()){
                if(tmp1.getColor().equals(Color.BLACK))
                    tmp1.setValue(getBoard().getBlackDice());
                if(tmp1.getColor().equals(Color.WHITE))
                    tmp1.setValue(getBoard().getWhiteDice());
                if(tmp1.getColor().equals(Color.RED))
                    tmp1.setValue(getBoard().getRedDice());
            }
        }
    }












}
