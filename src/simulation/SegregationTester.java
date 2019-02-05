package simulation;

import Configuration.GeneralParse;

public class SegregationTester {
   public static void main(String[] args) {
      GeneralParse myParser = new GeneralParse();
      myParser.startParse("Segregation");
      System.out.println(myParser.getThresh());
      System.out.println(myParser.getProbRed());
      System.out.println(myParser.getProbEmptSeg());
      SegregationGrid grid = new SegregationGrid(myParser.getRows(), myParser.getColumns(), myParser.getThresh(),myParser.getProbEmptSeg(),myParser.getProbRed());


      for (int i = 0; i < 100; i++) {
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
