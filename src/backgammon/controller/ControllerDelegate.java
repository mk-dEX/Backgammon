package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;
import backgammon.model.DefaultDataModel;
import backgammon.model.interfaces.IDataModel;
import backgammon.view.BackgammonViewGUI;

public class ControllerDelegate implements IControllerDelegate {
	
	private final String workingTitle = "Backgammon v0.1";
	
	private GameAgent rootController;
	private GameSettings gameSettings;
	private IDataModel model;
	
	public ControllerDelegate(GameAgent aRootController, GameSettings currentSettings) {
		
		this.rootController = aRootController;
		this.gameSettings = currentSettings;
		
		this.model = new DefaultDataModel(this.gameSettings);
		BackgammonViewGUI gameView = new BackgammonViewGUI(this);
		
		this.model.addDataModelListener(gameView);
		gameView.initGUI(this.workingTitle);
		
		this.model.initGameCheckers();
	}
	
	public GameSettings getCurrentGameSettings() {
		
		return this.gameSettings;
	}
	
	public void initGame() {
		
		this.model.initGame();
	}
	
	public void exitGame() {
		
		this.rootController.startUpdateSettings();
	}

	@Override
	public void initNextPlayerMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean startMove(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDoubleOffer(int playerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offerAccepted(boolean didAccept) {
		// TODO Auto-generated method stub
		
	}
}
