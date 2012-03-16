package backgammon.app;

import backgammon.view.BackgammonViewSettings;
import backgammon.app.GameSettings;
import backgammon.controller.ControllerDelegate;

public class GameAgent {
	
	/**
	 * Calls startUpdateSettings
	 */
	public GameAgent() {
		
		startUpdateSettings();
	}
	
	/**
	 * Shows {@link BackgammonViewSettings}.
	 */
	public void startUpdateSettings() {
		
		new BackgammonViewSettings(this);
	}
	
	/**
	 * Creates new ControllerDelegate instance to start a new game.
	 * @param newSettings The {@link GameSettings} initialized by the {@link BackgammonViewSettings}Settings class
	 */
	public void updateSettings(GameSettings newSettings) {
		
		new ControllerDelegate(this, newSettings);
	}
}
