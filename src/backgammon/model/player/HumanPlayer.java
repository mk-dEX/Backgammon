package backgammon.model.player;

import backgammon.model.interfaces.IDataController;

public class HumanPlayer extends Player {

	public HumanPlayer(String playerName, IDataController rootDataController) {
		this.name = playerName;
		this.rootDataController = rootDataController;
		this.dice = new Dice();
	}
	
	@Override
	public boolean isHuman() {
		return true;
	}
}
