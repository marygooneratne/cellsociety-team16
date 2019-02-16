package simulation;

import java.util.ArrayList;

public class WaTorGrid extends CellGrid {
   private static double INIT_PROB_EMPTY = 0.2;
   private static double INIT_PROB_FISH = 0.7;
   private static double INIT_PROB_SHARK = 0.3;

   private static int INIT_FISH_TIME = 5;
   private static int INIT_SHARK_TIME = 5;
   private static int INIT_STARVE_TIME = 3;

   private double probEmpty;
   private double probFish;
   private double probShark;

   private int fishTime;
   private int sharkTime;
   private int starveTime;

   public WaTorGrid(int initRows, int initCols){
      super(initRows, initCols);
      this.setInitProbs(INIT_PROB_EMPTY,INIT_PROB_FISH,INIT_PROB_SHARK);
      this.setInitTimes(INIT_FISH_TIME, INIT_SHARK_TIME, INIT_STARVE_TIME);
      this.addPredators();
   }

   public WaTorGrid(int initRows, int initCols, double probEmpty, double probFish, int fish, int shark, int starve){
      super(initRows, initCols);
      this.setInitProbs(probEmpty, probFish, 1-probFish);
      this.setInitTimes(fish, shark, starve);
      this.addPredators();

   }


   private void addPredators(){
      this.addFish();
      this.addSharks();
      this.updateCellNeighbors();
   }


   private void setInitProbs(double empty, double fish, double shark){
      this.probEmpty = empty;
      this.probFish = fish;
      this.probShark = shark;
   }

   private void setInitTimes(int fish, int shark, int starve){
      this.fishTime = fish;
      this.sharkTime = shark;
      this.starveTime = starve;
   }

   /** Initializes cells in the grid to be appropriate state
    *
    */
   public void initialize(){
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            PredatorCell newCell = new PredatorCell(CellState.EMPTY);
            newCell.setColumn(c);
            newCell.setRow(r);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }

   private void addFish(){
      int cellsLeft = (int)((1-this.probEmpty)*(this.getColumns()*this.getRows()));
      int numFish = (int)(cellsLeft*this.probFish);
      for(int i = 0; i < numFish; i++){
         int row = this.randRowIndex();
         int col = this.randColIndex();
         PredatorCell fishCell = new FishCell(CellState.FISH, row, col, this.fishTime);
         this.getCellList().get(this.randRowIndex()).set(this.randColIndex(), fishCell);
      }
   }

   private void addSharks(){
      int cellsLeft = (int)((1-this.probEmpty)*(this.getColumns()*this.getRows()));
      int numShark = (int)(cellsLeft*this.probShark);
      for(int i = 0; i < numShark; i++){
         int row = this.randRowIndex();
         int col = this.randColIndex();
         PredatorCell sharkCell = new SharkCell(CellState.SHARK, row, col, this.sharkTime, this.starveTime);
         this.getCellList().get(this.randRowIndex()).set(this.randColIndex(), sharkCell);
      }
   }

   /** Updates cell next states based on whether each cell is breeding, killing and moving and changes cellList
    *
    */
   public void updateCellNextStates(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null){
               c.updateNextState();
               if(((PredatorCell)(c)).hasMoveCell()){
                  Cell moveCell =((PredatorCell)(c)).getMoveCell();
                  int r = moveCell.getRow();
                  int col = moveCell.getColumn();
                  if(moveCell instanceof FishCell){
                     FishCell newCell = new FishCell();
                     newCell.setCurrentState(moveCell.getCurrentState());
                     newCell.setNextState(moveCell.getNextState());
                     newCell.setRow(r);
                     newCell.setColumn(col);
                     newCell.setBreedTime(this.fishTime);
                     newCell.setUntilBreed(((FishCell)(moveCell)).getUntilBreed());
                     this.getCellList().get(r).set(col, newCell);
                  }
                  else if(moveCell instanceof SharkCell){
                     SharkCell newCell = new SharkCell();
                     newCell.setCurrentState(moveCell.getCurrentState());
                     newCell.setNextState(moveCell.getNextState());
                     newCell.setRow(r);
                     newCell.setColumn(col);
                     newCell.setBreedTime(this.sharkTime);
                     newCell.setStarveTime(this.starveTime);
                     newCell.setUntilBreed(((SharkCell)(moveCell)).getUntilBreed());
                     newCell.setUntilStarve(((SharkCell)(moveCell)).getUntilStarve());
                     this.getCellList().get(r).set(col, newCell);
                  }
                  this.getCellList().get(r).set(col, moveCell);
                  ((PredatorCell)(c)).setMoveCell(null);
               }

            }
         }
      }
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null && c.getCurrentState() == CellState.EMPTY){
               c.setNextState(CellState.EMPTY);
               }
            }
         }
      }

   /** Updates cell neighbors to new list in toroidal fashion
    *
    */
   public void updateCellNeighbors() {
      for (int r = 0; r < this.getCellList().size(); r++) {
         ArrayList<Cell> row = this.getCellList().get(r);
         for (int c = 0; c < row.size(); c++) {
            Cell cell = this.getCellList().get(r).get(c);
            cell.emptyNeighbors();
            if (c > 0) {
               cell.addNeighbor(row.get(c - 1));
            }
            else{
               cell.addNeighbor(row.get(this.getColumns()-1));
            }
            if (c < row.size() - 1) {
               cell.addNeighbor(row.get(c+1));
            }
            else{
               cell.addNeighbor(row.get(0));
            }
            if (r > 0) {
               cell.addNeighbor(this.getCellList().get(r - 1).get(c));
            }
            else{
               cell.addNeighbor(this.getCellList().get(this.getRows()-1).get(c));
            }
            if (r < this.getCellList().size() - 1) {
               cell.addNeighbor(this.getCellList().get(r + 1).get(c));
            }
            else{
               cell.addNeighbor(this.getCellList().get(0).get(c));
            }
         }
      }
   }

}
