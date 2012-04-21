package backgammon.model;

import java.util.Vector;
import backgammon.app.GameSettings;
import backgammon.event.ActivePlayerInfoEvent;
import backgammon.event.BackgammonEvent;
import backgammon.event.CheckerMoveEvent;
import backgammon.event.CheckerMoveResultEvent;
import backgammon.event.DiceEvent;
import backgammon.event.ExceptionEvent;
import backgammon.listener.IModelEventListener;
import backgammon.model.board.Bar;
import backgammon.model.board.DefaultBackgammonBoard;
import backgammon.model.board.Out;
import backgammon.model.board.Point;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.interfaces.IDataController;
import backgammon.model.player.ComputerPlayer;
import backgammon.model.player.DiceResult;
import backgammon.model.player.HistoryMove;
import backgammon.model.player.HumanPlayer;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public class DefaultDataModel implements IDataController {

	protected IModelEventListener listener;
	protected GameSettings settings;

	protected Player player1;
	protected Player player2;
	protected Player currentPlayer;
	protected IBackgammonBoard gameBoard;
	
	protected Vector<HistoryMove> moveHistory = new Vector<HistoryMove>();

	protected boolean initialized = false;
	
	public DefaultDataModel(GameSettings currentSettings) {
		this.settings = currentSettings;

		GameSettings.KIMode KIModePlayer1 = this.settings.getSelectedKIModePlayer1();
		GameSettings.KIMode KIModePlayer2 = this.settings.getSelectedKIModePlayer2();
		String namePlayer1 = this.settings.getNamePlayer1();
		String namePlayer2 = this.settings.getNamePlayer2();

		if (KIModePlayer1.equals(GameSettings.KIMode.HUMAN)) {
			this.player1 = new HumanPlayer(namePlayer1, this);
		} else {
			this.player1 = new ComputerPlayer(namePlayer1, this, KIModePlayer1);
		}

		if (KIModePlayer2.equals(GameSettings.KIMode.HUMAN)) {
			this.player2 = new HumanPlayer(namePlayer2, this);
		} else {
			this.player2 = new ComputerPlayer(namePlayer2, this, KIModePlayer2);
		}
		
		this.gameBoard = new DefaultBackgammonBoard(this.player1, this.player2);
	}
	
	
// 	Privates
	
	//init
	protected void initCheckersOfPlayer(int playerID) {
		
		Move tempRegisteredMove;
		int maxIndex = IBackgammonBoard.BAR_INDEX - 1;
		int indexFrom = 14;

		// Point 0
		for (int indexTo = 0; indexTo < 2; indexTo++) {
			
			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 0, indexTo)) : 
					(new Move(playerID, 26, indexFrom, maxIndex - 0, indexTo));
			this.executeResultingMove(tempRegisteredMove);
			indexFrom--;		
		}

		// Point 11 & 18
		for (int indexTo = 0; indexTo < 5; indexTo++) {

			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 11, indexTo)) : 
					(new Move(playerID, 26, indexFrom, maxIndex - 11, indexTo));
			this.executeResultingMove(tempRegisteredMove);
			indexFrom--;
			
			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 18, indexTo)) : 
					(new Move(playerID, 26, indexFrom, maxIndex - 18, indexTo));
			this.executeResultingMove(tempRegisteredMove);
			indexFrom--;
		}

		// Point 16
		for (int indexTo = 0; indexTo < 3; indexTo++) {

			tempRegisteredMove = (playerID == 1) ? 
					(new Move(playerID, 25, indexFrom, 16, indexTo)) : 
					(new Move(playerID, 26, indexFrom, maxIndex - 16, indexTo));
			this.executeResultingMove(tempRegisteredMove);
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
		if (playerID == 1) return this.player1;
		if (playerID == 2) return this.player2;
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
		
		possibleMoves = this.getPossiblePlayerMoves(this.currentPlayer, this.getPlayerID(this.currentPlayer));
		return possibleMoves;
	}
	
	protected int getDistanceForMove(Move move) {
		
		int distance = 0;
		int playerID = move.getID();
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		
		ICheckerList fromFieldItem = this.gameBoard.getFieldOnBoard(fromPoint);
		ICheckerList toFieldItem = this.gameBoard.getFieldOnBoard(toPoint);
		
		if (fromFieldItem != null && toFieldItem != null) {
			
			Class<? extends ICheckerList> fromClass = fromFieldItem.getClass();
			Class<? extends ICheckerList> toClass = toFieldItem.getClass();
			
			if (fromClass.equals(Point.class) && toClass.equals(Point.class)) {
				
				distance = (playerID == 1) ? (toPoint - fromPoint) : (fromPoint - toPoint);

			} else if (fromClass.equals(Point.class) && toClass.equals(Out.class)) {
				
				int minDistance = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - fromPoint) : (fromPoint + 1);
				DiceResult playersResult = this.currentPlayer.getCurrentDiceResult();
				
				distance = playersResult.baseResultContainsDistanceOrGreater(minDistance);
				if (distance == 0) {
					distance = playersResult.composedResultContainsDistanceOrGreater(minDistance);
				}

			} else if (fromClass.equals(Bar.class) && toClass.equals(Point.class)) {
				
				distance = (playerID == 1) ? (toPoint + 1) : (IBackgammonBoard.BAR_INDEX - toPoint);
			}
		}
		return distance;
	}
	
	
	//push
	protected void executeResultingMoves(Vector<Move> resultingMoves, Move originalMove) {
		
		if (resultingMoves.isEmpty() == false) {
			
			int distanceUsedForOriginalMove = this.getDistanceForMove(originalMove);
			Vector<Integer> valuesRemovedFromDiceResult = this.currentPlayer.getCurrentDiceResult().makeMove(distanceUsedForOriginalMove);
			System.out.println("Der Vektor " + valuesRemovedFromDiceResult + " wurde aus dem Würfelergebnis entfernt");
			System.out.println("Neue mögliche Distanzen sind " + this.currentPlayer.getCurrentDiceResult().getPossibleMoveDistances());
			
			DiceEvent diceNumbersUsedEvent = new DiceEvent(originalMove.getID(), this.currentPlayer.getCurrentDiceResult(), valuesRemovedFromDiceResult);
			this.pushEvent(diceNumbersUsedEvent);
			
			for (Move oneResultingMove : resultingMoves) {
				this.executeResultingMove(oneResultingMove);
			}
		}
		else {
			CheckerMoveResultEvent failureMoveEvent = new CheckerMoveResultEvent(CheckerMoveResultEvent.moveResult.ILLEGAL_MOVE, originalMove);
			this.pushEvent(failureMoveEvent);
		}
	}
	
	protected void executeResultingMove(Move singleMove) {

		Move theMove = singleMove;
		Player thePlayer = (theMove.getID() == 1) ? (this.player1) : (this.player2);			
		
		theMove = this.commitMove( thePlayer, theMove );
		
		CheckerMoveResultEvent singleMoveResult;
		if (theMove != null) {
			CheckerMoveResultEvent.moveResult moveResult = (initialized) ? (CheckerMoveResultEvent.moveResult.CORRECT_MOVE) : (CheckerMoveResultEvent.moveResult.INIT);
			singleMoveResult = new CheckerMoveResultEvent(moveResult, theMove);
			
			HistoryMove currentMoveHistoryItem = new HistoryMove(theMove, this.getPlayerID(this.currentPlayer));
			this.moveHistory.add(currentMoveHistoryItem);
			
			CheckerMoveResultEvent historyMoveStoredEvent = new CheckerMoveResultEvent(CheckerMoveResultEvent.moveResult.HISTORY_MOVE, currentMoveHistoryItem);
			this.pushEvent(historyMoveStoredEvent);
			
		} else {
			singleMoveResult = new CheckerMoveResultEvent(CheckerMoveResultEvent.moveResult.ILLEGAL_MOVE, theMove);
		}
		this.pushEvent(singleMoveResult);
	}
	
	protected void pushEvent(BackgammonEvent event) {
		if (this.listener == null) {
			System.out.println("Sending event to GUI failed");
		}
		else {
			this.listener.handleBackgammonEvent(event);
		}
	}
	
	
	
//	Board Connection
	
	protected Vector<Move> getMoveResults(Player player, Move originalMove) {
		
		Vector<Move> moveResults = new Vector<Move>();
		
		ICheckerList fromFieldItem = this.gameBoard.getFieldOnBoard(originalMove.getFromPoint());
		ICheckerList toFieldItem = this.gameBoard.getFieldOnBoard(originalMove.getToPoint());
		if (fromFieldItem == null || toFieldItem == null || toFieldItem.isBlockedForPlayer(player)) {
			return moveResults;
		}
		
		Class<? extends ICheckerList> fromFieldItemClass = fromFieldItem.getClass();
		Class<? extends ICheckerList> toFieldItemClass = toFieldItem.getClass();
		boolean isLegal = false;
		
		if (fromFieldItemClass.equals(Point.class) && toFieldItemClass.equals(Out.class)) {
			
			isLegal = this.checkFieldOutMove(player, originalMove);
			
		} else if (fromFieldItemClass.equals(Bar.class) && toFieldItemClass.equals(Point.class)) {
			
			isLegal = this.checkBarFieldMove(player, originalMove);
			
		} else if (fromFieldItemClass.equals(Point.class) && toFieldItemClass.equals(Point.class)) {
			
			isLegal = this.checkInnerFieldMove(player, originalMove);
		
		} else {
			
			isLegal = false;
		}
	
		
		if (isLegal) {
			
			Move legalMove = new Move(originalMove.getID(), originalMove.getFromPoint(), originalMove.getFromIndex(), originalMove.getToPoint(), originalMove.getToIndex());
			moveResults.add(legalMove);
			
			boolean toHasOtherCheckers = (toFieldItem.isBlot() && !toFieldItem.hasCheckersOfPlayer(player));
			if (toHasOtherCheckers) {
				int otherPlayerID = (originalMove.getID() == 1) ? (2) : (1);
				int otherFromPoint = originalMove.getToPoint();
				int otherToPoint = (otherPlayerID == 1) ? (IBackgammonBoard.OUT_PLAYER1_INDEX) : (IBackgammonBoard.OUT_PLAYER2_INDEX);
				Move removeOtherChecker = new Move(otherPlayerID, otherFromPoint, 0, otherToPoint, -1);
				
				moveResults.add(removeOtherChecker);
			}
		}
			
		return moveResults;
	}
	
	protected Move commitMove(Player player, Move m) {
		
		Move move = m;
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		
		ICheckerList fromField = this.gameBoard.getFieldOnBoard(fromPoint);
		ICheckerList toField = this.gameBoard.getFieldOnBoard(toPoint);
		
		if (fromField == null || toField == null) {
			return null;
		}
		
		if (move.getFromIndex() == -1) {
			move.setFromIndex(fromField.getTopCheckerIndex());
		}
		
		fromField.removeChecker(player);
		toField.addChecker(player);
		
		if (move.getToIndex() == -1) {
			move.setToIndex(toField.getTopCheckerIndexForPlayer(player));
		}

		return move;
	}
	
	protected Vector<Move> getPossiblePlayerMoves(Player player, int playerID) {
		
		Vector<Move> possibleMoves = new Vector<Move>();
		
		boolean playerHasCheckersOnBar = (this.gameBoard.getFieldOnBoard(IBackgammonBoard.BAR_INDEX).getCheckerCountForPlayer(player) > 0);
		boolean barFieldMoveIsPossible = false;
		if (playerHasCheckersOnBar) {
			
			Move tempBarFieldMove = new Move(playerID, IBackgammonBoard.BAR_INDEX, 0, 0, 0);
			int tempToPoint;
			for (Integer possibleDistance : player.getCurrentDiceResult().getPossibleMoveDistances()) {
				
				tempToPoint = (playerID == 1) ? (possibleDistance - 1) : (IBackgammonBoard.BAR_INDEX - possibleDistance);
				tempBarFieldMove.setToPoint(tempToPoint);
				barFieldMoveIsPossible = this.checkBarFieldMove(player, tempBarFieldMove);
				if (barFieldMoveIsPossible) {
					int newFromIndex = this.gameBoard.getFieldOnBoard(IBackgammonBoard.BAR_INDEX).getTopCheckerIndexForPlayer(player);
					int newToIndex = this.gameBoard.getFieldOnBoard(tempToPoint).getCheckerCountForPlayer(player);
					tempBarFieldMove.setFromIndex(newFromIndex);
					tempBarFieldMove.setToIndex(newToIndex);
					possibleMoves.add(tempBarFieldMove);
				}
			}
			barFieldMoveIsPossible = (possibleMoves.isEmpty() == false);
		}
		
		if (barFieldMoveIsPossible == false) {
			
			for (int pointIndex = 0; pointIndex < IBackgammonBoard.NUMBER_OF_POINTS; pointIndex++) {
				
				boolean pointContainsCheckers = (this.gameBoard.getFieldOnBoard(pointIndex).getCheckerCountForPlayer(player) > 0);
				if (pointContainsCheckers) {
					
					boolean moveIsPossible = false;
					Move tempMove = new Move(playerID, pointIndex, 0, 0, 0);
					int tempToPoint;
					for (Integer possibleDistance : player.getCurrentDiceResult().getPossibleMoveDistances()) {
						
						tempToPoint = (playerID == 1) ? (pointIndex + possibleDistance) : (pointIndex - possibleDistance);
						boolean isPoint = ((0 <= tempToPoint) && (tempToPoint < IBackgammonBoard.BAR_INDEX));
						boolean isOut = ((playerID == 1) ? (tempToPoint >= IBackgammonBoard.BAR_INDEX) : (tempToPoint < 0));
						boolean toPointIsLegal = (isPoint || isOut);
						
						if (toPointIsLegal == false)
							continue;
						
						tempMove.setToPoint(tempToPoint);
						moveIsPossible = (isPoint) ? (this.checkInnerFieldMove(player, tempMove)) : (this.checkFieldOutMove(player, tempMove));
						if (moveIsPossible) {
							int newFromIndex = this.gameBoard.getFieldOnBoard(pointIndex).getTopCheckerIndexForPlayer(player);
							int newToIndex = this.gameBoard.getFieldOnBoard(tempToPoint).getCheckerCountForPlayer(player);
							tempMove.setFromIndex(newFromIndex);
							tempMove.setToIndex(newToIndex);
							possibleMoves.add(tempMove);
						}
					}
				}
			}
		}
		
		return possibleMoves;
	}
	
	
	
//	Checks
	
	protected boolean checkInnerFieldMove(Player player, Move move) {
		
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		if (this.gameBoard.getFieldOnBoard(IBackgammonBoard.BAR_INDEX).getCheckerCountForPlayer(player) > 0) {
			return false;
		}
		
		boolean isLegalDirection = (playerID == 1) ? (fromPoint < toPoint) : (fromPoint > toPoint);
		if (isLegalDirection) {
			
			int distance = (playerID == 1) ? (toPoint - fromPoint) : (fromPoint - toPoint);
			DiceResult playersResult = player.getCurrentDiceResult();
			if (playersResult.baseResultContainsDistance(distance)) {
				return true;
			
			} else if (playersResult.composedResultContainsDistance(distance)) {
				
				return this.checkDistanceSteps(player, playerID, fromPoint, distance);
			}
		}
		
		return false;
	}
	
	protected boolean checkBarFieldMove(Player player, Move move) {
		
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		int startMin = (playerID == 1) ? (0) : (IBackgammonBoard.BAR_INDEX - 6);
		int startMax = (playerID == 1) ? (5) : (IBackgammonBoard.BAR_INDEX - 1);
		boolean toIsAtStart = (startMin <= toPoint) && (toPoint <= startMax);
		
		if (toIsAtStart) {
			
			int distance = (playerID == 1) ? (toPoint + 1) : (IBackgammonBoard.BAR_INDEX - toPoint);
			DiceResult playersResult = player.getCurrentDiceResult();
			if (playersResult.baseResultContainsDistance(distance)) {
				return true;
			
			} else if (playersResult.composedResultContainsDistance(distance)) {
				
				return this.checkDistanceSteps(player, playerID, move.getFromPoint(), distance);
			}
		}
		
		return false;
	}

	protected boolean checkFieldOutMove(Player player, Move move) {
		
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		if (this.gameBoard.allCheckersInHouse(player, playerID) == false) {
			return false;
		}
		
		int outIndex = (playerID == 1) ? (IBackgammonBoard.OUT_PLAYER1_INDEX) : (IBackgammonBoard.OUT_PLAYER2_INDEX);
		boolean toIsOut = (toPoint == outIndex);
		
		if (toIsOut) {
			
			int minDistance = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - fromPoint) : (fromPoint + 1);
			DiceResult playersResult = player.getCurrentDiceResult();
			
			Integer baseResult = playersResult.baseResultContainsDistanceOrGreater(minDistance);
			if (baseResult != 0) {
				return this.checkDistanceSteps(player, playerID, fromPoint, baseResult);
			
			} else {
				
				Integer composedResult = playersResult.composedResultContainsDistanceOrGreater(minDistance);
				if (composedResult != 0) {
					return this.checkDistanceSteps(player, playerID, fromPoint, composedResult);
				}
			}
		}
		
		return false;
	}
	
	protected boolean checkDistanceSteps(Player player, int playerID, int startIndex, int distance) {
		
		int currentIndex = (startIndex == IBackgammonBoard.BAR_INDEX && playerID == 1) ? (-1) : (startIndex);
		int aimIndex = (playerID == 1) ? (currentIndex + distance) : (currentIndex - distance);
		DiceResult playersResult = player.getCurrentDiceResult();
		boolean isDoublet = playersResult.size() > 2;
		boolean isLegal = false;
		
		
		if (isDoublet) {
			
			Integer baseValue = playersResult.elementAt(0);
			ICheckerList currentPoint;
			do {
				currentIndex = (playerID == 1) ? (currentIndex += baseValue) : (currentIndex -= baseValue);
				currentPoint = this.gameBoard.getFieldOnBoard(currentIndex);
				
				if (currentIndex == aimIndex) {
					isLegal = true;
				
				} else if (currentPoint == null || currentPoint.getClass().equals(Point.class) == false || currentPoint.isBlockedForPlayer(player)) { 
					break; 
				}
				
			} while (!isLegal || (playerID == 1) ? (currentIndex >= aimIndex || currentIndex >= IBackgammonBoard.BAR_INDEX - 1) : (currentIndex <= aimIndex || currentIndex <= 0));
			
		} else {
			
			int tempIndex;
			for (int indexOfStep = 0; indexOfStep < 2; indexOfStep++) {
				if (isLegal == false) {
					tempIndex = (playerID == 1) ? (currentIndex += playersResult.elementAt(indexOfStep)) : (currentIndex -= playersResult.elementAt(indexOfStep));
					ICheckerList point = this.gameBoard.getFieldOnBoard(tempIndex);
					if (point != null && point.getClass().equals(Point.class) && !point.isBlockedForPlayer(player)) {
						isLegal = true;
					
					}
				}
			}
		}
		
		return isLegal;
	}
	
	
	
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
		catch (Exception e) { 
			ExceptionEvent diceFailEvent = new ExceptionEvent(ExceptionEvent.errorType.DICE_ROLL_DID_FAIL, e);
			this.pushEvent(diceFailEvent);
			return;
		}
		
		// push dice event to GUI
		DiceEvent diceResultEvent = new DiceEvent(DiceEvent.diceType.DICE, 0, diceResult); 
		this.pushEvent(diceResultEvent);
		
		// get current player from dice result
		this.currentPlayer = (diceResult.elementAt(0) > diceResult.elementAt(1)) ? (this.player1) : (this.player2);
		this.currentPlayer.setCurrentDiceResult(diceResult);
		
		ActivePlayerInfoEvent activePlayerEvent = new ActivePlayerInfoEvent(this.currentPlayer, this.currentPlayer.isHuman());
		this.pushEvent(activePlayerEvent);
	}
	
	@Override
	public void initGameCheckers() {
		this.initCheckersOfPlayer(1);
		this.initCheckersOfPlayer(2);
		this.initialized = true;
	}
	
	@Override
	public void initNextPlayerMove() {
		
		boolean nextMove = false;
		Vector<Move> possibleMovesOfCurrentPlayer = this.getPossibleMovesOfCurrentPlayer();
		
		if (possibleMovesOfCurrentPlayer.isEmpty()) {
			this.currentPlayer = (this.currentPlayer.equals(this.player1)) ? (this.player2) : (this.player1);
			nextMove = true;
		}
		
		ActivePlayerInfoEvent activePlayerEvent = new ActivePlayerInfoEvent(this.currentPlayer, this.currentPlayer.isHuman()); 
		this.pushEvent(activePlayerEvent);
		
		if (nextMove) {
			
			DiceResult diceResult = null;
			try { diceResult = this.getDiceResult(this.currentPlayer, false); } 
			catch (Exception e) { 
				ExceptionEvent diceFailEvent = new ExceptionEvent(ExceptionEvent.errorType.DICE_ROLL_DID_FAIL, e);
				this.pushEvent(diceFailEvent);
				return;
			}
			
			int playerID = this.getPlayerID(this.currentPlayer);
			DiceEvent diceResultEvent = new DiceEvent(DiceEvent.diceType.DICE, playerID, diceResult); 
			this.pushEvent(diceResultEvent);
			
			this.currentPlayer.setCurrentDiceResult(diceResult);
			
			if (!this.currentPlayer.isHuman()) {
				
				Vector<Move> computerPlayerMoves = ((ComputerPlayer)this.currentPlayer).move();
				this.executeResultingMoves(computerPlayerMoves, null);
				
				CheckerMoveResultEvent computerDidFinishEvent = new CheckerMoveResultEvent(CheckerMoveResultEvent.moveResult.COMPUTER_DID_FINISH_MOVE, null);
				this.pushEvent(computerDidFinishEvent);
			}
		}
	}

	@Override
	public boolean startMove(int playerID) {
		Player selectedPlayer = this.getPlayer(playerID);
		if (selectedPlayer == null)
			return false;
		
		return (this.isCurrentPlayer(selectedPlayer) && selectedPlayer.isHuman());
	}
	
	@Override
	public void endMove(CheckerMoveEvent moveEvent) {
		
		Move finishedMove = moveEvent.getMove();
		
		Vector<Move> moveResults = this.getMoveResults(this.currentPlayer, finishedMove);
		this.executeResultingMoves(moveResults, finishedMove);
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