package simulation;

import java.util.ArrayList;

public abstract class Cell {
   private CellState currentState;
   private CellState nextState;
   private ArrayList<Cell> currentNeighbors;
   private int row;
   private int column;

   public Cell() {
      this.currentNeighbors = new ArrayList<Cell>();
   }

   public Cell(CellState initialState) {
      this.currentNeighbors = new ArrayList<Cell>();
      this.currentState = initialState;
   }

   public Cell(CellState initialState, int r, int c){
      this.currentNeighbors = new ArrayList<Cell>();
      this.currentState = initialState;
      this.row = r;
      this.column = c;
   }
   public void emptyNeighbors(){
      this.currentNeighbors.removeAll(this.currentNeighbors);
   }
   public void setCurrentState(CellState newState) {
      this.currentState = newState;
   }

   public void setNextState(CellState newState) {
      this.nextState = newState;
   }

   public void setCurrentNeighbors(ArrayList<Cell> newNeighbors) {
      this.currentNeighbors = newNeighbors;
   }

   public void setRow(int newRow) {
      this.row = newRow;
   }

   public void setColumn(int newColumn) {
      this.column = newColumn;
   }

   public CellState getCurrentState() {
      return this.currentState;
   }

   public CellState getNextState() {
      return this.nextState;
   }

   public ArrayList<Cell> getCurrentNeighbors() {
      return this.currentNeighbors;
   }

   public void addNeighbor(Cell newNeighbor) {
      this.currentNeighbors.add(newNeighbor);
   }

   public int getRow() {
      return this.row;
   }

   public int getColumn() {
      return this.column;
   }

   public void updateState() {
      this.currentState = this.nextState;
      this.nextState = null;
   }

   public void updateNextState() {

   }
}