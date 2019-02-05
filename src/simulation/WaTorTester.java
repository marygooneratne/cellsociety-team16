package simulation;

public class WaTorTester {

   public static void main(String[] args){
      WaTorGrid grid = new WaTorGrid(5, 5);

      for(int i = 0; i < 10; i++) {
      printGrid(grid);
      grid.step();
      System.out.println();
   }

}

   public static void printGrid(WaTorGrid grid) {
      for (int r = 0; r < grid.getCellList().size(); r++) {
         String row = "";
         for (Cell c : grid.getCellList().get(r)) {
            if (c.getCurrentState() == CellState.EMPTY) row += "* ";
            if (c.getCurrentState() == CellState.FISH) row += "X ";
            if (c.getCurrentState() == CellState.SHARK) row += "O ";
         }
         System.out.println(row);
      }
   }
}
