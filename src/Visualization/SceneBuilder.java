package Visualization;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.control.Button;

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
    private int delay = 100; // ms
    private static String TITLE = "Cellular Automata";


    //important for our simulation
    private Scene myScene;
    private Shape[][] myGrid = new Shape[GRID_DIM][GRID_DIM];
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

        // updates segment of the window
        Group updates = new Group();

//        Button pause = new Button("pause");
//        Button play = new Button("play");
//        Button delay = new Button("delay");
//        Button cycle = new Button("cycle");

        Rectangle pause = new Rectangle(0, 0, 100, 20);
        Rectangle play = new Rectangle(0, 100, 20, 100);
        Rectangle delay = new Rectangle(300,0, 100, 20);
        Rectangle cycle = new Rectangle(300, 100, 20, 100);

        updates.getChildren().add(pause);
        updates.getChildren().add(play);
        updates.getChildren().add(delay);
        updates.getChildren().add(cycle);

//        updates.setAlignment(pause, Pos.TOP_LEFT);
//        updates.setAlignment(play, Pos.BOTTOM_LEFT);
//        updates.setAlignment(delay, Pos.TOP_RIGHT);
//        updates.setAlignment(cycle, Pos.BOTTOM_RIGHT);

        window.setBottom(updates);

        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }

    /**Construct the myGridSize x myGridSize grid for the cells to inhabit*/
    private Group makeGrid (int cellSize){
        Group gridRoot = new Group();
        //build grid
        for (int i = 0; i < myGridSize; i++) {
            for (int j = 0; j < myGridSize; j++) {
                SquareCell newCell = new FireCell(i * cellSize, j * cellSize, cellSize, cellSize);
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
