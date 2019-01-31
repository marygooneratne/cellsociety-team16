Part 1
1. The rules of the simulation are hidden away from the
displaying and visualization of the grid. The grid only sees what
cells to place on the screen, while how those cells were decided are hidden
completely from it. The grid sees nothing except for what cells to place and where.
The different simulations of cells are also hidden away from the visualization. It does not know
of the existence of several simulation types, rather, it simply displays the cells on the 
screen. 
2. I intend to build an abstract simulation class that several types of simulation classes (such as Fire simulation
Game of Life simulation, etc.) can inherit from and implement the rules in their own specific way. The grid itself will be 
private so as not to be seen by other classes, unless a getter method is used to pass in the correct grid object to be visualized.
3. I am making the grid closed off of other classes and making the visualization class closed as well, so as to allow for more simulations
and more features to be easily added on to the project at a later time. I want my code to be adaptable and changeable without having
to edit existing code. 
4. Exceptions may be if the cell tries to access a neighbor that doesnt exist (such as if the cell is at an edge of the grid). I will handle this by
checking whether the cell is an edge cell before calling its neighbors.
5. I think my design is good in the sense that it is adaptable and easily added to because all classes have their own functionality and
power, rather than everything simply being combined into one main class. Rather than this, my design implies all classes to have thier
own methods and variables, with these objects being passed into eachother's classes to execute the proper action.
Part 2
1 & 2. I can minimize dependencies between classes by thinking how to pass values and objects between classes without unneccesarily passing things in.
I want to be able to transfer data that is trustable, and also useful, rather than simply passing in a ton of parameters that will do only a few useful things.
Thus to avoid this I will design the code so that every class has its few methods and  variables that can be retrieved through getter methods.
Part 3
2. I am most excited to work on the rules of how the cells will update and how to go about updating all of them in one pass.