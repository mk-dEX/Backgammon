package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.view.BackgammonViewGUI;

public class ControllerDelegate implements IControllerDelegate {
	
	private GameSettings currentGameSettings;
	
	public ControllerDelegate(GameAgent rootController, GameSettings currentSettings) {
		
		BackgammonViewGUI game = new BackgammonViewGUI(this);
		
	}
	
	public GameSettings getCurrentGameSettings() {
		
		return this.currentGameSettings;
	}
	
}
