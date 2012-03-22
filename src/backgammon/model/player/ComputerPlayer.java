package backgammon.model.player;

import backgammon.app.GameSettings;
import backgammon.model.interfaces.IDataController;
import backgammon.model.interfaces.IPlayer;

public class ComputerPlayer extends HumanPlayer implements IPlayer {

	private GameSettings.KIMode mode;
	
	public ComputerPlayer(String playerName, IDataController rootDataController, GameSettings.KIMode mode) {
		super(playerName, rootDataController);
		this.mode = mode;
	}
	
	public Move move() {
		
		// KI
		//
		
		return null;
	}
	
}
