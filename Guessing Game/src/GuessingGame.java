import java.util.Scanner;

/*
 * This is a game that will have the user guess a number between 1 and 10
 * Author: Omar Nassar
 * Date: September 7th 2018
 */


public class GuessingGame {

	private Scanner scanner;

	//Constructor for Guessing Game
	public GuessingGame() {
		boolean stillPlaying = true;
		
		while (stillPlaying == true) {
			System.out.println("Welcome to the guessing game! Guess the number below.");
			int randomNum = (int)(Math.random() * 51);
			scanner = new Scanner(System.in);
			int guess = -1;
			int guessNumber = 0;
			while(guess != randomNum) {
					try {
				String input = scanner.nextLine();
				guess = Integer.parseInt(input);
				if (guess > 50 || guess < 0) {
					System.out.println("Invalid input. Try again.");
				}
				
				else if (guess == randomNum) {
					guessNumber = guessNumber + 1;
					System.out.println("You are correct!");
					System.out.println("You took " + guessNumber + " tries.");
					System.out.println();
				}
				else if (guess > randomNum) {
					System.out.println("Number is too high. Try again.");
					guessNumber = guessNumber + 1;
				}
				else if (guess < randomNum) {
					System.out.println("Number is too low. Try again.");
					guessNumber = guessNumber + 1;
				}
			}

		catch(Exception E) {
			System.out.println("Invalid input. Try again.");
		}	
			}
			System.out.println("Would you like to play again?");
			String yesno = scanner.nextLine();
			if (yesno.equals("yes") || yesno.equals("y") ) {
				stillPlaying = true;
			}
			else {
				stillPlaying = false;
				System.out.println("Goodbye! Come play again later!");
			}
			
			
		}
		
		
	
	}
	
	//Main method to start the program
	public static void main(String[] args) {
		new GuessingGame();

	}

}
