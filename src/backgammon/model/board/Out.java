package backgammon.model.board;

import backgammon.model.interfaces.IPlayer;

public class Out extends Bar {

	public int removeChecker(IPlayer player) {
		
		if (this.isEmpty()) {
			return -1;
		}
		
		int index = this.checkers.size() - 1;
		
		this.checkers.remove(index);
		
		return index;
	}
	
}
