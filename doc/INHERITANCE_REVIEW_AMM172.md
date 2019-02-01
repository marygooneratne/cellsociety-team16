# Inheritance Review Questions (mmg53)
## PART 1
1.	What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
As the visualization section, I expect my design to hide most of its functions from other areas of the program. The
simulation section should never have to “see” what my section is doing with the information that is passed to it.

2.	What inheritance hierarchies are you intending to build within your area and what behavior are they based around?
I was planning on making an abstract class for the normal scene configuration that could then be used to make new scenes
that would have different settings to speify outside of pause, play, and speed up.

3.	What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism
you are creating?


4.	What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
A possible error could be an invalid grid size entered into the program, such as a negative number or a float. A way to
handle that would be to throw an error: “Invalid grid size, must be a positive integer” to make it clear to the user.  

We only go this far because we started asking Professor Duvall specific questions about the assignment.

