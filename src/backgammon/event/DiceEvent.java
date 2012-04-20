package backgammon.event;

import java.util.Vector;

import backgammon.model.player.DiceResult;

public class DiceEvent extends BackgammonEvent {

	public static enum diceType {
		DICE,
		DOUBLE_DICE,
		NUMBERS_USED
	}
	
	private diceType type;
	private int playerID;
	private DiceResult diceResult;
	private Vector<Integer> numbersUsed;
	
	public DiceEvent(diceType type, int playerID, DiceResult diceResult) {
		super(BackgammonEvent.type.DICE);
		this.type = type;
		this.playerID = playerID;
		this.diceResult = diceResult;
	}
	
	public DiceEvent(int playerID, DiceResult diceResult, Vector<Integer> numbersUsedForDiceRoll) {
		this(DiceEvent.diceType.NUMBERS_USED, playerID, diceResult);
		this.numbersUsed = numbersUsedForDiceRoll;
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
	
	public Vector<Integer> getNumersUsed() {
		return (this.type == DiceEvent.diceType.NUMBERS_USED) ? (this.numbersUsed) : (new Vector<Integer>());
	}
}
