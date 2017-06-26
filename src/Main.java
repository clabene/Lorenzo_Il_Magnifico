import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.cards.Card;
import logic.player.Player;
import logic.pointsTracks.FaithPointsTrack;
import logic.utility.CardSetupHandler;

import java.util.Stack;

/**
 * Created by IBM on 09/05/2017.
 */
public class Main extends Application {


    public static void main(String args[]){

        /*
        Player player = new Player();

        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("vitory "+player.getQuantity());

        player.gain(new Losable(new Money(3), new Stone(7)), new MilitaryPointsTrack(27),
                new FaithPointsTrack(3), new FaithPointsTrack(1), new LandCardsPointsTrack(7),
                new VictoryPoint(21), new Wood(3));

        System.out.println("---------------------------------------------------");


        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("victory "+player.getQuantity());

        player.lose(new Stone(4), new MilitaryPointsTrack(3), new Losable(new Money(1), new Stone(4)),
                new Money(2), new VictoryPoint(31));

        System.out.println("---------------------------------------------------");


        System.out.println(player.getPlank().getSetOfResources());
        System.out.println("military "+player.getMilitaryPoints().getTrackPosition().getValue());
        System.out.println("faith "+player.getFaithPoints().getTrackPosition().getValue());
        System.out.println("person "+player.getPersonCardsPoints().getTrackPosition().getValue());
        System.out.println("land "+player.getLandCardsPoints().getTrackPosition().getValue());
        System.out.println("victory "+player.getQuantity());
        */
        /*
        Gainable g = new FaithPointsTrack(2);
        System.out.println(g instanceof Stone);
        System.out.println(g instanceof Wood);
        System.out.println(g instanceof Resource);
        System.out.println(g instanceof PointsTrack);
        System.out.println(g instanceof FaithPointsTrack);
        System.out.println(g instanceof MilitaryPointsTrack);

        ActionSpaceType a = ActionSpaceType.ACTIVATION;
        System.out.println(ActionSpaceType.ACTIVATION == a);
        */
        /*
        Plank p = new Plank();
        Plank p2 = p;
        System.out.println(p);
        System.out.println(p2);
        p = new Plank();
        System.out.println(p);
        System.out.println(p2);
        */
        /*
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3; j++){
                if(j == 2) continue;
                System.out.println("a");
            }
        }

        Player p = new Player();
        p.gain(new MilitaryPointsTrack(7));
        System.out.println(p.lose(new MilitaryPointsTrack(10)));

        ArrayList<Plank> planks = new ArrayList<>();
        planks.add(new Plank());
        System.out.println(planks.get(planks.size()-1));
        */
        /*
        Board b = new Board(7);

        Player cla = new Player(new Slave(2), new Money(5));

        cla.setId("q");

        cla.getFamilyMembers()[0].incrementFamilyMemberValue(100);
        try {
            cla.tryToTakeCard(new LandCard("ciao", PeriodNumber.FIRST, 3, new ReceiveGainablesEffect(new Slave(2)), new ExchangeGainablesEffect(new Money(2), new Wood(4))));
        } catch (Exception e){}

        System.out.println(cla.getPlank().getSetOfResources());
        System.out.println(cla.getPlank().getCards());

        //TowerActionSpace towerActionSpace = new TowerActionSpace(1, new Wood(2));

        //TowerActionSpace towerActionSpace = (TowerActionSpace) b.getArea(0).getActionSpace(0);
        //towerActionSpace.setCard(new LandCard("ww", PeriodNumber.FIRST, 0, new ReceiveGainablesEffect(new Slave(2)), new ReceiveGainablesEffect(new Wood(2))));

        ActivationActionSpace activationActionSpace = new ActivationActionSpace(1, ActivationActionSpaceType.HARVEST);

        ActionPhase a = new ActionPhase();
        a.putFamilyMemberOnActionSpace(cla, cla.getFamilyMembers()[0], activationActionSpace);

        System.out.println(cla.getPlank().getSetOfResources());
        */
        /*
        TowerActionSpace t1 = new TowerActionSpace(3, new Wood(2));
        TowerActionSpace t2 = new TowerActionSpace(1);
        t1.familyMemberAdded(new FamilyMember(Color.BLACK, 2, "ww"));
        t2.familyMemberAdded(new FamilyMember(Color.WHITE, 3, "ee"));

        String s = "";

        s += t1+"\t";
        s += t1+"\n";
        s += t1.getLastFamilyMemberAdded() + "\t\t\t";
        s += t2.getLastFamilyMemberAdded();

        System.out.println(s);
        */

        Player p = new Player();
        p.gain(new FaithPointsTrack(10));
        System.out.println(p.getFaithPoints().getTrackPosition().getValue());
        System.out.println(p.lose(new FaithPointsTrack(2)));
        System.out.println(p.getFaithPoints().getTrackPosition().getValue());


        //launch();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        LabeledImageView l = new LabeledImageView("Ciaone", "userInterface/cancellami.jpg");
        l.setLayoutX(20);
        l.setLayoutY(20);
        l.setFitHeight(150);
        l.setFitWidth(100);
        Pane pane = new Pane();
        pane.getChildren().addAll(l.getComponents());
        primaryStage.setScene(new Scene(pane, 300, 300));
        primaryStage.show();
        */
        /*
        ActionSpaceImageView a = new ActionSpaceImageView("userInterface/gui/images/cancellami.jpg");
        a.setFitHeight(150);
        a.setFitWidth(500);
        a.addFamilyMemberImage();
        a.addFamilyMemberImage();
        Pane pane = new Pane();
        a.xProperty().bind(pane.widthProperty().divide(4));
        pane.getChildren().addAll(a.getComponents());
        pane.getChildren().add(a.addFamilyMemberImage());
        primaryStage.setScene(new Scene(pane, 700, 400));
        primaryStage.show();
        */
        /*
        TowerActionSpaceImageView t = new TowerActionSpaceImageView("T","userInterface/gui/images/cancellami.jpg", "ciaone");
        t.setFitWidth(80);
        t.setFitHeight(80);
        t.setX(300);
        t.setY(300);
        Pane pane = new Pane();
        t.xProperty().bind(pane.widthProperty().divide(4));
        t.yProperty().bind(pane.heightProperty().divide(3));
        pane.getChildren().addAll(t.getComponents());
        primaryStage.setScene(new Scene(pane, 700, 700));
        primaryStage.show();
        */
        /*
        TowerActionSpaceImageView t1 = new TowerActionSpaceImageView("T1","userInterface/gui/images/cancellami.jpg", "ciaone");
        TowerActionSpaceImageView t2 = new TowerActionSpaceImageView("T2","userInterface/gui/images/cancellami.jpg", "ciaone");
        TowerView tv = new TowerView(300, 100, t1, t2);
        Pane pane = new Pane();
        pane.getChildren().addAll(tv.getComponents());
        primaryStage.setScene(new Scene(pane, 700, 700));
        primaryStage.show();
        */
        /*
        BoardView b = new BoardView();
        Pane pane = new Pane();
        b.getXPosition().bind(pane.widthProperty().divide(10).add(TowerActionSpaceImageView.getLeftOffset()));
        b.getYPosition().bind(pane.heightProperty().divide(30));
        //b.getYPosition().set(b.getSuperiorOffset()+10);
        pane.getChildren().addAll(b.getComponents());
        primaryStage.setScene(new Scene(pane, 1200, 900));
        primaryStage.show();
        */
        /*
        PlayerView p = new PlayerView(PlayerView.ConfigurationMode.OPPONENT);
        p.layoutXProperty().set(0);
        TitledPane g = new TitledPane("jhonny", "wwqww", p);
        g.setText("pippo");
        g.setPrefHeight(600);
        g.setPrefWidth(400);
        Accordion a = new Accordion(g);
        a.setLayoutX(100);
        a.setLayoutY(20);

        Pane mainPain = new Pane(a);

        primaryStage.setScene(new Scene(mainPain, 900, 900));
        primaryStage.show();
        */
        /*
        PlayerView p = new PlayerView("a", "qqwqq", PlayerView.ConfigurationMode.MY_PLAYER);

        TabPane tabPane;

        Tab tab = new Tab("Resources", new Group( p.getResourceImages() ) );
        Tab tab1 = new Tab("Land Cards", new Group(p.getLandCardsName()));

        tabPane = new TabPane(tab, tab1);
        tabPane.setLayoutX(100);
        tabPane.setLayoutY(20);

        primaryStage.setScene(new Scene(new Pane(tabPane), 900, 800));
        primaryStage.show();
        */




        /*
        Pane pane = new Pane();


        BoardView b = new BoardView();
        b.bindXPosition(pane.widthProperty().divide(30));
        b.bindYPosition(pane.heightProperty().divide(20));
        pane.getChildren().addAll(b.getComponents());

        OpponentView o = new OpponentView("id", "name");
        OpponentView o2 = new OpponentView("id2", "name2");
        OpponentView o2 = new OpponentView("id2", "name2");
        Accordion a = new Accordion(o, o2);
        a.layoutXProperty().bind(b.getXPosition().add(b.getWidthProperty()).add(b.getXPosition()));
        a.layoutYProperty().bind(b.getYPosition().add(20));
        pane.getChildren().add(a);

        MyPlayerView mp = new MyPlayerView("id", "name");
        mp.layoutXProperty().bind(b.getXPosition().add(b.getWidthProperty().divide(2.2)));
        mp.layoutYProperty().bind(b.getYPosition().add(b.getHeightProperty()).add(20));
        mp.setBackground(new Background(new BackgroundFill(Color.TAN, new CornerRadii(100), null)));
        pane.getChildren().add(mp);

        primaryStage.setScene(new Scene(pane, 1200, 900));
        */
        CardSetupHandler cardSetupHandler = new CardSetupHandler();
        Stack<Card> cards = cardSetupHandler.shuffleHandle();
        while(!cards.isEmpty()){
            System.out.println(cards.peek());
            Pane pane = new Pane(new ImageView(new Image("userInterface/gui/images/cards/"+ cards.peek().getName()+".png")));
            primaryStage.setScene(new Scene(pane, 1200, 900));
            primaryStage.show();
            cards.pop();

        }
        //Pane pane = new Pane(new ImageView(new Image()));






    }
}
