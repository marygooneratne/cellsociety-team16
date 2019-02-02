package simulation;

import java.util.ArrayList;

public class SpreadingOfFireGrid extends CellGrid{
   private double probCatch;

   public SpreadingOfFireGrid(int initRows, int initColumns) {
      super(initRows, initColumns);
      this.probCatch = .5;
   }

   public SpreadingOfFireGrid(int initRows, int initColumns, double initProbCatch){
      super(initRows, initColumns);
      this.probCatch = initProbCatch;
   }

   public void addCells(ArrayList<Cell> newCells){
      for(Cell c: newCells){
         ((SpreadingOfFireCell)c).setProbCatch(this.probCatch);
         this.setCell(c.getRow(), c.getColumn(), c);
      }
      this.updateCellNeighbors();
   }
   public void addType(CellState state, int num){
      ArrayList<Cell> addCells = new ArrayList<>();
      for(int i = 0; i < num; i++){
         int r = this.getRow();
         int c = this.getCol();
         SpreadingOfFireCell newC = new SpreadingOfFireCell(state, r, c);
         addCells.add(newC);
      }
      this.addCells(addCells);
   }

   public void initialize() {
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            SpreadingOfFireCell newCell = new SpreadingOfFireCell(CellState.EMPTY);
            newCell.setColumn(c);
            newCell.setRow(r);
            newCell.setProbCatch(this.probCatch);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }

   public int getRow(){
      return (int)(this.getRows() * Math.random());
   }

   public int getCol(){
      return (int)(this.getColumns() * Math.random());
   }

}
