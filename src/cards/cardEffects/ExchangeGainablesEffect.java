package cards.cardEffects;


import interfaces.Gainable;
import interfaces.Losable;
import player.Player;
import pointsTracks.FaithPointsTrack;
import pointsTracks.MilitaryPointsTrack;
import resources.Resource;
import resources.Slave;
import resources.Wood;

import java.util.Scanner;

/**
 * Created by IBM on 27/05/2017.
 */
public class ExchangeGainablesEffect implements CardEffect{

    private Losable[] losables = new Losable[2];
    private Gainable[] gainables = new Gainable[2];

    public ExchangeGainablesEffect(Losable losable, Gainable gainable) {
        this.losables[0] = losable;
        this.gainables[0] = gainable;
        this.losables[1] = null;
        this.gainables[1] = null;
    }

    public ExchangeGainablesEffect(Losable losable, Gainable gainable, Losable losable2, Gainable gainable2){
        this.losables[0] = losable;
        this.gainables[0] = gainable;
        this.losables[1] = losable2;
        this.gainables[1] = gainable2;
    }

    private void printMenu(){
        System.out.println("What do you wish to do? You can:");
        System.out.println("0 Do nothing");
        System.out.println("1 Exchange "+losables[0]+" in order to get "+gainables[0]);
        if(losables[1] != null && gainables[1] != null)
            System.out.println("or\n2 Exchange "+losables[1]+" in order to get "+gainables[1]);
    }
    private int selectedFromMenu(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt()-1;
    }

    private boolean doSelectedAction(int selectedInteger, Player player){
        if(selectedInteger == -1) return true;

        if(checkTypeOfLosable(selectedInteger, player)){
            player.getPlank().setToUseSeparateResources(false); //player can only use the resources they had before starting the activation
            if(player.lose(losables[selectedInteger])){
                player.getPlank().setToUseSeparateResources(true);
                player.gain(gainables[selectedInteger]);
                return true;
            }
        }
        System.out.println("\nYou cannot afford the exchange");
        return false;
    }

    private boolean checkTypeOfLosable(int selectedInteger, Player player){
        if(losables[selectedInteger] instanceof Resource)
            return true;

        if(losables[selectedInteger] instanceof MilitaryPointsTrack)
            return ((MilitaryPointsTrack) losables[selectedInteger]).getTrackPosition().getValue() <= player.getMilitaryPoints().getTrackPosition().getValue();

        if(losables[selectedInteger] instanceof FaithPointsTrack)
            return ((FaithPointsTrack) losables[selectedInteger]).getTrackPosition().getValue() <= player.getFaithPoints().getTrackPosition().getValue();

        //might wanna add VictoryPoint

        return false;
    }

    @Override
    public void activate(Player player){
        do{
            printMenu();
        } while(!doSelectedAction(selectedFromMenu(), player));
    }


    public static void main(String[] a){
        Player p = new Player();
        p.gain(new Wood(2), new Slave(3));
        System.out.println(p.getPlank().getSetOfResources());
        System.out.println(p.getMilitaryPoints());
        System.out.println(p.getFaithPoints());
        ExchangeGainablesEffect e = new ExchangeGainablesEffect(new Slave(12), new MilitaryPointsTrack(3), new Wood(), new FaithPointsTrack(1));
        e.activate(p);
        System.out.println(p.getPlank().getSetOfResources());
        System.out.println(p.getFaithPoints());
        System.out.println(p.getMilitaryPoints());

    }




}
