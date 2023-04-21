public class Participants {
    int[] cards = new int[3];
    public void drawCards() {
        for (int i=0; i<3; i++) {
            cards[i] = CardDeck.cardOut();
        }
    }
    public int getCard(int cardNumber) {
        return cards[cardNumber];
    }
}
