package simulation;

import java.util.ArrayList;

public class SegregationGrid extends CellGrid{
   private double threshold;
   private double probEmpty;
   private double probRed;
   private ArrayList<Cell> emptyCells;

   SegregationGrid(int initRows, int initCols){
      super(initRows, initCols);
      this.emptyCells = new ArrayList<>();
      this.threshold = .3;
      this.probRed = .5;
      this.probEmpty = .1;
      this.changeThresholds();
      this.addRed();
      this.addBlue();
      this.findEmptyStates();
   }
   SegregationGrid(int initRows, int initCols, double initThreshold, double initProbEmpty, double initProbRed){
      super(initRows, initCols);
      this.threshold = initThreshold;
      this.probEmpty = initProbEmpty;
      this.probRed = initProbRed;
      this.emptyCells = new ArrayList<>();
      this.changeThresholds();
      this.addRed();
      this.addBlue();
      this.findEmptyStates();
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
            if(((SegregationCell)(c)).toMove() && emptyIndex < this.emptyCells.size()){

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

   public int randRowIndex(){
      return (int)(this.getRows()*Math.random());
   }

   public int randColIndex(){
      return (int)(this.getColumns()*Math.random());
   }

   public void changeThresholds(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            ((SegregationCell)(c)).setThreshold(this.threshold);
         }
      }
   }

}
