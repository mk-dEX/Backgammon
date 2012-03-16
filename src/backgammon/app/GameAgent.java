package backgammon.app;

import backgammon.view.BackgammonViewSettings;
import backgammon.app.GameSettings;
import backgammon.controller.ControllerDelegate;

public class GameAgent {
	
	public GameAgent() {
		
		startUpdateSettings();
	}
	
	public void startUpdateSettings() {
		
		new BackgammonViewSettings(this);
	}
	
	public void updateSettings(GameSettings newSettings) {
		
		new ControllerDelegate(this, newSettings);
	}
}
