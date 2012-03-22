package backgammon.model.player;

import backgammon.model.interfaces.IPlayer;

public class Move {

	private int id;
	private IPlayer player;
	
	private int indexFrom;
	private int indexTo;
	
	public Move(int id, int from, int to) {
		this.id = id;
		this.indexFrom = from;
		this.indexTo = to;
	}
	
	public Move(IPlayer player, int from, int to) {
		this.player = player;
		this.indexFrom = from;
		this.indexTo = to;
	}
	
	public int getID() {
		return this.id;
	}
	
	public IPlayer getPlayer() {
		return this.player;
	}
	
	public int getIndexFrom() {
		return this.indexFrom;
	}
	
	public int getIndexTo() {
		return this.indexTo;
	}
	
}
