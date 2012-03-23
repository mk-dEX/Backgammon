package backgammon.model.board;

import backgammon.model.interfaces.IPlayer;

public class Bar extends Point {
	
	public boolean isBlockedForPlayer(IPlayer player) {
		return false;
	}
	
}
