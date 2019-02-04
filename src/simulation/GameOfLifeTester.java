package simulation;

public class GameOfLifeTester {
   public static void main(String[] args){
      GameOfLifeGrid testGrid = new GameOfLifeGrid(10, 10, .4);

      for(int i = 0; i < 5; i++){
         printGrid(testGrid);
         testGrid.step();
         System.out.println();
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
