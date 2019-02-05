package simulation;

import java.util.ArrayList;

public class SegregationGrid extends CellGrid{
   private static double INIT_THRESHOLD = 0.3;
   private static double INIT_PROB_EMPTY = 0.1;
   private static double INIT_PROB_RED = 0.5;

   private double threshold;
   private double probEmpty;
   private double probRed;
   private ArrayList<Cell> emptyCells;

   public SegregationGrid(int initRows, int initCols){
      super(initRows, initCols);
      this.setParams(INIT_THRESHOLD, INIT_PROB_RED, INIT_PROB_EMPTY);
      this.setUp();
   }

   public SegregationGrid(int initRows, int initCols, double initThreshold, double initProbEmpty, double initProbRed){
      super(initRows, initCols);
      this.setParams(initThreshold, initProbRed, initProbEmpty);
      this.setUp();
   }

   public void setParams(double threshold, double probRed, double probEmpty){
      this.threshold = threshold;
      this.probRed = probRed;
      this.probEmpty = probEmpty;
   }



   public void setUp(){
      this.emptyCells = new ArrayList<>();
      this.changeThresholds();
      this.addRed();
      this.addBlue();
      this.findEmptyStates();
      this.updateCellNeighbors();
   }

   public void initialize(){
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            SegregationCell newCell = new SegregationCell(CellState.EMPTY);
            newCell.setColumn(c);
            newCell.setRow(r);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }

   public void addRed(){
      int cellsLeft = (int)((1-probEmpty)*(this.getColumns()*this.getRows()));
      int numRed = (int)(cellsLeft*this.probRed);
      for(int i = 0; i < numRed; i++){
         this.getCellList().get(this.randRowIndex()).get(this.randColIndex()).setCurrentState(CellState.RED);
      }
   }

   public void addBlue(){
      int cellsLeft = (int)((1-probEmpty)*(this.getColumns()*this.getRows()));
      int numBlue = (int)(cellsLeft*(1-this.probRed));
      for(int i = 0; i < numBlue; i++){
         this.getCellList().get(this.randRowIndex()).get(this.randColIndex()).setCurrentState(CellState.BLUE);
      }
   }

   public void step(){
      this.updateCellNextStates();
      this.updateCellStates();
      this.findEmptyStates();
   }

   public void updateCellNextStates(){
      int emptyIndex = 0;
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null & ((SegregationCell)(c)).toMove() && emptyIndex < this.emptyCells.size()){

               this.emptyCells.get(emptyIndex).setNextState(c.getCurrentState());
               c.setNextState(CellState.EMPTY);
               emptyIndex++;
            }
            else{
               if(c.getNextState() == null){
                  c.setNextState(c.getCurrentState());
               }
            }
         }
      }
   }

   public void findEmptyStates(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getCurrentState() == CellState.EMPTY){
               this.emptyCells.add(c);
            }
         }
      }
   }

   public void changeThresholds(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            ((SegregationCell)(c)).setThreshold(this.threshold);
         }
      }
   }

}
