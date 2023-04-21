public class CardGame {
    Player player = new Player();
    Dealer dealer = new Dealer();
    GameGUI gui = new GameGUI();

    public static void main(String[] args){
        CardGame game = new CardGame();
        game.go();
    }
    public void go() {
        gui.go(player, dealer);
    }
}
