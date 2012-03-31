package backgammon.model.player;

import backgammon.model.interfaces.IDataController;

public abstract class Player {

	protected String name;
	protected IDataController rootDataController;
	protected Dice dice;
	protected DiceResult currentDiceResult;
	
	public int rollDice(int min, int max) {
		return this.dice.roll(min, max);
	}
	
	public void setCurrentDiceResult(DiceResult newDiceResult) {
		this.currentDiceResult = newDiceResult;
	}
	
	public String getName() {
		return this.name;
	}
	
	public DiceResult getCurrentDiceResult() {
		return this.currentDiceResult;
	}
	
	public abstract boolean isHuman();
	
}
