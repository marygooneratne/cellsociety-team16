package simulation;

import CellState.POPULATED;
import CellState.UNPOPULATED;

public class GameOfLifeCell extends Cell(){

    public GameOfLifeCell(){
        super();
    }

    public GameOfLifeCell(CellState initialState){
        super();
    }

    public void updateNextState(){
        int populated = 0;
        for(Cell neighbor: this.currentNeighbors){
            if(neighbor.getCurrentState() instanceof POPULATED){
                populated++;
            }
        }
        if(this.currentState instanceof UNPOPULATED){
            if(populated >= 3){
                this.nextState = POPULATED;
            }
        }
        else{
            if(populated <= 1){
                this.nextState = UNPOPULATED;
            }
            else if(populated < 4){
                this.nextState = POPULATED;
            }
            else{
                this.nextState = UNPOPULATED;
            }
        }
    }


}