package BlackJackGame;

import java.util.ArrayList;
import java.util.Map;

public class Player {

    static private Map<String,Integer> cardValues = Map.ofEntries(
        Map.entry("ace",11),
        Map.entry("king",10),
        Map.entry("queen",10),
        Map.entry("jack",10),
        Map.entry("ten",10),
        Map.entry("nine",9),
        Map.entry("eight",8),
        Map.entry("seven",7),
        Map.entry("six",6),
        Map.entry("five",5),
        Map.entry("four",4),
        Map.entry("three",3),
        Map.entry("two",2),
        Map.entry("one",1)
    );

    private int total;
    private ArrayList<String> hand;

    public Player() {
        total = 0;
        hand = new ArrayList<String>();
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<String> getHand() {
        return hand;
    }

    public void addToHand(String card) {
        hand.add(card);

        total = 0;
        int acesCount = 0;
        for (String cardName : hand) {
            total += cardValues.get(cardName);
            if (cardName.equals("ace")) {
                acesCount++;
            }
        }

        for (int i=0; i<acesCount; i++) {
            if (total < 22) {
                break;
            }
            total -= 10;
        }
    }

    public void reset() {
        total = 0;
        hand.clear();
    }
}
