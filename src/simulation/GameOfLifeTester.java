package simulation;

import java.util.ArrayList;

public class GameOfLifeTester {
   public static void main(String[] args){
      GameOfLifeGrid testGrid = new GameOfLifeGrid(10, 10);
      GameOfLifeCell cell1 = new GameOfLifeCell(CellState.POPULATED, 0, 2);
      GameOfLifeCell cell2 = new GameOfLifeCell(CellState.POPULATED, 0, 3);
      GameOfLifeCell cell3 = new GameOfLifeCell(CellState.POPULATED, 1, 2);
      GameOfLifeCell cell4 = new GameOfLifeCell(CellState.POPULATED, 1, 0);
      GameOfLifeCell cell5 = new GameOfLifeCell(CellState.POPULATED, 3, 4);

      ArrayList<Cell> addCells = new ArrayList<>();
      addCells.add(cell1);
      addCells.add(cell2);
      addCells.add(cell3);
      addCells.add(cell4);
      addCells.add(cell5);
      testGrid.addCells(addCells);

      for(int i = 0; i < 5; i++){
         printGrid(testGrid);
         testGrid.updateCellNextStates();
         testGrid.updateCellStates();
         System.out.println("***************");
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
