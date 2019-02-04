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

   public SharkCell(CellState initState){
      super(initState);
      this.setStarveTime(INIT_STARVE_TIME);
      this.resetUntilStarve();
   }

   public SharkCell(CellState initState, int r, int c){
      super(initState, r, c);
      this.setStarveTime(INIT_STARVE_TIME);
      this.resetUntilStarve();
   }

   public SharkCell(CellState initState, int r, int c, int initUntilBreed){
      super(initState, r, c, initUntilBreed);
   }

   public SharkCell(CellState initState, int r, int c, int initUntilBreed, int initStarveTime){
      super(initState, r, c, initUntilBreed);
      this.setStarveTime(initStarveTime);
      this.resetUntilStarve();
   }

   public int getStarveTime(){
      return this.starveTime;
   }

   public void setUntilStarve(int initUntilStarve){
      this.untilStarve = initUntilStarve;
   }

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

   public void setStarveTime(int initStarveTime){
      this.starveTime = initStarveTime;
   }

   public void resetUntilStarve(){
      this.untilStarve = starveTime;
   }

   public int getUntilStarve(){
      return this.untilStarve;
   }

   public void decreaseUntilStarveTime(){
      this.untilStarve--;
   }

   public ArrayList<Cell> getAvailableFish(){
      return this.availableFish;
   }

   public boolean starve(){
      if(this.untilStarve == 0){
         return true;
      }
      else{
         return false;
      }
   }

   public boolean eatFish(){
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

   public void updateAvailableFish(){
      ArrayList<Cell> fish = new ArrayList<Cell>();
      for(Cell c: this.getCurrentNeighbors()){
         if(c.getCurrentState() == CellState.FISH && c.getNextState() == null){
            fish.add(c);
         }
      }
      this.availableFish = fish;
   }

   public void eatRandomFish(){
      int index = (int)(this.availableFish.size() * Math.random());
      this.availableFish.get(index).setNextState(CellState.EMPTY);
   }

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

   public void breed(){
      if(this.getNextState() == CellState.EMPTY){
         this.breedCurrentLoc();
      }
      else if(this.deadFish != null){
         this.breedDeadFish();
      }
   }

   public void breedCurrentLoc(){
      this.setNextState(CellState.SHARK);
      this.resetUntilStarve();
      this.resetUntilBreed();
   }

   public void breedDeadFish(){
      this.setMoveCell(new SharkCell(CellState.SHARK, this.deadFish.getRow(), this.deadFish.getColumn(), this.getBreedTime(), this.getStarveTime()));
   }

}
