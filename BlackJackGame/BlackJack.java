package BlackJackGame;
import java.util.Scanner; 
import java.util.Arrays;
import java.util.List;

public class BlackJack {
    static Shoe shoe;
    static Player dealer;
    static Player player;

    static Scanner scanner;

    public static void main(String[] args) {
        shoe = new Shoe();
        dealer = new Player();
        player = new Player();

        scanner = new Scanner(System.in);

        while (true) {
            setupRound();

            if (player.getTotal() == 21) {
                handleBlackjack();
            } 
            else {
                handleHitOrStayLoop();
                handleFinalHandsScoring();
            }

            if (!playAgain()) {
                break;
            }
        }

        scanner.close();
    }

    public static void setupRound() {
        dealer.reset();
        player.reset();

        reshuffleShoeIfNeeded();

        dealer.addToHand(shoe.draw());
        dealer.addToHand(shoe.draw());

        player.addToHand(shoe.draw());
        player.addToHand(shoe.draw());

        System.out.println("\nRound Start!!!");
    }

    public static void reshuffleShoeIfNeeded() {
        if (shoe.getCardCount() < 26) {
            printSquiggleMessage("Reshuffling shoe...");
            shoe.shuffle();
        }
    }

    public static void handleBlackjack() {
        printGameState(false);
        if (dealer.getTotal() == 21) {
            printSquiggleMessage("YOU GOT BLACKJACK!\n"
                                +"BUT SO DID THE DEALER...\n"
                                +"IT'S A DRAW.");
        }
        else {
            printSquiggleMessage("YOU GOT BLACKJACK!\nYOU WIN!");
        }
    }

    public static void handleHitOrStayLoop() {
        printGameState(true);
        while (player.getTotal() < 22) {
            String question = "Would you like to [h]it or [s]tay?";
            List<String> validEntries = Arrays.asList("hit","h","stay","s");
            String move = askUntilValidInput(question, validEntries);
            if (move.equals("h") || move.equals("hit")) {
                player.addToHand(shoe.draw());
                System.out.println("\n\"HIT ME!\"");
            }
            else if (move.equals("s") || move.equals("stay")) {
                System.out.println("\n\"I'll stay.\"");
                break;
            }
            printGameState(true);
        }
    }

    public static void handleFinalHandsScoring() {
        if (player.getTotal() > 21) {
            printGameState(false);
            printSquiggleMessage("YOU BUSTED!\nYOU LOSE!");
        } 
        else {
            hitDealerToSeventeen();

            System.out.println("\nRevealing final hands...");
            printGameState(false);

            if (dealer.getTotal() > 21) {
                printSquiggleMessage("THE DEALER BUSTED!\nYOU WIN!");
            }
            else if (dealer.getTotal() > player.getTotal()) {
                printSquiggleMessage("YOU LOSE!");
            }
            else if (dealer.getTotal() < player.getTotal()) {
                printSquiggleMessage("YOU WIN!");
            }
            else {
                printSquiggleMessage("IT'S A DRAW!");
            }
        }
    }

    public static void hitDealerToSeventeen() {
        while (dealer.getTotal() <= 16) {
            dealer.addToHand(shoe.draw());
        }
    }

    public static boolean playAgain() {
        String question = "Play again?  Y or N";
        List<String> validEntries = Arrays.asList("Y","y","N","n");
        String decision = askUntilValidInput(question, validEntries);
        
        if (decision.equals("N") || decision.equals("n")) {
            printSquiggleMessage("Thanks for playing!");
            return false;
        } 
        else {
            return true;
        }
    }

    public static String askUntilValidInput(String question, List<String> validEntries) {
        boolean validInput = false;
        String decision = "";
        while (!validInput) {
            System.out.println(question);
            decision = scanner.nextLine();
            if (validEntries.contains(decision)) {
                validInput = true;
            }
        }
        return decision;
    }

    public static void printGameState(boolean hideDealerHand) {
        System.out.println("\n----------------------------------\n");
        System.out.println("Dealer's Hand:");
        if (hideDealerHand) {
            System.out.printf("['%s','----']\n\n",dealer.getHand().get(0));
        }
        else {
            System.out.println(dealer.getHand() + "\n");
        }

        System.out.println("Dealer's Total:");
        if (hideDealerHand) {
            System.out.println("???\n");
        }
        else {
            System.out.println(dealer.getTotal() + "\n");
        }

        System.out.println("Your Hand:");
        System.out.println(player.getHand() + "\n");

        System.out.println("Your Total:");
        System.out.println(player.getTotal());
        System.out.println("\n----------------------------------\n");
    }

    public static void printSquiggleMessage(String body) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(body);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
