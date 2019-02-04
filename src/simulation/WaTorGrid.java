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

   public WaTorGrid(int initRows, int initCols, double probEmpty, double probFish){
      super(initRows, initCols);
      this.setInitProbs(probEmpty, probFish, 1-probFish);
      this.setInitTimes(5, 5, 3);
      this.addPredators();
   }

   public WaTorGrid(int initRows, int initCols, double probEmpty, double probFish, int fish, int shark, int starve){
      super(initRows, initCols);
      this.setInitProbs(probEmpty, probFish, 1-probFish);
      this.setInitTimes(fish, shark, starve);
      this.addPredators();

   }


   public void addPredators(){
      this.addFish();
      this.addSharks();
      this.updateCellNeighbors();
   }

   public void setProbEmpty(double probEmpty){
      this.probEmpty = probEmpty;
   }

   public void setProbFish(double probFish){
      this.probFish = probFish;
      this.probShark = 1-probFish;
   }

   public void setProbShark(double probShark){
      this.probShark = probShark;
      this.probFish = 1-probShark;
   }

   public void setFishTime(int fish){
      this.fishTime = fish;
   }

   public void setSharkTime(int shark){
      this.sharkTime = shark;
   }

   public void setStarveTime(int starve){
      this.starveTime = starve;
   }

   public void setInitProbs(double empty, double fish, double shark){
      this.probEmpty = empty;
      this.probFish = fish;
      this.probShark = shark;
   }

   public void setInitTimes(int fish, int shark, int starve){
      this.fishTime = fish;
      this.sharkTime = shark;
      this.starveTime = starve;
   }

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

   public void addFish(){
      int cellsLeft = (int)((1-this.probEmpty)*(this.getColumns()*this.getRows()));
      int numFish = (int)(cellsLeft*this.probFish);
      for(int i = 0; i < numFish; i++){
         int row = this.randRowIndex();
         int col = this.randColIndex();
         PredatorCell fishCell = new FishCell(CellState.FISH, row, col, this.fishTime);
         this.getCellList().get(this.randRowIndex()).set(this.randColIndex(), fishCell);
      }
   }

   public void addSharks(){
      int cellsLeft = (int)((1-this.probEmpty)*(this.getColumns()*this.getRows()));
      int numShark = (int)(cellsLeft*this.probShark);
      for(int i = 0; i < numShark; i++){
         int row = this.randRowIndex();
         int col = this.randColIndex();
         PredatorCell sharkCell = new SharkCell(CellState.SHARK, row, col, this.sharkTime, this.starveTime);
         this.getCellList().get(this.randRowIndex()).set(this.randColIndex(), sharkCell);
      }
   }

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
   public void updateCellNeighbors() {
      for (int r = 0; r < this.getCellList().size(); r++) {
         ArrayList<Cell> row = this.getCellList().get(r);
         for (int c = 0; c < row.size(); c++) {
            Cell cell = this.getCellList().get(r).get(c);
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
