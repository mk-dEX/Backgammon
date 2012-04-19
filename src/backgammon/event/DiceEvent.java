package backgammon.event;

import backgammon.model.player.DiceResult;

public class DiceEvent extends BackgammonEvent {

	public static enum diceType {
		DICE,
		DOUBLE_DICE
	}
	
	private diceType type;
	private int playerID;
	private DiceResult diceResult;
	
	public DiceEvent(diceType type, int playerID, DiceResult diceResult) {
		super(BackgammonEvent.type.DICE);
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
	
	public DiceResult getDiceResult() {
		return this.diceResult;
	}
}
