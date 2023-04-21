import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the GUI class.
 **/
public class GameGUI{
    int bettingMoney = 0;
    int rpCount = 0;

    /**
     * This is the go method for GUI. It will take care of interactions with the user, determine the winner,
     * visual design of the game, behavior of game components as game progresses, and ending the game.
     *
     * @param player takes the player object from CardGame class as input.
     * @param dealer takes the dealer object from CardGame class as input.
     */
    public void go(Player player, Dealer dealer) {
        JFrame frame = new JFrame();
        frame.setSize(400, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup panels
        JPanel MainPanel = new JPanel();
        JPanel DealerPanel = new JPanel();
        JPanel PlayerPanel = new JPanel();
        JPanel RpCardBtnPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel InfoPanel = new JPanel();

        // Initialize Image Icons of cards from file, initialize Image labels from ImageIcons
        ImageIcon backImage = new ImageIcon("Images/card_back.gif");

        JLabel label_img1 = new JLabel();
        JLabel label_img2 = new JLabel();
        JLabel label_img3 = new JLabel();
        JLabel label_img4 = new JLabel();
        JLabel label_img5 = new JLabel();
        JLabel label_img6 = new JLabel();

        label_img1.setIcon(backImage);
        label_img2.setIcon(backImage);
        label_img3.setIcon(backImage);
        label_img4.setIcon(backImage);
        label_img5.setIcon(backImage);
        label_img6.setIcon(backImage);

        ArrayList<JLabel> playerImages = new ArrayList<JLabel>(
                Arrays.asList(label_img4,label_img5,label_img6)
        );
        ArrayList<JLabel> dealerImages = new ArrayList<JLabel>(
                Arrays.asList(label_img1,label_img2,label_img3)
        );

        // Add Images to appropriate panels
        DealerPanel.add(dealerImages.get(0));
        DealerPanel.add(dealerImages.get(1));
        DealerPanel.add(dealerImages.get(2));
        PlayerPanel.add(playerImages.get(0));
        PlayerPanel.add(playerImages.get(1));
        PlayerPanel.add(playerImages.get(2));

        // Buttons
        JButton btn_rpcard1 = new JButton("Replace Card 1");
        JButton btn_rpcard2 = new JButton("Replace Card 2");
        JButton btn_rpcard3 = new JButton("Replace Card 3");
        JButton btn_start = new JButton("Start");
        JButton btn_Result = new JButton("Result");

        // Add buttons to appropriate panels
        RpCardBtnPanel.add(btn_rpcard1);
        RpCardBtnPanel.add(btn_rpcard2);
        RpCardBtnPanel.add(btn_rpcard3);

        JTextField txt_inputbet = new JTextField(10);

        JLabel bet$Label = new JLabel("Bet: $");

        ButtonPanel.add(bet$Label);
        ButtonPanel.add(txt_inputbet);
        ButtonPanel.add(btn_start);
        ButtonPanel.add(btn_Result);

        btn_Result.setEnabled(false);
        btn_rpcard1.setEnabled(false);
        btn_rpcard2.setEnabled(false);
        btn_rpcard3.setEnabled(false);

        // Labels

        JLabel label_info = new JLabel("Please place your bet! Amount of money you have: $100");
        InfoPanel.add(label_info);

        // Action Listeners for buttons

        btn_start.addActionListener(new ActionListener() {
            /**
             * Inner class for start button action listener.
             * As a side note for the file names of card image files, they have been optimized.
             * for more efficient program design. Still carries out the required functionality.
             * @param e start button click event.
             */
            // Action listener for money input
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingMoney = Integer.parseInt(txt_inputbet.getText());
                if(bettingMoney <= player.getMoneyInHand()) {
                    rpCount = 0;
                    CardDeck deck = new CardDeck();
                    CardDeck.shuffle();
                    player.drawCards();
                    dealer.drawCards();

                    player.placeBet(bettingMoney);

                    ImageIcon flipC1 = new ImageIcon(String.format("Images/card_" + player.getCard(0) + ".gif"));
                    ImageIcon flipC2 = new ImageIcon(String.format("Images/card_" + player.getCard(1) + ".gif"));
                    ImageIcon flipC3 = new ImageIcon(String.format("Images/card_" + player.getCard(2) + ".gif"));

                    label_info.setText(String.format("Your current bet is $" + bettingMoney +
                            " Amount of money you have: $" + (bettingMoney + player.getMoneyInHand())));
                    playerImages.get(0).setIcon(flipC1);
                    playerImages.get(1).setIcon(flipC2);
                    playerImages.get(2).setIcon(flipC3);

                    ButtonPanel.repaint();
                    PlayerPanel.repaint();

                    btn_Result.setEnabled(true);
                    btn_rpcard1.setEnabled(true);
                    btn_rpcard2.setEnabled(true);
                    btn_rpcard3.setEnabled(true);
                    btn_start.setEnabled(false);
                }
            }
        });
        btn_Result.addActionListener(new ActionListener() {
            /**
             * Inner class for result button action listener.
             * @param e the event to be processed from result button.
             */
            // Action listener for money input
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon flipD1 = new ImageIcon(String.format("Images/card_" + dealer.getCard(0) + ".gif"));
                ImageIcon flipD2 = new ImageIcon(String.format("Images/card_" + dealer.getCard(1) + ".gif"));
                ImageIcon flipD3 = new ImageIcon(String.format("Images/card_" + dealer.getCard(2) + ".gif"));

                dealerImages.get(0).setIcon(flipD1);
                dealerImages.get(1).setIcon(flipD2);
                dealerImages.get(2).setIcon(flipD3);
                DealerPanel.repaint();

                if (playerWon(player,dealer)) {
                    player.addMoney(bettingMoney);
                    JFrame dialogBox = new JFrame();
                    JOptionPane.showMessageDialog(dialogBox, "Congratulations! You win this round!");
                } else {
                    JFrame dialogBox = new JFrame();
                    JOptionPane.showMessageDialog(dialogBox, "Sorry! The Dealer wins this round!");
                    if (player.getMoneyInHand() <= 0) {
                        JFrame endingDialog = new JFrame();
                        JOptionPane.showMessageDialog(endingDialog, "Game over!\nYou have no more money!\n Please start a new game!");
                        System.exit(0);
                    }
                }
                label_info.setText(String.format("Your current bet is $0 Amount of money you have: $"+player.getMoneyInHand()));

                playerImages.get(0).setIcon(backImage);
                playerImages.get(1).setIcon(backImage);
                playerImages.get(2).setIcon(backImage);
                dealerImages.get(0).setIcon(backImage);
                dealerImages.get(1).setIcon(backImage);
                dealerImages.get(2).setIcon(backImage);

                PlayerPanel.repaint();

                btn_rpcard1.setEnabled(false);
                btn_rpcard2.setEnabled(false);
                btn_rpcard3.setEnabled(false);
                btn_start.setEnabled(true);
                btn_Result.setEnabled(false);
            }
        });
        btn_rpcard1.addActionListener(new ActionListener() {
            /**
             * Inner class for the replace card button. Similar for all other replacement buttons.
             * @param e the event to be processed from replace card button.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rpCount < 2) {
                    player.draw1Card(0);
                    rpCount++;
                    ImageIcon rpIcon = new ImageIcon(String.format("Images/card_" + player.getCard(0) + ".gif"));
                    playerImages.get(0).setIcon(rpIcon);
                    PlayerPanel.repaint();
                    btn_rpcard1.setEnabled(false);
                }
            }
        });
        btn_rpcard2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rpCount < 2) {
                    player.draw1Card(1);
                    rpCount++;
                    ImageIcon rpIcon = new ImageIcon(String.format("Images/card_" + player.getCard(1) + ".gif"));
                    playerImages.get(1).setIcon(rpIcon);
                    PlayerPanel.repaint();
                    btn_rpcard2.setEnabled(false);
                }
            }
        });
        btn_rpcard3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rpCount < 2) {
                    player.draw1Card(2);
                    rpCount++;
                    ImageIcon rpIcon = new ImageIcon(String.format("Images/card_" + player.getCard(2) + ".gif"));
                    playerImages.get(2).setIcon(rpIcon);
                    PlayerPanel.repaint();
                    btn_rpcard3.setEnabled(false);
                }
            }
        });

        DealerPanel.setBackground(Color.green);
        PlayerPanel.setBackground(Color.green);
        RpCardBtnPanel.setBackground(Color.green);

        // MainPanel setup
        MainPanel.setLayout(new GridLayout(5, 1));

        MainPanel.add(DealerPanel);
        MainPanel.add(PlayerPanel);
        MainPanel.add(RpCardBtnPanel);
        MainPanel.add(ButtonPanel);
        MainPanel.add(InfoPanel);

        // Setup menu components
        JMenuBar menuBar = new JMenuBar();
        JMenu control = new JMenu("Control");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        control.add(exit);
        menuBar.add(control);

        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(MainPanel);
    }

    /**
     * This method determines the winner of the game with current deck.
     * @param player
     * @param dealer
     * @return true if player won, false if the dealer won.
     */
    private Boolean playerWon(Player player, Dealer dealer) {
        int dealerPt = 0;
        int playerPt = 0;
        // first rule
        for (int i=0; i < 3; i++) {
            if ((dealer.getCard(i)-1) % 13 >= 10) { dealerPt++; }
            if ((player.getCard(i)-1) % 13 >= 10) { playerPt++; }
        }
        if (playerPt > dealerPt) { return true; }
        else if (playerPt < dealerPt) { return false; }
        else {
            dealerPt = 0;
            playerPt = 0;
            for (int i=0; i < 3; i++) {
                if ((dealer.getCard(i)-1)%13 < 10) { dealerPt += (dealer.getCard(i)%13); }
                if ((player.getCard(i)-1)%13 < 10) { playerPt += (player.getCard(i)%13); }
            }
            dealerPt %= 10;
            playerPt %= 10;
            if (playerPt > dealerPt) { return true; }
            else { return false; }
        }
    }
}