/** Game.java
  *
  * Simulates a crazy 8 card game.
  *
  * @author Jonathon Elfar 
  * @author Justin Cellona
  */
import java.util.Scanner;
public class Game {
    public static final int NUMBER_OF_CARDS = 8;
    public static final String MAGIC_NUMBER_STR = "Eight";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        boolean eightPlayed = false;

        deck.shuffle();

        //cardOnTop keeps track of last played card
        Card cardOnTop = deck.deal();

        //Store current suit and face of last played card
        String currentSuit = cardOnTop.getSuit();
        String currentFace = cardOnTop.getFaceString();

        //Deal hands
        Hand userHand = deck.dealHand(NUMBER_OF_CARDS);
        Hand computerHand = deck.dealHand(NUMBER_OF_CARDS);


        System.out.println("Greetings, Let's play Crazy 8's.");
        System.out.println("_______________________________________________");
        System.out.println();

        //Main game loop
        while(userHand.getLength() != 0 && computerHand.getLength() != 0) {
            System.out.println("Here's your hand: ");
            System.out.println();
            System.out.println(userHand);
            System.out.println();

            //Change output depending on if 8 is played
            if(eightPlayed) {
                String msg = "The top card is a wildcard of ";
                msg += currentSuit + ".";
                System.out.println(msg);
            } else {
                System.out.println("The top card is the " + cardOnTop);
            }
            System.out.println();
            int userHandLength;

            //Change output depending on how many cards in hand
            if((userHandLength = userHand.getLength()) == 1) {
                System.out.println("What would you like to do? Select 1 to play your card, or 0 to draw from the deck.");
            } else {
                System.out.println("What would you like to do? Select 1 - " + userHandLength + 
                        " to play a card, or 0 to draw from the deck.");
            }

            boolean done = false;
            //Loop until user is done choosing an option
            while(!done) {
                //Get choice
                int choice;
                while(!sc.hasNextInt() || (choice = sc.nextInt()) < -1 || choice > userHandLength){
                    System.out.println("Invalid Choice");
                    sc.nextLine();
                }

                //Deal a card
                if(choice == 0) {
                    Card newCard = deck.deal();
                    if(deck.isEmpty()) {
                        System.out.println("The deck ran out of cards. Tie game!");
                        return;
                    }
                    userHand.addCard(newCard);
                    System.out.println();
                    System.out.println("Your new card is the " + newCard + ".");
                    System.out.println();
                    done = true;
                //Cheating
                } else if(choice == -1) {
                    Card chosenCard = userHand.removeCard(0);
                    if(userHand.isEmpty()) {
                        System.out.println("All of your cards are gone. You win!");
                        return;
                    }

                    cardOnTop = chosenCard;
                    currentSuit = chosenCard.getSuit();
                    currentFace = chosenCard.getFaceString();
                    System.out.println("The top card is the " + cardOnTop + ". ");
                    done = true;
                    eightPlayed = false;
                } else {
                    Card chosenCard = userHand.getCard(choice - 1);
                    //If you choose an 8
                    if(chosenCard.getFaceString().equals(MAGIC_NUMBER_STR)) {
                        userHand.removeCard(choice - 1);
                        if(userHand.isEmpty()) {
                            System.out.println("All of your cards are gone. You win!");
                            return;
                        }
                        System.out.println("You played a Crazy 8. Please pick from the following suits.");
                        System.out.println();
                        System.out.println("1. Hearts\n2. Diamonds\n3. Clubs\n4. Spades");
                        int choice2;
                        while(!sc.hasNextInt() || (choice2 = sc.nextInt()) < 1 || choice2 > 4) {
                            System.out.println("Invalid Choice");
                            sc.nextLine();
                        }

                        String msg = "The top card is a wildcard of ";
                        switch(choice2) {
                            case 1:
                                currentSuit = "Hearts";
                                msg += currentSuit + ".";
                                break;
                            case 2:
                                currentSuit = "Diamonds";
                                msg += currentSuit + ".";
                                break;
                            case 3:
                                currentSuit = "Clubs";
                                msg += currentSuit + ".";
                                break;
                            case 4:
                                currentSuit = "Spades";
                                msg += currentSuit + ".";
                                break;
                            default:
                                break;
                        }

                        System.out.println(msg);
                        System.out.println();
                        done = true;
                        eightPlayed = true;
                    //Play card if valid or else alert user of invalid operation
                    } else {
                        boolean sameSuit = chosenCard.getSuit().equals(currentSuit);
                        boolean sameFace = chosenCard.getFaceString().equals(currentFace);
                        //Handles wildcard logic-- if there is a wildcard on top only pay attention
                        //to the suit, otherwase test card for suit or face.
                        if((eightPlayed && sameSuit) || (!eightPlayed && (sameSuit || sameFace) )) {
                            userHand.removeCard(choice - 1);
                            if(userHand.isEmpty()) {
                                System.out.println("All of your cards are gone. You win!");
                                return;
                            }
                            cardOnTop = chosenCard;
                            currentSuit = chosenCard.getSuit();
                            currentFace = chosenCard.getFaceString();
                            System.out.println("The top card is the " + cardOnTop + ". ");
                            done = true;
                            eightPlayed = false;
                        } else { 
                            System.out.println("That's an invalid play. Try again.");
                            //Restarts the loop
                            continue;
                        }
                    }
                }

                //Computer's turn
                Card cardToPlay;
                //If computer has a card in its hand with current suit(if eight is played or not), face, and is not an 8
                //Handles wildcard logic-- if there is a wildcard on top only pay attention
                //to the suit, otherwase test card for suit or face.
                if((cardToPlay = computerHand.tryGetValidCard(currentSuit, currentFace, eightPlayed)) != null) {
                    if(computerHand.isEmpty()) {
                        System.out.println("Computer is out of cards. Computer wins!");
                        return;
                    }
                    cardOnTop = cardToPlay;
                    currentSuit = cardOnTop.getSuit();    
                    currentFace = cardOnTop.getFaceString();
                    System.out.println("The computer has played a card. The computer now has " 
                            + computerHand.getLength() + " cards.");
                    System.out.println(); 
                    done = true;
                    eightPlayed = false;
                //Else if computer has an eight in its hand
                } else if((cardToPlay = computerHand.tryGetEight()) != null) {
                    if(computerHand.isEmpty()) {
                        System.out.println("Computer is out of cards. Computer wins!");
                        return;
                    }
                    currentSuit = cardToPlay.getSuit(); 
                    //Randomly pick an int for computer's crazy 8
                    int ranInt = ((int)Math.random()*4) + 1;
                    String msg = "The top card is a wildcard of ";
                    switch(ranInt) {
                        case 1:
                            currentSuit = "Hearts";
                            msg += currentSuit + ".";
                            break;
                        case 2:
                            currentSuit = "Diamonds";
                            msg += currentSuit + ".";
                            break;
                        case 3:
                            currentSuit = "Clubs";
                            msg += currentSuit + ".";
                            break;
                        case 4:
                            currentSuit = "Spades";
                            msg += currentSuit + ".";
                            break;
                        default:
                            break;
                    }

                    done = true;
                    eightPlayed = true;
                //Else pull a card from the deck
                } else {
                    Card newCard = deck.deal();
                    if(deck.isEmpty()) {
                        System.out.println("The deck ran out of cards. Tie game!");
                        return;
                    }
                    computerHand.addCard(newCard);
                    System.out.println();
                    System.out.println("The computer has drawn a card. The computer now has " 
                            + computerHand.getLength() + " cards.");
                    System.out.println();
                    done = true;
                }
            }
        }

    }
}
