Traffic Simulation

 

Multiple Classes

 

Why are we doing this?

We're looking at multiple classes in a bit more depth, especially the property of polymorphism.  Each of your subclasses will be able to draw themselves, which makes the organization of your program much easier.

 

Overview:  Create a program that simulates traffic on a four-lane highway.  There will be three types of cars: semis, SUVs, and sports cars.  Semis have the largest footprint, accelerate the slowest, and have the lowest top speed.  SUVs will be moderate for all three properties.  Sports cars are the smallest, accelerate the fastest, and have the highest top speed.

 

Interface:  Create a panel that draws each step of the traffic simulation.  Your interface should have three buttons, one for each type of vehicle.  The buttons will add a car of the corresponding type onto the road, which will not overlap with any existing cars.  Add a label that updates throughput, also.  (# of cars off the end divided by time). (10 points for interface and proper throughput)

 

Implementation:  There should be a vehicle class that contains the size variables for each vehicle (x, y, width, height) and its top speed.  It will also have a generic paintComponent method. Three more classes should extend the vehicle class: semi, suv, and sports.  Each of these classes will have the individual car properties.  Each of these will also have their own specific paintComponent method which draws their individual shapes to the screen. (10 points)  

 

You should extend the JPanel class, much like we did for Life, and have it draw the roadway, after which it should tell each car to draw itself.  (10 points)  

 

A controller class should keep track of each vehicle, and check to see if they will collide in the next step of the simulation. The vehicle should add its current speed to its position, if able. Any car that goes off the right end of the road should reappear at the left end. (10 points)

 

If it will collide with the car ahead of it, the car should check to see if it can switch lanes to its immediate left or right, and otherwise will slow down.  (10 points)  

 

Comment your code! Don’t forget your name, the date, and what your class does.  (10 points)

 

Total: 60 points
