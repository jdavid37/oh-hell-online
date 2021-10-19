package oh;

import java.util.ArrayList;
import java.util.Comparator;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;
    private int bid;
    private int wins;

    public Player(String n) {
        name = n;
        wins = 0;
    }
    public void dealHand(ArrayList<Card> h) {
        hand = h;
        hand.sort(new CardComparator());
    }
    static class CardComparator implements Comparator<Card> {

        @Override
        public int compare(Card card1, Card card2) {
            if(card1.suit == card2.suit) {

                if(card1.value < card2.value) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if(card1.suit == Suit.SPADES) return 1;
                if(card1.suit == Suit.HEARTS && card2.suit != Suit.SPADES) return 1;
                if(card1.suit == Suit.DIAMONDS && card2.suit == Suit.CLUBS) return 1;
                return 0;
            }
        }
    }
    public String getHand() {
        StringBuilder s = new StringBuilder();
        for (Card c : hand) {
            s.append(c).append(", ");
        }
        s.append('\n');
        return s.toString();
    }
    public void setBid(int b) {
        bid = b;
    }
    public void score() {
        if(wins == bid) {
            score += wins + 10;
        } else {
            score += wins;
        }
        wins = 0;
    }
    public void increaseWins() {
        wins++;
    }
    public int getScore() {
        return score;
    }
    public void playCard(Card c) {
        hand.remove(c);
    }
    public String toString() {
        return name;
    }
}
