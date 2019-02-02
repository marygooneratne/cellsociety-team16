package simulation;

public class SpreadingOfFireCell extends Cell{
   private double probCatch;

   public SpreadingOfFireCell() {
      super();
   }

   public SpreadingOfFireCell(CellState initialState) {
      super(initialState);
   }

   public SpreadingOfFireCell(CellState initialState, int r, int c){
      super(initialState, r, c);
   }

   public void setProbCatch(double newProb){
      this.probCatch = newProb;
   }

   public void updateNextState() {
      int onFire = 0;
      for (Cell neighbor : this.getCurrentNeighbors()) {
         if (neighbor.getCurrentState() == CellState.BURNING) {
            onFire++;
         }
      }
      if(this.getCurrentState() == CellState.EMPTY){
         this.setNextState(CellState.EMPTY);
      }
      else if(this.getCurrentState() == CellState.BURNING){
         this.setNextState(CellState.EMPTY);
      }
      else{
         if(onFire > 0 && this.getBurning()){
            this.setNextState(CellState.BURNING);
         }
         else{
            this.setNextState(CellState.TREE);
         }
      }
   }

   public boolean getBurning(){
      double random = Math.random();
      return random < probCatch;
   }


}
