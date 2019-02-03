import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;

public class SceneBuilder extends Application {
    private static String TITLE = "Simulation Startup";
    private static int HEIGHT = 400;
    private static int WIDTH = 400;
    private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;

    //important for our simulation
    private int myGridSize = 40; // will need to taken in later
    private Scene myScene;
    private int myCellSize = WIDTH/myGridSize;
//    private int[][] myGrid = new int[]

    @Override
    public void start(Stage stage) {
        myScene = setupSim(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        //add animation stuff here
    }

    private Scene setupSim(int width, int height, Paint bg) {
        // create group
        Group root = new Group();
        Scene scn = new Scene(root, width, height, bg);
        // grid calculations

        // initialize grid

        Rectangle newCell;
        for (int i = 0; i < myGridSize; i++) {
            for (int j = 0; j < myGridSize; j++) {
                newCell = cellPlacement(myCellSize, i * myCellSize, j * myCellSize);
                root.getChildren().add(newCell);
            }
        }

        return scn;
    }

    public Rectangle cellPlacement(int cellSize, double x, double y) {
        Rectangle rect = new Rectangle(x, y, cellSize, cellSize);
        rect.setStroke(Color.BLACK);
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
