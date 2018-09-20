# Barren Land Analysis

This program will search a grid 400 meters wide by 600 meters long and find each
discrete area of land in square meters that is considered fertile.  Prior to the search
the user will input rectangles in the form of four numbers representing two x, y 
coordinates which identify the lower left corner and upper right corners of the rectangle
The program will then output each discrete area from smallest to largest.
 
This program contains two classes: the BarrenLandAnalysis class and the LandGrid class

#### BarrenLandAnalysis Class
The BarrenLandAnalysis class is the main application class.  It will accept input from the
terminal or command line.  Any exceptions that might be thrown by the LandGrid class 
are caught and handled here.

#### LandGrid Class
The LandGrid class is where all the major work is performed searching the grid.
There are two constructors one which simply takes the rectangle coordinates and another which
allows for a custom sized grid.  This class also has four public methods.

##### getFertileAreas
The getFertileAreas method will return the each discrete area of fertile land.

##### clearBarren
The clearBarren method will remove all barren land from the grid turning the grid
entirely into fertile land.

##### setBarren
The set barren method will set areas of barren land based on the string input.
This method throws two exceptions ArrayIndexOutOfBoundsException and NumberFormatException
in the case that invalid data is passed into the method

##### displayGrid
This method will display the grid on the terminal.  This method was written for visualization
purposes during the development of this application, but may have future utility.

####JUnit 4 Test
A JUnit 4 Test has also been provided which tests the getFertileAreas, clearBarren, and
setBarren methods.
