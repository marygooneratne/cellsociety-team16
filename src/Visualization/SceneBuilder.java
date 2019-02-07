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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.*;
import java.util.ArrayList;


public class SceneBuilder extends Application {
    private static String UPLOAD_FILE = "./resources/WatorWorld";

    //things that will be read in
    private int myGridSize;
    private int myFramesPerS = 1; // --> as this increases, the sim runs faster
    private int myDelayMS =  MILLI_TO_SEC_CONVERT/myFramesPerS ; // seconds --> *1000 for ms
    private int myDelayS = 1/myFramesPerS;
    private static String TITLE = "Cellular Automata";
    private static final int CYCLE_SETX = 200;
    private static final int MILLI_TO_SEC_CONVERT=1000;
    //important for our simulation
    private Scene myScene;
    private Shape[][] myGrid = new Shape[GRID_DIM][GRID_DIM];
    private LongProperty myCycle = new SimpleLongProperty(0);
    private static String CYCLE_LABEL = "CYCLE: ";
    private Group myGridRoot;
    private Text myCycleInfo;

    private int cellSize;

    private ModelBuilder model;
    private CellGrid cellGrid;
    private ArrayList<ArrayList<Cell>> myCells;
    private Timeline myAnimation;
    private GeneralParse myParser;



    @Override
    public void start(Stage stage) {
        myAnimation = new Timeline();
        myScene = setupSim(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        //add animation for simulation loop timeline
        var frame = new KeyFrame(Duration.millis(myDelayMS), e -> step(myDelayS, myCycleInfo));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    /** initial simulation with grid and buttons*/
    private Scene setupSim(int width, int height, Paint bg) {
        this.model = new ModelBuilder(UPLOAD_FILE);
        // BorderPane Layout
        BorderPane window = new BorderPane();
        // create group for grid and add to window
        myGridRoot = makeGrid(cellSize);
        window.setTop(myGridRoot);

        // options region of the UI
        Group options = new BuildOptions(new Group(), myAnimation, myFramesPerS).getRoot();

        myCycleInfo = new Text();
        myCycleInfo.textProperty().bind(Bindings.createStringBinding(() -> (CYCLE_LABEL + myCycle.get())));
        myCycleInfo.setX(CYCLE_SETX);
        myCycleInfo.setY(0);
        options.getChildren().add(myCycleInfo);

        window.setBottom(options);

        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }

    private void step (double elapsedTime, Text cycle){
        // cross reference the grid positions
        this.cellGrid.step();
        this.myCells = this.cellGrid.getCellList();
        this.updateGrid();
        myCycle.set(myCycle.get() + 1);
        cycle.textProperty().bind(Bindings.createStringBinding(() -> (CYCLE_LABEL + myCycle.get())));
    }

    /**Construct the myGridSize x myGridSize grid for the cells to inhabit*/

    private void updateGrid(){
        this.myGridRoot.getChildren().removeAll();
        for (int r=0; r< myGridSize; r++){
            for (int c=0; c<myGridSize; c++){
                    SquareCell newCell = CellBuilder.getImage(this.myCells.get(r).get(c));
//                    newCell.setX(i*this.cellSize);
//                    newCell.setY(j*this.cellSize);
//                    newCell.setWidth(this.cellSize);
//                    newCell.setHeight(this.cellSize);
                    myGrid[r][c] = makeCell(newCell,r,c);
                    this.myGridRoot.getChildren().add(newCell);
            }
        }
    }




    /**
     * Start the program
     */
    public static void main(String args[]) {
        launch(args);
    }
}
