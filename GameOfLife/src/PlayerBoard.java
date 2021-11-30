import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PlayerBoard extends JFrame{

    private final int BOARD_DIMENSION = 10;
    private Player player;

    private BoardPanel gridPanel;
    private JPanel optionPanel;

    //--
    public PlayerBoard(Player p){
        setPlayer(p);

        setupScreen();
    }
    //--
    /*
    =================================
    ======= GUI FUNCTIONS ===========
    =================================
    */
    private void setupScreen(){
        setLayout(new GridBagLayout());
        setSize(700,700);
        setPreferredSize(new Dimension(700, 700));
        setMaximumSize(new Dimension(700, 700));
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        setupGridPanel();
        c.gridx = 0;
        c.weightx = 0.5;
        add(gridPanel);
        
        setupOptionPanel();
        c.gridx = 1;
        add(optionPanel);

        pack();
        // Configure the JFrame and display it
        setVisible(true);
    }
    //--
    private void setupGridPanel(){
        gridPanel = new BoardPanel(BOARD_DIMENSION, BOARD_DIMENSION);
        gridPanel.setPreferredSize(new Dimension(500, 500));
    }
    //--
    private void setupOptionPanel(){
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(this.getWidth() - gridPanel.getWidth(), this.getHeight()));

        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(2,1));
        JLabel playerNum = new JLabel("Player #" + Integer.toString(player.getPlayerNum()));
        JLabel playerName = new JLabel(player.getPlayerName());
        playerInfoPanel.add(playerNum, JPanel.CENTER_ALIGNMENT);
        playerInfoPanel.add(playerName, JPanel.CENTER_ALIGNMENT);
        optionPanel.add(playerInfoPanel);
    }
    /*
    =================================
    ======= HELPER FUNCTIONS ========
    =================================
    */

    //--
    /*
    =================================
    ======= GETTERS & SETTERS =======
    =================================
    */
    public Player getPlayer(){
        return player;
    }
    //--
    public void setPlayer(Player p){
        player = p;
    }
}
