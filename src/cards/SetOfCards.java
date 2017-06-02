package cards;

import exceptions.LimitedValueOffRangeException;
import gameStructure.PeriodNumber;
import utility.LimitedInteger;
import utility.StaticVariables;

/**
 * Created by IBM on 14/05/2017.
 */
public class SetOfCards {

    private final int LAND_CARD_INDEX = 0;
    private final int BUILDING_CARD_INDEX = 1;
    private final int PERSON_CARD_INDEX = 2;
    private final int VENTURE_CARD_INDEX = 3;

    private Card[][] cards = new Card[StaticVariables.NUMBER_OF_CARD_TYPES][StaticVariables.MAX_NUMBER_OF_CARDS_PER_TYPE_ON_PLANK];

    /*
    * It is quite useless to have LimitedInteger's here, because the array is already limiting the number of possible values.
    * Also, ArrayIndexOutOfBoundsException is called before LimitedIntegerValueOffRangeException
    * However, having LimitedInteger instead of just int makes the code cleaner and safer
    * */

    private LimitedInteger numberOfLandCards;
    private LimitedInteger numberOfBuildingCards;
    private LimitedInteger numberOfPersonCards;
    private LimitedInteger numberOfVentureCards;

    public SetOfCards() {
        try {
            numberOfLandCards = new LimitedInteger(StaticVariables.MAX_NUMBER_OF_CARDS_PER_TYPE_ON_PLANK, 0, 0);
            numberOfBuildingCards = new LimitedInteger(StaticVariables.MAX_NUMBER_OF_CARDS_PER_TYPE_ON_PLANK, 0, 0);
            numberOfPersonCards = new LimitedInteger(StaticVariables.MAX_NUMBER_OF_CARDS_PER_TYPE_ON_PLANK, 0, 0);
            numberOfVentureCards = new LimitedInteger(StaticVariables.MAX_NUMBER_OF_CARDS_PER_TYPE_ON_PLANK, 0, 0);
        } catch (LimitedValueOffRangeException e) {
            e.printStackTrace();
        }
    }
    /*
    public void cardAdded(Card card) {
        try {
            if (card instanceof LandCard) {
                cards[LAND_CARD_INDEX][numberOfLandCards.getValue()] = card;
                numberOfLandCards.increment();
            } else if (card instanceof BuildingCard) {
                cards[BUILDING_CARD_INDEX][numberOfBuildingCards.getValue()] = card;
                numberOfBuildingCards.increment();
            } else if (card instanceof PersonCard) {
                cards[PERSON_CARD_INDEX][numberOfPersonCards.getValue()] = card;
                numberOfPersonCards.increment();
            } else if (card instanceof VentureCard) {
                cards[VENTURE_CARD_INDEX][numberOfVentureCards.getValue()] = card;
                numberOfVentureCards.increment();
            }
        } catch (LimitedValueOffRangeException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Action denied, no room for the selected card");
        }
    }
    */
    public void cardAdded(Card card) throws ArrayIndexOutOfBoundsException, LimitedValueOffRangeException{
        if (card instanceof LandCard) {
            cards[LAND_CARD_INDEX][numberOfLandCards.getValue()] = card;
            numberOfLandCards.increment();
        } else if (card instanceof BuildingCard) {
            cards[BUILDING_CARD_INDEX][numberOfBuildingCards.getValue()] = card;
            numberOfBuildingCards.increment();
        } else if (card instanceof PersonCard) {
            cards[PERSON_CARD_INDEX][numberOfPersonCards.getValue()] = card;
            numberOfPersonCards.increment();
        } else if (card instanceof VentureCard) {
            cards[VENTURE_CARD_INDEX][numberOfVentureCards.getValue()] = card;
            numberOfVentureCards.increment();
        }
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (Card[] tmpColumn : cards) {
            for(Card tmp : tmpColumn){
                if(tmp != null)
                toReturn += tmp.toString()+"   ";
            }
        }
        return toReturn;
    }

    public Card[] getLandCards() {
        return cards[LAND_CARD_INDEX];
    }
    public Card[] getBuildingCards() {
        return cards[BUILDING_CARD_INDEX];
    }
    public Card[] getPersonCards() {
        return cards[PERSON_CARD_INDEX];
    }
    public Card[] getVentureCards() {
        return cards[VENTURE_CARD_INDEX];
    }

    public static void main(String args[]) {
        SetOfCards s = new SetOfCards();
        try {
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
            s.cardAdded(new LandCard("pp", PeriodNumber.SECOND, 2, null, null));
        } catch (ArrayIndexOutOfBoundsException | LimitedValueOffRangeException e){
            System.out.println("ninsivs");
        }

    }


}

