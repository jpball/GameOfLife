import java.util.ArrayList;
public class GameOfLife {
    private ArrayList<ArrayList<Cell>> cells;
    private final int BOARD_DIMENSION;

    public GameOfLife(int bd){
        BOARD_DIMENSION = bd;
        cells = new ArrayList<ArrayList<Cell>>();
        createCells();
    }
    //--
    private void createCells(){
        for(int x = 0; x < BOARD_DIMENSION; x++){
            ArrayList<Cell> row = new ArrayList<Cell>();
            for(int y = 0; y < BOARD_DIMENSION; y++){
                row.add(new Cell());
            }
            cells.add(row);
        }
    }
    //--
    public Cell getCell(int x, int y){
        return cells.get(x).get(y);
    }
}
