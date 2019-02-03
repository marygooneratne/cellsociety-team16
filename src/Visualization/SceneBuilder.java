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
    private static String TITLE = "Simulation Startup";
    private static int WIDTH = 400;
    private static int HEIGHT = 600;
    private static int GRID_DIM = 400;
    private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;
    private static Paint BORDER_COLOR = Color.BLACK;

    //important for our simulation
    private int myGridSize = 40; // will need to taken in later
    private Scene myScene;
    private Rectangle[][] myGrid = new Rectangle[GRID_DIM][GRID_DIM];;

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
        BorderPane window = new BorderPane();
        // create group for grid and add to window
        Group grid = new Group();
        Group test = new Group();
        window.setTop(grid);

        // grid calculations
        int myCellSize = width/myGridSize;

        // initialize gridS
        Rectangle newCell;
        for (int i = 0; i < myGridSize; i++) {
            for (int j = 0; j < myGridSize; j++) {
                newCell = cellPlacement(myCellSize, i * myCellSize, j * myCellSize);
                myGrid[i][j] = newCell;
                grid.getChildren().add(newCell);
            }
        }
        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }

    public Rectangle cellPlacement(int cellSize, double x, double y) {
        Rectangle rect = new Rectangle(x, y, cellSize, cellSize);
        rect.setStroke(BORDER_COLOR);
        rect.setFill(Color.GRAY);
        return rect;
    }

    /**
     * Start the program
     */
    public static void main(String args[]) {
        launch(args);
    }
}
