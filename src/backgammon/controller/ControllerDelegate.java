package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.model.DefaultDataModel;
import backgammon.model.IDataModel;
import backgammon.view.BackgammonViewGUI;

public class ControllerDelegate implements IControllerDelegate {
	
	private final String workingTitle = "Backgammon v0.1";
	
	private GameAgent rootController;
	private GameSettings gameSettings;
	private IDataModel model;
	
	public ControllerDelegate(GameAgent aRootController, GameSettings currentSettings) {
		
		this.rootController = aRootController;
		this.gameSettings = currentSettings;
		
		model = new DefaultDataModel(this.gameSettings);
		BackgammonViewGUI gameView = new BackgammonViewGUI(this);
		
		model.addDataModelListener(gameView);
		gameView.initGUI(this.workingTitle);
		
		model.startGame();
	}
	
	public GameSettings getCurrentGameSettings() {
		
		return this.gameSettings;
	}
	
	public void exitGame() {
		
		rootController.startUpdateSettings();
	}
}
