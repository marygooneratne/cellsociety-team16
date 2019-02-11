package Visualization.SugarScape;

import Visualization.SquareCell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class AgentCell extends SquareCell {
   Paint myColor = Color.RED;

   public AgentCell(double x, double y, double width, double height){
      super(x,y,width,height);
      this.setFill(myColor);
   }

   public AgentCell(){
      this.setFill(myColor);
   }
}
