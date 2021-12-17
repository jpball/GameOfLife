public class ConwayStrategy implements Strategy{
    @Override
    public void calculateNextGenStatus(CellButton cButton) {
        /* RULES:
        Any live cell with 2 or 3 live neighbours survives.
        Any dead cell with 3 live neighbours becomes a live cell.
        All other live cells die in the next generation. Similarly, all other dead cells stay dead.
        */
        Cell c = cButton.getCell();
        if(c.isAlive()){ 
            if(c.getNumNeighbors() == 2 || c.getNumNeighbors() == 3){
                // Do nothing. Cell remains alive
            }
            else{
                cButton.killCell();
            }
        }
        else{
            if(c.getNumNeighbors() == 3){
                cButton.reviveCell();
            }
        }
    }
}
