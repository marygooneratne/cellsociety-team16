/** Mary Gooneratne
 * SharkCell inherits from Cell and predator cell and is one of the main cells of WaTorWorld model
 */
package simulation;

import java.util.ArrayList;

public class SharkCell extends PredatorCell{
   private static int INIT_STARVE_TIME = 5;

   private int untilStarve;
   private int starveTime;
   private ArrayList<Cell> availableFish;
   private Cell deadFish;

   public SharkCell(){
      super();
      this.setStarveTime(INIT_STARVE_TIME);
      this.resetUntilStarve();
   }

   public SharkCell(CellState initState, int r, int c, int initUntilBreed, int initStarveTime){
      super(initState, r, c, initUntilBreed);
      this.setStarveTime(initStarveTime);
      this.resetUntilStarve();
   }

   private int getStarveTime(){
      return this.starveTime;
   }

   /**
    * Setter method for starve time
    * @param initUntilStarve
    */
   public void setUntilStarve(int initUntilStarve){
      this.untilStarve = initUntilStarve;
   }

   /**
    * Updates next state of each cell based on whether it eats, moves, or breeds
    */
   public void updateNextState(){
      boolean isDead = false;
      this.decreaseUntilBreed();
      this.updateAvailableEmpty();
      this.updateAvailableFish();
      if(!this.eatFish()){
         if(this.starve()){
            this.die();
            isDead = true;
         }
         else{
            this.move();
         }
      }
      if(!isDead && this.canBreed()){
         this.breed();
      }
   }

   /**
    * Setter method for stave time
    * @param initStarveTime
    */
   public void setStarveTime(int initStarveTime){
      this.starveTime = initStarveTime;
   }

   private void resetUntilStarve(){
      this.untilStarve = starveTime;
   }

   /**
    * Getter method for get until starve
    * @return
    */
   public int getUntilStarve(){
      return this.untilStarve;
   }

   private void decreaseUntilStarveTime(){
      this.untilStarve--;
   }

   private ArrayList<Cell> getAvailableFish(){
      return this.availableFish;
   }

   private boolean starve(){
      if(this.untilStarve == 0){
         return true;
      }
      else{
         return false;
      }
   }

   private boolean eatFish(){
      if(this.getAvailableFish().size() == 0){
         this.decreaseUntilStarveTime();
         return false;
      }
      else{
         this.setNextState(CellState.SHARK);
         this.eatRandomFish();
         this.resetUntilStarve();
         return true;
      }
   }

   private void updateAvailableFish(){
      ArrayList<Cell> fish = new ArrayList<Cell>();
      for(Cell c: this.getCurrentNeighbors()){
         if(c.getCurrentState() == CellState.FISH && c.getNextState() == null){
            fish.add(c);
         }
      }
      this.availableFish = fish;
   }

   private void eatRandomFish(){
      int index = (int)(this.availableFish.size() * Math.random());
      this.availableFish.get(index).setNextState(CellState.EMPTY);
   }

   /**
    * If cell is going to move randomly, it finds a location to move and creates a new Move cell for that location and kills this current cell
    *
    */
   public void moveRandom() {
      int index = (int) (this.getAvailableEmpty().size() * Math.random());
      int row = this.getAvailableEmpty().get(index).getRow();
      int col = this.getAvailableEmpty().get(index).getColumn();
      SharkCell movingShark = new SharkCell(CellState.SHARK, row, col, this.getBreedTime(), this.getStarveTime());
      movingShark.setUntilStarve(this.getUntilStarve());
      movingShark.setUntilBreed(this.getUntilBreed());
      movingShark.setNextState(CellState.SHARK);
      this.setMoveCell(movingShark);
      this.die();
   }

   private void breed(){
      if(this.getNextState() == CellState.EMPTY){
         this.breedCurrentLoc();
      }
      else if(this.deadFish != null){
         this.breedDeadFish();
      }
   }

   private void breedCurrentLoc(){
      this.setNextState(CellState.SHARK);
      this.resetUntilStarve();
      this.resetUntilBreed();
   }

   private void breedDeadFish(){
      this.setMoveCell(new SharkCell(CellState.SHARK, this.deadFish.getRow(), this.deadFish.getColumn(), this.getBreedTime(), this.getStarveTime()));
   }

}
