package simulation;

import Configuration.GeneralParse;

import java.util.ArrayList;

public class GameOfLifeTester {
   public static void main(String[] args) {

      GeneralParse myParse = new GeneralParse();
      myParse.startParse("GameOfLife");
      System.out.println(myParse.getGOFPercFireprob());
      GameOfLifeGrid testGrid = new GameOfLifeGrid(myParse.getRows(), myParse.getColumns(), myParse.getGOFPercFireprob());


      for(int i =0; i < 5; i++){
         testGrid.step();
         printGrid(testGrid);
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

   public static String printList(ArrayList<Cell> list){
      String row = "";
         for(Cell c: list){
            if(c.getCurrentState() == CellState.UNPOPULATED) row += "U ";
            if(c.getCurrentState() == CellState.POPULATED) row += "P ";
         }
         return row;
      }
}
