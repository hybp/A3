public class Player extends Participants{

    public int getMoneyInHand() {
        return moneyInHand;
    }

    private int moneyInHand = 100;
    public void draw1Card(int cardNumber) {
        cards[cardNumber] = CardDeck.cardOut();
    }
    public void placeBet(int amount) {
        moneyInHand -= amount;
    }

    public void addMoney(int amount){
        moneyInHand += 2*amount;
    }

}
