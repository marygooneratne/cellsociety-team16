package simulation;

import java.util.ArrayList;

public class SugarScapeGrid extends CellGrid {
   private final static int INIT_AGENTS = 50;
   private final static int GROW_BACK_RATE = 4;
   private final static int GROW_BACK_INTERVAL = 1;

   private int growBackRate;
   private int growBackInterval;
   private int agents;
   private int intervalTime = 0;

   public SugarScapeGrid(int initRows, int initCols){
      super(initRows, initCols);
      this.setGrowBackRate(GROW_BACK_RATE);
      this.setGrowBackInterval(GROW_BACK_INTERVAL);
      this.initialize();
   }

   public SugarScapeGrid(int initRows, int initCols, int agents){
      super(initRows, initCols);
      this.setGrowBackRate(GROW_BACK_RATE);
      this.setGrowBackInterval(GROW_BACK_INTERVAL);
      this.setNumAgents(agents);
      this.initialize();
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

   public void initialize(){
      this.intervalTime = 0;
      for(int r = 0; r < this.getRows(); r++){
         ArrayList<Cell> row = new ArrayList<Cell>();
         for(int c = 0; c < this.getColumns(); c++){
            SugarCell sugarCell = new SugarCell(CellState.SUGAR, r, c);
            row.add(sugarCell);
         }
         this.addRowToCellList(row);
      }
      this.addAgents();
   }

   public void addAgents(){
      for(int i = 0; i < agents; i++){
         int r = this.randRowIndex();
         int c = this.randColIndex();
         AgentCell agent = new AgentCell(CellState.AGENT, r, c);
         this.getCellList().get(r).set(c, agent);
      }

   }

   public void updateCellNextStates(){
      this.intervalTime++;
      for(ArrayList<Cell> r: this.getCellList()){
         for(Cell c: r){
            if(c.getNextState() == null){
               if(c instanceof SugarCell){
                  if(this.intervalTime % this.growBackInterval == 0){
                     ((SugarCell)(c)).increaseSugar(this.growBackRate);
                  }
                  c.setNextState(CellState.SUGAR);
               }
            }
            else{
               if(((AgentCell)(c)).hasMoveCell()){
                  int curRow = c.getRow();
                  int curCol = c.getColumn();
                  AgentCell moveCell = ((AgentCell)(c)).getMoveCell();
                  int row = moveCell.getRow();
                  int col = moveCell.getColumn();
                  this.getCellList().get(row).set(col, moveCell);
                  c = new SugarCell(CellState.SUGAR, curRow, curCol);
                  ((SugarCell)(c)).setNextState(CellState.SUGAR);
                  ((SugarCell)(c)).noSugar();
               }
            }
         }
      }
   }
   /*



   public void updateCellNextStates(){
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null){
               c.updateNextState();
               if(((PredatorCell)(c)).hasMoveCell()){
                  Cell moveCell =((PredatorCell)(c)).getMoveCell();
                  int r = moveCell.getRow();
                  int col = moveCell.getColumn();
                  if(moveCell instanceof FishCell){
                     FishCell newCell = new FishCell();
                     newCell.setCurrentState(moveCell.getCurrentState());
                     newCell.setNextState(moveCell.getNextState());
                     newCell.setRow(r);
                     newCell.setColumn(col);
                     newCell.setBreedTime(this.fishTime);
                     newCell.setUntilBreed(((FishCell)(moveCell)).getUntilBreed());
                     this.getCellList().get(r).set(col, newCell);
                  }
                  else if(moveCell instanceof SharkCell){
                     SharkCell newCell = new SharkCell();
                     newCell.setCurrentState(moveCell.getCurrentState());
                     newCell.setNextState(moveCell.getNextState());
                     newCell.setRow(r);
                     newCell.setColumn(col);
                     newCell.setBreedTime(this.sharkTime);
                     newCell.setStarveTime(this.starveTime);
                     newCell.setUntilBreed(((SharkCell)(moveCell)).getUntilBreed());
                     newCell.setUntilStarve(((SharkCell)(moveCell)).getUntilStarve());
                     this.getCellList().get(r).set(col, newCell);
                  }
                  this.getCellList().get(r).set(col, moveCell);
                  ((PredatorCell)(c)).setMoveCell(null);
               }

            }
         }
      }
      for(ArrayList<Cell> row: this.getCellList()){
         for(Cell c: row){
            if(c.getNextState() == null && c.getCurrentState() == CellState.EMPTY){
               c.setNextState(CellState.EMPTY);
               }
            }
         }
      }
   public void updateCellNeighbors() {
      for (int r = 0; r < this.getCellList().size(); r++) {
         ArrayList<Cell> row = this.getCellList().get(r);
         for (int c = 0; c < row.size(); c++) {
            Cell cell = this.getCellList().get(r).get(c);
            cell.emptyNeighbors();
            if (c > 0) {
               cell.addNeighbor(row.get(c - 1));
            }
            else{
               cell.addNeighbor(row.get(this.getColumns()-1));
            }
            if (c < row.size() - 1) {
               cell.addNeighbor(row.get(c+1));
            }
            else{
               cell.addNeighbor(row.get(0));
            }
            if (r > 0) {
               cell.addNeighbor(this.getCellList().get(r - 1).get(c));
            }
            else{
               cell.addNeighbor(this.getCellList().get(this.getRows()-1).get(c));
            }
            if (r < this.getCellList().size() - 1) {
               cell.addNeighbor(this.getCellList().get(r + 1).get(c));
            }
            else{
               cell.addNeighbor(this.getCellList().get(0).get(c));
            }
         }
      }*/
}
