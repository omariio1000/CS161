/**
 * Each chain is an arraylist of stones and has a state
 */

import java.util.ArrayList;

public class Chain {
	
	public int state;
	public ArrayList<Stone> stones;
	public Chain(int inState) {
		stones = new ArrayList<Stone>();
		state = inState;
	}
	//returning total liberties
	public int getLiberties() {
		int total = 0;
		for (Stone stone : stones) {
			total += stone.liberties;
		}
		return total;
	}
	//adding a stone to the chain
	public void addStone(Stone stone) {
		//
		if (!stones.contains(stone)) {
			stones.add(stone);
		}
	}	
}