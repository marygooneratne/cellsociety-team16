package Visualization.Percolation;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class OpenCell extends SquareCell {
    Paint myColor = Color.LIGHTGRAY;

    public OpenCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public OpenCell(){
        this.setFill(myColor);
    }
}
