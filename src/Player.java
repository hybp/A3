/**
 * Player class for the user. Inherits Participant class.
 */
public class Player extends Participant {
    /**
     * @return current amount of money held by Player.
     */
    public int getMoneyInHand() {
        return moneyInHand;
    }

    private int moneyInHand = 100;
    /**
     * draw1Card from deck to the input card slot.
     */
    public void draw1Card(int slotNo) {
        cards[slotNo] = CardDeck.cardOut();
    }

    /**
     * Take money from Player.
     * @param amount
     */
    public void placeBet(int amount) {
        moneyInHand -= amount;
    }

    /**
     * Add money to the Player.
     * @param amount
     */
    public void addMoney(int amount){
        moneyInHand += 2*amount;
    }

}
