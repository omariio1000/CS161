Graph Creator

Overview:  Create a program that allows users to create a graph with vertices and edges. You will also be able to solve two graph searching problems.

Interface:  

CREATE GRAPH

You need two buttons, one for nodes, and one for edges.  You need a textfield for labeling your nodes and edges.  You also need a JPanel class that you will be able to click on.

When you click the node button, change it's color (and uncolor the edge button).  If the node button is colored, and you click in the JPanel area, create a circle that is labeled with the text from the JTextField.

When you click the edge button, change it's color (and uncolor the node button).  Click on a circle in your JPanel, and highlight it.  Click on a second circle, and create a line between them.  Label it with the text from the JTextField.

CHECK CONNECTION

Create an adjacency matrix that keeps track of all the connections in your graph.  Once you maintain an adjacency matrix, we need to be able to search through our graph.  Add two more JTextFields and a button to your current program.  The button should be labeled “Check Connection”.

When you press the "Check Connection" button, your program should read from the first JTextField and find the first Node with that label.  If there is no Node with that name, create a pop-up dialog that says that.  The same is true of the second JTextField.

If both nodes exist, check to see if there is a path from the first node to the second node.  Use the adjacency matrix, and add all the nodes that are connected to the first node to a Queue.  Keep track of all the nodes that are connected.  If the list of connected nodes contains the last node, create a pop-up dialog that says the two nodes are connected.  If the Queue is empty, create a pop-up dialog that says the two nodes are NOT connected.

TRAVELLING SALESMAN

Add a button labelled "Travelling Salesman" and a textfield to your frame. When you press the button, your program should try to find a path that visits every node starting with the one in your new textfield. Not only that, but it must find the path that costs the least of all paths through your graph.

 

Scores:  

Buttons, Textfields, Labels, Panel.  (10 points)

Create nodes visually.  (10 points)

Create edges between nodes visually.  (20 points)

Label correctly.  (10 points)

Check to see if the two nodes are connected properly.  (30 points)

Find the least cost path.  (20 points)

Output the series of nodes in the path and the cost of the path.  (10 points)

Comment your code! (10 points)

Total: 120 points
