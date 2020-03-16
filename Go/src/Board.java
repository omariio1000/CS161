/**
 * The board class holds the grid for holding the stones and contains the logic for capturing
 */

public class Board {

	static int boardSize = 13;
	static int[][] grid = new int[boardSize + 1][boardSize + 1];
	static Stone[][] stones = new Stone[boardSize + 1][boardSize + 1];
	static int blackCaptures = 0;
	static int whiteCaptures = 0;

	public Board(int in) {
		boardSize = in;
		grid = new int[boardSize + 1][boardSize + 1];
		stones = new Stone[boardSize + 1][boardSize + 1];
	}
	//setting the size of the board
	public static void setSize(int in) {
		boardSize = in;
		grid = new int[boardSize + 1][boardSize + 1];
		stones = new Stone[boardSize + 1][boardSize + 1];
	}
	//resetting the board
	public static void initializeGrid() {
		for (int i = 0; i < boardSize + 1; i++) {
			for (int j = 0; j < boardSize + 1; j++) {
				grid[i][j] = Main.EMPTY;
			}
		}
	}
	//adding a stone
	public static void addStone(int row, int column, int state) {
		grid[row][column] = state;
		Stone newStone = new Stone(row, column, state);
		// check if north is occupied or if at border
		if (row == 0) {
			newStone.liberties--;
		} else if (stones[row - 1][column] != null) {
			newStone.liberties--;
			stones[row - 1][column].liberties--;
		}
		// check if south is occupied or if at border
		if (row == boardSize) {
			newStone.liberties--;
		} else if (stones[row + 1][column] != null) {
			newStone.liberties--;
			stones[row + 1][column].liberties--;
		}
		// check if west is occupied or if at border
		if (column == 0) {
			newStone.liberties--;
		} else if (stones[row][column - 1] != null) {
			newStone.liberties--;
			stones[row][column - 1].liberties--;
		}
		// check if east is occupied or it at border
		if (column == boardSize) {
			newStone.liberties--;
		} else if (stones[row][column + 1] != null) {
			newStone.liberties--;
			stones[row][column + 1].liberties--;
		}
		//adding stone to the array of stones
		stones[row][column] = newStone;
		// System.out.println(stones[row][column].liberties + ", " +
		// newStone.liberties);
		//check all the stones and remove the ones without liberties
		checkStones();
	}

	private static void checkStones() {
		for (int i = 0; i < stones.length; i++) {
			for (int j = 0; j < stones.length; j++) {
				if (stones[i][j] != null) {
					if (stones[i][j].liberties == 0) { // && counter == 4) {
						//System.out.println("check 1");
						// grid[i][j] = Main.EMPTY;
						// stones[i][j] = null;
						//new chain of stones
						Chain chain = new Chain(stones[i][j].state);
						chain.addStone(stones[i][j]);
						//checking for stones of the same state above
						for (int k = i + 1; k < boardSize; k++) {
							if (stones[k][j] != null && stones[i][j].state == stones[k][j].state) {
								chain.addStone(stones[k][j]);
								//System.out.println("found one 1");
							} else {
								break;
							}
						}
						//checking for stones of the same state to the right
						for (int k = j + 1; k < boardSize; k++) {
							if (stones[i][k] != null && stones[i][j].state == stones[i][k].state) {
								chain.addStone(stones[i][k]);
								//System.out.println("found another 1");
							} else {
								break;
							}
						}
						//checking for stones of the same state below
						for (int k = i - 1; k >= 0; k--) {
							if (stones[k][j] != null && stones[i][j].state == stones[k][j].state) {
								chain.addStone(stones[k][j]);
								//System.out.println("found one 2");
							} else {
								break;
							}
						}
						//checking for stones of the same state to the left
						for (int k = j - 1; k >= 0; k--) {
							if (stones[i][k] != null && stones[i][j].state == stones[i][k].state) {
								chain.addStone(stones[i][k]);
								//System.out.println("found another 2");
							} else {
								break;
							}
						}
						//do the same checking as above for every stone in the chain
						for (int k = 0; k < chain.stones.size() - 1; k++) {
							int chainRow = chain.stones.get(k).row;
							int chainCol = chain.stones.get(k).column;
							//up
							for (int k1 = chainRow + 1; k1 < boardSize; k1++) {
								if (stones[k1][chainCol] != null && stones[chainRow][chainCol].state == stones[k1][chainCol].state) {
									chain.addStone(stones[k1][chainCol]);
									//System.out.println("found more 1");
								} else {
									break;
								}
							}
							//right
							for (int k1 = chainCol + 1; k1 < boardSize; k1++) {
								if (stones[chainRow][k1] != null && stones[chainRow][chainCol].state == stones[chainRow][k1].state) {
									chain.addStone(stones[chainRow][k1]);
									//System.out.println("found even more 1");
								} else {
									break;
								}
							}
							//down
							for (int k1 = chainRow - 1; k1 > 0; k1--) {
								if (stones[k1][chainCol] != null && stones[chainRow][chainCol].state == stones[k1][chainCol].state) {
									chain.addStone(stones[k1][chainCol]);
									//System.out.println("found more 2");
								} else {
									break;
								}
							}
							//left
							for (int k1 = chainCol - 1; k1 > 0; k1--) {
								if (stones[chainRow][k1] != null && stones[chainRow][chainCol].state == stones[chainRow][k1].state) {
									chain.addStone(stones[chainRow][k1]);
									//System.out.println("found even more 2");
								} else {
									break;
								}
							}
						}
						//removing chains without liberties
						if (chain.getLiberties() == 0) {
							for (int k = 0; k < chain.stones.size(); k++) {
								//System.out.println("removed " + chain.stones.get(k).row + ", " + chain.stones.get(k).column);
								grid[chain.stones.get(k).row][chain.stones.get(k).column] = Main.EMPTY;
								stones[chain.stones.get(k).row][chain.stones.get(k).column] = null;
								//increasing score
								if (chain.state == Main.BLACK) {
									whiteCaptures++;
								}
								else {
									blackCaptures++;
								}
							}
						}
					}
				}
			}
		}
	}
	
	//printing the grid to the console
	public static void printGrid(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			String line = "";
			for (int j = 0; j < grid.length; j++) {
				line += grid[j][i] + " ";
			}
			System.out.println(line);
		}
		System.out.println();
	}

}
