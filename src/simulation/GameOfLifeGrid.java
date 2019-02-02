package simulation;

import java.util.ArrayList;

public class GameOfLifeGrid extends CellGrid {
   public GameOfLifeGrid(int initRows, int initColumns) {
      super(initRows, initColumns);
   }

   public void initialize() {
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            row.add(new GameOfLifeCell(CellState.UNPOPULATED));
         }
         this.addToCellList(row);
      }
   }
}