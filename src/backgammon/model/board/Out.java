package backgammon.model.board;

import backgammon.model.interfaces.IPlayer;

public class Out extends Bar {

	public boolean removeChecker(IPlayer player) {
		
		if (this.isEmpty()) {
			return false;
		}
		
		this.checkers.remove(this.checkers.size() - 1);
		
		return true;
	}
	
}
