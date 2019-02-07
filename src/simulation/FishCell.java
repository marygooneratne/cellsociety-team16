package simulation;

import java.util.ArrayList;

public class FishCell extends PredatorCell{
   private ArrayList<Cell> availableEmpty;
   private Cell moveCell;

   public FishCell(){
      super();
   }

   public void updateNextState(){
      this.decreaseUntilBreed();
      this.updateAvailableEmpty();
      this.move();
      if(this.canBreed()){
         this.breed();
      }
   }

   public void moveRandom() {
      int index = (int) (this.getAvailableEmpty().size() * Math.random());
      int row = this.getAvailableEmpty().get(index).getRow();
      int col = this.getAvailableEmpty().get(index).getColumn();
      Cell movingFish = new FishCell().setBreedTime(this.getBreedTime()).setCurrentState(CellState.FISH).setRow(row).setColumn(col);
      this.setMoveCell(movingFish);
      this.die();
   }

   public void breed() {
      if (this.getNextState() == CellState.EMPTY) {
         this.breedCurrentLoc();
      }
   }

   public void breedCurrentLoc(){
      this.setNextState(CellState.FISH);
      this.resetUntilBreed();
   }

}
