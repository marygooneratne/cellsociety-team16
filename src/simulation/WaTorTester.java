package simulation;

import Configuration.GeneralParse;

public class WaTorTester {

   public static void main(String[] args){
      GeneralParse myParser = new GeneralParse();
      myParser.startParse("WatorWorld");
      WaTorGrid grid = new WaTorGrid(myParser.getRows(), myParser.getColumns(),myParser.getProbEmpty(),myParser.getProbFish(),myParser.getFishTime(),myParser.getSharkTime(),myParser.getStarveTime());
      System.out.println(myParser.getFishTime());
      System.out.println(myParser.getProbFish());
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
