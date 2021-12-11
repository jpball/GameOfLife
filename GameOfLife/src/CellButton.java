import javax.swing.JButton;

public class CellButton extends JButton{
    private int XCoord;
    private int YCoord;
    private Cell cell;
    //--
    public CellButton(int x, int y, Cell c){
        cell = c;
        setBackground(cell.getDeadColor()); // Default starting color
        XCoord = x;
        YCoord = y;
    }
    //--
    public void killCell(){
        setBackground(cell.getDeadColor());
        cell.killCell();
    }
    //--
    public void reviveCell(){
        setBackground(cell.getAliveColor());
        cell.reviveCell();
    }
    //--
    public int getXCoord(){
        return XCoord;
    }
    //--
    public int getYCoord(){
        return YCoord;
    }
    //--
    public Cell getCell(){
        return cell;
    }
    //--
    public void resetNeighborCount(){
        cell.setNumNeighbors(0);
    }

}
