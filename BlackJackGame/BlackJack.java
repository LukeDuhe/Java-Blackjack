package BlackJackGame;
import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;

public class BlackJack {
    public static void main(String[] args) {
        Shoe shoe = new Shoe();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Player dealer = new Player();
            Player playerOne = new Player();
        
            dealer.addToHand(shoe.draw());
            dealer.addToHand(shoe.draw());
            playerOne.addToHand(shoe.draw());
            playerOne.addToHand(shoe.draw());

            System.out.println("\nRound Start!!!");

            while (playerOne.total < 22) {
                printGameState(dealer,playerOne,true);

                boolean validInput = false;
                String move = "";
                while (!validInput) {
                    System.out.println("Would you like to [h]it or [s]tay?");
                    move = scanner.nextLine();
                    if (new ArrayList<String>(List.of("hit","h","stay","s")).contains(move)){
                        validInput = true;
                    }
                }

                if (move.equals("h") || move.equals("hit")) {
                    playerOne.addToHand(shoe.draw());
                    System.out.println("\n\"HIT ME!\"");
                }
                else if (move.equals("s") || move.equals("stay")) {
                    System.out.println("\n\"I'll stay.\"");
                    break;
                }
            }

            if(playerOne.total > 21){
                printGameState(dealer,playerOne,false);
                printSquiggleMessage("YOU BUSTED!\nYOU LOSE!");
            }
            else if (playerOne.total == 21) {
                printSquiggleMessage("YOU GOT 21!\nYOU WIN!");
            }
            else {
                while (dealer.total <= 16) {
                    dealer.addToHand(shoe.draw());
                }

                System.out.println("\nRevealing final hands...");
                printGameState(dealer,playerOne,false);

                if (dealer.total > 21) {
                    printSquiggleMessage("THE DEALER BUSTED!\nYOU WIN!");
                }
                else if (dealer.total > playerOne.total) {
                    printSquiggleMessage("YOU LOSE!");
                }
                else if (dealer.total < playerOne.total) {
                    printSquiggleMessage("YOU WIN!");
                }
                else {
                    printSquiggleMessage("IT'S A DRAW!");
                }
            }

            boolean validInput = false;
            String decision = "";
            while (!validInput) {
                System.out.println("Play again?  Y or N\n");
                decision = scanner.nextLine();
                if (new ArrayList<String>(List.of("Y","y","N","n")).contains(decision)){
                    validInput = true;
                }
            }
            
            if (decision.equals("N") || decision.equals("n")) {
                printSquiggleMessage("Thanks for playing!");
                break;
            }

            if (shoe.getCardCount() < 26) {
                printSquiggleMessage("Reshuffling shoe...");
                shoe.shuffle();
            }
        }
        scanner.close();
    }

    public static void printGameState(Player dealer, Player playerOne, boolean hideDealerHand){
        System.out.println("\n----------------------------------\n");
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
        System.out.println("\n----------------------------------\n");
    }

    public static void printSquiggleMessage(String body) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(body);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
