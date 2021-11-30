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
        setSize(800,600);
        setPreferredSize(new Dimension(800, 700));
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        setupGridPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        add(gridPanel, c);
        
        setupOptionPanel();
        c.gridx = 1;
        c.weightx = 0.5;
        add(optionPanel, c);

        pack();
        // Configure the JFrame and display it
        setVisible(true);
    }
    //--
    private void setupGridPanel(){
        gridPanel = new BoardPanel(BOARD_DIMENSION, BOARD_DIMENSION);
        gridPanel.setSize(new Dimension(250, 250));
    }
    //--
    private void setupOptionPanel(){
        optionPanel = new JPanel();
        optionPanel.add(new JLabel("Hello world!"));
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
