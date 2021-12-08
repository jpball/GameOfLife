public class Cell {
    private boolean isAlive;
    private int numNeighbors;
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
}
