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
	
	public void init(int playerID) {
		
		Move tempRegisteredMove;
		
		// IndexPoint 0
		for (int i = 0; i < 2; i++) {
			
			tempRegisteredMove = new Move(playerID, 0, i, 0, i);
			this.rootDataController.handleMove(tempRegisteredMove);
		}
		
		// IndexPoint 11 & 18
		for (int i = 0; i < 5; i++) {
			
			tempRegisteredMove = new Move(playerID, 11, i, 11, i);
			this.rootDataController.handleMove(tempRegisteredMove);
			
			tempRegisteredMove = new Move(playerID, 18, i, 18, i);
			this.rootDataController.handleMove(tempRegisteredMove);
		}
		
		// IndexPoint 16
		for (int i = 0; i < 3; i++) {
			
			tempRegisteredMove = new Move(playerID, 16, i, 16, i);
			this.rootDataController.handleMove(tempRegisteredMove);
		}

	}
	
	public void move() {
		
		Move resultingUIMove;
		
		do {
			
			resultingUIMove = this.rootDataController.requestMove(this);
		
		} while (this.rootDataController.testMove(resultingUIMove) < 0);
		
		this.rootDataController.handleMove(resultingUIMove);	
	}
	
	
}
