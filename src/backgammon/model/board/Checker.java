package backgammon.model.board;

import backgammon.model.player.Player;

public class Checker {

	private Player owner;
	
	public Checker(Player player) {
		this.owner = player;
	}
	
	public Player getOwner() {
		return this.owner;
	}
}
