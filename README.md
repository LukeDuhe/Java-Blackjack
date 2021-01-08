# Java-Blackjack
    A simple blackjack game in Java.


## How to Compile and Run
---
This program was developed using JDK version 15.0.1, so that version should be used to avoid any issues. 

1. Navigate to the `BlackJackGame/` directory.
1. Use javac to compile.
    - `javac BlackJackGame/BlackJack.java`
1. Run the program. 
    - `java BlackJackGame/BlackJack` 
1. Enjoy and good luck! 


## Overview and Rules
---
This game follows the general rules of single-deck Blackjack with one player and one dealer.

At the start of each round the Dealer and the Player are both dealt two cards. Only one of the dealer's cards is dealt face-up, allowing the Player to assess their odds of beating the Dealer's hand.

If the player is dealt 21 right away, they get Blackjack! But the round can still end in a draw if the dealer was also dealt a Blackjack. 

Otherwise, the player is asked if they want to "hit" or "stay". 

If the player hits, they are dealt a card. If that card pushes their hand over 21, they "bust" and automatically lose the round. 

If the player "stays" they decide to stick with the cards they have. 

Once the player stays, the Dealer must continue to hit until their hand reaches at least 17, then immediately stay. If the dealer busts, the player wins. 

If the Dealer's hand reaches at least 17 without busting, the final outcome of the round is determined accordingly:
- If the Dealer's hand is higher, the player loses.
- If the Player's hand is higher, the player wins.
- If the Player and Dealer have the same value hands, the round ends in a draw. 

At the end of the round the player is asked if they would like to play again. 
- If the player says yes, all of the cards in play are discarded and a new round begins. 
- If the player says no, the program ends. 

If the player decides to play another round and there are less than 26 cards left in the shoe, all of the cards are gathered and the shoe is reshuffled. 


## Other Notes
---
- When asked to provide input, you can enter either the whole word (hit or stay, yes or no) or a single letter (h or s, y or n). Neither are case-sensitive.
- I decided to go with the single-deck setup because it makes counting cards easier for the player and gives the dealer less edge. 
- I mayyy have read Clean Code recently, so I decided to refactor my functions to improve readability as described in that book. I believe it does make the code more readable, especially with typical IDE features like "go to definition". I certainly didn't follow ALL of their rules, but I decided to structure it so that the main method is an outline of the entire program, while the other methods describe the details. 
- Also, in case y'all look at the git history and it seems weird, I originally wrote the program in Python, in a separate repo. Then I rewrote it in Java. I can share a link to that repo if necessary, but I just wanted to be clear about that :) . 