package Visualization;

import java.util.ArrayList;

import Configuration.GeneralParse;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import simulation.*;

public class ModelBuilder {
   private static int WIDTH = 400;
   private static int HEIGHT = 600;
   private static int GRID_DIM = 400;
   private static Paint BACKGROUND = Color.LIGHTSLATEGRAY;

   private String fileName;
   private GeneralParse myParser;
   private CellGrid cellGrid;
   private ArrayList<ArrayList<Cell>> cellList;
   private SquareCell[][] nodeArray;
   private Group nodeGroup;
   private int myGridSize;
   private int cellSize;

   ModelBuilder(String fileName){
      this.fileName = fileName;
      this.parseFile();
      this.cellList = cellGrid.getCellList();
      this.myGridSize = this.cellGrid.getRows();
      this.cellSize = WIDTH/myGridSize;
      this.nodeArray = new SquareCell[this.myGridSize][this.myGridSize];
      this.nodeGroup = new Group();
      this.updateNodes();
   }

   public void parseFile(){
      this.myParser = new GeneralParse();
      myParser.startParse(this.fileName);
      if(myParser.getTypeSimulation().equals("GameOfLife")){
         this.setGOL();
      }
      else if(myParser.getTypeSimulation().equals("Percolation")){
         this.setPercolation();
      }
      else if(myParser.getTypeSimulation().equals("Segregation")){
         this.setSegregation();
      }
      else if(myParser.getTypeSimulation().equals("SpreadingFire")){
         this.setFire();
      }
      else if(myParser.getTypeSimulation().equals("SugarScape")){
         this.setSugarScape();
      }
      else{
         this.setWaTor();
      }
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
      SugarScapeGrid grid = new SugarScapeGrid(myParser.getRows(), myParser.getColumns());
   }

   public Group updateNodes(){
      for (int r = 0; r < myGridSize; r++) {
         for (int c = 0; c < myGridSize; c++) {
            SquareCell newCell = CellBuilder.getImage(this.cellList.get(r).get(c));
            this.nodeArray[r][c] = positionCell(newCell,r,c);
            this.nodeGroup.getChildren().add(newCell);
         }
      }
      return this.nodeGroup;
   }

   private SquareCell positionCell(SquareCell myCell, int r, int c){
      myCell.setX(r*this.cellSize);
      myCell.setY(c*this.cellSize);
      myCell.setWidth(this.cellSize);
      myCell.setHeight(this.cellSize);
      return myCell;
   }

   public void step(){
      this.cellGrid.step();
      this.cellList = this.cellGrid.getCellList();
      this.updateNodes();
   }

   public Group getRoot(){
      return this.nodeGroup;
   }

}
