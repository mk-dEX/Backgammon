package backgammon.event;

public class DiceEvent {

	public static enum diceType {
		DICE,
		DOUBLE_DICE
	}
	
	private diceType type;
	private int playerID;
	private int diceResult;
	
	public DiceEvent(diceType type, int playerID, int diceResult) {
		this.type = type;
		this.playerID = playerID;
		this.diceResult = diceResult;
	}
	
	public diceType getDiceType() {
		return this.type;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}
	
	public int getDiceResult() {
		return this.diceResult;
	}
	
}
