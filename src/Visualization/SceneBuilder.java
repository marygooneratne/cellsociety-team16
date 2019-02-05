package Visualization;

import Visualization.GameOfLife.PopulatedCell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;

public class SceneBuilder extends Application {
    private static int WIDTH = 400;
    private static int HEIGHT = 600;
    private static int GRID_DIM = 400;
    private static int UPDATE_HEIGHT = HEIGHT - GRID_DIM;
    private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;

    //things that will be read in
    private int myGridSize = 20;
    private int myFramesPerS = 60; // --> as this increases, the sim runs faster
    private int myDelay = 1/myFramesPerS; // seconds --> *1000 for ms
    private static String TITLE = "Cellular Automata";

    //important for our simulation
    private Scene myScene;
    private Shape[][] myGrid = new Shape[GRID_DIM][GRID_DIM];
    private LongProperty myCycle = new SimpleLongProperty(0);
    private Timeline myAnimation;

    @Override
    public void start(Stage stage) {
        myScene = setupSim(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        //add animation for simulation loop timeline
        var frame = new KeyFrame(Duration.millis(myDelay*1000), e -> step(myDelay, myGrid));
        var myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    /** initial simulation with grid and buttons*/
    private Scene setupSim(int width, int height, Paint bg) {
        // BorderPane Layout
        BorderPane window = new BorderPane();
        // create group for grid and add to window


        // grid calculations
        int cellSize = width/myGridSize;
        Group grid = makeGrid(cellSize);
        window.setTop(grid);

        // options region of the UI
        Group options = new BuildOptions(new Group(), /**myAnimation,*/ myFramesPerS, myCycle).getRoot();

        window.setBottom(options);

        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }

    /**Construct the myGridSize x myGridSize grid for the cells to inhabit*/
    private Group makeGrid (int cellSize){
        Group gridRoot = new Group();
        //build grid
        for (int i = 0; i < myGridSize; i++) {
            for (int j = 0; j < myGridSize; j++) {
                SquareCell newCell = new PopulatedCell(i * cellSize, j * cellSize, cellSize, cellSize);
                // feed in the cell types here
//                SquareCell newCell = inputGrid[i][j];
//                newCell.setX(i*cellSize);
//                newCell.setY(j*cellSize);
//                newCell.setWidth(cellSize);
//                newCell.setHeight(cellSize);
                myGrid[i][j] = newCell;
                gridRoot.getChildren().add(newCell);
            }
        }
        return gridRoot;
    }

    private void step (double elapsedTime, Shape[][] updatedGrid){
        // cross reference the grid positions
        for (int i=0; i< myGridSize; i++){
            for (int j=0; j<myGridSize; j++){
                if (myGrid[i][j] != updatedGrid[i][j]){
                    //remove from scene
                }
            }
        }

        myCycle.set(myCycle.get() + 1);
    }

    /**
     * Start the program
     */
    public static void main(String args[]) {
        launch(args);
    }
}
