package Visualization.SpreadingFire;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class EmptyCell extends SquareCell {
    Paint myColor = Color.WHITE;

    public EmptyCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public EmptyCell(){
        this.setFill(myColor);
    }
}
