import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private static ArrayList<Integer> deck;
    public CardDeck() {
        deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++){
            deck.add(i);
        }
    }

    public static void shuffle() {
        Collections.shuffle(deck);
    }
    public static int cardOut() {
        return deck.remove(0);
    }
}
