/** Mary Gooneratne
 * FishCell inherits from Cell and predator cell and is one of the main cells of WaTorWorld model
 */
package simulation;

import java.util.ArrayList;

public class FishCell extends PredatorCell{
   private ArrayList<Cell> availableEmpty;
   private Cell moveCell;

   public FishCell(){
      super();
   }

   public FishCell(CellState initState){
      super(initState);
   }

   public FishCell(CellState initState, int r, int c){
      super(initState, r, c);
   }

   public FishCell(CellState initState, int r, int c, int initUntilBreed){
      super(initState, r, c, initUntilBreed);
   }

   /** Implementation of update next state and updates cell parameters, moves, and breeds if possible
    *
    */

   public void updateNextState(){
      this.decreaseUntilBreed();
      this.updateAvailableEmpty();
      this.move();
      if(this.canBreed()){
         this.breed();
      }
   }

   /**
    * This class finds a cell for the fish to move if possible and updates the moveCell and kills the currentCell
    */

   public void moveRandom() {
      int index = (int) (this.getAvailableEmpty().size() * Math.random());
      int row = this.getAvailableEmpty().get(index).getRow();
      int col = this.getAvailableEmpty().get(index).getColumn();
      FishCell movingFish = new FishCell(CellState.FISH, row, col, this.getBreedTime());
      movingFish.setUntilBreed(this.getUntilBreed());
      movingFish.setNextState(CellState.FISH);
      this.setMoveCell(movingFish);
      this.die();
   }

   /**
    * If this cell has moved, breeds here
    */
   public void breed() {
      if (this.getNextState() == CellState.EMPTY) {
         this.breedCurrentLoc();
      }
   }

   /**
    * This breeds the current cell to create a new fish and resets the breeding time
    */

   public void breedCurrentLoc(){
      this.setNextState(CellState.FISH);
      this.resetUntilBreed();
   }

}
