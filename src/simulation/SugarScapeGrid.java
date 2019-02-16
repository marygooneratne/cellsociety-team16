/**
 * Mary Gooneratne
 * Grid for SugarScape model
 * Child of CellGrid class
 */
package simulation;

import java.util.ArrayList;

public class SugarScapeGrid extends CellGrid {
   private final static int INIT_AGENTS = 50;
   private final static int GROW_BACK_RATE = 2;
   private final static int GROW_BACK_INTERVAL = 2;

   private int growBackRate;
   private int growBackInterval;
   private int agents;
   private int intervalTime = 0;

   public SugarScapeGrid(int initRows, int initCols){
      super(initRows, initCols);
      this.setGrowBackRate(GROW_BACK_RATE);
      this.setGrowBackInterval(GROW_BACK_INTERVAL);
      this.setNumAgents(INIT_AGENTS);
      this.initialize();
      this.updateCellNeighbors();
   }

   public SugarScapeGrid(int initRows, int initCols, int agents){
      super(initRows, initCols);
      this.setGrowBackRate(GROW_BACK_RATE);
      this.setGrowBackInterval(GROW_BACK_INTERVAL);
      this.setNumAgents(agents);
      this.initialize();
      this.updateCellNeighbors();
   }

   private void setNumAgents(int amount){
      this.agents = amount;
   }

   private void setGrowBackRate(int amount){
      this.growBackInterval = amount;
   }

   private void setGrowBackInterval(int amount){
      this.growBackInterval = amount;
   }

   /** Sets initial states of cells and adds all the new cells to the grid
    *
    */
   public void initialize(){
      this.intervalTime = 0;
      for(int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<>();
         for(int c = 0; c < this.getColumns(); c++){
            SugarCell sugarCell = new SugarCell(CellState.SUGAR, r, c);
            row.add(sugarCell);
         }
         this.addRowToCellList(row);
      }
      this.addAgents();
   }

   private void addAgents(){
      for(int i = 0; i < agents; i++){
         int r = this.randRowIndex();
         int c = this.randColIndex();
         AgentCell agent = new AgentCell(CellState.AGENT, r, c);
         this.getCellList().get(r).set(c, agent);
      }
   }

   /**
    * Updates next states of cells based on parameters and neighbors
    */
   public void updateCellNextStates(){
      this.intervalTime++;
      for(ArrayList<Cell> r: this.getCellList()){
         for(Cell c: r){
               if(c.getNextState() == null) {
                  c.updateNextState();
               }
               if(c instanceof AgentCell && ((AgentCell)(c)).hasMoveCell()){
                  int curRow = c.getRow();
                  int curCol = c.getColumn();
                  AgentCell moveCell = ((AgentCell)(c)).getMoveCell();
                  int row = moveCell.getRow();
                  int col = moveCell.getColumn();
                  this.getCellList().get(row).set(col, moveCell);
                  SugarCell newCell = new SugarCell(CellState.SUGAR, curRow, curCol);
                  newCell.noSugar();
                  newCell.setNextState(CellState.SUGAR);
                  this.getCellList().get(curRow).set(curCol, newCell);
               }
            }
         }
      for(ArrayList<Cell> r: this.getCellList()) {
         for (Cell c : r) {
            if(c instanceof SugarCell){
               if(intervalTime % growBackInterval == 0){
                  ((SugarCell)(c)).increaseSugar(this.growBackRate);
               }
            }
         }
      }

   }

   /**
    * Updates cell neighbors for each cell and updates corresponding variable
    *
    */

   public void updateCellNeighbors(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell cell: row){
            if(cell instanceof AgentCell){
               cell.emptyNeighbors();
               int r = cell.getRow();
               int c = cell.getColumn();
               for(int i = 1; i <= ((AgentCell)(cell)).getVision(); i++){
                  if(this.isValidPos(r+i, c) && this.getCellList().get(r+i).get(c).getNextState() == null){
                     cell.addNeighbor(this.getCellList().get(r+i).get(c));
                  }
                  if(this.isValidPos(r-i, c)&& this.getCellList().get(r-i).get(c).getNextState() == null){
                     cell.addNeighbor((this.getCellList().get(r-i).get(c)));
                  }
                  if(this.isValidPos(r, c+i) && this.getCellList().get(r).get(c+i).getNextState() == null){
                     cell.addNeighbor(this.getCellList().get(r).get(c+i));
                  }
//<<<<<<< HEAD
//                  if(this.isValidPos(r, c-i)){
//                     neighbors.add(this.getCellList().get(r).get(c-i));
//=======
                  if(this.isValidPos(r, c-i) && this.getCellList().get(r).get(c-i).getNextState() == null){
                     cell.addNeighbor(this.getCellList().get(r+i).get(c-i));
//>>>>>>> ca01895cd775e28d449a14b9df2f15df8e850ad3
                  }
               }
            }
         }
      }
   }

   private boolean isValidPos(int r, int c){
      return (r > 0 && c > 0 && r < this.getRows() && c < this.getColumns());
   }
}
