package Visualization.SpreadingFire;

import Visualization.SquareCell;

import javafx.scene.paint.*;

public class TreeClass extends SquareCell {
    Paint myColor = Color.BROWN;

    public TreeClass(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public TreeClass(){
        this.setFill(myColor);
    }
}
