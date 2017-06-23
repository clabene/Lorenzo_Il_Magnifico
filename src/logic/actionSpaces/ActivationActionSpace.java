package logic.actionSpaces;

import logic.cards.Card;
import logic.cards.LandCard;
import logic.cards.cardEffects.ReceiveGainablesEffect;
import logic.gameStructure.PeriodNumber;
import logic.player.FamilyMember;
import logic.player.Player;
import logic.resources.Wood;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by IBM on 19/05/2017.
 */
public class ActivationActionSpace extends ActionSpace {

    private ActivationActionSpaceType activationType;
    public ArrayList<Card> cards = new ArrayList<>();

    public ActivationActionSpace( int MAX_NUMBER_OF_FAMILIARS, ActivationActionSpaceType activationType ){
        super(MAX_NUMBER_OF_FAMILIARS, ActionSpaceType.ACTIVATION, 1);
        this.activationType = activationType;
    }

    private void setCards(Player player){
        if(activationType == ActivationActionSpaceType.PRODUCTION)
            Collections.addAll(cards, player.getPlank().getCards().getBuildingCards());
        else if(activationType == ActivationActionSpaceType.HARVEST)
            Collections.addAll(cards, player.getPlank().getCards().getLandCards());
        /*
        Iterator iterator = cards.iterator();
        while(iterator.hasNext())
            if(iterator.next() == null)
                iterator.remove();
        */
    }

/*
    private void removeNotValidCard(Card card) {
        if(card.getActivationValue() != null && card.getActivationValue() > getLastFamilyMemberAdded().getValue())
            cards.remove(card);
    }*/

    private void validateCardEffects(){
        ArrayList<Card> act_cards = new ArrayList<>();

        for(Card tmp : cards){
            if(tmp != null && !(tmp.getActivationValue() != null && tmp.getActivationValue() > getLastFamilyMemberAdded().getValue()))
                act_cards.add(tmp);
        }

        cards = act_cards;
                //removeNotValidCard(tmp);


    }

    private void showCardEffects(){
        System.out.println("What card effect do you want to activate?");
        int i = 0;
        System.out.println("0 No effect");
        for(Card tmp : cards) {
            i++;
            System.out.println(i + " " + tmp.getPermanentEffect());
        }
    }

    private Card getSelectedCard(){
        Scanner input = new Scanner(System.in);
        int fromInput = input.nextInt()-1;
        if(fromInput == -1) return null;
        return cards.get(input.nextInt()-1);
    }

    public ActivationActionSpaceType getActivationType() {
        return activationType;
    }

    private void activateCardEffect(Card card, Player player){
        if( card == null)
            return;
        card.getPermanentEffect().activate(player);
    }

    public boolean action(Player player){
        player.getPlank().setToUseSeparateResources(true);

        setCards(player);
        validateCardEffects();

            Card selectedCard;
    /*
            do {
                showCardEffects();
                selectedCard = getSelectedCard();
                activateCardEffect(selectedCard, player);
            } while(selectedCard != null);
    */
            for (Card tmp : cards) {
                if (tmp != null)
                    activateCardEffect(tmp, player);
            }

        player.getPlank().dump();
        player.getPlank().setToUseSeparateResources(false);

        return true;
    }



}
