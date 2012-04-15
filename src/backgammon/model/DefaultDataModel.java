package backgammon.model;

import java.util.Vector;
import backgammon.app.GameSettings;
import backgammon.event.ActivePlayerInfoEvent;
import backgammon.event.CheckerMoveEvent;
import backgammon.event.CheckerMoveResultEvent;
import backgammon.event.DiceEvent;
import backgammon.event.DiceEvent.diceType;
import backgammon.listener.IModelEventListener;
import backgammon.model.board.DefaultBackgammonBoard;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.IDataController;
import backgammon.model.player.ComputerPlayer;
import backgammon.model.player.DiceResult;
import backgammon.model.player.HumanPlayer;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public class DefaultDataModel implements IDataController {

	private IModelEventListener listener;
	private GameSettings settings;

	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private IBackgammonBoard gameBoard;

	
	public DefaultDataModel(GameSettings currentSettings) {
		this.settings = currentSettings;
		this.gameBoard = new DefaultBackgammonBoard();

		GameSettings.KIMode KIModePlayer1 = this.settings.getSelectedKIModePlayer1();
		GameSettings.KIMode KIModePlayer2 = this.settings.getSelectedKIModePlayer2();
		String namePlayer1 = this.settings.getNamePlayer1();
		String namePlayer2 = this.settings.getNamePlayer2();

		if (KIModePlayer1.equals(GameSettings.KIMode.HUMAN)) {
			player1 = new HumanPlayer(namePlayer1, this);
		} else {
			player1 = new ComputerPlayer(namePlayer1, this, KIModePlayer1);
		}

		if (KIModePlayer2.equals(GameSettings.KIMode.HUMAN)) {
			player2 = new HumanPlayer(namePlayer2, this);
		} else {
			player2 = new ComputerPlayer(namePlayer2, this, KIModePlayer2);
		}
	}
	
	
// 	Privates
	
	//init
	protected void initCheckersOfPlayer(int playerID) throws Exception {
		
		Move tempRegisteredMove;
		int maxIndex = IBackgammonBoard.BAR_INDEX - 1;
		int indexFrom = 14;

		// Point 0
		for (int indexTo = 0; indexTo < 2; indexTo++) {
			
			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 0, indexTo)) : 
					(new Move(playerID, 25, indexFrom, maxIndex - 0, indexTo));
			try { this.executeResultingMoves(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;		
		}

		// Point 11 & 18
		for (int indexTo = 0; indexTo < 5; indexTo++) {

			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 11, indexTo)) : 
					(new Move(playerID, 25, indexFrom, maxIndex - 11, indexTo));
			try { this.executeResultingMoves(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;
			
			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 18, indexTo)) : 
					(new Move(playerID, 25, indexFrom, maxIndex - 18, indexTo));
			try { this.executeResultingMoves(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;
		}

		// Point 16
		for (int indexTo = 0; indexTo < 3; indexTo++) {

			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 16, indexTo)) : 
					(new Move(playerID, 25, indexFrom, maxIndex - 16, indexTo));
			try { this.executeResultingMoves(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;	
		}
	}
	
	
	//get
	protected DiceResult getDiceResult(Player currentPlayer, boolean initial) throws Exception {
		
		if (!initial && currentPlayer == null) {
			throw new Exception();
		}
		
		Integer diceResult1;
		Integer diceResult2;
		
		do {
			diceResult1 = (initial) ? (this.player1.rollDice(1, 6)) : (currentPlayer.rollDice(1, 6));
			try {Thread.sleep(50);} catch (Exception e) { e.printStackTrace(); }
			diceResult2 = (initial) ? (this.player2.rollDice(1, 6)) : (currentPlayer.rollDice(1, 6));
			try {Thread.sleep(50);} catch (Exception e) { e.printStackTrace(); }
			
		} while (initial && diceResult1.equals(diceResult2));
		
		DiceResult diceResults = new DiceResult();
		int factor = (!initial && diceResult1.equals(diceResult2)) ? (2) : (1);
		while (factor > 0) {
			diceResults.add(diceResult1);
			diceResults.add(diceResult2);
			factor--;
		}
		
		return diceResults;
	}
	
	protected int getPlayerID(Player player) {
		if (player.equals(this.player1))
			return 1;
		else if (player.equals(this.player2))
			return 2;
		else 
			return 0;
	}
	
	protected Player getPlayer(int playerID) {
		if (playerID == 1) return player1;
		if (playerID == 2) return player2;
		return null;
	}
	
	protected boolean isCurrentPlayer(Player requestedPlayer) {
		return (requestedPlayer != null && requestedPlayer.equals(this.currentPlayer));
	}
	
	protected boolean currentPlayerHasMovesLeft() {
		return (this.currentPlayer.getCurrentDiceResult().isEmpty() == false);
	}
	
	protected Vector<Move> getPossibleMovesOfCurrentPlayer() {
		
		boolean hasMovesLeft;
		Vector<Move> possibleMoves = new Vector<Move>();
		
		hasMovesLeft = this.currentPlayerHasMovesLeft();
		if (!hasMovesLeft) {
			return possibleMoves;
		}
		
		possibleMoves = this.gameBoard.getPossiblePlayerMoves(this.currentPlayer, this.getPlayerID(this.currentPlayer));
		return possibleMoves;
	}
	
	protected Vector<Move> getResultingMoves(Move originalMove) {
		return this.gameBoard.getMoveResults(this.currentPlayer, originalMove);
	}
	
	
	//push
	protected void executeResultingMoves(Vector<Move> resultingMoves) throws Exception {
		
		if (resultingMoves.isEmpty() == false) {
			for (Move oneResultingMove : resultingMoves) {
				try { this.executeResultingMoves(oneResultingMove); }
				catch (Exception e) { throw e; }
			}
		}
		else {
			try {
				CheckerMoveResultEvent failureMoveEvent = new CheckerMoveResultEvent(CheckerMoveResultEvent.infoType.ILLEGAL_MOVE, null);
				this.pushCheckerMoveEvent(failureMoveEvent);
				
			} catch (Exception e) { throw e; }
		}
	}
	
	protected void executeResultingMoves(Move singleMove) throws Exception {
		try {
			Move theMove = singleMove;

			boolean correctMove = this.gameBoard.commitMove( singleMove );
			
			CheckerMoveResultEvent moveResult;
			if (correctMove) {
				moveResult = new CheckerMoveResultEvent(CheckerMoveResultEvent.infoType.CORRECT_MOVE, theMove);
			} else {
				moveResult = new CheckerMoveResultEvent(CheckerMoveResultEvent.infoType.ILLEGAL_MOVE, theMove);
			}
			this.pushCheckerMoveEvent(moveResult);
		}
		catch (Exception e) { throw e; }
	}
	
	protected void pushCheckerMoveEvent(CheckerMoveEvent checkerMoveEvent) throws Exception {
		if (this.listener == null || (this.listener.handleCheckerMoveEvent(checkerMoveEvent)) < 0)
			throw new Exception();
	}
	
	protected void pushDiceEvent(DiceEvent.diceType type, int playerID, DiceResult result) throws Exception {
		
		DiceEvent diceEvent = new DiceEvent(DiceEvent.diceType.DICE, playerID, result);
		
		if (this.listener == null || (this.listener.handleDiceEvent(diceEvent)) < 0)
			throw new Exception();
	}
	
	protected void pushActivePlayerInfoEvent() throws Exception {
		if (this.listener == null || (this.listener.handleActivePlayerInfoEvent(new ActivePlayerInfoEvent(this.currentPlayer.getName(), this.currentPlayer.isHuman()))) < 0)
			throw new Exception();
	}
	
	
//TODO Exception unterscheiden zwischen EventException(kein Listener eingetragen,...) und InternalException(Zufallfehler, Zug illegal Fehler,...)	
	
//TODO pushExceptionEvent einbauen, statt Programm einfach zu beenden
//GUI/Benutzer kann dann entscheiden, was passiert und ggf Programm beenden / neustarten
	
	
//	IDataModel
	
	@Override
	public void addDataModelListener(IModelEventListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void initGame() {
		
		// initially roll dice
		DiceResult diceResult = null;
		try { diceResult = this.getDiceResult(null, true); } 
		catch (Exception e) { e.printStackTrace(); System.exit(0); }
		
		// push dice event to GUI
		try { this.pushDiceEvent(diceType.DICE, 0, diceResult); }
		catch (Exception e) { e.printStackTrace(); }
		
		// get current player from dice result
		this.currentPlayer = (diceResult.elementAt(0) > diceResult.elementAt(1)) ? (this.player1) : (this.player2);
		this.currentPlayer.setCurrentDiceResult(diceResult);
	}
	
	@Override
	public void initGameCheckers() throws Exception {
		this.initCheckersOfPlayer(1);
		this.initCheckersOfPlayer(2);
	}
	
	@Override
	public void initNextPlayerMove() {
		
		boolean nextMove = false;
		Vector<Move> possibleMovesOfCurrentPlayer = this.getPossibleMovesOfCurrentPlayer();
		
		if (possibleMovesOfCurrentPlayer.isEmpty()) {
			this.currentPlayer = (this.currentPlayer.equals(this.player1)) ? (this.player2) : (this.player1);
			nextMove = true;
		}
		
		try { this.pushActivePlayerInfoEvent(); }
		catch (Exception e) { e.printStackTrace(); }
		
		if (nextMove) {
			
			DiceResult diceResult = null;
			try { diceResult = this.getDiceResult(this.currentPlayer, false); } 
			catch (Exception e) { e.printStackTrace(); System.exit(0); }
			
			int playerID = this.getPlayerID(this.currentPlayer);
			
			try { this.pushDiceEvent(diceType.DICE, playerID, diceResult); }
			catch (Exception e) { e.printStackTrace(); }
			
			this.currentPlayer.setCurrentDiceResult(diceResult);
			
			if (!this.currentPlayer.isHuman()) {
				
				Vector<Move> computerPlayerMoves = ((ComputerPlayer)this.currentPlayer).move();
				try { 
					this.executeResultingMoves(computerPlayerMoves); 
				}
				catch (Exception e) { e.printStackTrace(); }
				
				CheckerMoveResultEvent moveResultEvent = new CheckerMoveResultEvent(CheckerMoveResultEvent.infoType.COMPUTER_DID_FINISH, null);
				try { 
					this.pushCheckerMoveEvent(moveResultEvent); 
				}
				catch (Exception e) { e.printStackTrace(); }
				
			}
		}
	}

	@Override
	public boolean startMove(int playerID) {
		return this.isCurrentPlayer( this.getPlayer(playerID) );
	}
	
	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		
		Move finishedMove = moveEvent.getMove();
		
		try {
			Vector<Move> moveResults = this.getResultingMoves(finishedMove);
			this.executeResultingMoves(moveResults);
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	@Override
	public void startDoubleOffer(int playerID) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void offerAccepted(boolean didAccept) {
		// TODO Auto-generated method stub
		
	}	
	
	
	
// IDataController
	
	public IBackgammonBoard getBackgammonBoard() {
		return this.gameBoard;
	}
}