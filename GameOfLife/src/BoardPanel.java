import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel{
    private int imgWidth;
    private int imgHeight;

    public BoardPanel(int width, int height) {
        imgWidth = width;
        imgHeight = height;
        
        setLayout(new GridLayout(10,10));
        //--
        ArrayList<JButton> grid = new ArrayList<JButton>();
        for(int i = 0; i < 10; i++){
            for(int y = 0; y < 10; y++){
                grid.add(new JButton());
            }
        }
        //--
        for (JButton jButton : grid) {
            add(jButton);
        }

    } 

}