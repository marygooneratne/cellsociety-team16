package simulation;

public enum CellState{
    //Game of Life
    POPULATED, UNPOPULATED,
    //Tree
    EMPTY, TREE, BURNING,
    //Percolation
    OPEN, BLOCKED, PERCOLATED,
    //Segregation, also uses EMPTY
    RED, BLUE,
    //WaTor, also uses EMPTY
    SHARK, FISH
}