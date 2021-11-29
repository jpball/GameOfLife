import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;

public class BoardPanel extends JPanel {
    private BufferedImage image;
    private int image_width;
    private int image_height;

    public BoardPanel(int width, int height) {
        this.image_width = width;
        this.image_height = height;
        // this is the internal representation of the image
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < image_width; x++){
            for(int y = 0; y < image_height; y++){
                image.setRGB(x, y, Color.white.getRGB());
            }
        }
    }
    //--
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(getSize().width, getSize().height, Image.SCALE_FAST), 0, 0, this);
    }
    //--
    void setPixel(Point p, Color c) {
        Graphics g = image.getGraphics();
        g.setColor(c);
        Point point = sanitizePoint(p);
        g.fillRect(point.x, point.y, 1, 1);
        repaint();
    }
    //--
    Point sanitizePoint(Point p) {
        float dx = getSize().width / (float) image_width;
        float dy = getSize().height / (float) image_height;
        int xcoord = (int) Math.floor(p.x / dx);
        int ycoord = (int) Math.floor(p.y / dy);
        return new Point(xcoord, ycoord);
    }

}