package backgammon.model.board;

import backgammon.model.interfaces.IPlayer;

public class Checker {

	private IPlayer owner;
	
	public Checker(IPlayer player) {
		this.owner = player;
	}
	
	public IPlayer getOwner() {
		return this.owner;
	}
}
