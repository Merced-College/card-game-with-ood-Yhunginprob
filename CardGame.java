// Prabjot Gakhal
// 6/20/2025
// Card game with OOD


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CardGame {

    private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
    private static ArrayList<Card> playerCards = new ArrayList<Card>();

    public static void main(String[] args) {
        Scanner input = null;

        try {
            input = new Scanner(new File("cards.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        while (input.hasNext()) {
            String[] fields = input.nextLine().split(",");
            Card newCard = new Card(fields[0], fields[1].trim(),
                    Integer.parseInt(fields[2].trim()), fields[3]);
            deckOfCards.add(newCard);
        }

        shuffle();

        // Deal 4 cards to the player
        for (int i = 0; i < 4; i++) {
            playerCards.add(deckOfCards.remove(0));
        }

        System.out.println("Player's cards:");
        for (Card c : playerCards) {
            System.out.println(c);
        }

        System.out.println("Pairs found: " + checkFor2Kind());
        System.out.println("Three of a kind found: " + checkFor3Kind());
        System.out.println("Total hand value: " + getPlayerHandValue());
    }

    // Shuffle the deck using built-in Java shuffle
    public static void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    // Check for 2 of a kind in player's hand
    public static boolean checkFor2Kind() {
        int count = 0;
        for (int i = 0; i < playerCards.size() - 1; i++) {
            Card current = playerCards.get(i);
            for (int j = i + 1; j < playerCards.size(); j++) {
                if (current.equals(playerCards.get(j))) {
                    count++;
                }
            }
        }
        return count >= 1;
    }

    // New Feature: Check for 3 of a kind in player's hand
    public static boolean checkFor3Kind() {
        for (int i = 0; i < playerCards.size(); i++) {
            int count = 1;
            Card current = playerCards.get(i);
            for (int j = 0; j < playerCards.size(); j++) {
                if (i != j && current.getRank().equals(playerCards.get(j).getRank())) {
                    count++;
                }
            }
            if (count == 3) return true;
        }
        return false;
    }

    // New Feature: Get total value of player's hand
    public static int getPlayerHandValue() {
        int total = 0;
        for (Card c : playerCards) {
            total += c.getValue();
        }
        return total;
    }
}
