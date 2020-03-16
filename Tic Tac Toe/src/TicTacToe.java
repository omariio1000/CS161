import java.util.Scanner;

/* 
*This is a Tic Tac Toe game that doesn't run using a GUI
*Author: Omar Nassar
*Date: September 17, 2018
*/

public class TicTacToe {
	
	int[][] board = new int[3][3];
	final int BLANK = 0; //blank spot on board
	final int X_MOVE = 1; //marking x on board
	final int O_MOVE = 2; //marking o on board
	final int X_TURN = 0; //signifying x turn
	final int O_TURN = 1; //signifying o turn
	int turn = X_TURN; //starting with x turn
	int X_wins = 0;
	int O_wins = 0;
	
	Scanner scanner;
	String input = "";
	
	public TicTacToe() {
		scanner = new Scanner(System.in);
		boolean stillPlaying = true;
		while (stillPlaying == true) {//making sure either first round or player wanted to play again
			while (checkWin(X_MOVE) == false && checkWin(O_MOVE) == false && checkTie() == false) {//making sure that nobody won and no tie
				printBoard();
				input = scanner.nextLine();
				if (input.length() != 2) {//checking for correct input length
					System.out.println("Enter a letter followed by a number");
				}
				else if (input.charAt(0) != 'a' && 
						 input.charAt(0) != 'b' &&
						 input.charAt(0) != 'c') {//making sure input starts with correct letter
					System.out.println("Row must be an a, b, or c.");
				}
				else if (input.charAt(1) != '1' && 
						 input.charAt(1) != '2' &&
						 input.charAt(1) != '3') {//makings sure has correct numbers
					System.out.println("Column must be a 1, 2, or 3..");
				}
				else {
					int row = input.charAt(0) - 'a';
					int column = input.charAt(1) - '1';
					if (board[row][column] == BLANK ) {//making sure no pieces already there
						if (turn == X_TURN) {
							board[row][column] = X_MOVE;
							turn = O_TURN;
						}
						else {
							board[row][column] = O_MOVE;
							turn = X_TURN;
						}
					}
					else {//when cell is occupied
						System.out.println("There is a piece there!");
					}
				}
			}
			if (checkWin(X_MOVE) == true) {//x winning
				printBoard();
				System.out.println("X wins!");
				X_wins++;
				System.out.println("X Wins: " + X_wins);
				System.out.println("O Wins: " + O_wins);
			}
			else if (checkWin(O_MOVE) == true) {//o winning
				printBoard();
				System.out.println("O wins!");
				O_wins++;
				System.out.println("X Wins: " + X_wins);
				System.out.println("O Wins: " + O_wins);
			}
			else {//tie
				printBoard();
				System.out.println("There was a tie.");
				System.out.println("X Wins: " + X_wins);
				System.out.println("O Wins: " + O_wins);
			}
			System.out.println("Would you like to play again?"); //determining whether they want to play again
			String playAgain = "";
			playAgain = scanner.nextLine();
			if (playAgain.equals("yes")) {
				turn = X_TURN;
				stillPlaying = true;
				for (int row = 0; row < board.length; row++) {
					for (int column = 0; column < board.length; column++) {
						board[row][column] = BLANK; //clearing board
					}
				}
			}
			else {
				stillPlaying = false;
				System.out.println("Thanks for playing!");
			}
			
		}
	}

	public static void main(String[] args) {
		new TicTacToe();

	}
	
	public void printBoard() {//printing the board
		System.out.println(" \t1\t2\t3");
		for (int row = 0; row < board.length; row++) {
			String output = (char)('a' + row) + "\t";
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == BLANK) {
					output += "\t";
				}
				else if (board[row][column] == X_MOVE) {
					output += "X\t";
				}
				else if (board[row][column] == O_MOVE) {
					output += "0\t";
				}
			}
			System.out.println(output);
		}
	}
	
	public boolean checkWin(int player) {//8 methods for checking wins
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {//top row
			return true;
		}
		if (board[0][0] == player && board [1][1] == player && board [2][2] == player) {//top left to bottom right diagonal
			return true;
		}
		if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {//middle row
			return true;
		}
		if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {//bottom row
			return true;
		}
		if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {//left column
			return true;
		}
		if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {//middle column
			return true;
		}
		if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {//right column
			return true;
		}
		if (board[0][2] == player && board [1][1] == player && board [2][0] == player) {//top right to bottom left diagonal
			return true;
		}
		return false;
	}
	
	public boolean checkTie() {//checking for ties
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				if (board[row][column] == BLANK) {
					return false;
				}
			}
		}
		return true;
	}
	
}
