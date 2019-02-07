**Duplication**
I refactored the code for the xml parsers, so that they
all extend an abstract Parser class. Before, all the parser classes
did the same action in initializing a document for the parser to parse, but
I simply created a getDocument method within the abstract class that could be called
by all parsers. I also created a makeCell method in the scenebuilder class so that
updating the grid and changing the grid could simply call the method rather than both 
initializing the cell the same way.
**Checklist**
I saw all the magic number warnings in the checklist, so went through several classes and created
private instance integers with descriptive names in order to get rid of any magic numbers
with no explanation in the code. I also got rid of some unused import statements.
**General**
Created cellbuilder class so that getting cell images does not need to happen 
in the main class. Created modelbuilder class so that the changing of the grid occurs not within the
main scenebuilder class, but rather happens in the modelbuilder class. This way, we limit
the functionality of the main class so that it is not responsible for as much. It also allows for more flexible modification 
of the code.
**Longest Method**
 Refactored the code for long methods for parser classes. Got rid of lines for all classes by simply calling
 abstract class method from newly made Parser class. This got rid of 5-7 lines of code that was shared between all of the
 parser classes. 