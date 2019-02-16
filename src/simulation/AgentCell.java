/** Mary Gooneratne
 * AgentCell of SugarScape
 */

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

   /** Constructors for AgentCell for SugarScape
    *
    */
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

   /** Common to all cell classes, updates next state based on SugarScape rules
    *
    */
   public void updateNextState() {
      this.moveCell = this.findMoveCell();
      if (moveCell != null) {
         this.moveCell.decreaseSugar();
         this.moveCell.die();
         this.moveCell.setNextState(CellState.AGENT);
      }
      else{
         this.setNextState(CellState.AGENT);
      }
   }

   /** Checks whether cell has new MoveCell in next state
    *
    * @return true if yes
    */
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

   /** Getter method for vision
    * 
    * @return
    */
   public int getVision(){
      return this.vision;
   }

   private void die() {
      if (sugar <= 0) {
         this.setNextState(CellState.EMPTY);
      }
   }

   private AgentCell findMoveCell() {
      int max = 0;
      int row = 0;
      int col = 0;
      for (Cell c : this.getCurrentNeighbors()) {
         if (c instanceof SugarCell && ((SugarCell) (c)).getSugar() > max) {
            row = c.getRow();
            col = c.getColumn();
            max = ((SugarCell)(c)).getSugar();
         }
      }
      System.out.println("max" + max);
      AgentCell moveCell = new AgentCell(this.getCurrentState(), row, col);
      moveCell.setSugar(this.sugar);
      moveCell.setVision(this.vision);
      moveCell.setSugarMetabolism(this.sugarMetabolism);
      return moveCell;
   }

   /** Returns a new Cell for where it has to move to
    *
    * @return AgentCell belonging in new location
    */
   public AgentCell getMoveCell(){
      return this.moveCell;
   }
}
