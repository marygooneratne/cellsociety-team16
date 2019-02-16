/**
 * Mary Gooneratne
 * CellGrid class acts as a a parent class for all models that have their own CellGrid child
 */
package simulation;

import java.util.ArrayList;

public abstract class CellGrid {
   private ArrayList<ArrayList<Cell>> cellList;
   private int rows;
   private int columns;

   public CellGrid(int initRows, int initCols) {
      this.cellList = new ArrayList<>() ;
      this.rows = initRows;
      this.columns = initCols;
      this.initialize();
      this.updateCellNeighbors();
   }

   /** Set rows of grid
    *
    * @param r
    */
   public void setRows(int r){
      this.rows = r;
   }

   /**
    * Set columns of grid
    * @param c
    */
   public void setColumns(int c){
      this.columns = c;
   }

   /**
    * Set arraylist of cells of grid
    * @param list
    */

   public void setCellList(ArrayList<ArrayList<Cell>> list){
      this.cellList = list;
   }

   /** Step of CellGrid that updates neighbors, next states, and then current states
    *
    */
   public void step(){
      this.updateCellNeighbors();
      this.updateCellNextStates();
      this.updateCellStates();
   }

   /**
    * Getter method to get current list of cells
    * @return cellList
    */
   public ArrayList<ArrayList<Cell>> getCellList(){
      return this.cellList;
   }

   /**
    * Used in initialization adds arraylist row of cells to the grid
    * @param row
    */

   public void addRowToCellList(ArrayList<Cell> row){
      this.cellList.add(row);
   }

   /** Changes one specific cell of cellList
    *
    * @param row
    * @param col
    * @param cell
    */
   public void setCell(int row, int col, Cell cell){
      this.cellList.get(row).set(col, cell);
      this.updateCellNeighbors();
   }

   /** Adds an arraylist of new cells based on row and col property of each cell
    *
    * @param newCells
    */
   public void addCells(ArrayList<Cell> newCells){
      for(Cell c: newCells){
         this.setCell(c.getRow(), c.getColumn(), c);
      }
      this.updateCellNeighbors();
   }

   /**
    * Getter method of num rows
    * @return rows
    */
   public int getRows(){
      return this.rows;
   }

   /**
    * Getter method of number of columns
    * @return cols
    */

   public int getColumns(){
      return this.columns;
   }

   /**
    * Iterates through cell list and updates all next states
    */
   public void updateCellNextStates() {
      for (ArrayList<Cell> row : this.cellList) {
         for (Cell c : row) {
            c.updateNextState();
         }
      }
   }

   /** Iterates through cellList and updatesState of each cell
    *
    */
   public void updateCellStates() {
      for(ArrayList<Cell> row : this.cellList) {
         for (Cell c : row) {
            c.updateState();
         }
      }
   }

   /** Sets initial states of cells in grid, implemented in children
    *
    */

   public void initialize() {
   }

   /** Gets cell at a specific location on grid
    *
    * @param r
    * @param c
    * @return
    */
   public Cell getCell(int r, int c) {
      return cellList.get(r).get(c);
   }

   /** Iterates through list of cells and updates each cells neighbor list as it changes with each step
    *
    */
   public void updateCellNeighbors() {
      for (int r = 0; r < cellList.size(); r++) {
         ArrayList<Cell> row = cellList.get(r);
         for (int c = 0; c < row.size(); c++) {
            Cell cell = cellList.get(r).get(c);
            cell.emptyNeighbors();
            if (c > 0) {
               cell.addNeighbor(row.get(c - 1));
            }
            if (c < row.size() - 1) {
               cell.addNeighbor(row.get(c+1));
            }
            if (r > 0) {
               cell.addNeighbor(cellList.get(r - 1).get(c));
            }
            if (r < cellList.size() - 1) {
               cell.addNeighbor(cellList.get(r + 1).get(c));
            }
            if (r > 0 && c > 0) {
               cell.addNeighbor(cellList.get(r - 1).get(c - 1));
            }
            if (r > 0 && c < row.size() - 1) {
               cell.addNeighbor(cellList.get(r - 1).get(c + 1));
            }
            if (r < row.size() - 1 && c > 0) {
               cell.addNeighbor(cellList.get(r + 1).get(c - 1));
            }
            if (r < row.size() - 1 && c < row.size() - 1) {
               cell.addNeighbor(cellList.get(r + 1).get(c + 1));
            }
         }
      }
   }

   /**
    * Get a random index of a row
    * @return row
    */
   public int randRowIndex(){
      return (int)(this.getRows()*Math.random());
   }

   /** Get a random index for a column
    *
    * @return col
    */

   public int randColIndex(){
      return (int)(this.getColumns()*Math.random());
   }

}