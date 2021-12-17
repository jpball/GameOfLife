import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Driver {
    public static void main(String[] args) throws Exception {
        EditQueue q = new EditQueue();
        
        int numInstances = 1;
        
        // Display the input dialog to gather WIDTH, HEIGHT, and NUMBER OF INSTANCES
        String numGUIStr = new String("Please enter the number of windows you would like:");
        JTextField numGUITF = new JTextField();

        Object[] items = new Object[] {numGUIStr, numGUITF};

        JOptionPane.showMessageDialog(null, items, "Game Of Life", JOptionPane.QUESTION_MESSAGE);

        try{
            numInstances = Integer.parseInt(numGUITF.getText());
        }
        catch(Exception exc){
            System.err.println("ERROR:" + exc.getStackTrace());
        }

        for(int i = 0; i < numInstances; i++){
            GameOfLifeGUI gui = new GameOfLifeGUI(q);
        }
    }
}
