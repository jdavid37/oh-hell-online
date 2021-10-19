package oh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Player roundWinner;
    public static Card winningCard = null;
    public static Card trumpCard;
    public static int currentPlayerIndex;
    public static Player[] players;
    public static Player currentPlayer;
    public static PrintWriter output;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Oh Hell!");
        System.out.println("How many players?");
        int numPlayers = input.nextInt();

        players = new Player[numPlayers];

        players[0] = new Player("Anthony");
        players[1] = new Player("Bryan");
        players[2] = new Player("John");


        // Pick someone to go first
        currentPlayerIndex = (int) (Math.random() * 3); // num Players
        int firstPlayer = currentPlayerIndex;


        int increment = 2;

        Deck deck;
        ArrayList<Card> playedCards;

        int round = 1;
        boolean run = true;

        while (run) {

            deck = new Deck();
            deck.populateFullDeck();
            deck.shuffle();

            playedCards = new ArrayList<>();

            // Code for each round
            // Setup player hands
            for (Player p : players) {
                p.dealHand(deck.dealHand(round));
            }

            System.out.println("Round: " + round);

            trumpCard = deck.dealCard();
            System.out.println("Trump card is: " + trumpCard);

            currentPlayer = players[firstPlayer];


            // Bidding
            for (int i = 0; i < 3; i++) {

                System.out.println("Hand printed");
                output = new PrintWriter(currentPlayer.toString()+".txt");
                output.println(currentPlayer + ", here is your hand:");
                output.println(currentPlayer.getHand());
                output.flush();
                System.out.println();

                System.out.println(currentPlayer + ", what is your bid?");
                int bid = input.nextInt();
                currentPlayer.setBid(bid);
                nextPlayer();

            }


            // Start the round
            for (int i = round; i > 0; i--) {
                roundWinner = currentPlayer;

                // Card playing
                for (int j = 0; j < 3; j++) {


                    System.out.println("Trump card is: " + trumpCard);
                    System.out.println("Current winning card is: " + winningCard);
                    System.out.println("Played Cards:");
                    System.out.println(playedCards);

                    System.out.println("Hand printed");

                    output = new PrintWriter(currentPlayer.toString()+".txt");
                    output.println(currentPlayer + ", here is your hand:");
                    output.println(currentPlayer.getHand());
                    output.flush();
                    System.out.println();


                    System.out.println(currentPlayer + ", what do you want to play?");


                    Card cardPlayed = new Card(input.next());
                    playedCards.add(cardPlayed);

                    // Get the card from the players hand
                    currentPlayer.playCard(cardPlayed);
                    System.out.println(currentPlayer + " plays " + cardPlayed);

                    updateWinner(currentPlayer, cardPlayed);
                    nextPlayer();
                }
                System.out.println(roundWinner + " wins this round.");
                winningCard = null;
                playedCards.clear();
                roundWinner.increaseWins();
                currentPlayer = roundWinner;
            }
            // Go back down
            if(round == 13) {
                increment = -2;
            }
            round += increment;

            // Tally the scores
            for (Player p : players) {
                p.score();
                System.out.println(p.getScore());
            }

        }
        System.out.println("Final Scores:");
        for (Player p : players) {
            System.out.println(p.getScore());
        }

    }

    public static void nextPlayer() {
        currentPlayerIndex += 1;
        currentPlayerIndex %= 3; // Num players
        currentPlayer = players[currentPlayerIndex];
    }

    public static void updateWinner(Player p, Card c) {
        if (winningCard == null) {
            winningCard = c;
            roundWinner = p;
        } else {
            // If someone plays a higher card
            if (c.getSuit() == winningCard.getSuit()) {
                if (c.getValue() > winningCard.getValue()) {
                    winningCard = c;
                    roundWinner = p;
                }
            }
            // If it's not the same suit, do nothing

            // If someone plays a trump
            if (c.getSuit() == trumpCard.getSuit() && winningCard.getSuit() != trumpCard.getSuit()) {
                winningCard = c;
                roundWinner = p;
            }
        }
    }
}
