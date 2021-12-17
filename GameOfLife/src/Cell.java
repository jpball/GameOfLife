import java.awt.Color;
public class Cell {
    private boolean isAlive;
    private int numNeighbors;
    private final Color ALIVE_COLOR = Color.black;
    private final Color DEAD_COLOR = Color.white;
    //--
    public Cell(){
        setIsAlive(false);
        setNumNeighbors(0);
    }
    //--
    public boolean isAlive(){
        return isAlive;
    }
    //--
    public void killCell(){
        isAlive = false;
    }
    //--
    public void reviveCell(){
        isAlive = true;
    }
    //--
    private void setIsAlive(boolean val){
        isAlive = val;
    }
    //--
    public int getNumNeighbors(){
        return numNeighbors;
    }
    //--
    public void incrementNeighbors(){
        numNeighbors++;
    }
    //--
    public void setNumNeighbors(int val){
        numNeighbors = val;
    }
    //--
    public Color getAliveColor(){
        return ALIVE_COLOR;
    }
    //--
    public Color getDeadColor(){
        return DEAD_COLOR;
    }
}
