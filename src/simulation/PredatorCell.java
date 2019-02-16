/**
 * Mary Gooneratne
 * Child of cell class
 * Parent of shark and fish cell classes
 * Used as predator cells in WaTorWorld simulation
 */
package simulation;

import java.util.ArrayList;

public class PredatorCell extends Cell{
   private static int INITIAL_BREED_TIME = 10;

   private int untilBreed;
   private int breedTime;
   private ArrayList<Cell> availableEmpty;
   private Cell moveCell;

   public PredatorCell(){
      super();
      this.setBreedTime(INITIAL_BREED_TIME);
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

   /**
    * Gets list of cells neighboring that are empty
    * @return the arraylist of cells
    */
   public ArrayList<Cell> getAvailableEmpty(){
      return this.availableEmpty;
   }

   /**
    * Resets the breed time of the cells
    */
   public void resetUntilBreed() {
      this.untilBreed = breedTime;
   }

   /**
    * Getter method for how long until breeding
    * @return
    */
   public int getUntilBreed(){
      return this.untilBreed;
   }

   /** Checking if cell has a moveCell
    *
    * @return true if it does
    */
   public boolean hasMoveCell(){
      if(this.moveCell == null){
         return false;
      }
      else{
         return true;
      }
   }

   /** Returns move cell
    *
    * @return move cell
    */

   public Cell getMoveCell(){
      return this.moveCell;
   }

   /**
    * Changes moveCell to specified value
    * @param newMoveCell
    */
   public void setMoveCell(Cell newMoveCell){
      this.moveCell = newMoveCell;
   }

   /**
    * Changes until breed parameter
    * @param initUntilBreed
    */
   public void setUntilBreed(int initUntilBreed){
      this.untilBreed = initUntilBreed;
   }

   /** changes breed time parameter
    *
    * @param initBreedTime
    */
   public void setBreedTime(int initBreedTime){
      this.breedTime = initBreedTime;
   }

   /** Getter method for breed time
    *
    * @return
    */
   public int getBreedTime(){
      return this.breedTime;
   }

   /**Decrements breed time by one
    *
    */

   public void decreaseUntilBreed(){
      this.untilBreed--;
   }

   /** Sets next state to current state
    *
    */
   public void updateNextState() {
      this.setNextState(this.getCurrentState());
   }

   /** Checks if enough time has passed until breeding is possible
    *
    * @return
    */

   public boolean canBreed(){
      if(this.untilBreed == 0){
         return true;
      }
      else{
         return false;
      }
   }

   /** Updates the available empty neighbors variable
    *
    */
   public void updateAvailableEmpty(){
      ArrayList<Cell> empty = new ArrayList<Cell>();
      for(Cell c: this.getCurrentNeighbors()){
         if(c.getCurrentState() == CellState.EMPTY && this.getNextState() == null){
            empty.add(c);
         }
      }
      this.availableEmpty = empty;

   }

   /**
    * Checks if the cell has an empty neighbor to move to and moves and returns true or changes nextState to current state
    *
    * @return whether or not current cell moved
    */
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

   /** kills cell by setting next state to empty
    *
    */
   public void die(){
      this.setNextState(CellState.EMPTY);
   }

   /** Implemented in children to move to random cell
    *
    */
   public void moveRandom(){};

}
