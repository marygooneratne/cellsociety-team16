package simulation;

import java.util.ArrayList;

public class GameOfLifeGrid extends CellGrid {
   private static double INITIAL_POPULATED_PROBABILITY = 0.2;

   double probPopulated;

   public GameOfLifeGrid(int initRows, int initColumns) {
      super(initRows, initColumns);
      this.setProbPopulated(INITIAL_POPULATED_PROBABILITY);
      this.addPopulated();
   }

   public GameOfLifeGrid(int initRows, int initColumns, double probPopulated) {
      super(initRows, initColumns);
      this.setProbPopulated(probPopulated);
      this.addPopulated();
   }

   public void setProbPopulated(double probPopulated){
      this.probPopulated = probPopulated;
   }
   public void initialize() {
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            Cell newCell = new GameOfLifeCell();
            newCell.setCurrentState(CellState.UNPOPULATED);
            newCell.setColumn(c);
            newCell.setRow(r);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }

   public void addPopulated(){
      double cellsLeft = this.getColumns()*this.getRows();
      int numPop= (int)(cellsLeft*this.probPopulated);
      for(int i = 0; i < numPop; i++){
         this.getCellList().get(this.randRowIndex()).get(this.randColIndex()).setCurrentState(CellState.POPULATED);
      }
   }
}