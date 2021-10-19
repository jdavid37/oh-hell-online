package oh;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    public ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }
    public void populateFullDeck() {
        cards.clear();
        populateSuit("c");
        populateSuit("d");
        populateSuit("h");
        populateSuit("s");
    }
    private void populateSuit(String suit) {
        for(int i = 2;i<11;i++) {
            cards.add(new Card(i+suit));
        }
        cards.add(new Card("j"+suit));
        cards.add(new Card("q"+suit));
        cards.add(new Card("k"+suit));
        cards.add(new Card("a"+suit));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
    public Card dealCard() {
        return cards.remove(cards.size()-1);
    }
    public ArrayList<Card> dealHand(int n) {
        ArrayList<Card> hand = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            hand.add(dealCard());
        }
        return hand;
    }
}
