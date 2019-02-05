package simulation;

public class SegregationTester {
   public static void main(String[] args) {
      SegregationGrid grid = new SegregationGrid(10, 10);


      for (int i = 0; i < 5; i++) {
         printGrid(grid);
         grid.step();
         System.out.println();
      }

   }

   public static void printGrid(SegregationGrid grid) {
      for (int r = 0; r < grid.getCellList().size(); r++) {
         String row = "";
         for (Cell c : grid.getCellList().get(r)) {
            if (c.getCurrentState() == CellState.EMPTY) row += "* ";
            if (c.getCurrentState() == CellState.RED) row += "X ";
            if (c.getCurrentState() == CellState.BLUE) row += "O ";
         }
         System.out.println(row);
      }
   }
}
