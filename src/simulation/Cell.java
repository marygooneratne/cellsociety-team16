package simulation;

public abstract class Cell(){
    private CellState currentState;
    private CellState nextState;
    private ArrayList<Cell> currentNeighbors;
    private int row;
    private int column;

    public Cell(){

    }

    public void setCurrentState(CellState newState){
        this.currentState = newState;
    }

    public void setNextState(CellState newState){
        this.nextState = newState;
    }

    public void setCurrentNeighbors(ArrayList<Cell> newNeighbors){
        this.currentNeighbors = newNeighbors;
    }

    public void setRow(int newRow){
        this.row = newRow;
    }

    public void setColumn(int newColumn){
        this.column = newColumn;
    }

    public CellState getCurrentState(){
        return this.currentState;
    }

    public ArrayList<Cell> getCurrentNeighbors(){
        return this.currentNeighbors;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void updateState(){
        this.currentState = this.nextState;
        this.nextState = null;
    }
}