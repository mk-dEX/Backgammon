package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;
import backgammon.model.DefaultDataModel;
import backgammon.model.interfaces.IDataModel;
import backgammon.view.BackgammonViewGUI;

public class ControllerDelegate implements IControllerDelegate {
	
	private final String workingTitle = "Backgammon v1.0";
	
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
		if(this.model != null)
			this.model.initNextPlayerMove();
	}
	
	@Override
	public boolean startMove(int playerID, int fromPoint) {
		return this.model.startMove(playerID, fromPoint);
	}

	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		this.model.endMove(moveEvent);
	}

	@Override
	public boolean startDoubleOffer(int playerID) {
		return this.model.startDoubleOffer(playerID);
	}

	@Override
	public void offerAccepted(boolean didAccept) {
		this.model.offerAccepted(didAccept);
	}
	
	@Override
	public boolean gameStarted() {
		return this.model.gameStarted();
	}

	@Override
	public int getCurrentPlayerID() {
		return this.model.getCurrentPlayerID();
	}
}
