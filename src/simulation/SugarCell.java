/** Mary Gooneratne
 * Child of abstract cell class
 * One of two cells used in SugarScape model
 */
package simulation;

public class SugarCell extends Cell {
   private static final int CAPACITY = 4;

   private int sugar;
   private int cap;

   public SugarCell(){
      super();
   }

   public SugarCell(CellState initialState){
      super(initialState);
      this.setCap(CAPACITY);
      this.setSugar();
   }

   public SugarCell(CellState initialState, int r, int c) {
      super(initialState, r, c);
      this.setCap(CAPACITY);
      this.setSugar();
   }

   private void setCap(int cap){
      this.cap = cap;
   }

   private void setSugar(){
      this.sugar = (int)(Math.random()*4);
   }

   /**
    * Increases amount of sugar by specified amount
    * @param amount
    */

   public void increaseSugar(int amount){
      if(this.sugar + amount <= this.cap){
         this.sugar += amount;
      }
      else {
         this.setSugar();
      }
   }

   /** Empties sugar supply
    *
    */
   public void noSugar(){
      this.sugar = 0;
   }

   /** Updates cell state to be sugar at next interval
    *
    */

   public void updateNextState(){
      this.setNextState(CellState.SUGAR);
   }

   /**Getter method for amount of sugar left
    *
    * @return
    */
   public int getSugar(){
      return this.sugar;
   }
}
