# Inheritance Review Questions (mmg53)
## PART 1
1. The design is hiding the cell values and cell behavior from the visualization. The GUI doesn't have any "knowledge" of cell behavior but merely holds the image data for the cells.
2. The major inheritance hierarchy will deal with using the same classes to implement several different kinds of simulations. Each simulation will have a unique Cell class that extends the abstract Cell class.
3. I expect the Cell class to be closed/abstract because instances of the Cell class aren't going to be made. Furthermore, functionality of the Cells and Grid will be closed and the user only needs to specify the type of simulation.
4. Creating a model that doesn't exist will throw a an error indicating the Simulation doesn't exist.
5. I think my design is good because it allows for expansion upon the design. I believe expansion and flexibility is a good measure of good. Because classes are so broad and there is a well-design inheritance hierarcy, it is very flexible and expandable.
## PART 2
1. The configuration is what sends the data to build and run the simulation and the visualization graphically depicts the simulation.
2. The simulation's starting point is dependent on the output of the configuration and the vizualization's behavior is dependent on how the simulation runs.
3. We can minimize these dependencies by encapsulating all of the "output" of each part into a singular object.
  