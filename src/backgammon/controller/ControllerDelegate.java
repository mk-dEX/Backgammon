package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;

public class ControllerDelegate implements IControllerDelegate {
	
	private GameSettings currentGameSettings;
	
	public ControllerDelegate(GameAgent rootController, GameSettings currentSettings) {
		
	}
	
	public GameSettings getCurrentGameSettings() {
		return this.currentGameSettings;
	}
	
}
