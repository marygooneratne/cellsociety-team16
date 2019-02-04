package Visualization;

import javafx.scene.paint.*;

public class FireCell extends SquareCell {
    Paint myColor = Color.RED;

    public FireCell (double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }
}
