package backgammon.event;

import java.util.Vector;

public class DiceEvent {

	public static enum diceType {
		DICE,
		DOUBLE_DICE
	}
	
	private diceType type;
	private int playerID;
	private Vector<Integer> diceResults;
	
	public DiceEvent(diceType type, int playerID, Vector<Integer> diceResults) {
		this.type = type;
		this.playerID = playerID;
		this.diceResults = diceResults;
	}
	
	public diceType getDiceType() {
		return this.type;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}
	
	public Vector<Integer> getDiceResult() {
		return this.diceResults;
	}
	
}
