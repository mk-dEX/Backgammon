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
import backgammon.model.interfaces.ICheckerList;
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
		int indexFrom = 14;

		// IndexPoint 0
		for (int indexTo = 0; indexTo < 2; indexTo++) {

			tempRegisteredMove = new Move(playerID, 25, indexFrom, 0, indexTo);
			try { this.pushCheckerMoveEvent(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;		
		}

		// IndexPoint 11 & 18
		for (int indexTo = 0; indexTo < 5; indexTo++) {

			tempRegisteredMove = new Move(playerID, 25, indexFrom, 11, indexTo);
			try { this.pushCheckerMoveEvent(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;
			
			tempRegisteredMove = new Move(playerID, 25, indexFrom, 18, indexTo);
			try { this.pushCheckerMoveEvent(tempRegisteredMove); }
			catch (Exception e) { throw e; }
			indexFrom--;
		}

		// IndexPoint 16
		for (int indexTo = 0; indexTo < 3; indexTo++) {

			tempRegisteredMove = new Move(playerID, 25, indexFrom, 16, indexTo);
			try { this.pushCheckerMoveEvent(tempRegisteredMove); }
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
			try {Thread.sleep(5);} catch (Exception e) { e.printStackTrace(); }
			diceResult2 = (initial) ? (this.player2.rollDice(1, 6)) : (currentPlayer.rollDice(1, 6));
			try {Thread.sleep(5);} catch (Exception e) { e.printStackTrace(); }
			
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
	
	protected Vector<Integer> getNumbersUsed(Move move) {
		Vector<Integer> numbersUsed = new Vector<Integer>();
		//TODO get all numbers which are used for the move
		return numbersUsed;
	}
	
	protected ICheckerList getCheckerListForIndex(int index) throws Exception{
		
		if (index >= 0 && index < IBackgammonBoard.BAR_INDEX) {
			return this.gameBoard.getPointAtIndex(index);
		
		} else if (index == IBackgammonBoard.BAR_INDEX) {
			return this.gameBoard.getBar();
					
		} else if (index == IBackgammonBoard.OUT_PLAYER1_INDEX) {
			return this.gameBoard.getOut(1);
			
		} else if (index == IBackgammonBoard.OUT_PLAYER2_INDEX) {
			return this.gameBoard.getOut(2);
		
		} else {
			throw new Exception();
		}
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
	
	protected boolean playerHasMovesLeft(Player currentPlayer) {
		
		//TODO
		
		return false;
	}
	
	
	//push
	protected void pushCheckerMove(Player currentPlayer, Move move) throws Exception {
		//TODO set chips on board
		//TODO Point methods should return Result indicating remove or similar
	}
	
	protected void pushCheckerMoveEvent(Move move) throws Exception {
		
		if (move.getID() == 2) {
			
			if (move.getFromPoint() < IBackgammonBoard.BAR_INDEX) {
				move.setFromPoint((IBackgammonBoard.BAR_INDEX - 1) - move.getFromPoint());
			}
			if (move.getToPoint() < IBackgammonBoard.BAR_INDEX) {
				move.setToPoint((IBackgammonBoard.BAR_INDEX - 1) - move.getToPoint());
			}

		}
		
		if (this.listener == null || (this.listener.handleCheckerMoveEvent(new CheckerMoveEvent(move))) < 0)
			throw new Exception();
	}
	
	protected void pushCheckerMoveEvent(CheckerMoveResultEvent moveResultEvent) throws Exception {
		if (this.listener == null || (this.listener.handleCheckerMoveEvent(moveResultEvent)) < 0)
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
	
	
	
//	IDataModel
	
	@Override
	public void addDataModelListener(IModelEventListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void initGame() {
		
		// roll dice
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
		try {
			this.initCheckersOfPlayer(1);
			this.initCheckersOfPlayer(2);
		
		} catch (Exception e) { throw e; }
	}
	
	@Override
	public void initNextPlayerMove() {
		
		boolean nextMove = false;
		
		if (!this.playerHasMovesLeft(this.currentPlayer)) {
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
				for (Move move : computerPlayerMoves) {
					try { this.pushCheckerMoveEvent(move); }
					catch (Exception e) { e.printStackTrace(); }
				}
				
				CheckerMoveResultEvent moveResultEvent = new CheckerMoveResultEvent(CheckerMoveResultEvent.infoType.COMPUTER_DID_FINISH, null);
				try { this.pushCheckerMoveEvent(moveResultEvent); }
				catch (Exception e) { e.printStackTrace(); }
				
			}
		}
	}

	@Override
	public boolean startMove(int playerID) {
		return this.isCurrentPlayer(this.getPlayer(playerID));
	}

	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		// TODO Auto-generated method stub
		
		// x CheckerMoveResultEvents
	}

	@Override
	public void startDoubleOffer(int playerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offerAccepted(boolean didAccept) {
		// TODO Auto-generated method stub
		
	}	
	
	
	
//	IDataController
	
	public IBackgammonBoard getBackgammonBoard() {
		return this.gameBoard;
	}

}