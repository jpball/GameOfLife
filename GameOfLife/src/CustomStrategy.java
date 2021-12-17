public class CustomStrategy implements Strategy{
    private int liveCellSurvLB = 2;
    private int deadCellResLB = 3;
    //--
    public CustomStrategy(int lcs, int dcr){
        liveCellSurvLB = lcs;
        deadCellResLB = dcr;
    }
    //--
    @Override
    public void calculateNextGenStatus(CellButton cButton) {
        /* RULES:
        Any live cell with liveCellSurvLB or liveCellSurvLB+1 live neighbours survives.
        Any dead cell with deadCellResLB live neighbours becomes a live cell.
        All other live cells die in the next generation. Similarly, all other dead cells stay dead.
        */
        Cell c = cButton.getCell();
        if(c.isAlive()){ 
            if(c.getNumNeighbors() == liveCellSurvLB || c.getNumNeighbors() == liveCellSurvLB+1){
                // Do nothing. Cell remains alive
            }
            else{
                cButton.killCell();
            }
        }
        else{ // Cell is not alive
            if(c.getNumNeighbors() == deadCellResLB){
                cButton.reviveCell();
            }
        }
    }
}
