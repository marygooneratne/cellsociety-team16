package Visualization.GameOfLife;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class PopulatedCell extends SquareCell {
    Paint myColor = Color.GREEN;

    public PopulatedCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public PopulatedCell(){
        this.setFill(myColor);
    }
}
