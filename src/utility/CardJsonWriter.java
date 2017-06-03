package utility;
import actionSpaces.ActionSpaceType;
import actionSpaces.ActivationActionSpace;
import actionSpaces.ActivationActionSpaceType;
import bonuses.BonusOnCardTake;
import bonuses.BonusOnFamilyMemberPlacement;
import bonuses.MalusOnTowerBonuses;
import cards.*;
import cards.cardEffects.*;
import com.google.gson.*;
import gameStructure.PeriodNumber;
import player.VictoryPoint;
import pointsTracks.FaithPointsTrack;
import pointsTracks.MilitaryPointsTrack;
import resources.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Created by Pinos on 31/05/2017.
 */
public class CardJsonWriter {

    public ArrayList<Card> CardDeclaration(){
        ArrayList<Card> cards = new ArrayList<>();

        //------------------cards_____________
//territorio primo periodo-----------------------------------------------------
        cards.add(new LandCard("Foresta", PeriodNumber.FIRST, 5, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood(3))));
        cards.add(new LandCard("Monastero",PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave()), new ReceiveGainablesEffect(new FaithPointsTrack(), new Stone())));
        cards.add(new LandCard("Cava di Ghiaia", PeriodNumber.FIRST, 4, new ReceiveGainablesEffect(new Stone(2)), new ReceiveGainablesEffect(new Stone(2))));
        cards.add(new LandCard("Bosco", PeriodNumber.FIRST, 2, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Wood())));
        cards.add(new LandCard("Città", PeriodNumber.FIRST, 6, new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new CouncilFavour(1))));
        cards.add(new LandCard("Borgo", PeriodNumber.FIRST, 3, null, new ReceiveGainablesEffect(new Money(), new Slave())));
        cards.add(new LandCard("Rocca", PeriodNumber.FIRST, 5, null, new ReceiveGainablesEffect(new Stone(), new MilitaryPointsTrack(2))));
        cards.add(new LandCard("Avamposto Commerciale", PeriodNumber.FIRST, 1, null, new ReceiveGainablesEffect(new Money(3))));

        //building primo periodo------------------------------------------------
        cards.add(new BuildingCard("Cappella", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(2))),2, new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ExchangeGainablesEffect(new Money(), new FaithPointsTrack(1))));
        cards.add(new BuildingCard("Arco di Trionfo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Stone(2))),6, new ReceiveGainablesEffect(new VictoryPoint(6)), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(1))));
        cards.add(new BuildingCard("Residenza", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2))),1, new ReceiveGainablesEffect(new VictoryPoint(1)),new ExchangeGainablesEffect(new Money(), new CouncilFavour(1))));
        cards.add(new BuildingCard("Teatro", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(2), new Wood(2))),6, new ReceiveGainablesEffect(new VictoryPoint(6)),new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(1))));
        cards.add(new BuildingCard("Esattoria", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3), new Stone())),5,new ReceiveGainablesEffect(new VictoryPoint(5)),new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(1))));
        cards.add(new BuildingCard("Tagliapietre", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Stone(2))),3, new ReceiveGainablesEffect(new VictoryPoint(2)),new ExchangeGainablesEffect(new Stone(), new Money(3), new Stone(2), new Money(5))));
        cards.add(new BuildingCard("Falegnameria", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(), new Stone(2))),4, new ReceiveGainablesEffect(new VictoryPoint(3)),new ExchangeGainablesEffect(new Wood(), new Money(3), new Wood(2), new Money(5))));
        cards.add(new BuildingCard("Zecca", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(3))),5, new ReceiveGainablesEffect(new VictoryPoint(5)),new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(1))));

        //venture primo periodo------------------------------------------------
        cards.add(new VentureCard("Ingaggiare Reclute", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Riparare la Chiesa", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(), new Wood(), new Stone())), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Sostegno al Vescovo", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(), new Stone(), new Money(2)), new MilitaryPointsTrack(2), 4), new ReceiveGainablesEffect(new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(1))));
        cards.add(new VentureCard("Innalzare una Statua", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(2), new Wood(2))), new ReceiveGainablesEffect(new CouncilFavour(2)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Ospitare i Mendicanti", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Wood(3))), new ReceiveGainablesEffect(new Slave(4)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Combattere le Eresie", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(3), 5), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Costruire le Mura", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Stone(3))), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new VentureCard("Campagna Militare", PeriodNumber.FIRST, new CardCost(new MilitaryPointsTrack(2), 3), new ReceiveGainablesEffect(new Money(3)), new ReceiveGainablesEffect(new VictoryPoint(5))));

        //person primo periodo------------------------------------------------
        cards.add(new PersonCard("Artigiano", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(3))),null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 2))));
        cards.add(new PersonCard("Condottiero", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.LAND, 2))));
        cards.add(new PersonCard("Costruttore", PeriodNumber.FIRST, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.BUILDING, 2), new BonusOnCardTake(CardType.BUILDING, new Wood()))));
        cards.add(new PersonCard("Cavaliere", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new CouncilFavour(1)), new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.VENTURE, 2))));
        cards.add(new PersonCard("Dama", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(CardType.PERSON, 2), new BonusOnCardTake(CardType.PERSON, new Money()))));
        cards.add(new PersonCard("Contadino", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(3))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 2))));
        cards.add(new PersonCard("Badessa",PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4), new ReceiveGainablesEffect(new FaithPointsTrack(1))) , null));
        cards.add(new PersonCard("Predicatore", PeriodNumber.FIRST,new CardCost(new SetOfResources(new Money(2))), new ReceiveGainablesEffect(new FaithPointsTrack(4)), new ReceiveBonusesEffect(new MalusOnTowerBonuses())));

        //territorio secondo periodo-----------------------------------------------------
        cards.add(new LandCard("Villaggio Minerario", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Slave(2), new Stone()), new ReceiveGainablesEffect(new Slave(), new Stone(2))));
        cards.add(new LandCard("Eremo", PeriodNumber.SECOND, 2, new ReceiveGainablesEffect(new FaithPointsTrack()), new ReceiveGainablesEffect(new FaithPointsTrack())));
        cards.add(new LandCard("Ducato", PeriodNumber.SECOND, 6, new ReceiveGainablesEffect(new Money(4)), new ReceiveGainablesEffect(new Money(), new Stone(), new Wood(2))));
        cards.add(new LandCard("Possedimento", PeriodNumber.SECOND, 4, new ReceiveGainablesEffect(new Wood(), new Slave(2)), new ReceiveGainablesEffect(new Wood(2), new Money())));
        cards.add(new LandCard("Cava di Pietra",PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Wood()), new ReceiveGainablesEffect(new Stone(3))));
        cards.add(new LandCard("Villaggio Montano", PeriodNumber.SECOND, 3, new ReceiveGainablesEffect(new Slave()), new ReceiveGainablesEffect(new Wood(2), new MilitaryPointsTrack(1))));
        cards.add(new LandCard("Maniero", PeriodNumber.SECOND, 5, null, new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new Slave(2))));
        cards.add(new LandCard("Miniera d'Oro", PeriodNumber.SECOND, 1, new ReceiveGainablesEffect(new Money()), new ReceiveGainablesEffect(new Money(2))));

        //building secondo periodo------------------------------------------------
        cards.add(new BuildingCard("Fortezza", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Stone(2), new Wood(2))), 6, new ReceiveGainablesEffect(new VictoryPoint(8)), new ReceiveGainablesEffect(new MilitaryPointsTrack(2), new VictoryPoint(2))));
        cards.add(new BuildingCard("Gilda dei Pittori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new VictoryPoint(3), new Wood(3), new VictoryPoint(7))));
        cards.add(new BuildingCard("Caserma", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(), new Stone())), 1, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3))));
        cards.add(new BuildingCard("Gilda dei Costruttori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(2), new Wood())), 4,new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new SetOfResources(new Slave(), new Wood(), new Slave()), new VictoryPoint(6))));
        cards.add(new BuildingCard("Battistero", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(3))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(2)), new ExchangeGainablesEffect(new FaithPointsTrack(1), new Money(2), new VictoryPoint(2))));
        cards.add(new BuildingCard("Mercato", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2),new Stone())), 3, new ReceiveGainablesEffect(new VictoryPoint(3)), new ExchangeGainablesEffect(new Money(3), new SetOfResources(new Wood(2), new Slave(2)))));
        cards.add(new BuildingCard("Tesoreria", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(3))), 3, new ReceiveGainablesEffect(new VictoryPoint(4)), new ExchangeGainablesEffect(new Money(), new VictoryPoint(3), new Money(2), new VictoryPoint(5))));
        cards.add(new BuildingCard("Gilda degli Scultori", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(6)), new ExchangeGainablesEffect(new Stone(1), new VictoryPoint(3), new Stone(3), new VictoryPoint(7))));

        //venture secondo periodo------------------------------------------------
        cards.add(new VentureCard("Costruire i Bastioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Stone(4))), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(2))));
        cards.add(new VentureCard("Supporto al Re", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(3), 6), new ReceiveGainablesEffect(new Money(5), new CouncilFavour(1)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new VentureCard("Accogliere gli Stranieri", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(4))), new ReceiveGainablesEffect(new Slave(5)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Sostegno al Cardinale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Wood(2), new Stone(2), new Money(3)), new MilitaryPointsTrack(4), 7), new ReceiveGainablesEffect(new FaithPointsTrack(3)), new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Ingaggiare Soldati", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(6)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Crociata", PeriodNumber.SECOND, new CardCost(new MilitaryPointsTrack(4), 8), new ReceiveGainablesEffect(new Money(5), new FaithPointsTrack(1)), new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Riparare l'Abbazia", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(2))), new ReceiveGainablesEffect(new FaithPointsTrack(2)), new ReceiveGainablesEffect(new VictoryPoint(6))));
        cards.add(new VentureCard("Scavare Canalizzazioni", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Slave(2), new Money(3))), new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new VictoryPoint(5))));

        //person secondo periodo------------------------------------------------
        cards.add(new PersonCard("Eroe", PeriodNumber.SECOND,new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.VENTURE), new ReceiveGainablesEffect(new CouncilFavour(1))) , null));
        cards.add(new PersonCard("Mecenate", PeriodNumber.SECOND,new CardCost(new SetOfResources(new Money(3))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.PERSON), new ReceiveGainablesEffect(new Money(2))) , null ));
        cards.add(new PersonCard("Fattore", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))),null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.HARVEST, 3))));
        cards.add(new PersonCard("Architetto", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(6, CardType.BUILDING), new ReceiveGainablesEffect(new Wood(), new Stone())), null));
        cards.add(new PersonCard("Messo Papale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new FaithPointsTrack(3)), null));
        cards.add(new PersonCard("Capitano", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects( new PlayExtraActionPhaseEffect(6, CardType.LAND), new ReceiveGainablesEffect(new MilitaryPointsTrack(2))), null));
        cards.add(new PersonCard("Messo Reale", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesEffect(new CouncilFavour(3)), null));
        cards.add(new PersonCard("Studioso", PeriodNumber.SECOND, new CardCost(new SetOfResources(new Money(4))), null, new ReceiveBonusesEffect(new BonusOnFamilyMemberPlacement(ActivationActionSpaceType.PRODUCTION, 3))));

        //territorio terzo periodo-----------------------------------------------------
        cards.add(new LandCard("Città Mercantile", PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new Money(), new Slave()), new ReceiveGainablesEffect(new Money(3))));
        cards.add(new LandCard("Castello",PeriodNumber.THIRD, 4, new ReceiveGainablesEffect(new Money(2), new VictoryPoint(2)), new ReceiveGainablesEffect(new MilitaryPointsTrack(3), new Slave())));
        cards.add(new LandCard("Santuario",PeriodNumber.THIRD, 1, new ReceiveGainablesEffect(new FaithPointsTrack()), new ReceiveGainablesEffect(new FaithPointsTrack(), new Money())));
        cards.add(new LandCard("Provincia", PeriodNumber.THIRD, 6, new ReceiveGainablesEffect(new CouncilFavour(1), new Stone()), new ReceiveGainablesEffect(new Stone(), new VictoryPoint(4))));
        cards.add(new LandCard("Colonia",PeriodNumber.THIRD, 5, new ReceiveGainablesEffect(new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Wood(), new VictoryPoint(4))));
        cards.add(new LandCard("Città Fortificata", PeriodNumber.THIRD, 2 , new ReceiveGainablesEffect(new Slave(), new MilitaryPointsTrack(2)), new ReceiveGainablesEffect(new Slave(2), new MilitaryPointsTrack(1))));
        cards.add(new LandCard("Tenuta",PeriodNumber.THIRD, 3, new ReceiveGainablesEffect(new Wood(), new VictoryPoint(1)), new ReceiveGainablesEffect(new Wood(2), new VictoryPoint(2))));
        cards.add(new LandCard("Cava di Marmo", PeriodNumber.THIRD, 2, new ReceiveGainablesEffect(new VictoryPoint(3)), new ReceiveGainablesEffect(new VictoryPoint(1), new Stone(2))));



        //building terzo periodo------------------------------------------------
        cards.add(new BuildingCard("Basilica", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(),new Stone(4))), 1, new ReceiveGainablesEffect(new FaithPointsTrack(1), new VictoryPoint(5)), new ExchangeGainablesEffect(new Wood(), new FaithPointsTrack(2), new Stone(), new FaithPointsTrack(2))));
        cards.add(new BuildingCard("Accademia Militare", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(), new Wood(2), new Stone(2))), 3, new ReceiveGainablesEffect(new VictoryPoint(7)), new ExchangeGainablesEffect(new Slave(), new MilitaryPointsTrack(3), new VictoryPoint(1))));
        cards.add(new BuildingCard("Fiera", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(3), new Money(4))), 4, new ReceiveGainablesEffect(new VictoryPoint(8)), new ExchangeGainablesEffect(new Money(4), new SetOfResources(new Stone(3), new Wood(3)))));
        cards.add(new BuildingCard("Castelletto", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(2), new Wood(2), new Stone(4))), 5, new ReceiveGainablesEffect(new VictoryPoint(9)), new ReceiveGainablesEffect(new VictoryPoint(2), new CouncilFavour(1)) ));
        cards.add(new BuildingCard("Palazzo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(1))), 6, new ReceiveGainablesEffect(new VictoryPoint(9)), new ExchangeGainablesEffect(new Money(1), new Slave(2), new VictoryPoint(4))));
        cards.add(new BuildingCard("Banca", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(1), new Stone(3))), 2, new ReceiveGainablesEffect(new VictoryPoint(7)), new ReceiveGainablesEffect(new Money(5))));
        cards.add(new BuildingCard("Giardino", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(2), new Wood(4), new Stone(2))), 1, new ReceiveGainablesEffect(new VictoryPoint(10)), new ReceiveGainablesEffect(new VictoryPoint(3))));
        cards.add(new BuildingCard("Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(4), new Stone(4))), 2, new ReceiveGainablesEffect(new FaithPointsTrack(3), new VictoryPoint(7)), new ReceiveGainablesEffect(new VictoryPoint(1))));



        //venture terzo periodo------------------------------------------------
        cards.add(new VentureCard("Ingaggiare Mercenari", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(8))), new ReceiveGainablesEffect(new MilitaryPointsTrack(7)),new ReceiveGainablesEffect(new VictoryPoint(6))));
        cards.add(new VentureCard("Conquista Militare", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(6), 12), new ReceiveGainablesEffect(new SetOfResources(new Money(3), new Wood(3), new Stone(3))),new ReceiveGainablesEffect(new VictoryPoint(7))));
        cards.add(new VentureCard("SOstegno al Papa", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4), new Stone(3), new Wood(3)), new MilitaryPointsTrack(5), 10), new ReceiveGainablesEffect(new FaithPointsTrack(10)),new ReceiveGainablesEffect(new VictoryPoint(5)) ));
        cards.add(new VentureCard("Migliorare le Strade", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Slave(3), new Money(4))), new PlayExtraActionPhaseEffect(3, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)) ,new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Riparare la Cattedrale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(3), new Wood(3), new Stone(3))), new PlayExtraActionPhaseEffect(7) ,new ReceiveGainablesEffect(new VictoryPoint(5))));
        cards.add(new VentureCard("Costruire le Torri", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Stone(6))), new ReceiveGainablesEffect(new MilitaryPointsTrack(4), new CouncilFavour(1)) , new ReceiveGainablesEffect(new VictoryPoint(4))));
        cards.add(new VentureCard("Commissionare Arte Sacra",PeriodNumber.THIRD, new CardCost(new SetOfResources(new Wood(6))),new ReceiveGainablesEffect(new FaithPointsTrack(3)),new ReceiveGainablesEffect(new VictoryPoint(3)) ));
        cards.add(new VentureCard("Guerra Santa", PeriodNumber.THIRD, new CardCost(new MilitaryPointsTrack(8),15), new ReceiveGainablesEffect(new FaithPointsTrack(4)),new ReceiveGainablesEffect(new VictoryPoint(8))));




        //person terzo periodo------------------------------------------------
        cards.add(new PersonCard("Governatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.BUILDING, new VictoryPoint(2)) , null));
        cards.add(new PersonCard("Vescovo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.PRODUCTION)), new ReceiveGainablesEffect(new FaithPointsTrack(1))), null));
        cards.add(new PersonCard("Cardinale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(4))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(4, new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST)), new ReceiveGainablesEffect(new FaithPointsTrack(2))), null));
        cards.add(new PersonCard("Cortigiana", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(7))),  new ReceiveGainablesForCardTypeEffect(CardType.PERSON, new VictoryPoint(2)) , null));
        cards.add(new PersonCard("Araldo", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.VENTURE, new VictoryPoint(2)), null));
        cards.add(new PersonCard("Generale", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(5))), new ReceiveGainablesForMilitaryPointsEffect(), null));
        cards.add(new PersonCard("Nobile", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new ReceiveGainablesForCardTypeEffect(CardType.LAND, new VictoryPoint(2)) , null));
        cards.add(new PersonCard("Ambasciatore", PeriodNumber.THIRD, new CardCost(new SetOfResources(new Money(6))), new SetOfCardEffects(new PlayExtraActionPhaseEffect(7), new ReceiveGainablesEffect(new CouncilFavour(1))) , null));


















        //-----------------------------------



        return cards;



    }

    public void writeCards(ArrayList<Card> cards) throws IOException {
        Gson gson = new Gson();
        FileWriter w = new FileWriter("file.txt");
        BufferedWriter b = new BufferedWriter(w);

        for(Card tmp: cards){

            String json = gson.toJson(tmp);

            b.write(json+ "\n");


        }

        b.flush();
    }


    public static void  main(String[] args){

        CardJsonWriter c = new CardJsonWriter();

        try {
            c.writeCards(c.CardDeclaration());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


