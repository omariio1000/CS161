/**
 * This class paints the grid and the stones
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings({ "serial", "unused" })
public class Panel extends JPanel {

	
	int boardSize = 13;
	int stoneSize = 500 / boardSize;
	
	public Panel(int in) {
		boardSize = in;
		stoneSize = 500 / boardSize;
	}
	//updating boardSize and stoneSize
	public void setSize(int in) {
		boardSize = in;
		stoneSize = 500 / (boardSize + 1);
	}
	
	boolean playing;
	double width;
	double height;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Dialog", Font.BOLD, 24));
		//adding click to play
		if (!playing) {
			g.drawString("Click to play", 190, 260);
		}
		else {
			width = (double)this.getWidth() / boardSize;
			height = (double)this.getHeight() / boardSize;
			//grid lines
			for (int i = 0; i < width; i++) {
				g.drawLine((int)Math.round(i*width), 0, (int)Math.round(i*width), this.getHeight());
			}
			for (int y = 0; y < height; y++) {
				g.drawLine(0, (int)Math.round(y*height), this.getWidth(), (int)Math.round(y*height));
			}
			g.drawLine(0, this.getWidth(), this.getHeight(), this.getWidth());
			//adding a stone
			for (int j = 0; j < Board.grid.length; j++) {
				for (int i = 0; i < Board.grid.length; i++) {
					if (Board.grid[i][j] == Main.BLACK) {
						g.setColor(Color.BLACK);
					}
					else if (Board.grid[i][j] == Main.WHITE) {
						g.setColor(Color.WHITE);
					}
					if (Board.grid[i][j] != Main.EMPTY) {
						int positionX = (int) Math.round(i * width) - stoneSize / 2;
						int positionY = (int) Math.round(j * height) - stoneSize / 2;
						g.fillOval(positionX, positionY, stoneSize, stoneSize);
					}
					
				}
			}
		}
		
	}
	

	
}
