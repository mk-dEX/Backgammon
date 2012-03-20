package backgammon.model.board;

import backgammon.model.interfaces.IPlayer;

public class Bar extends Point {

	public boolean isFull() {
		return false;
	}
	
	public boolean isBlockedByPlayer(IPlayer player) {
		return false;
	}
	
}
