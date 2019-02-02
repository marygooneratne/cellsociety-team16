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
   }

   public ArrayList<ArrayList<Cell>> getCellList(){
      return this.cellList;
   }

   public void addToCellList(ArrayList<Cell> row){
      this.cellList.add(row);
   }

   public int getRows(){
      return this.rows;
   }

   public int getColumns(){
      return this.columns;
   }

   public void updateCellNextStates() {
      for (ArrayList<Cell> row : this.cellList) {
         for (Cell c : row) {
            c.updateNextState();
         }
      }
   }

   public void initialize() {
   }

   public Cell getCell(int r, int c) {
      return cellList.get(r).get(c);
   }

   public void updateCellNeighbors() {
      for (int r = 0; r < cellList.size(); r++) {
         ArrayList<Cell> row = cellList.get(r);
         for (int c = 0; c < row.size(); c++) {
            Cell cell = cellList.get(r).get(c);
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
}