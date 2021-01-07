package BlackJackGame;

public class BlackJack {
    public static void main(String[] args) {
        Shoe shoe = new Shoe();
        Player dealer = new Player();
        Player playerOne = new Player();
        
        dealer.addToHand(shoe.draw());
        dealer.addToHand(shoe.draw());
        playerOne.addToHand(shoe.draw());
        playerOne.addToHand(shoe.draw());

        printGameState(dealer, playerOne, true);
    }

    public static void printGameState(Player dealer, Player playerOne, boolean hideDealerHand){
        System.out.println("\n--------------------------\n");
        System.out.println("Dealer's Hand:");
        if(hideDealerHand){
            System.out.printf("['%s','----']\n\n",dealer.hand.get(0));
        }
        else{
            System.out.println(dealer.hand + "\n");
        }

        System.out.println("Dealer's Total:");
        if(hideDealerHand){
            System.out.println("???\n");
        }
        else{
            System.out.println(dealer.total + "\n");
        }

        System.out.println("Your Hand:");
        System.out.println(playerOne.hand +"\n");

        System.out.println("Your Total:");
        System.out.println(playerOne.total);
        System.out.println("\n--------------------------\n");
    }

    public static void printSquiggleMessage(String body) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(body);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
