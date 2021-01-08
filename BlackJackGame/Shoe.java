package BlackJackGame;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Shoe {
    static private String[] cardTypes = {
        "ace", "king", "queen", "jack", "ten", "nine", "eight", 
        "seven", "six", "five", "four", "three", "two", "one"};
    private HashMap<String,Integer> deck;
    private int cardCount;

    public Shoe() {
        deck = new HashMap<String,Integer>();
        deck.put("ace",4);
        deck.put("king",4);
        deck.put("queen",4);
        deck.put("jack",4);
        deck.put("ten",4);
        deck.put("nine",4);
        deck.put("eight",4);
        deck.put("seven",4);
        deck.put("six",4);
        deck.put("five",4);
        deck.put("four",4);
        deck.put("three",4);
        deck.put("two",4);
        deck.put("one",4);

        cardCount = 52;
    }

    public void shuffle() {
        deck.replaceAll((card,count) -> 4);
        cardCount = 52;
    }

    public int getCardCount() {
        return cardCount;
    }

    public String draw() {
        ArrayList<String> filteredDeck = getFilteredDeck();

        Random random = new Random();
        int randomInt = random.nextInt(filteredDeck.size());

        String selectedCard = filteredDeck.get(randomInt);

        deck.put(selectedCard, deck.get(selectedCard) - 1);

        cardCount--;

        return selectedCard;
    }

    private ArrayList<String> getFilteredDeck() {
        ArrayList<String> filteredDeck = new ArrayList<String>();

        for (String card : cardTypes) {
            if(deck.get(card) > 0){
                filteredDeck.add(card);
            }
        }

        return filteredDeck;
    }
}
