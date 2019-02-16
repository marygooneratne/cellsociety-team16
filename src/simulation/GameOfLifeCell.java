/** Mary Gooneratne
 * Child class of Cell and part of the Game of Life model, can be POPULATED or UNPOPULATED
 */
package simulation;


public class GameOfLifeCell extends Cell {
   private static int UNDERPOPULATED = 2;
   private static int OVERPOPULATION = 4;
   private static int REPRODUCTION = 3;

   public GameOfLifeCell() {
      super();
   }

   public GameOfLifeCell(CellState initialState) {
      super(initialState);
   }

   public GameOfLifeCell(CellState initialState, int r, int c){
      super(initialState, r, c);
   }

   /** This method updates the next state of the cell based on the threshold and current values of neighbors
    *
    */

   public void updateNextState() {
      int populated = 0;
      for (Cell neighbor : this.getCurrentNeighbors()) {
         if (neighbor.getCurrentState() == CellState.POPULATED) {
            populated++;
         }
      }
      if (this.getCurrentState() == CellState.UNPOPULATED) {
         if (populated == REPRODUCTION) {
            this.setNextState(CellState.POPULATED);
         }
         else{
            this.setNextState(this.getCurrentState());
         }
      } else {
         if (populated < UNDERPOPULATED) {
            this.setNextState(CellState.UNPOPULATED);
         } else if (populated < OVERPOPULATION) {
            this.setNextState(CellState.POPULATED);
         } else {
            this.setNextState(CellState.UNPOPULATED);
         }
      }
   }


}