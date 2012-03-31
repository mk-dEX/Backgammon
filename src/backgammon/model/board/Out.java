package backgammon.model.board;

import backgammon.model.player.Player;

public class Out extends Bar {

	public int removeChecker(Player player) {
		
		if (this.isEmpty()) {
			return -1;
		}
		
		int index = this.checkers.size() - 1;
		
		this.checkers.remove(index);
		
		return index;
	}
	
}
