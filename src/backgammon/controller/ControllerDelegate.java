package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.model.DefaultDataModel;
import backgammon.model.IDataModel;
import backgammon.view.BackgammonViewGUI;

public class ControllerDelegate implements IControllerDelegate {
	
	private final String workingTitle = "Backgammon v0.1";
	
	private GameAgent rootController;
	private GameSettings currentGameSettings;
	private IDataModel model;
	
	public ControllerDelegate(GameAgent aRootController, GameSettings currentSettings) {
		
		this.rootController = aRootController;
		
		model = new DefaultDataModel();
		BackgammonViewGUI game = new BackgammonViewGUI(this);
		
		model.addDataModelListener(game);
		game.initGUI(this.workingTitle);
	}
	
	public GameSettings getCurrentGameSettings() {
		
		return this.currentGameSettings;
	}
	
	public void exitGame() {
		
		rootController.startUpdateSettings();
	}
}
