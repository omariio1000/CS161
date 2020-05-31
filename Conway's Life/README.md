Conway’s Life

Overview:  Create a simulation of “Conway’s Life”.  This takes place on a square grid of at least 25 by 25.  All adjacent cells (including diagonal) are considered neighbors.

 

Rules:  

If alive

0 or 1 neighbors - dies of loneliness

2 or 3 neighbors - survives to next round

4 or more neighbors - dies of overcrowding

If dead

3 neighbors - becomes populated

 

Every cell checks its neighbors and calculates whether it will be alive in the next generation.  All cells are then changed, and the calculation occurs again.

 

Interface:  The program should have a JPanel that is the drawing surface. (10 points) You should be able to click on an individual cell and change it from dead to alive and vice versa. (10 points) There should be three buttons in addition to the panel. (10 points) The Step button should show the next generation and stop. (The general algorithm is listed in the overview.) Start should continually step through the generations and display them.  Stop should halt a running simulation.  (10 points)

 

Implementation:  You should use a two dimensional array (or something similar) to keep track of the current state. (10 points) The hardest part of this simulation is making it work with boundary conditions (the edges of the grid).  (10 points)  Try and figure out how to deal with the edges of the grid, then how to deal with the cells in the interior.

 

The second tricky part is drawing to the JPanel.  Figuring out where and what to draw is somewhat difficult.  Overwrite the JPanel's paintComponent method.  Use the Graphics object to draw what you need to the screen. (10 points)

 

Comment your code! (10 points)

 

Total: 80 points

 

Additional Help:

Java swing components and layout managers:

http://www.beginner-java-tutorial.com/java-swing-tutorial.html
