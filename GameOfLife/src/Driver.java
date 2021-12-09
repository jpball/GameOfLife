public class Driver {
    public static void main(String[] args) throws Exception {
        EditQueue q = new EditQueue();
        for(int i = 0; i < 2; i++){
            GameOfLifeGUI gui = new GameOfLifeGUI(q);
        }
    }
}
