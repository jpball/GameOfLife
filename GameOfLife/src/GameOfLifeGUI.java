import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeGUI extends JFrame implements ActionListener, Observer{
    private EditQueue eque;
    
    // Board Panel Data Members
    private final int BOARD_DIMENSION = 20;
    private JPanel boardPanel;
    private ArrayList<ArrayList<CellButton>> grid;

    // Option Panel Data Members
    private JPanel optionPanel;
    private JCheckBox attachCB;
    
    // Strat Box
    private JPanel stratBoxPanel;
    private JRadioButton conwayRB;
    private JRadioButton customRB;

    // Run Panel
    private JPanel runPanel;

    private GameOfLife GOL;
    //--
    public GameOfLifeGUI(EditQueue q){
        super("Game of Life");
        GOL = new GameOfLife(BOARD_DIMENSION);
        eque = q;
        eque.attach(this); // Attach this to the Observer pattern
        AdjustLookAndFeel();
        createPixGrid();
        setupGUI();
    }
    private void AdjustLookAndFeel() {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } 
        catch (Exception e) {
            e.printStackTrace();
         }
    }
    /*  
        ===========================
        ===== GUI FUNCTIONS =======
        =========================== 
    */
    // Setup the main GUI
    private void setupGUI(){
        setSize(600,800);
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        setupBoardPanel();
        c.gridy = 0;
        add(boardPanel, c);

        setupOptionPanel();
        c.gridy = 1;
        add(optionPanel, c);

        pack();
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
                CellButton pb = new CellButton(col, row, GOL.getCell(col, row));
                pb.addActionListener(this);
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
        optionPanel.setPreferredSize(new Dimension(500, 250));

        setupStratBoxPanel(); // Intializes and adds components to JPanel stratBoxPanel
        optionPanel.add(stratBoxPanel);
        
        setupRunPanel();
        optionPanel.add(runPanel);

        attachCB = new JCheckBox("Attach", true);
        attachCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!attachCB.isSelected()){
                    eque.detach(GameOfLifeGUI.this);
                    attachCB.setSelected(false);
                }
                else{
                    eque.attach(GameOfLifeGUI.this);
                    attachCB.setSelected(true);
                }
            }
        });
        optionPanel.add(attachCB);
    }
    //--
    private void setupStratBoxPanel(){
        stratBoxPanel = new JPanel();
        stratBoxPanel.setLayout(new GridLayout(2,2));
        conwayRB = new JRadioButton("Conway's Rules", true);
        customRB = new JRadioButton("Custom Rules");

        stratBoxPanel.add(conwayRB);
        conwayRB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                conwayRB.setSelected(true);
                customRB.setSelected(false);
            }
        });
        // Create the CUSTOM BUTTON action listener
        customRB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                customRB.setSelected(true);
                conwayRB.setSelected(false);
            }
        });
        stratBoxPanel.add(customRB);
    }
    //--
    private void setupRunPanel(){
        runPanel = new JPanel();
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    /*  
        ===========================
        ===== OTHER FUNCTIONS =====
        =========================== 
    */
    @Override
    public void update(int x, int y, boolean isRevive) {
        if(isRevive){
            grid.get(y).get(x).reviveCell();
        }
        else{
            grid.get(y).get(x).killCell();
        }
    }
    //
    @Override
    public void actionPerformed(ActionEvent e) {
        CellButton cb = (CellButton)e.getSource();
        if(!cb.getCell().isAlive()){ // Check if the cell is not alive
            cb.reviveCell();
            if(attachCB.isSelected()){
                eque.notifyObservers(cb.getXCoord(), cb.getYCoord(), true);
            }
        }
        else{
            cb.killCell();
            if(attachCB.isSelected()){
                eque.notifyObservers(cb.getXCoord(), cb.getYCoord(), false);
            }
        }
    }
}
