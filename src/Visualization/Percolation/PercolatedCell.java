package Visualization.Percolation;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class PercolatedCell extends SquareCell {
    Paint myColor = Color.LIGHTBLUE;

    public PercolatedCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public PercolatedCell(){
        this.setFill(myColor);
    }
}
