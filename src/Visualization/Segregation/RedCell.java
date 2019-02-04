package Visualization.Segregation;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class RedCell extends SquareCell {
    Paint myColor = Color.RED;

    public RedCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public RedCell(){
        this.setFill(myColor);
    }
}
