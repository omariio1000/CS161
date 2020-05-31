#Guessing Game

Interface

The computer will pick a random number between 0 and 50, inclusive.  The user will guess a number, and the computer will tell you if it is too high, too low, or correct.  If it is correct, it will display how many guesses it took you, and ask if you want to play again.

 

Implementation

Create a variable which will hold the random number and use Math.random() to create it.  (10 points)  Use System.out.println() to print things out to the command line.  (10 points)  Use the following to read in a line (10 points):

 

String input;

Scanner scanner = new Scanner(System.in);

input = scanner.nextLine();

System.out.println(input);

 

Convert the string into an int using Integer.parseInt() and compare it to the random number.  (10 points)

If the guess is correct, print out the number of guesses (use a variable to keep track) and ask if they want to play again.  If so, restart the game.  (10 points)

 

Total: 50 points
