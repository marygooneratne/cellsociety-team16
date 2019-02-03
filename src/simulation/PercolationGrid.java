package simulation;

import java.util.ArrayList;

public class PercolationGrid extends CellGrid{
   private double probBlocked;

   public PercolationGrid(int initRows, int initColumns) {
      super(initRows, initColumns);
      this.probBlocked = .5;
   }

   public PercolationGrid(int initRows, int initColumns, double initProbBlocked){
      super(initRows, initColumns);
      this.probBlocked = initProbBlocked;
      this.setBlocked();
   }

   public double getProbBlocked(){
      return this.probBlocked;
   }

   public void addCells(ArrayList<Cell> newCells){
      for(Cell c: newCells){
         ((PercolationCell)c).setProbBlocked(this.probBlocked);
         this.setCell(c.getRow(), c.getColumn(), c);
      }
      this.updateCellNeighbors();
   }
   public void addType(CellState state, int num){
      ArrayList<Cell> addCells = new ArrayList<>();
      for(int i = 0; i < num; i++){
         int r = this.getRow();
         int c = this.getCol();
         PercolationCell newC = new PercolationCell(state, r, c);
         addCells.add(newC);
      }
      this.addCells(addCells);
   }

   public void initialize() {
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            PercolationCell newCell = new PercolationCell(CellState.OPEN);
            newCell.setColumn(c);
            newCell.setRow(r);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }

   public void setBlocked() {
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            ((PercolationCell)(c)).setProbBlocked(this.probBlocked);
            if(getIfBlocked()){
               c.setCurrentState(CellState.BLOCKED);
            }
         }
      }
   }

   public int getRow(){
      return (int)(this.getRows() * Math.random());
   }

   public int getCol(){
      return (int)(this.getColumns() * Math.random());
   }

   public boolean getIfBlocked(){
      double prob = Math.random();
      return (prob < this.probBlocked);
   }
}
