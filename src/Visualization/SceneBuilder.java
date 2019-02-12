package Visualization;

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

    private static int WIDTH = 400;
    private static int HEIGHT = 600;
    private static int GRID_DIM = 400;
    private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;


    private int myFramesPerS = 1; // --> as this increases, the sim runs faster
    private int myDelayMS =  MILLI_TO_SEC_CONVERT/myFramesPerS ; // seconds --> *1000 for ms
    private int myDelayS = 1/myFramesPerS;
    private static String TITLE = "Cellular Automata";
    private static final int CYCLE_SETX = 200;
    private static final int MILLI_TO_SEC_CONVERT=1000;

    private Scene myScene;
    private LongProperty myCycle = new SimpleLongProperty(0);
    private static String CYCLE_LABEL = "CYCLE: ";
    private Group myGridRoot;
    private Text myCycleInfo;



    private ModelBuilder model;
    private Timeline myAnimation;
    private String uploadFile;



    @Override
    public void start(Stage stage) {
       myAnimation = new Timeline();
       this.model = new ModelBuilder(stage, myAnimation);
       stage.setTitle(TITLE);
       stage.show();

        var frame = new KeyFrame(Duration.millis(myDelayMS), e -> step(myDelayS, myCycleInfo));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    /*private Scene setupSim(int width, int height, Paint bg, Stage stage) {
        this.model = new ModelBuilder(stage);
        this.myGridRoot = model.getRoot();
        BorderPane window = new BorderPane();
        window.setTop(myGridRoot);

        Group options = new BuildOptions(new Group(), myAnimation, myFramesPerS).getRoot();

        myCycleInfo = new Text();
        myCycleInfo.textProperty().bind(Bindings.createStringBinding(() -> (CYCLE_LABEL + myCycle.get())));
        myCycleInfo.setX(CYCLE_SETX);
        myCycleInfo.setY(0);
        options.getChildren().add(myCycleInfo);

        window.setBottom(options);

        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }*/

    private void step (double elapsedTime, Text cycle){
        this.model.step();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
