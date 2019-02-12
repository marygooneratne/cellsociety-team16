package Visualization;

import Configuration.BadFileInputException;
import Configuration.GeneralParse;
import Visualization.GameOfLife.PopulatedCell;
import Visualization.GameOfLife.UnpopulatedCell;
import Visualization.Percolation.BlockedCell;
import Visualization.Percolation.OpenCell;
import Visualization.Percolation.PercolatedCell;
import Visualization.Segregation.BlueCell;
import Visualization.Segregation.RedCell;
import Visualization.SpreadingFire.BurningCell;
import Visualization.SpreadingFire.EmptyCell;
import Visualization.SpreadingFire.TreeCell;
import Visualization.WaTorWorld.FishCell;
import Visualization.WaTorWorld.SharkCell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.*;

import java.io.File;
import java.util.ArrayList;


public class SceneBuilder extends Application {
//<<<<<<< HEAD
    private static String UPLOAD_FILE = "./resources/WatorWorld";
//=======
//>>>>>>> ffc6131b824598190019ef09dfbbfebb09a9046d

    private static int WIDTH = 400;
    private static int HEIGHT = 600;
    private static int GRID_DIM = 400;
    private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;


    private int myFramesPerS = 1;
    private int myDelayMS =  MILLI_TO_SEC_CONVERT/myFramesPerS ;
    private static String TITLE = "Cellular Automata";
    private static final int CYCLE_SETX = 200;
    private static final int MILLI_TO_SEC_CONVERT=1000;


    private ModelBuilder model;
    private Timeline myAnimation;



    @Override
    public void start(Stage stage) throws BadFileInputException {
       myAnimation = new Timeline();
       this.model = new ModelBuilder(stage, myAnimation);
       stage.setTitle(TITLE);
       stage.show();

       var frame = new KeyFrame(Duration.millis(myDelayMS), e -> step());
       myAnimation.setCycleCount(Timeline.INDEFINITE);
       myAnimation.getKeyFrames().add(frame);
       myAnimation.play();
    }

    private void step(){
        this.model.step();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
