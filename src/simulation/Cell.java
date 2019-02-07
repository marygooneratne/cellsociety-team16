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

   public void setInitialState(CellState initial){
      this.currentState = initial;
   }

   public void emptyNeighbors(){
      this.currentNeighbors.removeAll(this.currentNeighbors);
   }
   public Cell setCurrentState(CellState newState) {
      this.currentState = newState;
      return this;
   }

   public Cell setNextState(CellState newState) {
      this.nextState = newState;
      return this;
   }

   public Cell setCurrentNeighbors(ArrayList<Cell> newNeighbors) {
      this.currentNeighbors = newNeighbors;
      return this;
   }

   public Cell setRow(int newRow) {
      this.row = newRow;
      return this;
   }

   public Cell setColumn(int newColumn) {
      this.column = newColumn;
      return this;
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