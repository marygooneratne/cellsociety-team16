package simulation;

public class AgentCell extends Cell {
   private static int SUGAR_MIN = 5;
   private static int SUGAR_MAX = 25;
   private static int VISION_MIN = 1;
   private static int VISION_MAX = 10;
   private static int METAB_MIN = 1;
   private static int METAB_MAX = 4;

   private int sugar;
   private int sugarMetabolism;
   private int vision;

   private AgentCell moveCell;

   public AgentCell() {
      super();
      this.initializeProps();
   }

   public AgentCell(CellState initialState) {
      super(initialState);
      this.initializeProps();
   }

   public AgentCell(CellState initialState, int r, int c) {
      super(initialState, r, c);
      this.initializeProps();
   }

   private void initializeProps() {
      this.sugar = this.getRandom(SUGAR_MIN, SUGAR_MAX);
      this.vision = this.getRandom(VISION_MIN, VISION_MAX);
      this.sugarMetabolism = this.getRandom(METAB_MIN, METAB_MAX);
   }

   public void updateNextState() {
      this.moveCell = this.findMoveCell();
      if (moveCell != null) {
         this.moveCell.decreaseSugar();
         this.moveCell.die();
         this.setNextState(CellState.EMPTY);
      }
   }

   public boolean hasMoveCell() {
      return this.moveCell != null;
   }

   private void setSugar(int amount) {
      this.sugar = amount;
   }

   private void setSugarMetabolism(int amount) {
      this.sugarMetabolism = amount;
   }

   private void setVision(int amount) {
      this.vision = amount;
   }

   private int getRandom(int min, int max) {
      return (int) ((max - min + 1) * Math.random() + min);
   }

   private void decreaseSugar() {
      this.sugar -= this.sugarMetabolism;
   }

   private void die() {
      if (sugar <= 0) {
         this.setNextState(CellState.EMPTY);
      }
   }

   public int getVision(){
      return this.vision;
   }

   private AgentCell findMoveCell() {
      int max = 0;
      SugarCell eatCell = new SugarCell();
      for (Cell c : this.getCurrentNeighbors()) {
         if (c instanceof SugarCell && ((SugarCell) (c)).getSugar() > max) {
            eatCell = (SugarCell) c;
         }
      }
      int r = eatCell.getRow();
      int c = eatCell.getRow();
      AgentCell moveCell = new AgentCell(this.getCurrentState(), r, c);
      moveCell.setSugar(this.sugar);
      moveCell.setVision(this.vision);
      moveCell.setSugarMetabolism(this.sugarMetabolism);
      return moveCell;
   }

   public AgentCell getMoveCell(){
      return this.moveCell;
   }
}
