package simulation;

public class SugarCell extends Cell {
   private static final int CAPACITY = 4;
   private static final int GROW_RATE = 4;

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

   public SugarCell(CellState initialState, int r, int c){
      super(initialState, r, c);
      this.setCap(CAPACITY);
      this.setSugar();

   }

   private void setCap(int cap){
      this.cap = cap;
   }

   private void setSugar(){
      this.sugar = this.cap;
   }

   public void noSugar(){
      this.sugar = 0;
   }

   public void decreaseSugar(int amount){
      if(sugar >= amount) {
         this.sugar -= amount;
      }
   }

   public void increaseSugar(int amount){
      if(this.sugar + amount <= this.cap){
         this.sugar += amount;
      }
      else {
         this.setSugar();
      }
   }

   public int getSugar(){
      return this.sugar;
   }

   public int getCap(){
      return this.cap;
   }
}
