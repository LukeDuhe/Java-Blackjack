package BlackJackGame;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    static private HashMap<String,Integer> cardValues = new HashMap<String,Integer>();

    public int total;
    public ArrayList<String> hand;

    public Player(){
        total = 0;
        hand = new ArrayList<String>();

        cardValues.put("ace",11);
        cardValues.put("king",10);
        cardValues.put("queen",10);
        cardValues.put("jack",10);
        cardValues.put("ten",10);
        cardValues.put("nine",9);
        cardValues.put("eight",8);
        cardValues.put("seven",7);
        cardValues.put("six",6);
        cardValues.put("five",5);
        cardValues.put("four",4);
        cardValues.put("three",3);
        cardValues.put("two",2);
        cardValues.put("one",1);
    }

    public void addToHand(String card){
        hand.add(card);

        total = 0;
        int acesCount = 0;
        for (String cardName : hand) {
            total += cardValues.get(cardName);
            if(cardName == "ace"){
                acesCount++;
            }
        }

        if(total > 21){
            total -= acesCount*10;
        }
    }

    public void reset(){
        total = 0;
        hand.clear();
    }
}
