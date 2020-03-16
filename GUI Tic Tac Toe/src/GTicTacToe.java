import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/* 
*This is a Tic Tac Toe game that has a GUI
*Author: Omar Nassar
*Date: September 21, 2018
*/

public class GTicTacToe implements ActionListener {//ActionListener makes it so that the button can perform an action

	JFrame frame = new JFrame("Tic Tac Toe");
	JButton[][] button = new JButton[3][3];	//The 3x3 grid
	int[][] board = new int[3][3];			//board for keeping track of moves
	final int BLANK = 0;					//blank square
	final int X_MOVE = 1;					//Marking X on the grid and board
	final int O_MOVE = 2;					//Marking O on the grid and board
	final int X_TURN = 0;					//Signaling X's turn
	final int O_TURN = 1;					//Signaling O's turn
	int turn = X_TURN;						//Starting with X's turn
	int combo = 0;							//Color combo for end starts as a tie
	Container center = new Container();		//Container for center grid
	Container south = new Container();		//Container for south grid
	JLabel xLabel = new JLabel("X wins: 0");//X Wins label 
	JLabel oLabel = new JLabel("O wins: 0");//O Wins label
	JButton xChangeName = new JButton("Change X's name.");	//Button for changing X's name
	JButton oChangeName = new JButton("Change O's name.");	//Button for changing O's name
	JButton winReset = new JButton("Reset Wins");			//Button for resetting wins
	JButton resetAll = new JButton("Reset All");				//Button for resetting everything
	JLabel placeHolder = new JLabel("");					//Empty placeholder
	JLabel placeHolder2 = new JLabel("");					//Another empty placeholder
	JTextField xChangeField = new JTextField();				//Field for changing X's name
	JTextField oChangeField = new JTextField();				//Field for changing O's name
	Container north = new Container();						//Container for north grid
	String xPlayerName = "X";								//Starting xPlayerName as X
	String oPlayerName = "O";								//Starting oPlayerName as O
	int xwins = 0;											//Keeping track of X's wins
	int owins = 0;											//Keeping track of O's wins
	JButton playAgain = new JButton("Play again?");			//Button for playing again
	JLabel turnDisplay = new JLabel(xPlayerName + "'s Turn");//Displaying who's turn it is

	public GTicTacToe() {
		frame.setSize(500, 500);							//Setting window default size
		frame.setLayout(new BorderLayout());				//Defining layout for frame
		center.setLayout(new GridLayout(3, 3));				//Defining center container layout
		south.setLayout(new GridLayout(1, 3));				//Defining south layout
		for (int i = 0; i < button.length; i++) {			//Creating buttons for center
			for (int j = 0; j < button[0].length; j++) {
				button[j][i] = new JButton();
				center.add(button[j][i]);
				button[j][i].addActionListener(this);
			}
		}
		frame.add(center, BorderLayout.CENTER);				//Adding center to the frame
		frame.add(south, BorderLayout.SOUTH);				//Adding south to the frame
		north.setLayout(new GridLayout(3, 3));				//Size of north
		north.add(xLabel);									//X's win count added to north
		north.add(winReset);								//Win reset button added to north
		winReset.addActionListener(this);					//Adding action listener to win reset
		north.add(oLabel);									//Adding O's win count to north
		north.add(xChangeName);								//Adding X's change name button to north
		xChangeName.addActionListener(this);				//Adding action listener to xChangeName
		north.add(placeHolder);								//Adding placeholder between xChangeName and oChangeName
		north.add(oChangeName);								//Adding oChangeName to north
		oChangeName.addActionListener(this);				//Action action listener for oChangeName
		north.add(xChangeField);							//Adding xChangeField to north
		north.add(placeHolder2);							//Placeholder between xChangeField and oChangeField
		north.add(oChangeField);							//Adding oChangeField to north
		frame.add(north, BorderLayout.NORTH);				//Adding north to the frame
		south.add(turnDisplay);								//Adding turnDisplay to south
		south.add(resetAll);								//Adding resetAll button to south
		south.add(playAgain);								//Adding playAgain to south
		playAgain.addActionListener(this);					//Action listener for playAgain
		resetAll.addActionListener(this);					//Action listener for resetAll

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Ending program when exiting frame
		frame.setVisible(true);									//Making frame visible
		playAgain.setVisible(false);							//Hiding playAgain
		winReset.setVisible(false);								//Hiding winReset

	}

	public static void main(String[] args) {
		new GTicTacToe();
	}

	@Override
	public void actionPerformed(ActionEvent event) {//Action listener
		JButton current;
		boolean gridButton = false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				if (event.getSource().equals(button[j][i])) {//For clicking buttons
					current = button[j][i];
					if (board[j][i] == BLANK && checkTie() == false && checkWin(X_MOVE) == false && checkWin(O_MOVE) == false) {//If game isn't over then this action listener works
						xChangeName.setVisible(false);
						oChangeName.setVisible(false);
						xChangeField.setVisible(false);
						oChangeField.setVisible(false);
						winReset.setVisible(false);
						gridButton = true;
						if (turn == X_TURN) {//If it's X's turn
							current.setText("X");
							board[j][i] = X_MOVE;
							turn = O_TURN;
							turnDisplay.setText(oPlayerName + "'s Turn");
						} else {//If it's O's turn
							current.setText("O");
							board[j][i] = O_MOVE;
							turn = X_TURN;
							turnDisplay.setText(xPlayerName + "'s Turn");
						}
						if (checkWin(X_MOVE) == true) {//If X Wins
							xwins++;
							xLabel.setText(xPlayerName + " wins: " + xwins);
							turnDisplay.setText(xPlayerName + " Wins!");
							playAgain.setVisible(true);
							winReset.setVisible(true);
							setColor(combo);
						} else if (checkWin(O_MOVE) == true) {//If O wins
							owins++;
							oLabel.setText(oPlayerName + " wins: " + owins);
							turnDisplay.setText(oPlayerName + " Wins!");
							playAgain.setVisible(true);
							winReset.setVisible(true);
							setColor(combo);
						} else if (checkTie() == true) {
							turnDisplay.setText("It's a tie.");
							playAgain.setVisible(true);
							winReset.setVisible(true);
							setColor(0);
						}
					}
				}
			}
		}
		if (gridButton == false) {
			if (event.getSource().equals(xChangeName) == true) {//If xChangeName is clicked
				if (xChangeField.getText().equals("")) {//If empty
					xPlayerName = "X";
				}
				else {
					xPlayerName = xChangeField.getText();
				}
				xLabel.setText(xPlayerName + " wins: " + xwins);
				turnDisplay.setText(xPlayerName + "'s Turn");
				xChangeName.setVisible(false);
				xChangeField.setVisible(false);
			} else if (event.getSource().equals(oChangeName) == true) {//If oChangeName is clicked
				if (oChangeField.getText().equals("")) {//If empty
					oPlayerName = "O";
				}
				else {
					oPlayerName = oChangeField.getText();
				}
				oLabel.setText(oPlayerName + " wins: " + owins);
				oChangeName.setVisible(false);
				oChangeField.setVisible(false);
			}
		}
		if (event.getSource().equals(playAgain)) {//If playAgain button is clicked
			clearBoard();
			playAgain.setVisible(false);
			winReset.setVisible(false);
			turnDisplay.setText(xPlayerName + "'s Turn");
			xChangeName.setVisible(true);
			oChangeName.setVisible(true);
			xChangeField.setVisible(true);
			oChangeField.setVisible(true);
		}
		if (event.getSource().equals(resetAll)) {//If reset all button is clicked
			clearBoard();
			xPlayerName = "X";
			oPlayerName = "O";
			turnDisplay.setText(xPlayerName + "'s Turn");
			xChangeName.setVisible(true);
			oChangeName.setVisible(true);
			xChangeField.setVisible(true);
			oChangeField.setVisible(true);
			winReset.setVisible(false);
			playAgain.setVisible(false);
			xwins = 0;
			owins = 0;
			xLabel.setText(xPlayerName + " wins: " + xwins);
			oLabel.setText(oPlayerName + " wins: " + owins);
			xChangeField.setText("");
			oChangeField.setText("");
		}
		if (event.getSource().equals(winReset)) {//If win reset button is clicked
			xwins = 0;
			owins = 0;
			xLabel.setText(xPlayerName + " wins: " + xwins);
			oLabel.setText(oPlayerName + " wins: " + owins);
			winReset.setVisible(false);
		}
	}

	public boolean checkWin(int player) {// 8 methods for checking wins
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {// top row
			combo = 1;
			return true;
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {// top left to bottom right
																						// diagonal
			combo = 2;
			return true;
		}
		if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {// middle row
			combo = 3;
			return true;
		}
		if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {// bottom row
			combo = 4;
			return true;
		}
		if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {// left column
			combo = 5;
			return true;
		}
		if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {// middle column
			combo = 6;
			return true;
		}
		if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {// right column
			combo = 7;
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {// top right to bottom left
																						// diagonal
			combo = 8;
			return true;
		}
		return false;
	}

	public boolean checkTie() {// checking for ties
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				if (board[row][column] == BLANK) {
					return false;
				}
			}
		}
		return true;
	}

	public void clearBoard() {//Clearing the board
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board[0].length; b++) {
				board[a][b] = BLANK;
				button[a][b].setText("");
			}
		}
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				button[row][column].setBackground(new JButton().getBackground());
			}
		}
		turn = X_TURN;
	}

	public void setColor(int combo) {//Coloring squares for wins and ties
		if (combo == 1) {
			button[0][0].setBackground(Color.GREEN);
			button[0][1].setBackground(Color.GREEN);
			button[0][2].setBackground(Color.GREEN);
		} else if (combo == 2) {
			button[0][0].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[2][2].setBackground(Color.GREEN);
		} else if (combo == 3) {
			button[1][0].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[1][2].setBackground(Color.GREEN);
		} else if (combo == 4) {
			button[2][0].setBackground(Color.GREEN);
			button[2][1].setBackground(Color.GREEN);
			button[2][2].setBackground(Color.GREEN);
		} else if (combo == 5) {
			button[0][0].setBackground(Color.GREEN);
			button[1][0].setBackground(Color.GREEN);
			button[2][0].setBackground(Color.GREEN);
		} else if (combo == 6) {
			button[0][1].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[2][1].setBackground(Color.GREEN);
		} else if (combo == 7) {
			button[0][2].setBackground(Color.GREEN);
			button[1][2].setBackground(Color.GREEN);
			button[2][2].setBackground(Color.GREEN);
		} else if (combo == 8) {
			button[0][2].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[2][0].setBackground(Color.GREEN);
		} else if (combo == 0) {
			for (int row = 0; row < board.length; row++) {
				for (int column = 0; column < board.length; column++) {
					button[row][column].setBackground(Color.RED);
				}
			}
		}
	}

}
