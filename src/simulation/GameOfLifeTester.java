package simulation;

public class GameOfLifeTester {
   public static void main(String[] args){
      GameOfLifeGrid testGrid = new GameOfLifeGrid(10, 10);
      GameOfLifeCell cell1 = new GameOfLifeCell(CellState.POPULATED);
      GameOfLifeCell cell2 = new GameOfLifeCell(CellState.POPULATED);
      GameOfLifeCell cell3 = new GameOfLifeCell(CellState.POPULATED);
      GameOfLifeCell cell4 = new GameOfLifeCell(CellState.POPULATED);
      GameOfLifeCell cell5 = new GameOfLifeCell(CellState.POPULATED);
      testGrid.setCell(3,2,cell1);
      testGrid.setCell(3,3,cell2);
      testGrid.setCell(1,2,cell3);
      testGrid.setCell(1,1,cell4);
      testGrid.setCell(3,4,cell5);

      for(int i = 0; i < 5; i++){
         testGrid.updateCellNextStates();
         testGrid.updateCellStates();
         printGrid(testGrid);
      }
   }

   public static void printGrid(GameOfLifeGrid grid){
      for(int r = 0; r < grid.getCellList().size(); r++){
         String row = "";
         for(Cell c: grid.getCellList().get(r)){
            if(c.getCurrentState() == CellState.UNPOPULATED) row += "U ";
            if(c.getCurrentState() == CellState.POPULATED) row += "P ";
         }
         System.out.println(row);
      }
   }
}
