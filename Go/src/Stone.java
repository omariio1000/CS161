/**
 * Each stone class holds a row, column, state (color) and has 4 liberties by default
 */

public class Stone {

	int state;
	int liberties;
	int row;
	int column;
	
	public Stone(int row, int column, int state) {
		this.state = state;
	    liberties = 4;
	    this.row = row;
	    this.column = column;
	}
	
	
}