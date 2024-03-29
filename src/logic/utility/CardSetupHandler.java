package logic.utility;

import logic.actionSpaces.TowerActionSpace;
import logic.board.Board;
import logic.cards.Card;

import java.io.*;
import java.util.Collections;
import java.util.Stack;

import logic.actionSpaces.ActivationActionSpace;
import logic.actionSpaces.ActivationActionSpaceType;
import logic.bonuses.BonusOnCardTake;
import logic.bonuses.BonusOnFamilyMemberPlacement;
import logic.bonuses.MalusOnTowerBonuses;
import logic.cards.*;
import logic.cards.cardEffects.*;
import com.google.gson.*;
import logic.excommunicationTessels.ExcommunicationTassel;
import logic.excommunicationTessels.MalusOnFamilyMemberPlacementTessel;
import logic.excommunicationTessels.PointsTrackToZeroTessel;
import logic.gameStructure.PeriodNumber;
import logic.player.VictoryPoint;
import logic.pointsTracks.FaithPointsTrack;
import logic.pointsTracks.MilitaryPointsTrack;
import logic.resources.*;

/**
 * Created by Pinos on 07/06/2017.
 */
public class CardSetupHandler {

    Stack<Card> landCards = new Stack<>();
    Stack<Card> personCards = new Stack<>();
    Stack<Card> buildingCards = new Stack<>();
    Stack<Card> ventureCards = new Stack<>();
    Stack<Card> totalCards = new Stack<>();




    public void  cardDescription() {
        Stack<VentureCard> ventureCards = new Stack<>();
        Stack<LandCard> landCards = new Stack<>();
        Stack<BuildingCard> buildingCards = new Stack<>();
        Stack<PersonCard> personCards = new Stack<>();

        //venture primo periodo------------------------------------------------
        ventureCards.add(new VentureCard("Ingaggiare Reclute", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        ventureCards.add(new VentureCard("Riparare la Chiesa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Wood(), new Stone())), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(1))));
        ventureCards.add(new VentureCard("Innalzare una Statua", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2), new Wood(2))), new ReceiveGainablesEffect(new CouncilFavour(2)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        ventureCards.add(new VentureCard("Ospitare i Mendicanti", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3))), new ReceiveGainablesEffect(new Slave(4)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        ventureCards.add(new VentureCard("Combattere le Eresie", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(3), 5), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Costruire le Mura", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(3))), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        ventureCards.add(new VentureCard("Campagna Militare", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(2), 3), new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new VictoryPoint(5))));

        //building primo periodo------------------------------------------------
        buildingCards.add(new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1))));
        buildingCards.add(new BuildingCard("Arco di Trionfo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Stone(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(1))));
        buildingCards.add(new BuildingCard("Residenza", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(1)), new ExchangeGainablesEffect(new Money(), new CouncilFavour(1))));
        buildingCards.add(new BuildingCard("Teatro", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(1))));
        buildingCards.add(new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())), 5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(1))));
        buildingCards.add(new BuildingCard("Tagliapietre", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(2)), new ExchangeGainablesEffect(new Stone(), new Money(3), new Stone(2), new Money(5))));
        buildingCards.add(new BuildingCard("Falegnameria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))), 4, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Wood(), new Money(3), new Wood(2), new Money(5))));
        buildingCards.add(new BuildingCard("Zecca", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(3))), 5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(1))));

        //person primo periodo------------------------------------------------
        personCards.add(new PersonCard("Artigiano", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 2))));
        personCards.add(new PersonCard("Condottiero", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.LAND, 2))));
        personCards.add(new PersonCard("Costruttore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.BUILDING, 2), new BonusOnCardTake(CardType.BUILDING, new Wood()))));
        personCards.add(new PersonCard("Cavaliere", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new CouncilFavour(1)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.VENTURE, 2))));
        personCards.add(new PersonCard("Dama", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.PERSON, 2), new BonusOnCardTake(CardType.PERSON, new Money()))));
        personCards.add(new PersonCard("Contadino", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 2))));
        personCards.add(new PersonCard("Badessa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
        personCards.add(new PersonCard("Predicatore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveBonusesEffect(new MalusOnTowerBonuses())));

        //territorio primo periodo-----------------------------------------------------
        landCards.add(new LandCard("Foresta", PeriodNumber.FIRST, 5, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood(3))));
        landCards.add(new LandCard("Monastero", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave()), new ReceiveGainablesEffect(new FaithPointsTrack(), new Stone())));
        landCards.add(new LandCard("Cava di Ghiaia", PeriodNumber.FIRST, 4, new ReceiveGainablesEffect(new Stone(2)), new ReceiveGainablesEffect(new Stone(2))));
        landCards.add(new LandCard("Bosco", PeriodNumber.FIRST, 2, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood())));
        landCards.add(new LandCard("Città", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new CouncilFavour(1))));
        landCards.add(new LandCard("Borgo", PeriodNumber.FIRST, 3, null, new ReceiveGainablesEffect(new Money(), new Slave())));
        landCards.add(new LandCard("Rocca", PeriodNumber.FIRST, 5, null, new ReceiveGainablesEffect(new Stone(), new MilitaryPointsTrack(2))));
        landCards.add(new LandCard("Avamposto Commerciale", PeriodNumber.FIRST, 1, null, new ReceiveGainablesEffect(new Money(3))));


        //venture secondo periodo------------------------------------------------
        ventureCards.add(new VentureCard("Costruire i Bastioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(2))));
        ventureCards.add(new VentureCard("Supporto al Re", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(3), 6), new ReceiveGainablesEffect(new Money(5), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        ventureCards.add(new VentureCard("Accogliere gli Stranieri", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), new ReceiveGainablesEffect(new Slave(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        ventureCards.add(new VentureCard("Sostegno al Cardinale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone(2), new Money(3))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        ventureCards.add(new VentureCard("Ingaggiare Soldati", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(6)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Crociata", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(4), 8), new ReceiveGainablesEffect(new Money(5), new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Riparare l'Abbazia", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(2))), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(6))));
        ventureCards.add(new VentureCard("Scavare Canalizzazioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Slave(2), new Money(3))), new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new VictoryPoint(5))));

        //building secondo periodo------------------------------------------------
        buildingCards.add(new BuildingCard("Fortezza", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Stone(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(8)), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new VictoryPoint(2))));
        buildingCards.add(new BuildingCard("Gilda dei Pittori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new VictoryPoint(3), new Wood(3), new VictoryPoint(7))));
        buildingCards.add(new BuildingCard("Caserma", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(), new Stone())), 1, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3))));
        buildingCards.add(new BuildingCard("Gilda dei Costruttori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(2), new Wood())), 4, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new SetOfResources(new Slave(), new Wood(), new Slave()), new VictoryPoint(6))));
        buildingCards.add(new BuildingCard("Battistero", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(3))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(2)), new ExchangeGainablesEffect(new FaithPointsTrack(1), new Money(2), new VictoryPoint(2))));
        buildingCards.add(new BuildingCard("Mercato", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone())), 3, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Money(3), new SetOfResources(new Wood(2), new Slave(2)))));
        buildingCards.add(new BuildingCard("Tesoreria", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(3))), 3, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new Money(), new VictoryPoint(3), new Money(2), new VictoryPoint(5))));
        buildingCards.add(new BuildingCard("Gilda degli Scultori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(6)), new ExchangeGainablesEffect(new Stone(1), new VictoryPoint(3), new Stone(3), new VictoryPoint(7))));

        //person secondo periodo------------------------------------------------
        personCards.add(new PersonCard("Eroe", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.VENTURE), new ReceiveGainablesEffect(new CouncilFavour(1))), null));
        personCards.add(new PersonCard("Mecenate", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.PERSON), new ReceiveGainablesEffect(new Money(2))), null));
        personCards.add(new PersonCard("Fattore", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 3))));
        personCards.add(new PersonCard("Architetto", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.BUILDING), new ReceiveGainablesEffect(new Wood(), new Stone())), null));
        personCards.add(new PersonCard("Messo Papale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), null));
        personCards.add(new PersonCard("Capitano", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.LAND), new ReceiveGainablesEffect(new MilitaryPointsTrack(2))), null));
        personCards.add(new PersonCard("Messo Reale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new CouncilFavour(3)), null));
        personCards.add(new PersonCard("Studioso", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 3))));

        //territorio secondo periodo-----------------------------------------------------
        landCards.add(new LandCard("Villaggio Minerario", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Slave(2), new Stone()), new ReceiveGainablesEffect(new Slave(), new Stone(2))));
        landCards.add(new LandCard("Eremo", PeriodNumber.SECOND, 2, new ReceiveGainablesEffect(new FaithPointsTrack()), new ReceiveGainablesEffect(new FaithPointsTrack())));
        landCards.add(new LandCard("Ducato", PeriodNumber.SECOND, 6, new ReceiveGainablesEffect(new Money(4)), new ReceiveGainablesEffect(new Money(), new Stone(), new Wood(2))));
        landCards.add(new LandCard("Possedimento", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Wood(), new Slave(2)), new ReceiveGainablesEffect(new Wood(2), new Money())));
        landCards.add(new LandCard("Cava di Pietra", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Stone(3))));
        landCards.add(new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2), new MilitaryPointsTrack(1))));
        landCards.add(new LandCard("Maniero", PeriodNumber.SECOND, 5, null, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave(2))));
        landCards.add(new LandCard("Miniera d'Oro", PeriodNumber.SECOND, 1, new ReceiveGainablesEffect(new Money()), new ReceiveGainablesEffect(new Money(2))));


        //venture terzo periodo------------------------------------------------
        ventureCards.add(new VentureCard("Ingaggiare Mercenari", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(8))), new ReceiveGainablesEffect(new MilitaryPointsTrack(7)), new ReceiveGainablesEffect(new VictoryPoint(6))));
        ventureCards.add(new VentureCard("Conquista Militare", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(6), 12), new ReceiveGainablesEffect(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new ReceiveGainablesEffect(new VictoryPoint(7))));
        ventureCards.add(new VentureCard("Sostegno al Papa", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4), new Stone(3), new Wood(3))), new ReceiveGainablesEffect(new FaithPointsTrack(10)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Migliorare le Strade", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(3), new Money(4))), new PlayExtraActionPhaseEffect(3, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Riparare la Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new VictoryPoint(5))));
        ventureCards.add(new VentureCard("Costruire le Torri", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Stone(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(4), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        ventureCards.add(new VentureCard("Commissionare Arte Sacra", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(6))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        ventureCards.add(new VentureCard("Guerra Santa", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(8), 15), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveGainablesEffect(new VictoryPoint(8))));

        //building terzo periodo------------------------------------------------
        buildingCards.add(new BuildingCard("Basilica", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(), new Stone(4))), 1, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new FaithPointsTrack(2), new Stone(), new FaithPointsTrack(2))));
        buildingCards.add(new BuildingCard("Accademia Militare", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(), new Wood(2), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(7)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3), new VictoryPoint(1))));
        buildingCards.add(new BuildingCard("Fiera", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(3), new Money(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(8)), new ExchangeGainablesEffect(new Money(4), new SetOfResources(new Stone(3), new Wood(3)))));
        buildingCards.add(new BuildingCard("Castelletto", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(9)), new ReceiveGainablesEffect(new VictoryPoint(2), new CouncilFavour(1))));
        buildingCards.add(new BuildingCard("Palazzo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(1))), 6, new ReceiveGainablesEffect(new VictoryPoint(9)), new ExchangeGainablesEffect(new Money(1), new Slave(2), new VictoryPoint(4))));
        buildingCards.add(new BuildingCard("Banca", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(1), new Stone(3))), 2, new ReceiveGainablesEffect(new VictoryPoint(7)), new ReceiveGainablesEffect(new Money(5))));
        buildingCards.add(new BuildingCard("Giardino", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(2), new Wood(4), new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(10)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        buildingCards.add(new BuildingCard("Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(4), new Stone(4))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(3), new VictoryPoint(7)), new ReceiveGainablesEffect(new VictoryPoint(1))));

        //person terzo periodo------------------------------------------------
        personCards.add(new PersonCard("Governatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(2)), null));
        personCards.add(new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
        personCards.add(new PersonCard("Cardinale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new FaithPointsTrack(2))), null));
        personCards.add(new PersonCard("Cortigiana", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(7))), new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(2)), null));
        personCards.add(new PersonCard("Araldo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(2)), null));
        personCards.add(new PersonCard("Generale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesForMilitaryPointsEffect(), null));
        personCards.add(new PersonCard("Nobile", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(2)), null));
        personCards.add(new PersonCard("Ambasciatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new CouncilFavour(1))), null));


        //territorio terzo periodo-----------------------------------------------------
        landCards.add(new LandCard("Città Mercantile", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new Money(), new Slave()), new ReceiveGainablesEffect(new Money(3))));
        landCards.add(new LandCard("Castello", PeriodNumber.THIRD, 4, new ReceiveGainablesEffect(new Money(2), new VictoryPoint(2)), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new Slave())));
        landCards.add(new LandCard("Santuario", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new FaithPointsTrack(1), new Money())));
        landCards.add(new LandCard("Provincia", PeriodNumber.THIRD, 6, new ReceiveGainablesEffect(new CouncilFavour(1), new Stone()), new ReceiveGainablesEffect(new Stone(), new VictoryPoint(4))));
        landCards.add(new LandCard("Colonia", PeriodNumber.THIRD, 5, new ReceiveGainablesEffect(new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Wood(), new VictoryPoint(4))));
        landCards.add(new LandCard("Città Fortificata", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new Slave(), new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Slave(2), new MilitaryPointsTrack(1))));
        landCards.add(new LandCard("Tenuta", PeriodNumber.THIRD, 3, new ReceiveGainablesEffect(new Wood(), new VictoryPoint(1)), new ReceiveGainablesEffect(new Wood(2), new VictoryPoint(2))));
        landCards.add(new LandCard("Cava di Marmo", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new VictoryPoint(3)), new ReceiveGainablesEffect(new VictoryPoint(1), new Stone(2))));

//------------------------scrittura---------------------------------------------

        Gson gson = new Gson();

        String json;



        try {
            try (FileWriter w = new FileWriter("venture.txt")) {
                //b = new BufferedWriter(w);
                json = gson.toJson(ventureCards);
                w.write(json);
                w.flush();
                w.close();
            }


            try (FileWriter w = new FileWriter("person.txt")) {
                json = gson.toJson(personCards);
                w.write(json);
                w.flush();
                w.close();

            }


            try (FileWriter w = new FileWriter("building.txt")) {
                json = gson.toJson(buildingCards);
                w.write(json);
                w.flush();
                w.close();

            }


            try (FileWriter w = new FileWriter("land.txt")) {
                json = gson.toJson(landCards);
                w.write(json);
                w.flush();
                w.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//-----------------------------------scrittura end-----------------------



        //return cards;
    }
/*

    public void writeOnFile(String string, ArrayList<VentureCard> cards) {
        Gson gson = new Gson();
        BufferedWriter b;
        String json;



        try {
            try (FileWriter w = new FileWriter(string)) {
                b = new BufferedWriter(w);
            }
            json = gson.toJson(cards);
            b.write(json);
            b.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/

    public /*<T extends Card>*/ Stack<Card> readFromFile(){
        Gson gson = new Gson();

        Stack<VentureCard> ventureCards = new Stack<>();
        Stack<LandCard> landCards = new Stack<>();
        Stack<BuildingCard> buildingCards = new Stack<>();
        Stack<PersonCard> personCards = new Stack<>();
        String json;

        try {
            try (FileReader r = new FileReader("venture.txt")) {
                BufferedReader rb = new BufferedReader(r);
                json = rb.readLine();
                ventureCards = gson.fromJson(json, Stack.class);

            }


            try (FileReader r = new FileReader("person.txt")) {
                BufferedReader rb = new BufferedReader(r);
                json = rb.readLine();
                personCards = gson.fromJson(json, Stack.class);
            }


            try (FileReader r = new FileReader("building.txt")) {
                BufferedReader rb = new BufferedReader(r);
                json = rb.readLine();
                buildingCards = gson.fromJson(json, Stack.class);

            }


            try (FileReader r = new FileReader("land.txt")) {
                BufferedReader rb = new BufferedReader(r);
                json = rb.readLine();
                landCards = gson.fromJson(json, Stack.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




        return  b(ventureCards, personCards, buildingCards, landCards);






    }

    public ExcommunicationTassel[] tesselExcomunication(){
        Stack<ExcommunicationTassel> tassels1 = new Stack<>();
        Stack<ExcommunicationTassel> tassels2 = new Stack<>();
        Stack<ExcommunicationTassel> tassels3 = new Stack<>();
        tassels1.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.FIRST, ActivationActionSpaceType.HARVEST, 3, "a"));
        tassels1.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.FIRST, ActivationActionSpaceType.PRODUCTION, 3,"b"));
        tassels1.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.FIRST,"c"));

        //2
        tassels2.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.SECOND, CardType.BUILDING, 4,"d"));
        tassels2.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.SECOND, CardType.LAND, 4,"e"));
        tassels2.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.SECOND, CardType.PERSON, 4,"f"));
        tassels2.add(new MalusOnFamilyMemberPlacementTessel(PeriodNumber.SECOND, CardType.VENTURE, 4,"g"));

        //3
        tassels3.add(new PointsTrackToZeroTessel(CardType.PERSON,"h"));
        tassels3.add(new PointsTrackToZeroTessel(CardType.LAND,"i"));
        tassels3.add(new PointsTrackToZeroTessel(CardType.VENTURE,"j"));

        Collections.shuffle(tassels1);
        Collections.shuffle(tassels2);
        Collections.shuffle(tassels3);


        ExcommunicationTassel[] tassels = new ExcommunicationTassel[3];
        tassels[0] = tassels1.pop();
        tassels[1] = tassels2.pop();
        tassels[2] = tassels3.pop();
        return tassels;
    }


    public  Stack<Card> a(Stack<Card> deck){
        Stack<Card> cards = new Stack<>();

        for(int i = 0; i < 8 ; i++){
            cards.push(deck.pop());
        }

        Collections.shuffle(cards);
        return cards;

    }

    public  Stack<Card> b(Stack<VentureCard> venture,Stack<PersonCard> person, Stack<BuildingCard> building, Stack<LandCard>  land ) {
        Stack<Card> definitive_deck = new Stack<>();
        Stack<VentureCard> ventureC = new Stack<>();
        Stack<PersonCard> personC = new Stack<>();
        Stack<BuildingCard> buildingC = new Stack<>();
        Stack<LandCard> landC = new Stack<>();
        for(int k = 0; k < 3; k++){
            for (int j = 0; j < 8; j++){
                ventureC.push(venture.pop());
                personC.push(person.pop());
                buildingC.push(building.pop());
                landC.push(land.pop());
            }

            Collections.shuffle(ventureC);
            Collections.shuffle(personC);
            Collections.shuffle(buildingC);
            Collections.shuffle(landC);

            for (int i = 0; i < 2; i++) {
                //------------------------------
                for(int j = 0; j < 4; j++){
                    definitive_deck.add(ventureC.pop());
                }
                for(int j = 0; j < 4; j++){
                    definitive_deck.add(buildingC.pop());
                }
                for(int j = 0; j < 4; j++){
                    definitive_deck.add(personC.pop());
                }
                for(int j = 0; j < 4; j++){
                    definitive_deck.add(landC.pop());
                }
                //------------------------
                /*
                definitive_deck.addAll(c(ventureC));
                definitive_deck.addAll(c(buildingC));
                definitive_deck.addAll(c(personC));
                definitive_deck.addAll(c(landC));
*/

            }
        }


        return definitive_deck;
    }

    private <T extends Card> Stack<T> c(Stack<T> deck){

        Stack<T> cards = new Stack<>();
        for(int i = 0; i < 4; i++){
            cards.push(deck.pop());
        }
        return cards;
    }




    public Stack<Card> prova(){
        Stack<Card> cards = new Stack<>();


        //venture primo periodo------------------------------------------------
        cards.add(new VentureCard("Ingaggiare Reclute", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Riparare la Chiesa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Wood(), new Stone())), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(1))));
        cards.add(new VentureCard("Innalzare una Statua", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2), new Wood(2))), new ReceiveGainablesEffect(new CouncilFavour(2)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Ospitare i Mendicanti", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3))), new ReceiveGainablesEffect(new Slave(4)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Combattere le Eresie", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(3), 5), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Costruire le Mura", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(3))), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new VentureCard("Campagna Militare", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(2), 3), new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new VictoryPoint(5))));

        //building primo periodo------------------------------------------------
        cards.add(new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1))));
        cards.add(new BuildingCard("Arco di Trionfo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Stone(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(1))));
        cards.add(new BuildingCard("Residenza", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(1)), new ExchangeGainablesEffect(new Money(), new CouncilFavour(1))));
        cards.add(new BuildingCard("Teatro", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(1))));
        cards.add(new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())), 5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(1))));
        cards.add(new BuildingCard("Tagliapietre", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(2)), new ExchangeGainablesEffect(new Stone(), new Money(3), new Stone(2), new Money(5))));
        cards.add(new BuildingCard("Falegnameria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))), 4, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Wood(), new Money(3), new Wood(2), new Money(5))));
        cards.add(new BuildingCard("Zecca", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(3))), 5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(1))));

        //person primo periodo------------------------------------------------
        cards.add(new PersonCard("Artigiano", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 2))));
        cards.add(new PersonCard("Condottiero", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.LAND, 2))));
        cards.add(new PersonCard("Costruttore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.BUILDING, 2), new BonusOnCardTake(CardType.BUILDING, new Wood()))));
        cards.add(new PersonCard("Cavaliere", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new CouncilFavour(1)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.VENTURE, 2))));
        cards.add(new PersonCard("Dama", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.PERSON, 2), new BonusOnCardTake(CardType.PERSON, new Money()))));
        cards.add(new PersonCard("Contadino", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 2))));
        cards.add(new PersonCard("Badessa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
        cards.add(new PersonCard("Predicatore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveBonusesEffect(new MalusOnTowerBonuses())));

        //territorio primo periodo-----------------------------------------------------
        cards.add(new LandCard("Foresta", PeriodNumber.FIRST, 5, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood(3))));
        cards.add(new LandCard("Monastero", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave()), new ReceiveGainablesEffect(new FaithPointsTrack(), new Stone())));
        cards.add(new LandCard("Cava di Ghiaia", PeriodNumber.FIRST, 4, new ReceiveGainablesEffect(new Stone(2)), new ReceiveGainablesEffect(new Stone(2))));
        cards.add(new LandCard("Bosco", PeriodNumber.FIRST, 2, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood())));
        cards.add(new LandCard("Città", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new CouncilFavour(1))));
        cards.add(new LandCard("Borgo", PeriodNumber.FIRST, 3, null, new ReceiveGainablesEffect(new Money(), new Slave())));
        cards.add(new LandCard("Rocca", PeriodNumber.FIRST, 5, null, new ReceiveGainablesEffect(new Stone(), new MilitaryPointsTrack(2))));
        cards.add(new LandCard("Avamposto Commerciale", PeriodNumber.FIRST, 1, null, new ReceiveGainablesEffect(new Money(3))));


        //venture secondo periodo------------------------------------------------
        cards.add(new VentureCard("Costruire i Bastioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(2))));
        cards.add(new VentureCard("Supporto al Re", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(3), 6), new ReceiveGainablesEffect(new Money(5), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new VentureCard("Accogliere gli Stranieri", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), new ReceiveGainablesEffect(new Slave(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Sostegno al Cardinale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone(2), new Money(3))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Ingaggiare Soldati", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(6)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Crociata", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(4), 8), new ReceiveGainablesEffect(new Money(5), new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Riparare l'Abbazia", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(2))), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(6))));
        cards.add(new VentureCard("Scavare Canalizzazioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Slave(2), new Money(3))), new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new VictoryPoint(5))));

        //building secondo periodo------------------------------------------------
        cards.add(new BuildingCard("Fortezza", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Stone(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(8)), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new VictoryPoint(2))));
        cards.add(new BuildingCard("Gilda dei Pittori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new VictoryPoint(3), new Wood(3), new VictoryPoint(7))));
        cards.add(new BuildingCard("Caserma", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(), new Stone())), 1, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3))));
        cards.add(new BuildingCard("Gilda dei Costruttori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(2), new Wood())), 4, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new SetOfResources(new Slave(), new Wood(), new Slave()), new VictoryPoint(6))));
        cards.add(new BuildingCard("Battistero", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(3))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(2)), new ExchangeGainablesEffect(new FaithPointsTrack(1), new Money(2), new VictoryPoint(2))));
        cards.add(new BuildingCard("Mercato", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone())), 3, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Money(3), new SetOfResources(new Wood(2), new Slave(2)))));
        cards.add(new BuildingCard("Tesoreria", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(3))), 3, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new Money(), new VictoryPoint(3), new Money(2), new VictoryPoint(5))));
        cards.add(new BuildingCard("Gilda degli Scultori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(6)), new ExchangeGainablesEffect(new Stone(1), new VictoryPoint(3), new Stone(3), new VictoryPoint(7))));

        //person secondo periodo------------------------------------------------
        cards.add(new PersonCard("Eroe", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.VENTURE), new ReceiveGainablesEffect(new CouncilFavour(1))), null));
        cards.add(new PersonCard("Mecenate", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.PERSON), new ReceiveGainablesEffect(new Money(2))), null));
        cards.add(new PersonCard("Fattore", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 3))));
        cards.add(new PersonCard("Architetto", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.BUILDING), new ReceiveGainablesEffect(new Wood(), new Stone())), null));
        cards.add(new PersonCard("Messo Papale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), null));
        cards.add(new PersonCard("Capitano", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.LAND), new ReceiveGainablesEffect(new MilitaryPointsTrack(2))), null));
        cards.add(new PersonCard("Messo Reale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new CouncilFavour(3)), null));
        cards.add(new PersonCard("Studioso", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 3))));

        //territorio secondo periodo-----------------------------------------------------
        cards.add(new LandCard("Villaggio Minerario", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Slave(2), new Stone()), new ReceiveGainablesEffect(new Slave(), new Stone(2))));
        cards.add(new LandCard("Eremo", PeriodNumber.SECOND, 2, new ReceiveGainablesEffect(new FaithPointsTrack()), new ReceiveGainablesEffect(new FaithPointsTrack(1))));
        cards.add(new LandCard("Ducato", PeriodNumber.SECOND, 6, new ReceiveGainablesEffect(new Money(4)), new ReceiveGainablesEffect(new Money(), new Stone(), new Wood(2))));
        cards.add(new LandCard("Possedimento", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Wood(), new Slave(2)), new ReceiveGainablesEffect(new Wood(2), new Money())));
        cards.add(new LandCard("Cava di Pietra", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Stone(3))));
        cards.add(new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2), new MilitaryPointsTrack(1))));
        cards.add(new LandCard("Maniero", PeriodNumber.SECOND, 5, null, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave(2))));
        cards.add(new LandCard("Miniera d'Oro", PeriodNumber.SECOND, 1, new ReceiveGainablesEffect(new Money()), new ReceiveGainablesEffect(new Money(2))));


        //venture terzo periodo------------------------------------------------
        cards.add(new VentureCard("Ingaggiare Mercenari", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(8))), new ReceiveGainablesEffect(new MilitaryPointsTrack(7)), new ReceiveGainablesEffect(new VictoryPoint(6))));
        cards.add(new VentureCard("Conquista Militare", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(6), 12), new ReceiveGainablesEffect(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new ReceiveGainablesEffect(new VictoryPoint(7))));
        cards.add(new VentureCard("SOstegno al Papa", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4), new Stone(3), new Wood(3))), new ReceiveGainablesEffect(new FaithPointsTrack(10)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Migliorare le Strade", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(3), new Money(4))), new PlayExtraActionPhaseEffect(3, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Riparare la Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Costruire le Torri", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Stone(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(4), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Commissionare Arte Sacra", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(6))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new VentureCard("Guerra Santa", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(8), 15), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveGainablesEffect(new VictoryPoint(8))));

        //building terzo periodo------------------------------------------------
        cards.add(new BuildingCard("Basilica", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(), new Stone(4))), 1, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new FaithPointsTrack(2), new Stone(), new FaithPointsTrack(2))));
        cards.add(new BuildingCard("Accademia Militare", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(), new Wood(2), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(7)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3), new VictoryPoint(1))));
        cards.add(new BuildingCard("Fiera", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(3), new Money(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(8)), new ExchangeGainablesEffect(new Money(4), new SetOfResources(new Stone(3), new Wood(3)))));
        cards.add(new BuildingCard("Castelletto", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(9)), new ReceiveGainablesEffect(new VictoryPoint(2), new CouncilFavour(1))));
        cards.add(new BuildingCard("Palazzo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(1))), 6, new ReceiveGainablesEffect(new VictoryPoint(9)), new ExchangeGainablesEffect(new Money(1), new Slave(2), new VictoryPoint(4))));
        cards.add(new BuildingCard("Banca", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(1), new Stone(3))), 2, new ReceiveGainablesEffect(new VictoryPoint(7)), new ReceiveGainablesEffect(new Money(5))));
        cards.add(new BuildingCard("Giardino", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(2), new Wood(4), new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(10)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new BuildingCard("Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(4), new Stone(4))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(3), new VictoryPoint(7)), new ReceiveGainablesEffect(new VictoryPoint(1))));

        //person terzo periodo------------------------------------------------
        cards.add(new PersonCard("Governatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(2)), null));
        cards.add(new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
        cards.add(new PersonCard("Cardinale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new FaithPointsTrack(2))), null));
        cards.add(new PersonCard("Cortigiana", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(7))), new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(2)), null));
        cards.add(new PersonCard("Araldo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(2)), null));
        cards.add(new PersonCard("Generale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesForMilitaryPointsEffect(), null));
        cards.add(new PersonCard("Nobile", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(2)), null));
        cards.add(new PersonCard("Ambasciatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new CouncilFavour(1))), null));


        //territorio terzo periodo-----------------------------------------------------
        cards.add(new LandCard("Città Mercantile", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new Money(), new Slave()), new ReceiveGainablesEffect(new Money(3))));
        cards.add(new LandCard("Castello", PeriodNumber.THIRD, 4, new ReceiveGainablesEffect(new Money(2), new VictoryPoint(2)), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new Slave())));
        cards.add(new LandCard("Santuario", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new FaithPointsTrack(1), new Money())));
        cards.add(new LandCard("Provincia", PeriodNumber.THIRD, 6, new ReceiveGainablesEffect(new CouncilFavour(1), new Stone()), new ReceiveGainablesEffect(new Stone(), new VictoryPoint(4))));
        cards.add(new LandCard("Colonia", PeriodNumber.THIRD, 5, new ReceiveGainablesEffect(new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Wood(), new VictoryPoint(4))));
        cards.add(new LandCard("Città Fortificata", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new Slave(), new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Slave(2), new MilitaryPointsTrack(1))));
        cards.add(new LandCard("Tenuta", PeriodNumber.THIRD, 3, new ReceiveGainablesEffect(new Wood(), new VictoryPoint(1)), new ReceiveGainablesEffect(new Wood(2), new VictoryPoint(2))));
        cards.add(new LandCard("Cava di Marmo", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new VictoryPoint(3)), new ReceiveGainablesEffect(new VictoryPoint(1), new Stone(2))));


        return cards;
    }


    public void setLandCards(){

        //territorio primo periodo-----------------------------------------------------
        landCards.add(new LandCard("Foresta", PeriodNumber.FIRST, 5, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood(3))));
        landCards.add(new LandCard("Monastero", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave()), new ReceiveGainablesEffect(new FaithPointsTrack(), new Stone())));
        landCards.add(new LandCard("Cava di Ghiaia", PeriodNumber.FIRST, 4, new ReceiveGainablesEffect(new Stone(2)), new ReceiveGainablesEffect(new Stone(2))));
        landCards.add(new LandCard("Bosco", PeriodNumber.FIRST, 2, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood())));
       landCards.add(new LandCard("Città", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new CouncilFavour(1))));
       landCards.add(new LandCard("Borgo", PeriodNumber.FIRST, 3, null, new ReceiveGainablesEffect(new Money(), new Slave())));
       landCards.add(new LandCard("Rocca", PeriodNumber.FIRST, 5, null, new ReceiveGainablesEffect(new Stone(), new MilitaryPointsTrack(2))));
       landCards.add(new LandCard("Avamposto Commerciale", PeriodNumber.FIRST, 1, null, new ReceiveGainablesEffect(new Money(3))));
//territorio secondo periodo-----------------------------------------------------
       landCards.add(new LandCard("Villaggio Minerario", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Slave(2), new Stone()), new ReceiveGainablesEffect(new Slave(), new Stone(2))));
       landCards.add(new LandCard("Eremo", PeriodNumber.SECOND, 2, new ReceiveGainablesEffect(new FaithPointsTrack()), new ReceiveGainablesEffect(new FaithPointsTrack(1))));
       landCards.add(new LandCard("Ducato", PeriodNumber.SECOND, 6, new ReceiveGainablesEffect(new Money(4)), new ReceiveGainablesEffect(new Money(), new Stone(), new Wood(2))));
       landCards.add(new LandCard("Possedimento", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Wood(), new Slave(2)), new ReceiveGainablesEffect(new Wood(2), new Money())));
       landCards.add(new LandCard("Cava di Pietra", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Stone(3))));
       landCards.add(new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2), new MilitaryPointsTrack(1))));
       landCards.add(new LandCard("Maniero", PeriodNumber.SECOND, 5, null, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave(2))));
       landCards.add(new LandCard("MinieraOro", PeriodNumber.SECOND, 1, new ReceiveGainablesEffect(new Money()), new ReceiveGainablesEffect(new Money(2))));
//territorio terzo periodo-----------------------------------------------------
       landCards.add(new LandCard("Città Mercantile", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new Money(), new Slave()), new ReceiveGainablesEffect(new Money(3))));
       landCards.add(new LandCard("Castello", PeriodNumber.THIRD, 4, new ReceiveGainablesEffect(new Money(2), new VictoryPoint(2)), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new Slave())));
       landCards.add(new LandCard("Santuario", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new FaithPointsTrack(1), new Money())));
       landCards.add(new LandCard("Provincia", PeriodNumber.THIRD, 6, new ReceiveGainablesEffect(new CouncilFavour(1), new Stone()), new ReceiveGainablesEffect(new Stone(), new VictoryPoint(4))));
       landCards.add(new LandCard("Colonia", PeriodNumber.THIRD, 5, new ReceiveGainablesEffect(new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Wood(), new VictoryPoint(4))));
       landCards.add(new LandCard("Città Fortificata", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new Slave(), new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Slave(2), new MilitaryPointsTrack(1))));
       landCards.add(new LandCard("Tenuta", PeriodNumber.THIRD, 3, new ReceiveGainablesEffect(new Wood(), new VictoryPoint(1)), new ReceiveGainablesEffect(new Wood(2), new VictoryPoint(2))));
       landCards.add(new LandCard("Cava di Marmo", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new VictoryPoint(3)), new ReceiveGainablesEffect(new VictoryPoint(1), new Stone(2))));
    }


    public void setPersonCards(){
        //person primo periodo------------------------------------------------
    personCards.add(new PersonCard("Artigiano", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 2))));
    personCards.add(new PersonCard("Condottiero", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.LAND, 2))));
    personCards.add(new PersonCard("Costruttore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.BUILDING, 2), new BonusOnCardTake(CardType.BUILDING, new Wood()))));
    personCards.add(new PersonCard("Cavaliere", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new CouncilFavour(1)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.VENTURE, 2))));
    personCards.add(new PersonCard("Dama", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.PERSON, 2), new BonusOnCardTake(CardType.PERSON, new Money()))));
    personCards.add(new PersonCard("Contadino", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 2))));
    personCards.add(new PersonCard("Badessa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
    personCards.add(new PersonCard("Predicatore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveBonusesEffect(new MalusOnTowerBonuses())));
//person secondo periodo------------------------------------------------
    personCards.add(new PersonCard("Eroe", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.VENTURE), new ReceiveGainablesEffect(new CouncilFavour(1))), null));
    personCards.add(new PersonCard("Mecenate", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.PERSON), new ReceiveGainablesEffect(new Money(2))), null));
    personCards.add(new PersonCard("Fattore", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 3))));
    personCards.add(new PersonCard("Architetto", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.BUILDING), new ReceiveGainablesEffect(new Wood(), new Stone())), null));
    personCards.add(new PersonCard("Messo Papale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), null));
    personCards.add(new PersonCard("Capitano", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.LAND), new ReceiveGainablesEffect(new MilitaryPointsTrack(2))), null));
    personCards.add(new PersonCard("Messo Reale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new CouncilFavour(3)), null));
    personCards.add(new PersonCard("Studioso", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 3))));
//person terzo periodo------------------------------------------------
    personCards.add(new PersonCard("Governatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(2)), null));
    personCards.add(new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
    personCards.add(new PersonCard("Cardinale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new FaithPointsTrack(2))), null));
    personCards.add(new PersonCard("Cortigiana", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(7))), new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(2)), null));
    personCards.add(new PersonCard("Araldo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(2)), null));
    personCards.add(new PersonCard("Generale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesForMilitaryPointsEffect(), null));
    personCards.add(new PersonCard("Nobile", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(2)), null));
    personCards.add(new PersonCard("Ambasciatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new CouncilFavour(1))), null));


    }

    public void setBuildingCards(){

        //building primo periodo------------------------------------------------
     buildingCards.add(new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1))));
     buildingCards.add(new BuildingCard("Arco di Trionfo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Stone(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(1))));
     buildingCards.add(new BuildingCard("Residenza", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(1)), new ExchangeGainablesEffect(new Money(), new CouncilFavour(1))));
     buildingCards.add(new BuildingCard("Teatro", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(1))));
     buildingCards.add(new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())), 5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(1))));
     buildingCards.add(new BuildingCard("Tagliapietre", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(2)), new ExchangeGainablesEffect(new Stone(), new Money(3), new Stone(2), new Money(5))));
     buildingCards.add(new BuildingCard("Falegnameria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))), 4, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Wood(), new Money(3), new Wood(2), new Money(5))));
     buildingCards.add(new BuildingCard("Zecca", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(3))), 5, new ReceiveGainablesEffect(new VictoryPoint(5)), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(1))));
//building secondo periodo------------------------------------------------
     buildingCards.add(new BuildingCard("Fortezza", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Stone(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(8)), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new VictoryPoint(2))));
     buildingCards.add(new BuildingCard("Gilda dei Pittori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new VictoryPoint(3), new Wood(3), new VictoryPoint(7))));
     buildingCards.add(new BuildingCard("Caserma", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(), new Stone())), 1, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3))));
     buildingCards.add(new BuildingCard("Gilda dei Costruttori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(2), new Wood())), 4, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new SetOfResources(new Slave(), new Wood(), new Slave()), new VictoryPoint(6))));
     buildingCards.add(new BuildingCard("Battistero", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(3))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(2)), new ExchangeGainablesEffect(new FaithPointsTrack(1), new Money(2), new VictoryPoint(2))));
     buildingCards.add(new BuildingCard("Mercato", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone())), 3, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Money(3), new SetOfResources(new Wood(2), new Slave(2)))));
     buildingCards.add(new BuildingCard("Tesoreria", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(3))), 3, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new Money(), new VictoryPoint(3), new Money(2), new VictoryPoint(5))));
     buildingCards.add(new BuildingCard("Gilda degli Scultori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(6)), new ExchangeGainablesEffect(new Stone(1), new VictoryPoint(3), new Stone(3), new VictoryPoint(7))));
//buiilding terzo periodo------------------------------------------------
     buildingCards.add(new BuildingCard("Basilica", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(), new Stone(4))), 1, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new FaithPointsTrack(2), new Stone(), new FaithPointsTrack(2))));
     buildingCards.add(new BuildingCard("Accademia Militare", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(), new Wood(2), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(7)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3), new VictoryPoint(1))));
     buildingCards.add(new BuildingCard("Fiera", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(3), new Money(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(8)), new ExchangeGainablesEffect(new Money(4), new SetOfResources(new Stone(3), new Wood(3)))));
     buildingCards.add(new BuildingCard("Castelletto", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(9)), new ReceiveGainablesEffect(new VictoryPoint(2), new CouncilFavour(1))));
     buildingCards.add(new BuildingCard("Palazzo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(1))), 6, new ReceiveGainablesEffect(new VictoryPoint(9)), new ExchangeGainablesEffect(new Money(1), new Slave(2), new VictoryPoint(4))));
     buildingCards.add(new BuildingCard("Banca", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(1), new Stone(3))), 2, new ReceiveGainablesEffect(new VictoryPoint(7)), new ReceiveGainablesEffect(new Money(5))));
     buildingCards.add(new BuildingCard("Giardino", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(2), new Wood(4), new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(10)), new ReceiveGainablesEffect(new VictoryPoint(3))));
     buildingCards.add(new BuildingCard("Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(4), new Stone(4))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(3), new VictoryPoint(7)), new ReceiveGainablesEffect(new VictoryPoint(1))));



    }

    public void setVentureCards() {
        //venture primo periodo------------------------------------------------
    ventureCards.add(new VentureCard("Ingaggiare Reclute", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
    ventureCards.add(new VentureCard("Riparare la Chiesa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Wood(), new Stone())), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(), new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(1))));
    ventureCards.add(new VentureCard("Innalzare una Statua", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2), new Wood(2))), new ReceiveGainablesEffect(new CouncilFavour(2)), new ReceiveGainablesEffect(new VictoryPoint(4))));
    ventureCards.add(new VentureCard("Ospitare i Mendicanti", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3))), new ReceiveGainablesEffect(new Slave(4)), new ReceiveGainablesEffect(new VictoryPoint(4))));
    ventureCards.add(new VentureCard("Combattere le Eresie", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(3), 5), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Costruire le Mura", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(3))), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
    ventureCards.add(new VentureCard("Campagna Militare", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(2), 3), new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    //venture secondo periodo------------------------------------------------
    ventureCards.add(new VentureCard("Costruire i Bastioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(2))));
    ventureCards.add(new VentureCard("Supporto al Re", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(3), 6), new ReceiveGainablesEffect(new Money(5), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
    ventureCards.add(new VentureCard("Accogliere gli Stranieri", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), new ReceiveGainablesEffect(new Slave(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
    ventureCards.add(new VentureCard("Sostegno al Cardinale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone(2), new Money(3))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(4))));
    ventureCards.add(new VentureCard("Ingaggiare Soldati", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(6)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Crociata", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(4), 8), new ReceiveGainablesEffect(new Money(5), new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Riparare_la_Abbazia", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(2))), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(6))));
    ventureCards.add(new VentureCard("Scavare Canalizzazioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Slave(2), new Money(3))), new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new VictoryPoint(5))));
//venture terzo periodo------------------------------------------------
    ventureCards.add(new VentureCard("Ingaggiare Mercenari", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(8))), new ReceiveGainablesEffect(new MilitaryPointsTrack(7)), new ReceiveGainablesEffect(new VictoryPoint(6))));
    ventureCards.add(new VentureCard("Conquista Militare", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(6), 12), new ReceiveGainablesEffect(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new ReceiveGainablesEffect(new VictoryPoint(7))));
    ventureCards.add(new VentureCard("Supporto al Papa", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4), new Stone(3), new Wood(3))), new ReceiveGainablesEffect(new FaithPointsTrack(10)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Migliorare le Strade", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(3), new Money(4))), new PlayExtraActionPhaseEffect(3, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Riparare la Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new VictoryPoint(5))));
    ventureCards.add(new VentureCard("Costruire le Torri", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Stone(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(4), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(4))));
    ventureCards.add(new VentureCard("Commissionare Arte Sacra", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(6))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(3))));
    ventureCards.add(new VentureCard("Guerra Santa", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(8), 15), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveGainablesEffect(new VictoryPoint(8))));

    }



    public Stack<Card> shuffleHandle(){
        setBuildingCards();
        setLandCards();
        setPersonCards();
        setVentureCards();

        Stack<Card> l_cards = new Stack();
        Stack<Card> p_cards = new Stack();
        Stack<Card> b_cards = new Stack();
        Stack<Card> v_cards = new Stack();
        for(int k = 0; k<3; k++) {
            for (int i = 0; i < 8; i++) {
                l_cards.add(landCards.pop());
                p_cards.add(personCards.pop());
                b_cards.add(buildingCards.pop());
                v_cards.add(ventureCards.pop());
            }
            Collections.shuffle(l_cards);
            Collections.shuffle(p_cards);
            Collections.shuffle(v_cards);
            Collections.shuffle(b_cards);

            for (int i = 0; i < 2; i++) {
                deckHandle(v_cards);
                deckHandle(b_cards);
                deckHandle(p_cards);
                deckHandle(l_cards);
            }
        }
        return totalCards;
    }

    public void deckHandle(Stack<Card> deck){
        for(int i = 0; i < 4; i++){
            totalCards.add(deck.pop());
        }
    }



    public static void main(String[] args) {
        CardSetupHandler cardSetupHandler = new CardSetupHandler();
        cardSetupHandler.cardDescription();
        Stack<Card> cards = cardSetupHandler.readFromFile();
        LandCard card = (LandCard) cards.pop();

        Board board = new Board(3);
        ((TowerActionSpace)board.getHashMap().get("TL1")).setCard((LandCard)cards.pop());



        while(!cards.isEmpty())
            System.out.println(cards.pop());

    }


}
