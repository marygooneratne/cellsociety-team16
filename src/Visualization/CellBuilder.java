package Visualization;
import Visualization.GameOfLife.PopulatedCell;
import Visualization.GameOfLife.UnpopulatedCell;
import Visualization.Percolation.BlockedCell;
import Visualization.Percolation.OpenCell;
import Visualization.Percolation.PercolatedCell;
import Visualization.Segregation.BlueCell;
import Visualization.Segregation.RedCell;
import Visualization.SpreadingFire.BurningCell;
import Visualization.SpreadingFire.EmptyCell;
import Visualization.SpreadingFire.TreeCell;
import Visualization.SugarScape.AgentCell;
import Visualization.WaTorWorld.FishCell;
import Visualization.WaTorWorld.SharkCell;
import Visualization.SugarScape.SugarSquareCell;
import simulation.SugarCell;
import simulation.Cell;
import simulation.CellState;

public class CellBuilder {

   public static SquareCell getImage(Cell cell){
      if(cell.getCurrentState() == CellState.POPULATED){
            return new PopulatedCell();
         }
         else if(cell.getCurrentState() == CellState.UNPOPULATED){
            return new UnpopulatedCell();
         }
         else if(cell.getCurrentState() == CellState.PERCOLATED){
            return new PercolatedCell();
         }
         else if(cell.getCurrentState() == CellState.OPEN){
            return new OpenCell();
         }
         else if(cell.getCurrentState() == CellState.BLOCKED){
            return new BlockedCell();
         }
         else if(cell.getCurrentState() == CellState.BLUE){
            return new BlueCell();
         }
         else if(cell.getCurrentState() == CellState.RED){
            return new RedCell();
         }
         else if(cell.getCurrentState() == CellState.BURNING){
            return new BurningCell();
         }
         else if(cell.getCurrentState() == CellState.TREE){
            return new TreeCell();
         }
         else if(cell.getCurrentState() == CellState.FISH){
            return new FishCell();
         }
         else if(cell.getCurrentState() == CellState.SHARK){
            return new SharkCell();
         }
         else if(cell.getCurrentState() == CellState.SUGAR){
            return new SugarSquareCell(((SugarCell)(cell)).getSugar());
         }
         else if(cell.getCurrentState() == CellState.AGENT){
            return new AgentCell();
      }
         else{
            return new EmptyCell();
         }
   }

}
