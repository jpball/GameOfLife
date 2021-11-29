import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
    private BufferedImage bimg;
    //--
    public BoardPanel(BufferedImage b){
        bimg = b;
    }
    //--
    public void paint(Graphics g) {
        g.drawRect(5, 5, 1, 1);
        super.paint(g);
    }
}
