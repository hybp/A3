/**
 * The CardGame program uses java GUI to implement a simple game.
 * with an imaginary dealer.
 *
 * It consists of a player object, dealer object and a GameGUI object all specifically configured.
 *
 * @author Hyeonbeom Park
 * @version 1.0
 * @since 2023-04-18
 */
public class CardGame {
    Player player = new Player();
    Dealer dealer = new Dealer();
    GameGUI gui = new GameGUI();

    /**
     * This is the main method. It will initiate a new game and run GUI.
     * @param args Unused.
     */
    public static void main(String[] args){
        CardGame game = new CardGame();
        game.go();
    }

    /**
     * This method starts the GUI object, where all interactions will take place.
     */
    public void go() {
        gui.go(player, dealer);
    }
}
