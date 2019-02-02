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

}
