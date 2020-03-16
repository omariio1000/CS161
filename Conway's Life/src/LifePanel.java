import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/* 
*This is the panel for Conway's Life simulation. This draws the grid and cells
*Author: Omar Nassar
*Date: October 1, 2018
*/

@SuppressWarnings("serial")
public class LifePanel extends JPanel {

	boolean[][] cells;
	double width;
	double height;

	public LifePanel(boolean[][] in) {//taking in cells from Life
		cells = in;
	}
	public void setCells(boolean[][] in) {//taking in cells from Life
		cells = in;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double) this.getWidth() / cells[0].length;
		height = (double) this.getHeight() / cells.length;

		// Draw the cells
		g.setColor(Color.BLUE);
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				if (cells[row][column] == true) {
					g.fillRect((int) Math.round(column * width), (int) Math.round(row * height),
							(int) Math.round(width + 1), (int) Math.round(height + 1));
				}
			}
		}
		// Draw the grid
		g.setColor(Color.BLACK);
		for (int x = 0; x < cells[0].length + 1; x++) {
			g.drawLine((int) Math.round(x * width), 0, (int) Math.round(x * width), this.getHeight());
		}
		for (int y = 0; y < cells.length + 1; y++) {
			g.drawLine(0, (int) Math.round(y * height), this.getWidth(), (int) Math.round(y * height));
		}
	}

}
