package BlackJackGame;
import java.util.Scanner; 
import java.util.Arrays;

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

            if (player.total == 21) {
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
        if (dealer.total == 21) {
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
        while (player.total < 22) {
            String move = askForPlayerMove();
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

    public static String askForPlayerMove() {
        boolean validInput = false;
        String move = "";
        while (!validInput) {
            System.out.println("Would you like to [h]it or [s]tay?");
            move = scanner.nextLine();
            if (Arrays.asList("hit","h","stay","s").contains(move)) {
                validInput = true;
            }
        }
        return move;
    }

    public static void handleFinalHandsScoring() {
        if (player.total > 21) {
            printGameState(false);
            printSquiggleMessage("YOU BUSTED!\nYOU LOSE!");
        } 
        else {
            hitDealerToSeventeen();

            System.out.println("\nRevealing final hands...");
            printGameState(false);

            if (dealer.total > 21) {
                printSquiggleMessage("THE DEALER BUSTED!\nYOU WIN!");
            }
            else if (dealer.total > player.total) {
                printSquiggleMessage("YOU LOSE!");
            }
            else if (dealer.total < player.total) {
                printSquiggleMessage("YOU WIN!");
            }
            else {
                printSquiggleMessage("IT'S A DRAW!");
            }
        }
    }

    public static void hitDealerToSeventeen() {
        while (dealer.total <= 16) {
            dealer.addToHand(shoe.draw());
        }
    }

    public static boolean playAgain() {
        boolean validInput = false;
        String decision = "";
        while (!validInput) {
            System.out.println("Play again?  Y or N");
            decision = scanner.nextLine();
            if (Arrays.asList("Y","y","N","n").contains(decision)) {
                validInput = true;
            }
        }
        
        if (decision.equals("N") || decision.equals("n")) {
            printSquiggleMessage("Thanks for playing!");
            return false;
        } 
        else {
            return true;
        }
    }

    public static void printGameState(boolean hideDealerHand) {
        System.out.println("\n----------------------------------\n");
        System.out.println("Dealer's Hand:");
        if (hideDealerHand) {
            System.out.printf("['%s','----']\n\n",dealer.hand.get(0));
        }
        else {
            System.out.println(dealer.hand + "\n");
        }

        System.out.println("Dealer's Total:");
        if (hideDealerHand) {
            System.out.println("???\n");
        }
        else {
            System.out.println(dealer.total + "\n");
        }

        System.out.println("Your Hand:");
        System.out.println(player.hand + "\n");

        System.out.println("Your Total:");
        System.out.println(player.total);
        System.out.println("\n----------------------------------\n");
    }

    public static void printSquiggleMessage(String body) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(body);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
