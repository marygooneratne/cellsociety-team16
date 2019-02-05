package Visualization.GameOfLife;

import Visualization.SquareCell;
import javafx.scene.paint.*;

public class UnpopulatedCell extends SquareCell {
    Paint myColor = Color.YELLOW;

    public UnpopulatedCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }

    public UnpopulatedCell(){
        this.setFill(myColor);
    }
}
