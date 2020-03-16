/**
 * Authored by Omar Nassar and Nabhan Abdedin
 * Wednesday June 5, 2019
 * This is a Go board game implementation in Java 
 * This is the main class with the GUI as well as clicking to add stones and the suicide rule
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Main implements ActionListener, MouseListener {

	int boardSize = 13;
	JFrame frame = new JFrame("Go");
	Panel panel = new Panel(boardSize);
	JButton nine = new JButton("9 x 9");
	JButton thirteen = new JButton("13 x 13");
	JButton nineteen = new JButton("19 x 19");
	JTextField black_score = new JTextField("Black: 0");
	JTextField white_score = new JTextField("White: 0");
	JButton pass = new JButton("Pass");
	JButton end = new JButton("End");
	JButton reset = new JButton("Reset");
	JButton undo = new JButton("<html>U<br />n<br />d<br />o</html>");
	JButton redo = new JButton("<html>R<br />e<br />d<br />o</html>");
	Container center = new Container();
	Container north = new Container();
	Container south = new Container();
	Container east = new Container();
	Container scores = new Container();
	Container buttons = new Container();
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	public static final int WHITE = 2;
	int state = BLACK;
	boolean passed;
	boolean playing = false;
	boolean first = true;
	boolean firstUndo = true;
	boolean firstRedo = true;
	int blackScore;
	int whiteScore;
	int redoCount;
	int counter = 0;
	ArrayList<int[][]> history = new ArrayList<int[][]>();
	ArrayList<int[][]> removed = new ArrayList<int[][]>();

	public Main() {
		frame.setSize(650, 775);
		frame.setLayout(new BorderLayout());
		//setting up the main board
		panel.setBackground(new Color(220, 179, 92));
		panel.setBorder(new LineBorder(Color.BLACK));
		panel.addMouseListener(this);
		center.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 500;
		c.ipady = 500;
		center.add(panel, c);
		frame.add(center, BorderLayout.CENTER);
		//board size changing buttons
		north.setLayout(new GridLayout(1, 2));
		nine.setPreferredSize(new Dimension(200, 25));
		north.add(nine);
		nine.setEnabled(true);
		nine.addActionListener(this);
		thirteen.setPreferredSize(new Dimension(200, 25));
		north.add(thirteen);
		thirteen.setEnabled(false);
		thirteen.addActionListener(this);
		nineteen.setPreferredSize(new Dimension(200, 25));
		north.add(nineteen);
		nineteen.setEnabled(true);
		nineteen.addActionListener(this);
		//score textfields
		south.setLayout(new GridLayout(2, 1));
		scores.setLayout(new GridLayout(1, 2));
		black_score.setPreferredSize(new Dimension(300, 50));
		black_score.setFont(new Font("Dialog", Font.BOLD, 24));
		scores.add(black_score);
		black_score.setEditable(false);
		black_score.setHorizontalAlignment(JTextField.CENTER);
		white_score.setPreferredSize(new Dimension(300, 50));
		white_score.setFont(new Font("Dialog", Font.BOLD, 24));
		scores.add(white_score);
		white_score.setEditable(false);
		white_score.setHorizontalAlignment(JTextField.CENTER);
		//pass end and reset buttons
		buttons.setLayout(new GridLayout(1, 3));
		pass.setPreferredSize(new Dimension(200, 50));
		pass.setFont(new Font("Dialog", Font.BOLD, 24));
		buttons.add(pass);
		pass.setEnabled(false);
		pass.addActionListener(this);
		end.setPreferredSize(new Dimension(200, 50));
		end.setFont(new Font("Dialog", Font.BOLD, 24));
		buttons.add(end);
		end.setEnabled(false);
		end.addActionListener(this);
		reset.setPreferredSize(new Dimension(200, 50));
		reset.setFont(new Font("Dialog", Font.BOLD, 24));
		buttons.add(reset);
		reset.setEnabled(false);
		reset.addActionListener(this);
		south.add(scores);
		south.add(buttons);
		//undo and redo buttons (not used)
		east.setLayout(new GridLayout(2, 1));
		undo.setPreferredSize(new Dimension(50, 25));
		undo.setFont(new Font("Dialog", Font.BOLD, 24));
		undo.setEnabled(false);
		undo.addActionListener(this);
		east.add(undo);
		redo.setPreferredSize(new Dimension(50, 25));
		redo.setFont(new Font("Dialog", Font.BOLD, 24));
		redo.setEnabled(false);
		redo.addActionListener(this);
		east.add(redo);
		frame.add(north, BorderLayout.NORTH);
		frame.add(south, BorderLayout.SOUTH);
		//frame.add(east, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//initializing grid if it's the first turn
		if (first) {
			Board.initializeGrid();
			first = false;
			playing = true;
			panel.playing = playing;
			/*int[][] temp = new int[boardSize + 1][boardSize + 1];
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp.length; j++) {
					temp[i][j] = Board.grid[i][j];
				}
			}
			history.add(temp);*/
			frame.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (playing) {
			undo.setEnabled(true);
			firstUndo = true;
			redo.setEnabled(false);
			redoCount = 0;
			nine.setEnabled(false);
			thirteen.setEnabled(false);
			nineteen.setEnabled(false);
			pass.setEnabled(true);
			end.setEnabled(true);
			//making sure the coordinates don't pass the limits of the board
			int x = e.getX();
			int y = e.getY();
			if (x < 0) {
				x = 0;
			}
			if (x > 500) {
				x = 500;
			}
			if (y < 0) {
				y = 0;
			}
			if (y > 500) {
				y = 500;
			}
			//calculating row and column from mouse click
			int row = (int) Math.round((float) x / panel.width);
			int column = (int) Math.round((float) y / panel.height);
			//only play if spot is empty and the move isn't suicidal
			if (Board.grid[row][column] == EMPTY && !suicide(row, column)) {
				addStone(row, column);
				if (passed) {
					pass.setEnabled(true);
					passed = false;
					pass.setText("Pass");
				}
				//switching color
				if (state == BLACK) {
					state = WHITE;
					// System.out.println(state);
				} else if (state == WHITE) {
					state = BLACK;
					// System.out.println(state);
				}
			}
			//updating score
			int blackCounter = 0;
			int whiteCounter = 0;
			for (int i = 0; i < Board.grid.length; i++) {
				for (int j = 0; j < Board.grid.length; j++) {
					if (Board.grid[i][j] == BLACK) {
						blackCounter++;
					} else if (Board.grid[i][j] == WHITE) {
						whiteCounter++;
					}
				}
			}
			blackScore = blackCounter + Board.blackCaptures;
			whiteScore = whiteCounter + Board.whiteCaptures;
			black_score.setText("Black: " + blackScore);
			white_score.setText("White: " + whiteScore);
			/*int[][] temp = new int[boardSize + 1][boardSize + 1];
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp.length; j++) {
					temp[i][j] = Board.grid[i][j];
				}
			}
			if (ko(temp)) {
				history.add(temp);
			}
			else {
				Board.grid = history.get(history.size() - 1);
			}*/
			//Board.printGrid(Board.grid);
			// Board.printGrid(history.get(1));
			/*if (Board.stones[row][column] != null) {
				System.out.println(Board.stones[row][column].liberties);
			}*/
			frame.repaint();
		}
	}

	//ko rule (unused)
	@SuppressWarnings("unused")
	private boolean ko(int[][] grid) {
		for (int k = 0; k < history.size() - 1; k++) {
			int sameCounter = 0;
			for (int i = 0; i < history.get(k).length; i++) {
				for (int j = 0; j < history.get(k).length; j++) {
					if (history.get(k)[i][j] == grid[i][j]) {
						sameCounter++;
					}
				}
			}
			//System.out.println(sameCounter);
			//System.out.println(Math.pow(boardSize + 1, 2));
			//System.out.println();
			if (sameCounter == Math.pow(boardSize + 1, 2)) {
				return false;
			}
		}
		return true;
	}
	
	//making sure the move isn't going to be suicidal
	private boolean suicide(int row, int column) {
		int neighbors = 0;
		//check north
		if (row == 0) {
			neighbors++;
		} else if (Board.grid[row - 1][column] != state && Board.grid[row - 1][column] != EMPTY) {
			neighbors++;
		}
		//check south
		if (row == boardSize) {
			neighbors++;
		} else if (Board.grid[row + 1][column] != state && Board.grid[row + 1][column] != EMPTY) {
			neighbors++;
		}
		//check west
		if (column == 0) {
			neighbors++;
		} else if (Board.grid[row][column - 1] != state && Board.grid[row][column - 1] != EMPTY) {
			neighbors++;
		}
		//check east
		if (column == boardSize) {
			neighbors++;
		} else if (Board.grid[row][column + 1] != state && Board.grid[row][column + 1] != EMPTY) {
			neighbors++;
		}
		
		if (neighbors == 4) {
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//making board size 9 x 9
		if (e.getSource().equals(nine)) {
			boardSize = 9;
			nine.setEnabled(false);
			thirteen.setEnabled(true);
			nineteen.setEnabled(true);
			panel.setSize(boardSize);
			Board.setSize(boardSize);
			frame.repaint();
		}
		//making board size 13 x 13
		if (e.getSource().equals(thirteen)) {
			boardSize = 13;
			nine.setEnabled(true);
			thirteen.setEnabled(false);
			nineteen.setEnabled(true);
			panel.setSize(boardSize);
			Board.setSize(boardSize);
			frame.repaint();
		}
		//making board size 19 x 19
		if (e.getSource().equals(nineteen)) {
			boardSize = 19;
			nine.setEnabled(true);
			thirteen.setEnabled(true);
			nineteen.setEnabled(false);
			panel.setSize(boardSize);
			Board.setSize(boardSize);
			frame.repaint();
		}
		//passing the turn or ending if passed twice
		if (e.getSource().equals(pass)) {
			if (passed) {
				// System.out.println("Game Ended.");
				// System.out.println();
				passed = false;
				playing = false;
				pass.setFont(new Font("Dialog", Font.BOLD, 24));
				pass.setText("Pass");
				pass.setEnabled(false);
				end.setEnabled(false);
				reset.setEnabled(true);
				undo.setEnabled(false);
				redo.setEnabled(false);
			} else if (!passed) {
				passed = true;
				pass.setFont(new Font("Dialog", Font.BOLD, 14));
				pass.setText("Pass Again? (End Game)");
				if (state == BLACK) {
					state = WHITE;
					// System.out.println(state);
				} else if (state == WHITE) {
					state = BLACK;
					// System.out.println(state);
				}
			}
		}
		//resetting the board and textfields
		if (e.getSource().equals(reset)) {
			if (boardSize == 9) {
				thirteen.setEnabled(true);
				nineteen.setEnabled(true);
			} else if (boardSize == 13) {
				nine.setEnabled(true);
				nineteen.setEnabled(true);
			} else if (boardSize == 19) {
				thirteen.setEnabled(true);
				nine.setEnabled(true);
			}
			// System.out.println("Reset.");
			// System.out.println();
			playing = false;
			black_score.setText("Black: 0");
			white_score.setText("White: 0");
			first = true;
			state = BLACK;
			pass.setEnabled(false);
			end.setEnabled(false);
			reset.setEnabled(false);
			panel.playing = playing;
			history = new ArrayList<int[][]>();
			panel.setSize(boardSize);
			Board.setSize(boardSize);
			Board.blackCaptures = 0;
			Board.whiteCaptures = 0;
		}
		//ending the game
		if (e.getSource().equals(end)) {
			// System.out.println("Game Ended.");
			// System.out.println();
			passed = false;
			playing = false;
			pass.setFont(new Font("Dialog", Font.BOLD, 24));
			pass.setText("Pass");
			pass.setEnabled(false);
			end.setEnabled(false);
			reset.setEnabled(true);
			undo.setEnabled(false);
			redo.setEnabled(false);
		}
		//undoing move (not used)
		if (e.getSource().equals(undo)) {
			passed = false;
			pass.setText("Pass");
			pass.setFont(new Font("Dialog", Font.BOLD, 24));
			if (firstUndo) {
				history.remove(history.size() - 1);
				firstUndo = false;
			}
			removed.add(history.get(history.size() - 1));
			Board.grid = history.get(history.size() - 1);
			history.remove(history.size() - 1);
			redo.setEnabled(true);
			undo.setEnabled(false);
			if (history.size() > 0) {
				undo.setEnabled(true);
			}
			if (state == BLACK) {
				state = WHITE;
			} else if (state == WHITE) {
				state = BLACK;
			}
			redoCount++;
			frame.repaint();
		}
		//redoing move (not used)
		if (e.getSource().equals(redo)) {
			undo.setEnabled(true);
			/*if (firstRedo) {
				removed.remove(0);
				firstRedo = false;
			}*/
			Board.grid = removed.get(removed.size() - 1);
			history.add(removed.get(removed.size() - 1));
			removed.remove(removed.size() - 1);
			redoCount--;
			if (redoCount == 0) {
				redo.setEnabled(false);
			}
			// redo.setEnabled(false);
			// Board.printGrid();
			frame.repaint();
		}

		frame.repaint();
	}
	//adding stone
	public void addStone(int row, int column) {
		// System.out.println(x + ", " + y);
		// System.out.println(row + ", " + column);
		// System.out.println();
		Board.addStone(row, column, state);
		// Board.printGrid(Board.grid);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}