package Visualization.SugarScape;

import Visualization.SquareCell;

import javafx.scene.paint.*;

public class SugarSquareCell extends SquareCell {
   Paint COLOR0 = Color.WHITE;
   Paint COLOR1 = Color.web("FFCC99");
   Paint COLOR2 = Color.web("FCB36A");
   Paint COLOR3 = Color.web("FF9C3A");
   Paint COLOR4 = Color.web("FF8205");

   private int sugar;
   private Paint myColor;

   public SugarSquareCell(double x, double y, double width, double height, int sugar){
      super(x,y,width,height);
      this.sugar = sugar;
      this.myColor = this.getColor();
      this.setFill(myColor);
   }

   public SugarSquareCell(int sugar){
      this.sugar = sugar;
      this.myColor = this.getColor();
      this.setFill(myColor);
   }

   private Paint getColor(){
      if(this.sugar == 1){
         return COLOR1;
      }
      else if(this.sugar == 2){
         return COLOR2;
      }
      else if(this.sugar == 3){
         return COLOR3;
      }
      else if(this.sugar == 4){
         return COLOR4;
      }
      else{
         return COLOR0;
      }
   }

}
