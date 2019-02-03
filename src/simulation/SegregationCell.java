package simulation;

public class SegregationCell extends Cell{
   private double threshold;

   public SegregationCell(){
      super();
   }

   public SegregationCell(CellState initialState){
      super(initialState);
   }

   public SegregationCell(CellState initialState, int r, int c){
      super(initialState, r, c);
   }

   public void setThreshold(double newThreshold){
      this.threshold = newThreshold;
   }

   public boolean toMove(){
      if(this.getCurrentState() == CellState.EMPTY){
         return false;
      }
      else {
         double total = 0;
         double same = 0;
         for (Cell c : this.getCurrentNeighbors()) {
            if(c.getCurrentState() != CellState.EMPTY){
               total++;
            }
            if (c.getCurrentState() == this.getCurrentState()) {
               same++;
            }
         }
         if (total != 0 && (same / total) < threshold) {
            return true;
         } else {
            return false;
         }
      }

   }
}
