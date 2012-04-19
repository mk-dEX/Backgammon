package backgammon.model.player;

import java.util.Vector;

import backgammon.app.GameSettings;
import backgammon.model.interfaces.IDataController;

public class ComputerPlayer extends HumanPlayer {

	private GameSettings.KIMode mode;
	
	public ComputerPlayer(String playerName, IDataController rootDataController, GameSettings.KIMode mode) {
		super(playerName, rootDataController);
		this.mode = mode;
	}

	@Override
	public boolean isHuman() {
		return false;
	}
	
	public GameSettings.KIMode getMode() {
		return this.mode;
	}
	
	public Vector<Move> move() {
		return null;
	}
	

}
