public class ConwayStrategy implements Strategy{
    @Override
    public void calculateNextGenStatus(CellButton cButton) {
        /* RULES:
            Any live cell with fewer than two live neighbours dies, as if by underpopulation.
            Any live cell with two or three live neighbours lives on to the next generation.
            Any live cell with more than three live neighbours dies, as if by overpopulation.
            Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction. */
        Cell c = cButton.getCell();
        if(c.isAlive()){ 
            if(c.getNumNeighbors() < 2){ // fewer than two live neighbours dies, as if by underpopulation.
                cButton.killCell();
            }
            else if(c.getNumNeighbors() <= 3){ // two or three live neighbours lives on to the next generation.
                // Cell remains alive
            }
            else if(c.getNumNeighbors() > 3){
                cButton.killCell();
            }
        }
        else{
            // 	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            if(c.getNumNeighbors() == 3){
                cButton.reviveCell();
            }
        }
    }
}
