package backgammon.model.player;

import backgammon.model.interfaces.IDataController;

/**
 * Ein abstraktes Modell f�r einen Spieler
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
	 * Der W�rfel des Spielers
	 */
	protected Dice dice;
	/**
	 * Das aktuelle W�rfelergebnis
	 */
	protected DiceResult currentDiceResult;
	
	/**
	 * Der Spieler w�rfelt ein mal
	 * @param min Die minimale Augenzahl
	 * @param max Die maximale Augenzahl
	 * @return Die gew�rfelte Augenzahl
	 */
	public int rollDice(int min, int max) {
		return this.dice.roll(min, max);
	}
	
	/**
	 * Setzen des aktuellen W�rfelergebnisses
	 * @param newDiceResult Das neue W�rfelergebnis
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
