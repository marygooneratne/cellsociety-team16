package simulation;

import java.util.ArrayList;

public class PredatorCell extends Cell{
   private int untilBreed;
   private int breedTime;
   private ArrayList<Cell> availableEmpty;
   private Cell moveCell;

   public PredatorCell(){
      super();
      this.setBreedTime(10);
      this.resetUntilBreed();
   }

   public PredatorCell(CellState initState){
      super(initState);
      this.moveCell = null;
   }

   public PredatorCell(CellState initState, int r, int c){
      super(initState, r, c);
      this.moveCell = null;
   }

   public PredatorCell(CellState initState, int r, int c, int initUntilBreed){
      super(initState, r, c);
      this.setBreedTime(initUntilBreed);
      this.resetUntilBreed();
      this.moveCell = null;
   }

   public void setAvailableEmpty(ArrayList<Cell> availableEmpty) {
      this.availableEmpty = availableEmpty;
   }

   public ArrayList<Cell> getAvailableEmpty(){
      return this.availableEmpty;
   }

   public void resetUntilBreed() {
      this.untilBreed = breedTime;
   }

   public int getUntilBreed(){
      return this.untilBreed;
   }

   public boolean hasMoveCell(){
      if(this.moveCell == null){
         return false;
      }
      else{
         return true;
      }
   }

   public Cell getMoveCell(){
      return this.moveCell;
   }

   public void setMoveCell(Cell newMoveCell){
      this.moveCell = newMoveCell;
   }

   public void setUntilBreed(int initUntilBreed){
      this.untilBreed = initUntilBreed;
   }

   public void setBreedTime(int initBreedTime){
      this.breedTime = initBreedTime;
   }

   public int getBreedTime(){
      return this.breedTime;
   }

   public void decreaseUntilBreed(){
      this.untilBreed--;
   }

   public void updateNextState() {
      this.setNextState(this.getCurrentState());
   }

   public boolean canBreed(){
      if(this.untilBreed == 0){
         return true;
      }
      else{
         return false;
      }
   }

   public void updateAvailableEmpty(){
      ArrayList<Cell> empty = new ArrayList<Cell>();
      for(Cell c: this.getCurrentNeighbors()){
         if(c.getCurrentState() == CellState.EMPTY && this.getNextState() == null){
            empty.add(c);
         }
      }
      this.availableEmpty = empty;

   }

   public boolean move(){
      if(this.getAvailableEmpty().size() == 0){
         this.setNextState(this.getCurrentState());
         return false;
      }
      else{
         this.moveRandom();
         return true;
      }
   }

   public void die(){
      this.setNextState(CellState.EMPTY);
   }

   public void moveRandom(){};

}
