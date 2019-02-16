/**
 * Mary Gooneratne
 * Child of abstract Cell class
 * Cell class used for Percolation model with PercolationGrid
 */
package simulation;

public class PercolationCell extends Cell {
   private double probBlocked;

   public PercolationCell(){
      super();
   }

   public PercolationCell(CellState initialState){
      super(initialState);
   }

   public PercolationCell(CellState initialState, int r, int c){
      super(initialState, r, c);
   }

   /** Sets the probability of a place being blocked
    *
    * @param newProb
    */

   public void setProbBlocked(double newProb){
      this.probBlocked = newProb;
   }

   /** Updates next state of cell based on percolation CellState of neighbors
    *
    */

   public void updateNextState(){
      int percolated = 0;
      for(Cell c: this.getCurrentNeighbors()){
         if(c.getCurrentState() == CellState.PERCOLATED){
            percolated++;
         }
      }
      if(this.getCurrentState() == CellState.BLOCKED){
         this.setNextState(CellState.BLOCKED);
      }
      else if(this.getCurrentState() == CellState.PERCOLATED){
         this.setNextState(CellState.PERCOLATED);
      }
      else{
         if(percolated > 0){
            this.setNextState(CellState.PERCOLATED);
         }
         else{
            this.setNextState(CellState.OPEN);
         }
      }
   }
}
