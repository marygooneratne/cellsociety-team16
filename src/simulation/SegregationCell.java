/**
 * Mary Gooneratne
 * Child of cell class
 * Main cell agent in the Segregation model and SegregatioGrid
 */
package simulation;

public class SegregationCell extends Cell{
   private double threshold;

   public SegregationCell(){
      super();
   }

   public SegregationCell(CellState initialState){
      super(initialState);
   }

   public SegregationCell(CellState initialState, int r, int c){
      super(initialState, r, c);
   }

   /** Setter method for neighbor similarity threshold
    *
    * @param newThreshold
    */
   public void setThreshold(double newThreshold){
      this.threshold = newThreshold;
   }

   /**
    * Checks if cell wants to move based on threshold
    * @return true if cell wants to move
    */
   public boolean toMove(){
      if(this.getCurrentState() == CellState.EMPTY){
         return false;
      }
      else {
         double total = 0;
         double same = 0;
         for (Cell c : this.getCurrentNeighbors()) {
            if(c.getCurrentState() != CellState.EMPTY){
               total++;
            }
            if (c.getCurrentState() == this.getCurrentState()) {
               same++;
            }
         }
         if (total != 0 && (same / total) < threshold) {
            return true;
         } else {
            return false;
         }
      }

   }
}
