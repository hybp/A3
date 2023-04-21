import java.util.ArrayList;
import java.util.Collections;

/**
 * The CardDeck class simulates an actual 52-cards card deck.
 * Each integer from 1 to 52 represent a card corresponding to the card image in the "Images" folder.
 *
 * As a side note, file names of images in the "Images" folder have been changed for better efficiency of program.
 *
 * Integers 1 - 13: Clover
 * Integers 14 - 26: Space
 * Integers 27 - 39: Diamond
 * Integers 40 - 52: Heart
 * In the order of 1 - 10, J, Q K
 *
 * Since we do not need many deck objects at the same time during the game, static is used where appropriate.
 */
public class CardDeck {
    private static ArrayList<Integer> deck;

    /**
     * Constructs an ordered CardDeck from scratch.
     */
    public CardDeck() {
        deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++){
            deck.add(i);
        }
    }

    /**
     * Shuffles the CardDeck.
     */
    public static void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Get the card at the top of the deck and remove from deck.
     * @return card as integer.
     */
    public static int cardOut() {
        return deck.remove(0);
    }
}
