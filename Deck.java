/** Deck.java
  *
  * Represents a deck of cards.
  *
  * @author Jonathon Elfar 
  * @author Justin Cellona
  */
import java.util.ArrayList;
public class Deck extends LQueue {
    final private int NUMBER_OF_CARDS = 52;
    private LQueue<Card> cards;

    public Deck() {
        cards = new LQueue<Card>();
        for(int i = 0; i < NUMBER_OF_CARDS; i++) {
            cards.enqueue(new Card(i));
        }
    }

    /**
      * Shuffle by:
      *
      * dequeue all elements into arraylist -> enqueue elements randomly
      * removed from the arraylist
      */
    public void shuffle() {
        ArrayList<Card> tmp = new ArrayList<Card>();

        for(int i = 0; i < NUMBER_OF_CARDS; i++) {
            tmp.add(cards.dequeue());
        }

        for(int i = 0; i < NUMBER_OF_CARDS; i++) {
            cards.enqueue(tmp.remove((int)(Math.random()*tmp.size())));
        }

    }

    public Card deal() {
        return cards.dequeue();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
    * Deals a hand 
    * 
    * @param size       Number of cards to deal
    * @return           The Hand object
    */
    public Hand dealHand(int size) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for(int x = 0; x < size; x++) {
            hand.add(deal());
        }
        return new Hand(hand);
    }

    public String toString() {
        String output = "";
        for(int i = 0; !cards.isEmpty(); i++){
            output += deal() + "\n";
        }
        return output;
    }
}
