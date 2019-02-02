package simulation;


public class GameOfLifeCell extends Cell {

   public GameOfLifeCell() {
      super();
   }

   public GameOfLifeCell(CellState initialState) {
      super();
   }

   public void updateNextState() {
      int populated = 0;
      for (Cell neighbor : this.getCurrentNeighbors()) {
         if (neighbor.getCurrentState() == CellState.POPULATED) {
            populated++;
         }
      }
      if (this.getCurrentState() == CellState.UNPOPULATED) {
         if (populated >= 3) {
            this.setNextState(CellState.POPULATED);
         }
      } else {
         if (populated <= 1) {
            this.setNextState(CellState.UNPOPULATED);
         } else if (populated < 4) {
            this.setNextState(CellState.POPULATED);
         } else {
            this.setNextState(CellState.UNPOPULATED);
         }
      }
   }


}