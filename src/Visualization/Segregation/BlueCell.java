package Visualization.Segregation;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class BlueCell extends SquareCell {
    Paint myColor = Color.BLUE;

    public BlueCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public BlueCell(){
        this.setFill(myColor);
    }
}
