package logic.gameStructure;

import javafx.print.PageLayout;
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
        //this.deck = cartSetupHandler.prova();
        this.deck = cartSetupHandler.shuffleHandle();
    }

    public void addPlayerToRoom(RemotePlayer remotePlayer) throws LimitedValueOffRangeException{
        numberOfPlayers.increment();

        remotePlayer.setGameRoom(this);
        remotePlayer.setBoard(board);
        players.put(remotePlayer.getId(), remotePlayer);

        if (numberOfPlayers.getValue() == board.getMAX_NUMBER_OF_PLAYERS()) {
            setValueOfFamilyMembers();
            inizializeTurnOrder();
            currentPlayer = turnOrder.get(0);
            gameStarted = true;
            for(RemotePlayer tmp: players.values()){
                if(tmp.getId().equals(currentPlayer))
                    tmp.setCurrentPlayer(true);
                tmp.notifyRequestHandleOutcome(ResponseCode.GAME_STARTED);
            }
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
        updatePlayersView();
        players.get(playerId).notifyRequestHandleOutcome(responseCode);


    }

    private void puttingFamilyMemberOnActionSpace(String playerId) {

        RemotePlayer player = players.get(playerId);

        ResponseCode responseCode = game.puttingFamilyMemberOnActionSpace(player);
        useCouncilFavours(player);
        useExtraAction(player);
        //updatePlayersView();

        //players.get(playerId).notifyRequestHandleOutcome(responseCode);

        if(responseCode == ResponseCode.OK) updatePlayersView();



        changeCurrentPlayer();
        changeTurn();


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

        remotePlayer.notifyRequestHandleOutcome(responseCode);



        remotePlayer.setExtraAction(null);
    }
    private void restoreArea(ActionSpace actionSpace) {
        Card card = ( (TowerActionSpace) actionSpace).getCard();
        actionSpace = new TowerActionSpace(actionSpace.getMinValueToPlaceFamiliar(), actionSpace.getBonus().toArray(new Gainable[actionSpace.getBonus().size()]));
        ((TowerActionSpace)actionSpace).setCard(card);
    }


    private void updatePlayersView() {
        ;
        for(RemotePlayer tmp : players.values()){
            tmp.updateView(board, players.values());

        }


    }

    //todo use this to handle turn switching
    private void getNextTurnOrder() {
        turnOrder = game.gettingNextTurnOrder(turnOrder, board);
    }

    public void dealWithVatican(String playerId, int minFaithPoints, ExcommunicationTassel tassel){
        if(!game.hasEnoughFaithPoints(players.get(playerId), minFaithPoints)){
            game.takeExcommunication(players.get(playerId), tassel, true);
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.EXCOMMUNICATION_TAKEN);
            players.get(playerId).setExcommunications(periodNumber-1, true);
        }
        else {
            //players.get(playerId).dealWithVatican(game.getCurrentPeriodNumber());
            players.get(playerId).dealWithVatican(periodNumber);
            players.get(playerId).notifyRequestHandleOutcome(ResponseCode.OK);
        }
    }

    public void takeExcommunication(Player player, boolean notSupporting){
        game.takeExcommunication(player, board.getTassels()[game.getCurrentPeriodNumber()], notSupporting);
    }


    private void changeTurn(){


        /*
        for(Player tmp: players.values()){
            if( !checkIfPlayerHasAvailableAction(tmp) /*|| tmp.getFamilyMembersAvailable().size() >0) return;*/
                //toChangeTurn = false;*/
        boolean toChangeTurn = true;
        for(Player tmp: players.values()){
            if(tmp.getFamilyMembersAvailable().size() >0)
                return;
        }

        if(toChangeTurn){
            if(turnNumber == 1){//todo qui dovrebbe essere due perchè sono due i turni
                for(Player tmp: players.values()){
                    System.out.println("quiii----zac");
                    dealWithVatican(tmp.getId(), periodNumber+2, board.getTassels()[periodNumber-1]);
                    game.takingBackFamilyMembers(tmp, board);//todo ho aggiunto questo stasera perchè non potevo andare avanti
                    getNextTurnOrder();
                    setCardsOnBoard();
                    setValueOfFamilyMembers();
                    updatePlayersView();


                }
                if(periodNumber == 3){
                    WinnerElector winnerElector = new WinnerElector();
                    ArrayList<Player> playersWinner = new ArrayList<>();
                    for(Player tmp: players.values()){
                        playersWinner.add(tmp);
                    }
                    winnerElector.setPlayers(playersWinner);
                    players.get(winnerElector.getWinner()).setWinner(true);
                    updatePlayersView();

                }
                periodNumber++;
                turnNumber = 0;
            }
            //game.changeTurn();//todo ho commentato questo il 23/06/17
            turnNumber++;
            for(Player tmp: players.values())
                game.takingBackFamilyMembers(tmp, board);
            getNextTurnOrder();
            setCardsOnBoard();
            setValueOfFamilyMembers();

        }
    }

    public void changeCurrentPlayer(){
        for(int i = 0; i < turnOrder.size()-1; i++){
            if(currentPlayer.equals(turnOrder.get(i))){
                currentPlayer = turnOrder.get(i+1);
                for(RemotePlayer tmp: players.values()) {
                    if (tmp.getId().equals(currentPlayer))
                        tmp.setCurrentPlayer(true);
                    else
                        tmp.setCurrentPlayer(false);
                }
                updatePlayersView();
                return;

            }
        }
        currentPlayer = turnOrder.get(0);
        if(!checkIfPlayerHasAvailableAction(players.get(currentPlayer))){
            System.out.println("player can't play");// todo forse fare messaggio che va al client
            changeCurrentPlayer();
        }

        for(RemotePlayer tmp: players.values()) {
            if (tmp.getId().equals(currentPlayer))
                tmp.setCurrentPlayer(true);
            else
                tmp.setCurrentPlayer(false);
        }
        updatePlayersView();
        return;
    }


    public void setCardsOnBoard(){
        board.setCardsOnBoard(deck);
    }

    public void inizializeTurnOrder(){
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
            tmp.setBlackFM(getBoard().getBlackDice());
            tmp.setWhiteFM(getBoard().getWhiteDice());
            tmp.setRedFM(getBoard().getRedDice());
        }
    }

    public void leaveGame(String id){

        WinnerElector winnerElector = new WinnerElector();
        ArrayList<Player> playersWinner = new ArrayList<>();
        for(Player tmp: players.values()) {
            if (!tmp.getId().equals(id))
                playersWinner.add(tmp);
        }

        winnerElector.setPlayers(playersWinner);
        players.get(winnerElector.getWinner()).setWinner(true);
        players.get(id).setPlayerLeft(true);

        updatePlayersView();



    }












}
