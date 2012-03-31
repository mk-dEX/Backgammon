package backgammon.model.player;

import backgammon.model.interfaces.IDataController;

public abstract class Player {

	protected String name;
	protected IDataController rootDataController;
	protected Dice dice;
	
	public int rollDice(int min, int max) {
		return this.dice.roll(min, max);
	}
	public abstract boolean isHuman();
	
}
