/** Mary Gooneratne
 * Abstract Cell class that acts as parent class for all Cells of the models
 */
package simulation;

import java.util.ArrayList;

public abstract class Cell {
   private CellState currentState;
   private CellState nextState;
   private ArrayList<Cell> currentNeighbors;
   private int row;
   private int column;

   /** Constructors for cell class
    *
    */
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

   /** Empties cell neighbors
    *
    */
   public void emptyNeighbors(){
      this.currentNeighbors.removeAll(this.currentNeighbors);
   }

   /**
    * Changes current state
    *
    * @param newState
    */
   public void setCurrentState(CellState newState) {
      this.currentState = newState;
   }

   /** Changes next state
    *
    * @param newState
    */
   public void setNextState(CellState newState) {
      this.nextState = newState;
   }

   /** Setter method for cell row
    *
    * @param newRow
    */
   public void setRow(int newRow) {
      this.row = newRow;
   }

   /**
    * Setter method for column
    * @param newColumn
    */
   public void setColumn(int newColumn) {
      this.column = newColumn;
   }

   /**
    * Getter method for current cellState
    * @return CellState
    */
   public CellState getCurrentState() {
      return this.currentState;
   }

   /**
    * Getter method for next cellState
    * @return CellState
    */

   public CellState getNextState() {
      return this.nextState;
   }

   /** Returns arrayList of currentNeighbors
    *
    * @return neighbor list
    */

   public ArrayList<Cell> getCurrentNeighbors() {
      return this.currentNeighbors;
   }

   /**
    * Adds new neighbor to cell arraylist
    * @param newNeighbor
    */

   public void addNeighbor(Cell newNeighbor) {
      this.currentNeighbors.add(newNeighbor);
   }

   /**
    * Getter method for cell row
    * @return row
    */

   public int getRow() {
      return this.row;
   }

   /** Getter method for cell column
    *
    * @return column
    */
   public int getColumn() {
      return this.column;
   }

   /** Updates state by changing current to next and next to null
    *
    */

   public void updateState() {
      this.currentState = this.nextState;
      this.nextState = null;
   }

   /**
    * Implemented in specific model cells
    */

   public void updateNextState() {

   }
}