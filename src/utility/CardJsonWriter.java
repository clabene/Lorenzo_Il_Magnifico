package utility;
import cards.Card;
import com.google.gson.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Pinos on 31/05/2017.
 */
public class CardJsonWriter {

    public ArrayList<Card> CardDeclaration(){
        ArrayList<Card> cards = new ArrayList<>();



        return cards;
    }

    public void writeCards(ArrayList<Card> cards) throws IOException {
        Gson gson = new Gson();

        for(Card tmp: cards){

            String json = gson.toJson(tmp);

            FileWriter w = new FileWriter("a.txt");
            BufferedWriter b = new BufferedWriter(w);
            b.write(json+ "\n");
            b.flush();

        }
    }

}


