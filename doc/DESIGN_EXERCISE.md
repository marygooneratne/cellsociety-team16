Team 16

Mary Gooneratne

Amanda Madden

Hyunjae Lee


**High Level Cell Society Design**


**1. How does a Cell know what rules to apply for its simulation?**

The rules could be part of a superclass that the different kinds of cells extend. By making the rules broader than each individual cell, parameters of the rule can be easily modified. 

**2. How does a Cell know about its neighbors? How can it update itself without affecting its neighbors update?**

There would be a separate class responsible for the grid data structure that ‘holds’ the cells. This grid can be used to create a property for each cell, such as a ‘Cell list’

**3. What is the grid? Does it have any behaviors? Who needs to know about it?**

Possible data structures: 2-D Array, 2-D ArrayList, HashMap (Cell → Neighbors), Graph. The cells only need to know about its neighbors in the grid in order to apply the correct rule. The behaviors that the grid would need include rearranging cells and updating neighbors. The data structure responsible for the grid should have private instance variables. The grid must also have an algorithm to determine the initial placement of cells and an algorithm to determine placement in empty cells.

**4. What information about a simulation needs to be the configuration file?**

The information needed would include any rule parameters required by the cells, the size of the grid, the speed of the model, potential ‘empty’ cells, and the starting ratio of cells (such as in the Schelling’s Model of Segregation).

**5. How is the GUI updated after all the cells have been updated?**

The GUI is updated by displaying each cell that was updated its new characteristics based on a key that was decided on beforehand.
