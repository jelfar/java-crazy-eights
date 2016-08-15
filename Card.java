/** Card.java
  *
  * Represents a card in a deck of cards.
  *
  * @author Jonathon Elfar 
  * @author Justin Cellona
  */
public class Card {
    private int cardNum;

    private String[] faces = {
        "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
        "Eight", "Nine", "Ten", "Jack", "Queen", "King"
    };
    private String[] suits = {
        "Hearts", "Diamonds", "Spades", "Clubs"
    };

    public Card(int num) {
        cardNum = num;
    }

    public String getFaceString() {
        return faces[cardNum % 13];
    }
        
    public String getSuit() {
        return suits[cardNum % 4];
    }

    public String toString() {
        return getFaceString() + " of " + getSuit();
    }
}
