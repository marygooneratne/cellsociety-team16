/** Mary Gooneratne
 * The main class to run the Game of Life simulation, extends the CellGrid
 */
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

   /** Setter method for the probability that a location is populated
    *
    * @param probPopulated
    */
   public void setProbPopulated(double probPopulated){
      this.probPopulated = probPopulated;
   }

   /** Initializes the grid to have cells that have the state based on probability parameters
    *
    */
   public void initialize() {
      for (int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for (int c = 0; c < this.getColumns(); c++) {
            Cell newCell = new GameOfLifeCell(CellState.UNPOPULATED);
            newCell.setColumn(c);
            newCell.setRow(r);
            row.add(newCell);
         }
         this.addRowToCellList(row);
      }
   }

   /**
    * Adds populated cells in random locations based on probability parameters
    */
   public void addPopulated(){
      double cellsLeft = this.getColumns()*this.getRows();
      int numPop= (int)(cellsLeft*this.probPopulated);
      for(int i = 0; i < numPop; i++){
         this.getCellList().get(this.randRowIndex()).get(this.randColIndex()).setCurrentState(CellState.POPULATED);
      }
   }
}