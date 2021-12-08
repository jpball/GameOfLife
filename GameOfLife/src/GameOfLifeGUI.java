import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class GameOfLifeGUI extends JFrame{
    // Board Panel Data Members
    private final int BOARD_DIMENSION = 20;
    private JPanel boardPanel;
    private ArrayList<ArrayList<CellButton>> grid;

    // Option Panel Data Members
    private JPanel optionPanel;
    private JPanel stratBoxPanel;
    private JCheckBox conwayCB;
    private JCheckBox customCB;

    //--
    public GameOfLifeGUI(){
        super("Game of Life");
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
         } catch (Exception e) {
                    e.printStackTrace();
         }
        createPixGrid();
        setupGUI();
    }
    /*  
        ===========================
        ===== GUI FUNCTIONS =======
        =========================== 
    */
    // Setup the main GUI
    private void setupGUI(){
        setSize(700,700);
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        setupBoardPanel();
        c.gridx = 0;
        add(boardPanel, c);

        setupOptionPanel();
        c.gridx = 1;
        add(optionPanel, c);

    
        // Frame settings
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /*  
        ===========================
        ===== BOARD PANEL =========
        =========================== 
    */
    // Create the boardPanel (JPanel), customize it, and fill it with buttons
    private void setupBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_DIMENSION, BOARD_DIMENSION));
        boardPanel.setPreferredSize(new Dimension(500, 500));

        // Add the pixel buttons to the grid
        for (ArrayList<CellButton> arrayList : grid) {
            for (CellButton cellButton : arrayList) {
                boardPanel.add(cellButton);
            }
        }
    }
    //--
    // Fill the grid 2D ArrayList with CellButtons based on the BOARD_DIMENSION constant
    private void createPixGrid(){
        grid = new ArrayList<ArrayList<CellButton>>();
        for(int row = 0; row < BOARD_DIMENSION; row++){
            ArrayList<CellButton> rowList = new ArrayList<CellButton>();
            for(int col = 0; col < BOARD_DIMENSION; col++){
                CellButton pb = new CellButton(col, row);
                pb.setOpaque(true);
                pb.setBackground(Color.white);
                rowList.add(pb);
            }
            grid.add(rowList);
        }
    }
    /*  
        ===========================
        ===== OPTION PANEL ========
        =========================== 
    */
    // Setup the Option Panel that includes all interactive options
    // - Conways GoL Checkbox
    // - Custom GoL Checkbox
    // - Run/Stop Button
    // - Step Generation Button
    // - Attach/Detach Button
    // - Explain Button???
    private void setupOptionPanel(){
        optionPanel = new JPanel();

        setupStratBoxPanel(); // Intializes and adds components to JPanel stratBoxPanel
        add(stratBoxPanel);
    }
    //--
    private void setupStratBoxPanel(){
        stratBoxPanel = new JPanel();
        conwayCB = new JCheckBox();
        customCB = new JCheckBox();
        stratBoxPanel.add(conwayCB);
        stratBoxPanel.add(customCB);
    }
}
