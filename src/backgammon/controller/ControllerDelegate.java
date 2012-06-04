package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;
import backgammon.model.DefaultDataModel;
import backgammon.model.interfaces.IDataModel;
import backgammon.view.BackgammonViewGUI;

/**
 * Kontrolliert Events der View Klassen und gibt diese an das Datenmodell weiter
 */
public class ControllerDelegate implements IControllerDelegate {
	
	private final String workingTitle = "Backgammon v1.0";
	
	/**
	 * Der {@link GameAgent}, von dem der Controller gestartet wurde
	 */
	private GameAgent rootController;
	/**
	 * Die aktuellen Spieleinstellungen
	 */
	private GameSettings gameSettings;
	/**
	 * Das Dateninterface
	 */
	private IDataModel model;
	/**
	 * Die View Klasse zur Darstellung des Spielablaufs
	 */
	private BackgammonViewGUI gameView;
	
	/**
	 * Initialisiert Datenmodell und Views, verknüpft diese über ein Event-Listener Interface und initialisiert das Spielfeld im Datenmodell
	 * mit der Anfangsformation der Spielsteine
	 * @param aRootController Der {@link GameAgent}, von dem der Controller gestartet wurde
	 * @param currentSettings Die aktuellen Spieleinstellungen
	 */
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
		if (model == null) return false;
		return this.model.startMove(playerID, fromPoint);
	}

	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		this.model.endMove(moveEvent);
	}
	
	@Override
	public void rewindToMove(int numberOfHistoryMoveElement) {
		this.model.rewindToMove(numberOfHistoryMoveElement);
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
