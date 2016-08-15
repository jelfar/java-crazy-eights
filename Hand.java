/** Hand.java
  *
  * Represents a hand in a card game.
  *
  * @author Jonathon Elfar 
  * @author Justin Cellona
  */
import java.util.ArrayList;
public class Hand {
    private ArrayList<Card> hand;

    public Hand(ArrayList<Card> h) {
        hand = h;
    }

    public int getLength() { return hand.size(); }
    
    public boolean isEmpty() { return hand.size() == 0; }

    public Card getCard(int index) { return hand.get(index); }

    public Card removeCard(int index) { return hand.remove(index); }

    public void addCard(Card c) { hand.add(c); }

    /**
      * Returns a card in the hand if it has the specified suit
      * suit and is not equal to 8.
      *
      * @param suit         The suit to search for.
      * @return             The first card to have the suit or face and
      *                     not equal to 8. Returns null if not found.
      */
    public Card tryGetValidCard(String suit, String face, boolean eightPlayed) {
        for(int i = 0; i < getLength(); i++) {
            Card curCard = hand.get(i);
            boolean sameSuit = curCard.getSuit().equals(suit);
            boolean sameFace = curCard.getFaceString().equals(face);
            if((eightPlayed && sameSuit) || (!eightPlayed && (sameSuit || sameFace)) &&
                !curCard.getFaceString().equals(Game.MAGIC_NUMBER_STR)) {
                
                hand.remove(i);
                return curCard;
            }
        }

        return null;
    }

    /**
      * Returns card if has face value of eight.
      */
    public Card tryGetEight() {
        for(int i = 0; i < getLength(); i++) {
            Card curCard = hand.get(i);
            if(curCard.getFaceString().equals(Game.MAGIC_NUMBER_STR)) {
                hand.remove(i);
                return curCard;
            }
        }

        return null;
    }

    public String toString() {
        String str = "";
        for(int x = 1; x <= getLength(); x++) {
            str += x + ". " + hand.get(x-1) + "\n";
        }
        return str;
    }
}
