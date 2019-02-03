package Visualization;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;

public class SceneBuilder extends Application {
    private static String TITLE = "Cellular Automata";
    private static int WIDTH = 400;
    private static int HEIGHT = 600;
    private static int GRID_DIM = 400;
    private static int UPDATE_HEIGHT = HEIGHT - GRID_DIM;
    private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;
    private static Paint BORDER_COLOR = Color.BLACK;

    //things that will be read in
    private int myGridSize = 40;
    private int delay = 100; // ms


    //important for our simulation
    private Scene myScene;
    private Rectangle[][] myGrid = new Rectangle[GRID_DIM][GRID_DIM];
    private int myCycle = 0;

    @Override
    public void start(Stage stage) {
//        myGrid = new int[GRID_DIM][GRID_DIM]; // is this a dependency?
        myScene = setupSim(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        //add animation stuff here
    }

    private Scene setupSim(int width, int height, Paint bg) {
        // BorderPane Layout
        BorderPane window = new BorderPane();
        // create group for grid and add to window


        // grid calculations
        int cellSize = width/myGridSize;
        window.setTop(makeGrid(cellSize));
        Group updates = new Group();
        window.setBottom(updates);


        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }

    private Group makeGrid (int cellSize){
        Group gridRoot = new Group();
        //build grid
        for (int i = 0; i < myGridSize; i++) {
            for (int j = 0; j < myGridSize; j++) {
                Rectangle newCell = new Rectangle(i * cellSize, j * cellSize, cellSize, cellSize);
                newCell.setStroke(BORDER_COLOR);
                newCell.setFill(BACKGROUND); // ---> CHANGE THIS WHEN YOU FIGURE OUT INHERITANCE OF RECTANGLE CLASS
                myGrid[i][j] = newCell;
                gridRoot.getChildren().add(newCell);
            }
        }
        return gridRoot;
    }

    /**
     * Start the program
     */
    public static void main(String args[]) {
        launch(args);
    }
}
