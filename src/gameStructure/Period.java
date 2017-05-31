package gameStructure;

import board.Board;
import cards.Card;
import cards.CardType;
import com.google.gson.Gson;
import player.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/**
 * Created by Pinos on 25/05/2017.
 */
public class Period {

    ArrayList<Player> players;
    Stack<Card> cards;


    public Period( ArrayList<Player> players){
        this.board = board;
        this.players = players;

    }

    public void stardPeriod(){
        Stack<Card> PeriodDeck = setPeriodDeck();
        Turn firstTurn= new Turn(players);
        firstTurn.playTurn(PeriodDeck);

        Turn secondTurn = new Turn(players);
        secondTurn.playTurn(PeriodDeck);

        //todo church  operations


    }

    /*
    the input is a json file were there are cards setted such that
    each row correspond to a card
    */

    public Card cardJsonStringReader(File file){
        try{
            //todo handle file json in the right way (divided by period)
            Gson gson = new Gson();
            FileReader r = new FileReader("a.txt");
            BufferedReader rb = new BufferedReader(r);
            String s = rb.readLine();
            return gson.fromJson(s, Card.class);

        }catch (IOException e){}

    }

    /*
    return the cards of the same type and of the same period ( 32 cards for each period)
     */
    public Stack<Card> getCardsOfSameType (CardType cardType){
        Stack<Card> cardsOfSameType = new Stack<>();
        Card card;
        for(int i = 0; i < 32; i++){
            card = cardJsonStringReader(file);
            if(card.getCardType() == cardType)
                cardsOfSameType.push(card);

        }

        return cardsOfSameType;
    }

    /*
    input decks ( Stack of cards) (one for each typeCard), the method shuffles the input
     */

    public void shuffleDecks(Stack<Card>  deck1, Stack<Card>  deck2, Stack<Card>  deck3, Stack<Card>  deck4){
        Collections.shuffle(deck1);
        Collections.shuffle(deck2);
        Collections.shuffle(deck3);
        Collections.shuffle(deck4);

    }

    /*
    input four decks ( stack of cards) put them into one bigger stack
     */

    public Stack<Card> mergeDecks(Stack<Card>  deck1, Stack<Card>  deck2, Stack<Card>  deck3, Stack<Card>  deck4){
        Stack<Card> cards = new Stack<>();
        insertCardFromDeckToDeck(deck1, cards);
        insertCardFromDeckToDeck(deck2, cards);
        insertCardFromDeckToDeck(deck3, cards);
        insertCardFromDeckToDeck(deck4, cards);

        return cards;

    }

    /*
    input deck from which the method take four cards, put them in a stack and return it
     */

    public Stack<Card> getFourCardsFromDeck(Stack<Card> deck){
        Stack<Card> cards = new Stack<>();
        for(int i = 0; i < 4; i++){
            cards.push(deck.pop());
        }
        return cards;
    }

    /*
    return a big deck composed by two decks (one for each turn of the period)
    the deck returned is composed in the right way for the game
     */

    public Stack<Card> setPeriodDeck(){
        Stack<Card> LandDeck = getCardsOfSameType(CardType.LAND);
        Stack<Card> PersonDeck = getCardsOfSameType(CardType.PERSON);
        Stack<Card> VentureDeck = getCardsOfSameType(CardType.VENTURE);
        Stack<Card> BuildingDeck = getCardsOfSameType(CardType.BUILDING);

        shuffleDecks(LandDeck,   PersonDeck,   BuildingDeck,   VentureDeck);


        Stack<Card> cards1 = mergeDecks(getFourCardsFromDeck(LandDeck), getFourCardsFromDeck(PersonDeck), getFourCardsFromDeck(BuildingDeck), getFourCardsFromDeck(VentureDeck));
        Stack<Card> cards2 = mergeDecks(getFourCardsFromDeck(LandDeck), getFourCardsFromDeck(PersonDeck), getFourCardsFromDeck(BuildingDeck), getFourCardsFromDeck(VentureDeck));
        return mergeDecks(cards1, cards2, null, null);
    }


    /*
    put the cards of the "fromdeck" and put them in "todeck"
     */
    public void insertCardFromDeckToDeck(Stack<Card> fromdeck, Stack<Card> todeck){
        while(!fromdeck.empty()){
            todeck.push(fromdeck.pop());
        }
    }










}
