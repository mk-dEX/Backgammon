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
	private BackgammonViewGUI gameView;
	
	public ControllerDelegate(GameAgent aRootController, GameSettings currentSettings) {
		
		this.rootController = aRootController;
		this.gameSettings = currentSettings;
		
		this.model = new DefaultDataModel(this.gameSettings);
		this.gameView = new BackgammonViewGUI(this);
		
		this.model.addDataModelListener(gameView);
		this.gameView.initGUI(this.workingTitle);
		
		this.model.initGameCheckers();
	}
	
	@Override
	public GameSettings getCurrentGameSettings() {
		return this.gameSettings;
	}
	
	@Override
	public void initGame() {
		this.model.initGame();
	}
	
	@Override
	public void exitGame() {
		this.gameView = null;
		this.model = null;
		this.rootController.startUpdateSettings();
	}

	@Override
	public void initNextPlayerMove() {
		this.model.initNextPlayerMove();
	}
	
	@Override
	public boolean startMove(int playerID) {
		return this.model.startMove(playerID);
	}

	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		this.model.endMove(moveEvent);
	}

	@Override
	public void startDoubleOffer(int playerID) {
		this.model.startDoubleOffer(playerID);
	}

	@Override
	public void offerAccepted(boolean didAccept) {
		this.model.offerAccepted(didAccept);
	}
	
	@Override
	public boolean gameStarted() {
		return this.model.gameStarted();
	}
}
