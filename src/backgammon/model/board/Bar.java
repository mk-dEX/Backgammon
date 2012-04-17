package backgammon.model.board;

import backgammon.model.player.Player;

public class Bar extends Point {
	
	public boolean isBlockedForPlayer(Player player) {
		return false;
	}
	
	public boolean isBlot() {
		return false;
	}
}
