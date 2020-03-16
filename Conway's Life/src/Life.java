import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/* 
*This is and implementation of Conway's Life Simulation with a GUI
*Author: Omar Nassar
*Date: October 1, 2018
*/

public class Life implements MouseListener, ActionListener, Runnable {//Uses MouseListener, ActionListener, and Runnable in thi class

	// Variables and objects
	int cellX = 10;	//X dimension
	int cellY = 10;	//Y dimension
	boolean[][] cells = new boolean[cellX][cellY];	//Cell array
	JFrame frame = new JFrame("Life Simulation");	//Frame
	LifePanel panel = new LifePanel(cells);	//Panel with cells
	Container south = new Container();		//Container for controls
	Container north = new Container();		//Container for changing dimensions
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton clear = new JButton("Clear");
	JButton speedUp = new JButton("Speed Up");
	JButton slowDown = new JButton("Slow Down");
	JTextField cellCount = new JTextField();
	JButton change = new JButton("Change Dimensions");
	JLabel current = new JLabel("Current Dimensions: " + cellX + " by " + cellY);	//Displaying current dimensions

	boolean running = false;	//Starts not running
	int threadTime = 500;		//Default thread time

	// Constructor
	public Life() {
		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(north, BorderLayout.NORTH);
		panel.addMouseListener(this);	//Tracking clicked cells
		// north container
		north.setLayout(new GridLayout(1, 3));	//Dimension change container
		north.add(current);
		north.add(cellCount);
		north.add(change);
		change.addActionListener(this);	//Changing dimension button
		// south container
		south.setLayout(new GridLayout(1, 6));	//Controls container
		south.add(step);
		step.addActionListener(this);	//one step
		south.add(start);
		start.addActionListener(this);	//continuous steps
		south.add(stop);
		stop.addActionListener(this);	//stop stepping
		south.add(clear);
		clear.addActionListener(this);	//clear board
		south.add(speedUp);
		speedUp.addActionListener(this);	//decreasing thread time
		south.add(slowDown);
		slowDown.addActionListener(this);	//increasing thread time

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//program ends when frame is closed
		frame.setVisible(true);	//frame is visible
	}

	public static void main(String[] args) {
		new Life();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {//making cells active
		System.out.println(event.getX() + "," + event.getY());
		double width = (double) panel.getWidth() / cells[0].length;
		double height = (double) panel.getHeight() / cells.length;
		int column = Math.min(cells[0].length - 1, (int) (event.getX() / width));
		int row = Math.min(cells.length - 1, (int) (event.getY() / height));
		System.out.println(column + "," + row);
		cells[row][column] = !cells[row][column];
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent event) {//step
		if (event.getSource().equals(step)) {
			System.out.println("Step");
			step();
		}
		if (event.getSource().equals(start)) {//start
			System.out.println("Start");
			if (running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop)) {//stop
			System.out.println("Stop");
			running = false;
		}
		if (event.getSource().equals(clear)) {//clear
			System.out.println("Clear");
			running = false;
			for (int row = 0; row < cells.length; row++) {
				for (int column = 0; column < cells[0].length; column++) {
					cells[row][column] = false;
					frame.repaint();
				}
			}
		}
		if (event.getSource().equals(speedUp)) {//speeding up
			if (threadTime > 0) {//making sure thread is higher than or equal to 0
				threadTime = threadTime - 25;
				System.out.println("Speed Up");
				System.out.println(threadTime + "ms");
			} else {
				System.out.println("Already at max speed");
			}
		}
		if (event.getSource().equals(slowDown)) {//slowing down
			System.out.println("Slow Down");
			if (threadTime < 1000) {//making sure thread is lower than or equal to 1000
				threadTime = threadTime + 25;
				System.out.println("Slow Down");
				System.out.println(threadTime + "ms");
			} else {
				System.out.println("Already at minimum speed.");
			}
		}
		if (event.getSource().equals(change)) {//changing dimensions
			cellX = Integer.parseInt(cellCount.getText());
			cellY = Integer.parseInt(cellCount.getText());
			System.out.println(cellX + "," + cellY);
			current.setText("Current Dimensions: " + cellX + " by " + cellY);
			cells = new boolean[cellX][cellY];
			if (Integer.parseInt(cellCount.getText()) > 0) {
				panel.setCells(cells);
				frame.repaint();
			}
		}
	}

	@Override
	public void run() {//running frame alongside buttons
		while (running == true) {
			step();
			try {
				Thread.sleep(threadTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void step() {
		boolean[][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {//up left
				int neighborCount = 0;
				if (row > 0 && column > 0 && cells[row - 1][column - 1] == true) {
					neighborCount++;
				}
				if (row > 0 && cells[row - 1][column] == true) {//up
					neighborCount++;
				}
				if (row > 0 && column < cells[0].length - 1 && cells[row - 1][column + 1] == true) {//up right
					neighborCount++;
				}
				if (column > 0 && cells[row][column - 1] == true) {//left
					neighborCount++;
				}
				if (column < cells[0].length - 1 && cells[row][column + 1] == true) {//right
					neighborCount++;
				}
				if (row < cells.length - 1 && column > 0 && cells[row + 1][column - 1] == true) {//down left
					neighborCount++;
				}
				if (row < cells.length - 1 && cells[row + 1][column] == true) {//down
					neighborCount++;
				}
				if (row < cells.length - 1 && column < cells[0].length - 1 && cells[row + 1][column + 1]) {//down right
					neighborCount++;
				}
				// Rules of game
				if (cells[row][column] == true) {//alive
					if (neighborCount == 2 || neighborCount == 3) {
						nextCells[row][column] = true;	//alive next round
					} else {
						nextCells[row][column] = false;	//dead next round
					}
				} else {//dead now
					if (neighborCount == 3) {
						nextCells[row][column] = true;	//alive next round
					} else {
						nextCells[row][column] = false;	//dead next round
					}
				}
			}
		}
		for (int row2 = 0; row2 < cells.length; row2++) {//taking the step
			for (int column2 = 0; column2 < cells[0].length; column2++) {
				cells[row2][column2] = nextCells[row2][column2];
			}
		}
		frame.repaint();
	}

}
