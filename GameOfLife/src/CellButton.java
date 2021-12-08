import javax.swing.JButton;

import org.w3c.dom.events.MouseEvent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class CellButton extends JButton implements ActionListener{
    private final Color ALIVE_COLOR = Color.cyan;
    private final Color DEAD_COLOR = Color.white;
    private int XCoord;
    private int YCoord;
    private Cell cell;
    //--
    public CellButton(int x, int y){
        cell = new Cell();
        setBackground(DEAD_COLOR);
        XCoord = x;
        YCoord = y;
        addActionListener(this);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!cell.isAlive()){ // Check if the cell is not alive
            cell.reviveCell(); // When clicked, set the cell to be alive
            setBackground(ALIVE_COLOR);
        }
        else{
            cell.killCell();
            setBackground(DEAD_COLOR);
        }
    }
}
