package backgammon.event;

import java.util.Vector;

import backgammon.model.player.DiceResult;

/**
 * Enth�lt Informationen bez�glich eines W�rfelergebnisses
 */
public class DiceEvent extends BackgammonEvent {

	/**
	 * Die m�glichen Ereignisarten
	 */
	public static enum diceType {
		DICE,
		DOUBLE_DICE,
		NUMBERS_USED
	}
	
	/**
	 * Der aktuelle {@link diceType}
	 */
	private diceType type;
	/**
	 * Die ID des Spielers, der gew�rfelt hat
	 */
	private int playerID;
	/**
	 * Der {@link DiceResult}
	 */
	private DiceResult diceResult;
	/**
	 * Die Augenzahlen, die f�r den Wurf benutzt wurden
	 */
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
