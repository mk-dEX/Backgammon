package backgammon.controller;

import backgammon.app.GameSettings;

public interface IControllerDelegate {
	public GameSettings getCurrentGameSettings();
	public void initGame();
	public void exitGame();
}
