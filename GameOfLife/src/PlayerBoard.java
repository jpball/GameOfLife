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
        setLayout(new GridLayout(1,2));
        setSize(1000,700);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.66;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        setupGridPanel();
        add(gridPanel);

        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.33;
        c.gridx = 1;
        setupOptionPanel();
        add(optionPanel);

        // Configure the JFrame and display it
        setVisible(true);
    }
    //--
    private void setupGridPanel(){
        gridPanel = new BoardPanel(BOARD_DIMENSION, BOARD_DIMENSION);
        gridPanel.setPreferredSize(new Dimension(2*this.getWidth()/3, this.getHeight()));
        gridPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                gridPanel.setPixel(e.getPoint(), player.getPlayerColor());
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
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
