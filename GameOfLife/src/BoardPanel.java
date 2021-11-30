import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel{
    private int image_width;
    private int image_height;

    public BoardPanel(int width, int height) {
        this.image_width = width;
        this.image_height = height;
        setLayout(new GridLayout(10,10));
    
        ArrayList<JButton> grid = new ArrayList<JButton>();
        for(int i = 0; i < 10; i++){
            for(int y = 0; y < 10; y++){
                grid.add(new JButton());
            }
        }
        for (JButton jButton : grid) {
            add(jButton);
        }

    } 

}