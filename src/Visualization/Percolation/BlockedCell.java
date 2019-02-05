package Visualization.Percolation;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class BlockedCell extends SquareCell {
    Paint myColor = Color.DARKGRAY;

    public BlockedCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public BlockedCell(){
        this.setFill(myColor);
    }
}
