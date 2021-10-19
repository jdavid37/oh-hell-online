package oh;

enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}

public class Card {

    public Suit suit;
    public int value;

    public Card(String n) {
        // Gotta remember 10 is 2 characters
        if (n.length() == 3) {
            switch (n.substring(2)) {
                case "c" -> suit = Suit.CLUBS;
                case "d" -> suit = Suit.DIAMONDS;
                case "h" -> suit = Suit.HEARTS;
                case "s" -> suit = Suit.SPADES;
            }
            value = 10;
        } else {
            switch (n.substring(0, 1)) {
                case "j" -> value = 11;
                case "q" -> value = 12;
                case "k" -> value = 13;
                case "a" -> value = 14;
                default -> value = Integer.parseInt(n.substring(0,1));
            }
            switch (n.substring(1)) {
                case "c" -> suit = Suit.CLUBS;
                case "d" -> suit = Suit.DIAMONDS;
                case "h" -> suit = Suit.HEARTS;
                case "s" -> suit = Suit.SPADES;
            }
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public boolean equals(Card other) {
        return suit == other.getSuit() && value == other.getValue();
    }

    public String toString() {
        String v;
        switch (value) {
            case 11 -> v = "J";
            case 12 -> v = "Q";
            case 13 -> v = "K";
            case 14 -> v = "A";
            default -> v = value + "";
        }
        return v + " " + suit;
    }


}
