#I. Introduction
#II. Overview
The simulation program organization/structure can be viewed [HERE](https://imgur.com/a/4gNSoJk). Different model types will be labeled in an enumeration class ModelType. This ModelType is the parameter to a JavaFX application extension SimulationApplication that runs startModel(). The startModel() creates an instance of Model that initializes, starts, and runs a grid to completion. This grid is an instance of the View object which is the JavaFX object representing the CellGrid object. This View object updates the Cell JavaFX Nodes when the Cell states are updated. The CellGrid object is a object representation of the array of Cells. Cell is a abstract superclass with subclasses unique to each type of model. These subclasses will defer mostly in how they update as rules differ for different kinds of models. Cells have a CellView object property that represents the JavaFX node in the View responsible for its representation. Each Cell has a property ArrayList<Cell> of currentNeighbors. With each step of the application, the CellGrid “updates” by calling its updateCellNextStates() method that iterates through the cellList, calling updateNextState() on each cell, saving the next state it should switch to. Finally, CellGrid calls updateCellStates() in which the list is iterated through again changing the currentState to the saved value nextState. This update causes a domino effect that updates the View and then the Model and then the SimulationApplication, effectively creating a continuously updating simulation.
#III. User Interface
#IV. Design Details
##CellState
####Purpose:
CellState is an enumeration class unique to each model that denotes the different kinds of cell states possible for a cell in that model.
##Cell
####Purpose:
Cell is an abstract superclass that will have subclasses unique to each type of model. The cellGrid will be made up of these cells.
####Instance Variables:
* currentState: the Cell’s current CellState, options as made available by the CellState enumeration class
* nextState: the Cell’s next CellState. Saving this as its own variable is important as to determine all cell’s next states before updating all of the cells at the end of determining each cell’s nextState
* currentNeighbors: an ArrayList<Cell> updated with every grid step to make it easier to identify the rule to apply to the cell
* row: this integer identifies this Cell’s row in the CellGrid
* column: this integer identifies this Cell’s row in the CellGrid
* CellView: the visual representation object of the cell

####Classes:
* updateState(): This method changes the cell’s currentState to its next state. This method is called upon after all cell’s nextStates have been identified. This method changes the visual representation of the cell
* updateNextState(): this is a series of if and else statements unique to the type of Cell in the model, the method processes the Cell’s neighbors’ currentStates to determine its next state, saving it in the variable next state. This method does not affect the visual representation of the Cell.
* setCurrentState(): Setter method for currentState, called by updateState()
* setNextState(): Setter method for nextState, called by updateNextState()
* setCurrentNeighbors(): Setter method for setCurrentNeighbors, called by the Grid after cells are updated.
* setRow(): setter method for row
* setColumn(): setter method for column
* getCurrentState(): getter method for currentState
* getNextState(): getter method for nextState
* getCurrentNeighbors(): getter method for currentNeighbors
* getRow(): getter method for row
* getColumn(): getter method for row

##CellView
####Purpose:
CellView
is the visual representation of a Cell and is an instance variable of a Cell object
####Instance Variables:
* currentImage: the JavaFX node associated with this Cell
* nextImage: the JavaFX node associated with the Cell’s next state
####Classes:
* getCurrentImage(): getter method for currentImage
* getNextImage(): getter method for nextImage
* setCurrentImage(): setter method for currentImage
* setNextImage(): setter method for nextImage
##CellGrid
####Purpose:
The CellGrid superclass is an object responsible for holding and updating the list of Cells of the model in order. Its model subclasses will generally only affect the setUp() method.
####Instance Variables:
* cellList: a 2D ArrayList<ArrayList<Cell>> representing the grid of cells in the simulation
* rows: the number of rows in the grid
* columns: the number of columns in the grid
####Classes:
* updateCellStates(): loops through the cellList and updates each cell to change currentState to nextState and make nextState null (calling updateState() on each cell)
* upateCellNextStates(): loops through cell list and calls updateNextState() on each cell
* updateCellNeighbors(): loops through cell list after update and sets cell’s currentNeighbors list to be new neighbors
* setUp(): sets up grid of appropriate size with random starting point with correct type of cell for model (dependent on type of model)
* findCellNeighbors(): this method will be called in updateCellNeighbors to generate ArrayList<Cell> of new neighbors
* getCellAt(): getter method to index specific Cell
* getCellList: getter method for cellList
* setCellList: setter method for cellList
* getRows:getter method for rows
* setRows: setter method for rows
* getColumns: getter method for columns
* setColumns: setter method for columns
##View:
####Purpose:
The View object is responsible for the visual representation of the CellGrid and integrating the appropriate cellView objects into it.
####Instance Variables:
* gridView: the JavaFX node responsible for the visual representation of the grid
* cellViewList: an 2D arraylist of JavaFX nodes of CellView objects corresponding to cells
####Classes:
* updateGridView(): updates the nodes of the grid representing the cells by calling updateCellViewList()
* updateCellViewList(): iterates through list and changes image node for changed Cells
##Model
####Purpose:
Model is a abstract superclass that allows for the simulation of different kinds of models.
####Instance Variables:
* modelName: the name of the model as denoted by the enumeration ModelTypes
* modelView: the view object associated with the Model

####Classes:
* initializeGrid(): creates all of the JavaFX objects to run the simulation
* startGrid(): begins the simulation
* gridStep(): updates the simulation
* endGrid(): ends the simulation

##ModelTypes
####Purpose: 
An enumeration class that denotes the different kinds of models.
##SimulationApplication
####Purpose:
This class extends the JavaFX class object and begins the model/creates Model object based on the ModelTypes
####Classes:
startModel(): starts the model based on denoted type

##USE CASES
1. To update the middle cell, the Cell subclass of the Game of Life model would have a update() function that would check if the Cell currentState was 'populated' or 'empty'. Then it would get the Cell's currentNeighbors and iterate through them before setting the nextState to the appropriate one.
2. The same would happen at the edge case because the cellGrid handles adding the valid neighbors to that list through indexing and condiitons.
3. This would result from a "step()" which would lead to a updateCellNextStates() call in cellGrid and then an updateCellStates() in the cellGrid which would then change each of the Cell's JavaFX nodes.
4. This would require to parse the XML file for that value and then setting it in the update() method of the appropriate Cell subclass.
5. Changing the Model type in SimulationApplication to the Wator enum.


#V. Design Considerations
#VI. Team Responsibilities