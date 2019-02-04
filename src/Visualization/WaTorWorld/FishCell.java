package Visualization.WaTorWorld;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class FishCell extends SquareCell {
    Paint myColor = Color.ORANGE;

    public FishCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public FishCell(){
        this.setFill(myColor);
    }
}
