package Visualization;

import javafx.scene.paint.Paint;

import java.awt.*;

public abstract class Cell extends Rectangle {
    Paint myColor;
    public Cell(Paint color){
        this.myColor = color;
    }
}
