import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class GameOfLifeGUI extends JFrame{
    private final int BOARD_DIMENSION = 20;
    private JPanel boardPanel;

    private ArrayList<ArrayList<PixelButton>> grid;
    //--
    public GameOfLifeGUI(){
        super("Game of Life");
        createPixGrid();
        setupGUI();
    }
    //--
    private void setupGUI(){
        setSize(700,700);
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        setupBoardPanel();
        add(boardPanel, c);

        // Frame settings
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //--
    private void setupBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_DIMENSION, BOARD_DIMENSION));
        boardPanel.setMinimumSize(new Dimension(500, 500));
        boardPanel.setMaximumSize(new Dimension(500, 500));

        for (ArrayList<PixelButton> arrayList : grid) {
            for (PixelButton pixelButton : arrayList) {
                boardPanel.add(pixelButton);
            }
        }
    
    }
    //--
    private void createPixGrid(){
        grid = new ArrayList<ArrayList<PixelButton>>();
        for(int row = 0; row < BOARD_DIMENSION; row++){
            ArrayList<PixelButton> rowList = new ArrayList<PixelButton>();
            for(int col = 0; col < BOARD_DIMENSION; col++){
                PixelButton pb = new PixelButton(col, row);
                pb.setOpaque(true);
                rowList.add(pb);
            }
            grid.add(rowList);
        }
    }
    //--
}
