package simulation;

public enum CellState{
    //Game of Life
    POPULATED, UNPOPULATED,
    //Tree
    EMPTY, TREE, BURNING,
    //Percolation
    OPEN, BLOCKED, PERCOLATED,
    //Segregation
    RED, BLUE,
    //WaTor
    SHARK, FISH
}