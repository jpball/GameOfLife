import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerBoard extends JFrame{

    private final int BOARD_DIMENSION = 50;
    private Player player;
    private JPanel gridPanel;
    int x = 0;
    int y = 0;
    //--
    public PlayerBoard(Player p){
        setPlayer(p);

        displayBoard();
    }
    //--
    /*
    =================================
    ======= GUI FUNCTIONS ===========
    =================================
    */
    private void displayBoard(){
        gridPanel = setupGridPanel();

        // Configure the JFrame and display it
        setLocation(250, 250);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //--
    private JPanel setupGridPanel(){
        JPanel gPanel = new JPanel();
        gPanel.setLayout(new GridLayout(1,1));

        BufferedImage bimg = new BufferedImage(250,250, BufferedImage.TYPE_INT_RGB);
        bimg.getGraphics().drawRect(100, 100, 50, 50);

        JLabel imgLabel = new JLabel(new ImageIcon(bimg));

        gPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - imgLabel.getX();
                int y = e.getY() - imgLabel.getY();
                bimg.setRGB(x, y, player.getPlayerColor().getRGB());
                imgLabel.repaint();
                gPanel.repaint();
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
        gPanel.add(imgLabel);
        return gPanel;
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
