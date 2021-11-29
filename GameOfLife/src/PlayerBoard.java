import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class PlayerBoard extends JFrame{

    private final int BOARD_DIMENSION = 50;
    private Player player;

    private JPanel gridPanel;
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
        setSize(1000,750);
        setLocation(250, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.1;
        c.gridx = 0;
        setupGridPanel();
        add(gridPanel);

        c.weightx = 0.5;
        c.gridx = 1;
        setupOptionPanel();
        add(optionPanel);

        // Configure the JFrame and display it
        setVisible(true);
    }
    //--
    private void setupGridPanel(){
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(BOARD_DIMENSION, BOARD_DIMENSION));
        for(int i = 0; i < BOARD_DIMENSION*BOARD_DIMENSION; i++){
            JButton jb = new JButton();
            jb.setOpaque(true);
            jb.setSize(new Dimension(((this.getWidth()/3)/BOARD_DIMENSION), this.getHeight()/BOARD_DIMENSION));
            gridPanel.add(jb);
        }
        gridPanel.setPreferredSize(new Dimension(500, 500));
    }
    //--
    private void setupOptionPanel(){
        optionPanel = new JPanel();

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
