package Visualization;

import java.io.File;
import java.util.ArrayList;

import Configuration.BadFileInputException;
import Configuration.GeneralParse;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import simulation.*;
import javafx.scene.control.Alert;

public class ModelBuilder {
   private static int WIDTH = 530;
   private static int HEIGHT = 700;
   //816
   private static int GRID_DIM = 400;
   private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;
   private static String BUTTON_LABEL = "Select a configuration file";

   private static final int CYCLE_SETX = 200;

   private LongProperty myCycle = new SimpleLongProperty(0);
   private static String CYCLE_LABEL = "CYCLE: ";


   private String fileName;
   private GeneralParse myParser;
   private CellGrid cellGrid;
   private ArrayList<ArrayList<Cell>> cellList;
   private SquareCell[][] nodeArray;
   private Group nodeGroup;
   private int myGridSize;
   private int cellSize;
   private Stage myStage;
   private Pane bottomPane;
   private Timeline myAnimation;
   private int myFramesPerSec;
   private Text myCycleInfo;


   ModelBuilder(Stage stage, Timeline myAnimation) throws BadFileInputException{
      this.myStage = stage;
      this.myAnimation = myAnimation;
      this.initialScene(WIDTH, HEIGHT);
   }

   public void parseFile() throws BadFileInputException {
      try {
         this.myParser = new GeneralParse();

         System.out.println(this.fileName);
         myParser.startParse(this.fileName);
         if (myParser.getTypeSimulation().equals("GameOfLife")) {
            this.setGOL();
         } else if (myParser.getTypeSimulation().equals("Percolation")) {
            this.setPercolation();
         } else if (myParser.getTypeSimulation().equals("Segregation")) {
            this.setSegregation();
         } else if (myParser.getTypeSimulation().equals("SpreadingFire")) {
            this.setFire();
         } else if (myParser.getTypeSimulation().equals("SugarScape")) {
            this.setSugarScape();
         } else {
            this.setWaTor();
         }
      }
      catch(BadFileInputException b){
         makeAnAlert("Error!", "Bad File!", b.getExceptionMsg());
      }
   }

   private Button makeFilePane() throws BadFileInputException{
      final FileChooser fileChooser = new FileChooser();
      final Button openButton = new Button(BUTTON_LABEL);
      openButton.setOnAction(
         new EventHandler<ActionEvent>(){
            @Override
            public void handle(final ActionEvent e) {
               File file = fileChooser.showOpenDialog(myStage);
               if(file != null){
                  try {
                     uploadFile(file);
                  }
                  catch(BadFileInputException b){
                     makeAnAlert("Error", "Incorrect File Type!!!", b.getExceptionMsg());
                  }
               }
            }
         });
      return openButton;
   }


   private void setWaTor(){
      WaTorGrid grid = new WaTorGrid(myParser.getRows(), myParser.getColumns(), myParser.getProbEmpty(),myParser.getProbFish(), myParser.getFishTime(), myParser.getSharkTime(), myParser.getStarveTime());
      this.cellGrid = grid;
   }
   private void setSegregation(){
      SegregationGrid grid = new SegregationGrid(myParser.getRows(), myParser.getColumns(), myParser.getThresh(), myParser.getProbEmptSeg(), myParser.getProbRed());
      this.cellGrid = grid;
   }

   private void setFire(){
      SpreadingOfFireGrid grid = new SpreadingOfFireGrid(myParser.getRows(), myParser.getColumns(), myParser.getFireProb(), myParser.getNumTree(), myParser.getNumBurn());
      this.cellGrid = grid;
   }

   private void setGOL(){
      GameOfLifeGrid grid = new GameOfLifeGrid(myParser.getRows(), myParser.getColumns(), myParser.getGOFPercFireprob());
      this.cellGrid = grid;
   }

   private void setPercolation(){
      PercolationGrid grid = new PercolationGrid(myParser.getRows(), myParser.getColumns(), myParser.getPercProb(), myParser.getNumPerc());
      this.cellGrid = grid;
   }

   private void setSugarScape(){
      SugarScapeGrid grid = new SugarScapeGrid(myParser.getRows(), myParser.getColumns(), myParser.getNumAgents());
//<<<<<<< HEAD
      this.cellGrid=grid;
   }


//   public Group updateNodes(){
////=======
//      this.cellGrid = grid;
//   }

   public void updateNodes(){
      if(this.nodeGroup == null){
         this.nodeGroup = new Group();
      }
      else{
         this.nodeGroup.getChildren().removeAll();
      }
//>>>>>>> ffc6131b824598190019ef09dfbbfebb09a9046d
      for (int r = 0; r < myGridSize; r++) {
         for (int c = 0; c < myGridSize; c++) {
            SquareCell newCell = CellBuilder.getImage(this .cellList.get(r).get(c));
            this.nodeArray[r][c] = positionCell(newCell,r,c);
            this.nodeGroup.getChildren().add(newCell);
         }
      }
   }

   private SquareCell positionCell(SquareCell myCell, int r, int c){
      myCell.setX(r*this.cellSize);
      myCell.setY(c*this.cellSize);
      myCell.setWidth(this.cellSize);
      myCell.setHeight(this.cellSize);
      return myCell;
   }

   public void step(){
      if(this.cellGrid != null)
      {
         this.cellGrid.step();
         this.cellList = this.cellGrid.getCellList();
         this.updateNodes();
         myCycle.set(myCycle.get() + 1);
         myCycleInfo.textProperty().bind(Bindings.createStringBinding(() -> (CYCLE_LABEL + myCycle.get())));
      }
   }

   public Group getRoot(){
      return this.nodeGroup;
   }


   private void uploadFile(File file) throws BadFileInputException{

         this.fileName = file.getPath();
         //System.out.println(fileName.substring(this.fileName.indexOf("Percolation.xml")));
      if(!(fileName.substring(fileName.length()-4).equals(".xml"))){

         throw new BadFileInputException("Select Another File and Press Play to Continue");
      }
         this.fileName = this.fileName.substring(this.fileName.indexOf("resources"));
         this.resetModel(WIDTH, HEIGHT);


      //catch(BadFileInputException b){
        // makeAnAlert("Error!","File Error", b.getExceptionMsg());


   }
   private void makeAnAlert(String title, String header, String content){
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle(title);
      alert.setHeaderText(header);
      alert.setContentText(content);
      alert.show();
      myAnimation.pause();
   }

   private void resetGrid() throws BadFileInputException{
      this.parseFile();
      this.cellList = cellGrid.getCellList();
      this.myGridSize = this.cellGrid.getRows();
      this.cellSize = GRID_DIM/myGridSize;
      this.nodeArray = new SquareCell[this.myGridSize][this.myGridSize];
      this.updateNodes();
      this.myCycle.set(0);
   }

   private void initialScene(int width, int height) throws BadFileInputException{
      BorderPane window = new BorderPane();
      Image img = new Image(this.getClass().getClassLoader().getResourceAsStream("background.jpg"));
      BackgroundImage bi = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
      window.setBackground(new Background(bi));
      window.setBottom(this.makeFilePane());
      Scene initialScene = new Scene(window, width, height);
      this.myStage.setScene(initialScene);
   }

   private void resetModel(int width, int height) throws BadFileInputException{
      BorderPane window = new BorderPane();
      Image img = new Image(this.getClass().getClassLoader().getResourceAsStream("background.jpg"));
      BackgroundImage bi = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
      Background imBack = new Background(bi);
      window.setBackground(imBack);
      this.resetGrid();
      window.setCenter(this.getRoot());
      window.setBottom(this.getLowerPane());
      Scene modelScene = new Scene(window, width, height);
      this.myStage.setScene(modelScene);
   }

   private HBox getLowerPane() throws BadFileInputException{
      Group options = new BuildOptions(new Group(), this.myAnimation, this.myFramesPerSec).getRoot();
      this.myCycleInfo = new Text();
      myCycleInfo.textProperty().bind(Bindings.createStringBinding(() -> (CYCLE_LABEL + myCycle.get())));
      myCycleInfo.setX(CYCLE_SETX);
      myCycleInfo.setY(0);
      HBox hbox = new HBox(10);
      hbox.setMaxWidth(WIDTH);
      hbox.getChildren().addAll(options);
      hbox.getChildren().add(myCycleInfo);
      hbox.getChildren().add(this.makeFilePane());
      return hbox;

   }
}
