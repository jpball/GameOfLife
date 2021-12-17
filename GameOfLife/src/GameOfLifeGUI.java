import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameOfLifeGUI extends JFrame implements ActionListener, Observer{
    private EditQueue eque;
    
    // Board Panel Data Members
    private final int BOARD_DIMENSION = 20;
    private JPanel boardPanel;
    private ArrayList<ArrayList<CellButton>> grid;

    // Option Panel Data Members
    private JPanel optionPanel;
    private JCheckBox attachCB;
    private JButton clearButton;
    
    // Strat Box
    private JPanel stratBoxPanel;
    private JRadioButton conwayRB;
    private JRadioButton customRB;


    // Run Panel
    private Strategy gameStrat;

    private int liveCellSurvLB = 2;
    private int deadCellResLB = 3;
    private JLabel cellSurvReqLabel = new JLabel("Live Cell Survival Requirement");
    private JSlider cellSurvSliderLowerBound = new JSlider(JSlider.HORIZONTAL, 1, 8, 2);
    private JLabel ressReqLabel = new JLabel("Dead Cell Ressurection Requirement");
    private JSlider ressReqSliderLowerBound = new JSlider(JSlider.HORIZONTAL, 1, 8, 3);
    private JLabel noteLabel = new JLabel("NOTE: All other live cells will die in the next generation and all other dead cells will remain dead.");

    //--
    public GameOfLifeGUI(EditQueue q){
        super("Game of Life");
        eque = q;
        eque.attach(this); // Attach this to the Observer pattern
        gameStrat = new ConwayStrategy(); // By default, run Conway's rules
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
                CellButton pb = new CellButton(col, row, new Cell());
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
    // - Step Generation Button
    // - Attach/Detach Button
    // - Explain Button
    private void setupOptionPanel(){
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(500, 250));
        optionPanel.setLayout(new GridLayout(3,1));

        setupStratBoxPanel(); // Intializes and adds components to JPanel stratBoxPanel
        optionPanel.add(stratBoxPanel);

        attachCB = new JCheckBox("Attach to other windows?", true);
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

        JButton runButton = new JButton("Run Next Generation");
        runButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(attachCB.isSelected()){
                    eque.run();
                }
                else{
                    runNextGeneration();
                }
            }
        });

        optionPanel.add(runButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ArrayList<CellButton> arrayList : grid) {
                    for (CellButton cellButton : arrayList) {
                        cellButton.killCell();
                    }
                }
            }
        });

        optionPanel.add(clearButton);
        optionPanel.add(createCustomizeButton());
        
        JButton conwayRulesButton = new JButton("What are Conway's Rules?");
        conwayRulesButton.addActionListener(new ActionListener(){
            // https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel title = new JLabel("Rules:");
                JLabel r1 = new JLabel("Any live cell with two or three live neighbours survives.");
                JLabel r2 = new JLabel("Any dead cell with three live neighbours becomes a live cell.");
                JLabel r3 = new JLabel("All other live cells die in the next generation. Similarly, all other dead cells stay dead.");
                Object [] objects = {title, r1, r2, r3};
                JOptionPane.showMessageDialog(null, objects, "What are Conway's Rules?", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        optionPanel.add(conwayRulesButton);
    }
    //--
    private void setupStratBoxPanel(){
        // Setup the checkboxes that will determine how the GOL is played (what rules)
        stratBoxPanel = new JPanel();
        stratBoxPanel.setLayout(new GridLayout(1,2));

        // Initialize both radio buttons
        conwayRB = new JRadioButton("Conway's Rules", true);
        customRB = new JRadioButton("Custom Rules");

        // Add the buttons to the Panel and add an action listener
        stratBoxPanel.add(conwayRB);
        conwayRB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // When selected, mark the radio button and change the strat
                conwayRB.setSelected(true);
                customRB.setSelected(false);
                gameStrat = new ConwayStrategy();
            }
        });
        // Create the CUSTOM BUTTON action listener
        customRB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // When selected, mark the radio button and change the strat
                customRB.setSelected(true);
                conwayRB.setSelected(false);
                gameStrat = new CustomStrategy(liveCellSurvLB, deadCellResLB);
            }
        });
        stratBoxPanel.add(customRB);
    }
    //--
    private JButton createCustomizeButton(){
        // Create the customzizeRules button
        JButton retVal = new JButton("Edit Custom Rules");
        retVal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the customize window (JDialog)
                displayCustomizeMenu();
            }
        });
        return retVal;
    }
    //--
    private void displayCustomizeMenu(){
        JDialog menu = new JDialog();
        menu.setLayout(new GridLayout(5,1));

        // Make the sliders appear properly and make them appealing
        cellSurvSliderLowerBound.setPaintTicks(true);
        cellSurvSliderLowerBound.setMajorTickSpacing(1);
        cellSurvSliderLowerBound.setPaintLabels(true);
        ressReqSliderLowerBound.setPaintTicks(true);
        ressReqSliderLowerBound.setMajorTickSpacing(1);
        ressReqSliderLowerBound.setPaintLabels(true);

        // Add the sliders and labels to the JDialog
        menu.add(cellSurvReqLabel);
        menu.add(cellSurvSliderLowerBound);
        menu.add(ressReqLabel);
        menu.add(ressReqSliderLowerBound);
        menu.add(noteLabel);

        menu.addWindowListener(new WindowListener(){
            @Override
            public void windowClosing(WindowEvent e) {
                // Assign the values when the window closes
                liveCellSurvLB = cellSurvSliderLowerBound.getValue();
                deadCellResLB = ressReqSliderLowerBound.getValue();
                customRB.setSelected(true);
                conwayRB.setSelected(false);
                gameStrat = new CustomStrategy(liveCellSurvLB, deadCellResLB);
            }
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {
                // Assign the values when the window closes
                liveCellSurvLB = cellSurvSliderLowerBound.getValue();
                deadCellResLB = ressReqSliderLowerBound.getValue();
                customRB.setSelected(true);
                conwayRB.setSelected(false);
                gameStrat = new CustomStrategy(liveCellSurvLB, deadCellResLB);

            }
            @Override public void windowDeactivated(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowOpened(WindowEvent e) {}
        });
        // Adjust the size and unhide the JDialog
        menu.pack();
        menu.setVisible(true);
    }
    //--
    /*  
        ===========================
        ===== OTHER FUNCTIONS =====
        =========================== 
    */
    @Override
    public void update(int x, int y, boolean isRevive) {
        // Perform the desired edit from the subject queue
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
        else{ // Cell is not alive
            cb.killCell();
            if(attachCB.isSelected()){ // Check if we need to notify the other windows
                eque.notifyObservers(cb.getXCoord(), cb.getYCoord(), false);
            }
        }
    }
    //--
    @Override
    public void runNextGeneration(){
        updateNeighborCounts();
        for (ArrayList<CellButton> arrayList : grid) {
            for (CellButton cellButton : arrayList) {
                gameStrat.calculateNextGenStatus(cellButton);
            }
        }
    }
    //--
    private void updateNeighborCounts(){
        // Iterate through each button
        for(int row = 0; row < BOARD_DIMENSION; row++){
            for(int col = 0; col < BOARD_DIMENSION; col++){

                CellButton cb = grid.get(row).get(col);
                cb.resetNeighborCount(); // ENSURE THE NUMBER IS BACK TO 0
                CellButton neighbor;

                if(row > 0){ // cb not top row
                    neighbor = grid.get(row-1).get(col);
                    if(neighbor.getCell().isAlive()){
                        cb.getCell().incrementNeighbors();
                    }
                }
                if(row < BOARD_DIMENSION-1){ // cb not bot row
                    neighbor = grid.get(row+1).get(col);
                    if(neighbor.getCell().isAlive()){
                        cb.getCell().incrementNeighbors();
                    }
                }

                if(col > 0){ // cb not far left col
                    neighbor = grid.get(row).get(col-1);
                    if(neighbor.getCell().isAlive()){
                        cb.getCell().incrementNeighbors();
                    }

                    if(row > 0){ // cb not far left col and not top row
                        neighbor = grid.get(row-1).get(col-1);
                        if(neighbor.getCell().isAlive()){
                            cb.getCell().incrementNeighbors();
                        }
                    }
                    if(row < BOARD_DIMENSION-1){
                        neighbor = grid.get(row+1).get(col-1);
                        if(neighbor.getCell().isAlive()){
                            cb.getCell().incrementNeighbors();
                        }
                    }
                }

                if(col < BOARD_DIMENSION-1){ // cb not far right col
                    neighbor = grid.get(row).get(col+1);
                    if(neighbor.getCell().isAlive()){
                        cb.getCell().incrementNeighbors();
                    }

                    if(row > 0){ // cb not top row
                        neighbor = grid.get(row-1).get(col+1);
                        if(neighbor.getCell().isAlive()){
                            cb.getCell().incrementNeighbors();
                        }
                    }
                    if(row < BOARD_DIMENSION-1){ // cb not bot row
                        neighbor = grid.get(row+1).get(col+1);
                        if(neighbor.getCell().isAlive()){
                            cb.getCell().incrementNeighbors();
                        }
                    }
                }
            }
        }
    }
}
