package Visualization.WaTorWorld;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class SharkCell extends SquareCell {
    Paint myColor = Color.GRAY;

    public SharkCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public SharkCell(){
        this.setFill(myColor);
    }
}
