import javax.swing.JButton;

public class PixelButton extends JButton{
    private int XCoord;
    private int YCoord;
    //--
    public PixelButton(int x, int y){
        XCoord = x;
        YCoord = y;
    }
    //--
    public int getXCoord(){
        return XCoord;
    }
    //--
    public int getYCoord(){
        return YCoord;
    }
}
