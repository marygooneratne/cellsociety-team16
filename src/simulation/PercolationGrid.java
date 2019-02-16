/**
 * Mary Gooneratne
 * Child of abstract CellGrid class
 * Utilizes Percolation cell to create and step simulation of Percolation
 */
package simulation;

import java.util.ArrayList;

public class PercolationGrid extends CellGrid{
   private static double INITIAL_PROB_BLOCKED = 0.5;
   private double probBlocked;
   private int numPercolated;

   public PercolationGrid(int initRows, int initColumns) {
      super(initRows, initColumns);
      this.setProbBlocked(INITIAL_PROB_BLOCKED);
      this.setBlocked();
   }

   public PercolationGrid(int initRows, int initColumns, double initProbBlocked, int numPercolated){
      super(initRows, initColumns);
      this.numPercolated = numPercolated;
      this.setProbBlocked(INITIAL_PROB_BLOCKED);
      this.setBlocked();
      this.addType(CellState.PERCOLATED, numPercolated);
   }

   /** Sets the probability of a block being blocked
    *
    * @param probBlocked
    */
   public void setProbBlocked(double probBlocked){
      this.probBlocked = probBlocked;
   }

   /** Adds new cells to arraylist based on cell rows, columns, and cell state
    *
    * @param newCells
    */
   public void addCells(ArrayList<Cell> newCells){
      for(Cell c: newCells){
         ((PercolationCell)c).setProbBlocked(this.probBlocked);
         this.setCell(c.getRow(), c.getColumn(), c);
      }
      this.updateCellNeighbors();
   }

   /** Adds certain number of cells to random locations based on number and  state specified
    *
    * @param state
    * @param num
    */
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

   /** Initializes state of current cells at beginning of simulation
    *
    */
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

   /** Adds blocked cells to initial grid
    *
    */
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

   /** Gets random row for indexing
    *
    * @return row
    */
   public int getRow(){
      return (int)(this.getRows() * Math.random());
   }

   /** Gets random column for indexing
    *
    * @return col
    */
   public int getCol(){
      return (int)(this.getColumns() * Math.random());
   }

   /** determine whether block is blocked based on a random probability
    *
    * @return
    */

   public boolean getIfBlocked(){
      double prob = Math.random();
      return (prob < this.probBlocked);
   }
}
