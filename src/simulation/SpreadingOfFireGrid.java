/**
 * Mary Gooneratne
 * Child of CellGrid class
 * Responsible for simulation of Spreading of Fire CA
 */
package simulation;

import java.util.ArrayList;

public class SpreadingOfFireGrid extends CellGrid{
   private static double INIT_PROB_CATCH = 0.5;
   private double probCatch;

   public SpreadingOfFireGrid(int initRows, int initColumns, double initProbCatch){
      super(initRows, initColumns);
      this.setProbCatch(initProbCatch);
   }

   public SpreadingOfFireGrid(int initRows, int initColumns, double initProbCatch, int trees, int onFire){
      super(initRows, initColumns);
      this.setProbCatch(initProbCatch);
      this.addTreesRandom(trees);
      this.addOnFireRandom(onFire);
   }


   private void addTreesRandom(int trees){
      for(int i = 0; i < trees; i++){
         int row = this.getRow();
         int col = this.getCol();
         this.getCellList().get(row).get(col).setCurrentState(CellState.TREE);
      }
   }

   private void addOnFireRandom(int onFire){
      for(int i = 0; i < onFire; i++){
         int row = this.getRow();
         int col = this.getCol();
         this.getCellList().get(row).get(col).setCurrentState(CellState.BURNING);
      }
   }


   private void setProbCatch(double initProbCatch){
      this.probCatch = initProbCatch;
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            ((SpreadingOfFireCell)(c)).setProbCatch(this.probCatch);
         }
      }
   }

   /**
    * Adds arraylist of cells based on state and location associated with each cell in parameter list
    * @param newCells
    */
   public void addCells(ArrayList<Cell> newCells){
      for(Cell c: newCells){
         ((SpreadingOfFireCell)c).setProbCatch(this.probCatch);
         this.setCell(c.getRow(), c.getColumn(), c);
      }
      this.updateCellNeighbors();
   }

   /** Adds cells of specified type and number randomly throughout grid
    *
    * @param state
    * @param num
    */
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

   /**
    * Creates all of the new cells for the grid and sets their initial location and state to empty
    */
   public void initialize() {
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            SpreadingOfFireCell newCell = new SpreadingOfFireCell(CellState.EMPTY);
            newCell.setColumn(c);
            newCell.setRow(r);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }


   /** Getter method for random row in grid
    *
    * @return row
    */
   public int getRow(){
      return (int)(this.getRows() * Math.random());
   }

   private int getCol(){
      return (int)(this.getColumns() * Math.random());
   }

}
