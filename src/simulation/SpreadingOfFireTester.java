package simulation;

public class SpreadingOfFireTester {
   public static void main(String[] args){
      SpreadingOfFireGrid grid = new SpreadingOfFireGrid(10, 10, .9);
      grid.addType(CellState.BURNING, 20);
      grid.addType(CellState.TREE, 200);

      for(int i = 0; i < 20; i++){
         printGrid(grid);
         grid.step();
         System.out.println();
      }

   }

   public static void printGrid(SpreadingOfFireGrid grid){
      for(int r = 0; r < grid.getCellList().size(); r++){
         String row = "";
         for(Cell c: grid.getCellList().get(r)){
            if(c.getCurrentState() == CellState.BURNING) row += "B ";
            if(c.getCurrentState() == CellState.TREE) row += "T ";
            if(c.getCurrentState() == CellState.EMPTY) row += "* ";
         }
         System.out.println(row);
      }
   }
}
