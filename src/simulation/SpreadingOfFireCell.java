/** Mary Gooneratne
 * Child of abstract class Cell
 * Main class used as cell in Spreading of fire model along with SpreadingOFFiregrid
 */
package simulation;

public class SpreadingOfFireCell extends Cell{
   private double probCatch;

   public SpreadingOfFireCell(CellState initialState) {
      super(initialState);
   }

   public SpreadingOfFireCell(CellState initialState, int r, int c){
      super(initialState, r, c);
   }

   /**
    * Setter method for the probability to catch on fire
    * @param newProb
    */
   public void setProbCatch(double newProb){
      this.probCatch = newProb;
   }

   /**
    * Updates next state based on values of neighbors
    *
    */

   public void updateNextState() {
      int onFire = 0;
      for (Cell neighbor : this.getCurrentNeighbors()) {
         if (neighbor.getCurrentState() == CellState.BURNING) {
            onFire++;
         }
      }
      if(this.getCurrentState() == CellState.EMPTY){
         this.setNextState(CellState.EMPTY);
      }
      else if(this.getCurrentState() == CellState.BURNING){
         this.setNextState(CellState.EMPTY);
      }
      else{
         if(onFire > 0 && this.getBurning()){
            this.setNextState(CellState.BURNING);
         }
         else{
            this.setNextState(CellState.TREE);
         }
      }
   }

   private boolean getBurning(){
      double random = Math.random();
      boolean ifBurn = false;
      if(random< probCatch){
         ifBurn = true;
      }

      return ifBurn;
   }


}
