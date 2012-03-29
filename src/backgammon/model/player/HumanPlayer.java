package backgammon.model.player;

import backgammon.model.interfaces.IDataController;
import backgammon.model.interfaces.IPlayer;

public class HumanPlayer implements IPlayer {

	protected String name;
	protected IDataController rootDataController;
	protected Dice dice;
	
	public HumanPlayer(String playerName, IDataController rootDataController) {
		this.name = playerName;
		this.rootDataController = rootDataController;
		this.dice = new Dice();
	}
	
	public int rollDice(int min, int max) {
		return this.dice.roll(min, max);
	}

	public Move move(DiceResult diceResult) {
		
		Move resultingUIMove;
		
		do {
			
			resultingUIMove = this.rootDataController.requestMove(this);
		
		} while (this.rootDataController.checkMove(resultingUIMove, diceResult) < 0);
		
		return resultingUIMove;
	}
	
	
}
