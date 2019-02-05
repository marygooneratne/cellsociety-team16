package simulation;

public class PercolationTester {
   public static void main(String[] args){
      PercolationGrid grid = new PercolationGrid(10, 10, .7, 2);
      grid.addType(CellState.PERCOLATED, 5);

      for(int i = 0; i < 20; i++){
         printGrid(grid);
         grid.step();
         System.out.println();
      }

   }

   public static void printGrid(PercolationGrid grid){
      for(int r = 0; r < grid.getCellList().size(); r++){
         String row = "";
         for(Cell c: grid.getCellList().get(r)){
            if(c.getCurrentState() == CellState.BLOCKED) row += "* ";
            if(c.getCurrentState() == CellState.OPEN) row += "_ ";
            if(c.getCurrentState() == CellState.PERCOLATED) row += "X ";
         }
         System.out.println(row);
      }
   }
}
