package Visualization.SpreadingFire;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class BurningCell extends SquareCell {
    Paint myColor = Color.RED;

    public BurningCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public BurningCell(){
        this.setFill(myColor);
    }
}
