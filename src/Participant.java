/**
 * Class for Participants (Player and Dealer)
 * This class have some common attributes for both participants.
 *
 * The deck of a participant is protected from outer classes, but subclasses can use it.
 */
public class Participant {
    protected int[] cards = new int[3];

    /**
     * Draws 3 cards from card deck to the participant's deck.
     */
    public void drawCards() {
        for (int i=0; i<3; i++) {
            cards[i] = CardDeck.cardOut();
        }
    }

    /**
     * Gets one card information from the participant's deck.
     * @param slotNo : which one of the three cards.
     * @return card as integer.
     */
    public int getCard(int slotNo) {
        return cards[slotNo];
    }
}
