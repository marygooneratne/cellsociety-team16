package Visualization.SpreadingFire;

import Visualization.SquareCell;

import javafx.scene.paint.*;

public class TreeCell extends SquareCell {
    Paint myColor = Color.BROWN;

    public TreeCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public TreeCell(){
        this.setFill(myColor);
    }
}
