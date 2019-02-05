package Visualization;

import Configuration.GeneralParse;
import Visualization.GameOfLife.PopulatedCell;
import Visualization.GameOfLife.UnpopulatedCell;
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
import simulation.Cell;
import simulation.CellGrid;
import simulation.CellState;
import simulation.GameOfLifeGrid;

import java.util.ArrayList;

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
    private int myGridSize = 10;
    private int myFramesPerS = 1; // --> as this increases, the sim runs faster
    private int myDelayMS =  1000/myFramesPerS ; // seconds --> *1000 for ms
    private int myDelayS = 1/myFramesPerS;
    private static String TITLE = "Cellular Automata";

    //important for our simulation
    private Scene myScene;
    private Shape[][] myGrid = new Shape[GRID_DIM][GRID_DIM];
    private LongProperty myCycle = new SimpleLongProperty(0);
    private Group myGridRoot;

    private int cellSize;

    private CellGrid cellGrid;
    private ArrayList<ArrayList<Cell>> myCells;

    @Override
    public void start(Stage stage) {
        myScene = setupSim(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        //add animation for simulation loop timeline
        var frame = new KeyFrame(Duration.millis(myDelayMS), e -> step(myDelayS));
        System.out.println("here");
        var myAnimation = new Timeline();
        System.out.println("here 2");
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        System.out.println("here 3");
        myAnimation.getKeyFrames().add(frame);
        System.out.println("here 4x");
        myAnimation.play();
    }

    /** initial simulation with grid and buttons*/
    private Scene setupSim(int width, int height, Paint bg) {

        // BorderPane Layout
        BorderPane window = new BorderPane();
        // create group for grid and add to window


        // grid calculations
        this.cellSize = width/myGridSize;
        myGridRoot = makeGrid(cellSize);
        window.setTop(myGridRoot);

        // options region of the UI
        Group options = new BuildOptions(new Group(), /**myAnimation,*/ myFramesPerS, myCycle).getRoot();

        window.setBottom(options);

        Scene scn = new Scene(window, width, height, bg);
        return scn;
    }

    /**Construct the myGridSize x myGridSize grid for the cells to inhabit*/
    private Group makeGrid (int cellSize){
        this.startGOL();
        Group newGroup = new Group();
        //build grid
        for (int i = 0; i < myGridSize; i++) {
            for (int j = 0; j < myGridSize; j++) {
                //SquareCell newCell = new PopulatedCell(i * cellSize, j * cellSize, cellSize, cellSize);
                // feed in the cell types here
                SquareCell newCell = this.getCellShape(i,j);
                newCell.setX(i*this.cellSize);
                newCell.setY(j*this.cellSize);
                newCell.setWidth(this.cellSize);
                newCell.setHeight(this.cellSize);
                myGrid[i][j] = newCell;
                newGroup.getChildren().add(newCell);
            }
        }
        return newGroup;
    }


    private void step (double elapsedTime){
        // cross reference the grid positions
        System.out.println("STEP CALLED");
        this.cellGrid.step();
        this.myCells = this.cellGrid.getCellList();
        this.updateGrid();

        myCycle.set(myCycle.get() + 1);
    }

    private void updateGrid(){
        this.myGridRoot.getChildren().removeAll();
        for (int i=0; i< myGridSize; i++){
            for (int j=0; j<myGridSize; j++){
                    SquareCell newCell = this.getCellShape(i,j);
                    newCell.setX(i*this.cellSize);
                    newCell.setY(j*this.cellSize);
                    newCell.setWidth(this.cellSize);
                    newCell.setHeight(this.cellSize);
                    myGrid[i][j] = newCell;
                    this.myGridRoot.getChildren().add(newCell);
            }
        }
    }

    private void startGOL(){
        GeneralParse myParse = new GeneralParse();
        myParse.startParse("GameOfLife");
        System.out.println(myParse.getGOFPercFireprob());
        GameOfLifeGrid testGrid = new GameOfLifeGrid(myParse.getRows(), myParse.getColumns(), myParse.getGOFPercFireprob());
        this.cellGrid = testGrid;
        this.myCells = testGrid.getCellList();
    }

    public SquareCell getCellShape(int r, int c){
        Cell cell = this.myCells.get(r).get(c);
        if(cell.getCurrentState() == CellState.POPULATED){
            return new PopulatedCell();
        }
        else{
            return new UnpopulatedCell();
        }


    }

    /**
     * Start the program
     */
    public static void main(String args[]) {
        launch(args);
    }
}
