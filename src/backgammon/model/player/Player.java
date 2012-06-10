package backgammon.model.player;

import backgammon.model.interfaces.IDataController;

/**
 * Ein abstraktes Modell für einen Spieler
 */
public abstract class Player {

	/**
	 * Der Name des Spielers
	 */
	protected String name;
	/**
	 * Das Dateninterface mit den Spieldaten
	 */
	protected IDataController rootDataController;
	/**
	 * Der Würfel des Spielers
	 */
	protected Dice dice;
	/**
	 * Das aktuelle Würfelergebnis
	 */
	protected DiceResult currentDiceResult;
	
	/**
	 * Der Spieler würfelt ein mal
	 * @param min Die minimale Augenzahl
	 * @param max Die maximale Augenzahl
	 * @return Die gewürfelte Augenzahl
	 */
	public int rollDice(int min, int max) {
		return this.dice.roll(min, max);
	}
	
	/**
	 * Setzen des aktuellen Würfelergebnisses
	 * @param newDiceResult Das neue Würfelergebnis
	 */
	public void setCurrentDiceResult(DiceResult newDiceResult) {
		this.currentDiceResult = newDiceResult;
	}
	
	public String getName() {
		return this.name;
	}
	
	public DiceResult getCurrentDiceResult() {
		return this.currentDiceResult;
	}
	
	/**
	 * @return true wenn der Spieler ein Mensch ist. Sonst false
	 */
	public abstract boolean isHuman();
	
}
