package backgammon.model.player;

import backgammon.model.interfaces.IDataController;
import backgammon.model.interfaces.IPlayer;

public class HumanPlayer implements IPlayer {

	protected String name;
	protected IDataController rootDataController;
	
	public HumanPlayer(String playerName, IDataController rootDataController) {
		this.name = playerName;
		this.rootDataController = rootDataController;
	}

	public void init() {
		// set init chips onto board
	}

}
