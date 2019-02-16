/**
 * Mary Gooneratne
 * Grid class for Segregation model
 * Child of CellGrid
 */
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



   private void setParams(double threshold, double probRed, double probEmpty){
      this.threshold = threshold;
      this.probRed = probRed;
      this.probEmpty = probEmpty;
   }



   private void setUp(){
      this.emptyCells = new ArrayList<>();
      this.changeThresholds();
      this.addRed();
      this.addBlue();
      this.updateEmptyStates();
      this.updateCellNeighbors();
   }

   /** Sets up cells in grid to initially be empty
    *
    */
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

   private void addRed(){
      int cellsLeft = (int)((1-probEmpty)*(this.getColumns()*this.getRows()));
      int numRed = (int)(cellsLeft*this.probRed);
      for(int i = 0; i < numRed; i++){
         this.getCellList().get(this.randRowIndex()).get(this.randColIndex()).setCurrentState(CellState.RED);
      }
   }

   private void addBlue(){
      int cellsLeft = (int)((1-probEmpty)*(this.getColumns()*this.getRows()));
      int numBlue = (int)(cellsLeft*(1-this.probRed));
      for(int i = 0; i < numBlue; i++){
         this.getCellList().get(this.randRowIndex()).get(this.randColIndex()).setCurrentState(CellState.BLUE);
      }
   }

   /**
    * Steps all of cells to updae next state and then current state and then checks for empty states
    */
   public void step(){
      this.updateCellNextStates();
      this.updateCellStates();
      this.updateEmptyStates();
   }

   /**
    * Updates cell next states based on thresholds and needing to move
    */
   public void updateCellNextStates(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null & ((SegregationCell)(c)).toMove() && this.emptyCells.size()>0){
               int index = this.getRandEmpty();
               this.emptyCells.get(index).setNextState(c.getCurrentState());
               emptyCells.remove(index);
               c.setNextState(CellState.EMPTY);
            }
            else{
               if(c.getNextState() == null && c.getCurrentState() != CellState.EMPTY){
                  c.setNextState(c.getCurrentState());
               }
            }
         }
         for(Cell c: emptyCells){
               c.setNextState(CellState.EMPTY);
         }
      }
   }

   private void updateEmptyStates(){
      this.emptyCells.removeAll(this.emptyCells);
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null && c.getCurrentState() == CellState.EMPTY){
               this.emptyCells.add(c);
            }
         }
      }
   }

   private void changeThresholds(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            ((SegregationCell)(c)).setThreshold(this.threshold);
         }
      }
   }

   private int getRandEmpty(){
      return (int)(this.emptyCells.size() * Math.random());
   }

}
