
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

    private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
    private static ArrayList<Card> playerCards = new ArrayList<Card>();

    public static void main(String[] args) {
        // Name: [Your Name]
        // Date: June 20, 2025
        // Description: This is the main driver class for the card game project using object-oriented design.

        Scanner input = null;
        try {
            input = new Scanner(new File("cards.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        while (input.hasNext()) {
            String[] fields = input.nextLine().split(",");
            Card newCard = new Card(fields[0], fields[1].trim(), Integer.parseInt(fields[2].trim()), fields[3]);
            deckOfCards.add(newCard);
        }

        shuffle();

        // Deal the player 5 cards
        for (int i = 0; i < 4; i++) {
            playerCards.add(deckOfCards.remove(i));
        }

        System.out.println("players cards:");
        for (Card c : playerCards) {
            System.out.println(c);
        }

        // --- Added Feature: Calculate total points of player cards ---
        int totalPoints = 0;
        for (Card c : playerCards) {
            totalPoints += c.getValue();
        }
        System.out.println("Total points in hand: " + totalPoints);
        // --- End of Added Feature ---

        System.out.println("pairs is = " + checkFor2Kind());
    }

    public static void shuffle() {
        for (int i = 0; i < deckOfCards.size(); i++) {
            int index = (int) (Math.random() * deckOfCards.size());
            Card c = deckOfCards.remove(index);
            deckOfCards.add(c);
        }
    }

    public static boolean checkFor2Kind() {
        int count = 0;
        for (int i = 0; i < playerCards.size() - 1; i++) {
            Card current = playerCards.get(i);
            Card next = playerCards.get(i + 1);
            for (int j = i + 1; j < playerCards.size(); j++) {
                next = playerCards.get(j);
                if (current.equals(next)) {
                    count++;
                }
            }
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}
