package backgammon.model.board;

import java.util.Vector;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.DiceResult;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public class DefaultBackgammonBoard implements IBackgammonBoard {
	
	private final int numberOfPoints = IBackgammonBoard.BAR_INDEX;
	private final int numberOfCheckersOfOnePlayer = 15;
	
	private ICheckerList[] points = new Point[this.numberOfPoints];
	private ICheckerList bar;
	private ICheckerList[] outs = new Out[2];
	
	public DefaultBackgammonBoard(Player player1, Player player2) {
		for (int i = 0; i < this.numberOfPoints; i++) {
			this.points[i] = new Point();
		}
		this.bar = new Bar(player1, player2);
		this.outs[0] = new Out();
		this.outs[1] = new Out();
	}

	public ICheckerList getFieldOnBoard(int index) {
		if (0 <= index && index < IBackgammonBoard.BAR_INDEX) {
			return this.points[index];
		
		} else if (index == IBackgammonBoard.BAR_INDEX) {
			return this.bar;
			
		} else if (index == IBackgammonBoard.OUT_PLAYER1_INDEX) {
			return this.outs[0];
			
		} else if (index == IBackgammonBoard.OUT_PLAYER2_INDEX) {
			return this.outs[1];	
		}
		
		return null;
	}
	
	
	
	@Override
	public Vector<Move> getMoveResults(Player player, Move originalMove) {
		
		Vector<Move> moveResults = new Vector<Move>();
		
		ICheckerList fromFieldItem = this.getFieldOnBoard(originalMove.getFromPoint());
		ICheckerList toFieldItem = this.getFieldOnBoard(originalMove.getToPoint());
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
				Move removeOtherChecker = new Move(otherPlayerID, otherFromPoint, -1, otherToPoint, -1);
				
				moveResults.add(removeOtherChecker);
			}
		}
			
		return moveResults;
	}
	
	public Move commitMove(Player player, Move m) {
		
		Move move = m;
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		
		ICheckerList fromField = this.getFieldOnBoard(fromPoint);
		ICheckerList toField = this.getFieldOnBoard(toPoint);
		
		if (fromField == null || toField == null) {
			return null;
		}
		
		if (move.getFromIndex() == -1) {
			move.setFromIndex(fromField.getTopCheckerIndexForPlayer(player));
		}
		
		fromField.removeChecker(player);
		toField.addChecker(player);
		
		if (move.getToIndex() == -1) {
			move.setToIndex(toField.getTopCheckerIndexForPlayer(player));
		}

		return move;
	}
	
	public Vector<Move> getPossiblePlayerMoves(Player player, int playerID) {
		//TODO
		
		Vector<Move> possibleMoves = new Vector<Move>();
		
		boolean playerHasCheckersOnBar = (this.bar.getCheckerCountForPlayer(player) > 0);
		boolean barFieldMoveIsPossible = false;
		if (playerHasCheckersOnBar) {
			
			Move tempBarFieldMove = new Move(playerID, IBackgammonBoard.BAR_INDEX, 0, 0, 0);
			int tempToPoint;
			for (Integer possibleDistance : player.getCurrentDiceResult().getPossibleMoveDistances()) {
				
				tempToPoint = (playerID == 1) ? (possibleDistance - 1) : (IBackgammonBoard.BAR_INDEX - possibleDistance);
				tempBarFieldMove.setToPoint(tempToPoint);
				barFieldMoveIsPossible = this.checkBarFieldMove(player, tempBarFieldMove);
				if (barFieldMoveIsPossible) {
					int newFromIndex = this.bar.getTopCheckerIndexForPlayer(player);
					int newToIndex = this.getFieldOnBoard(tempToPoint).getCheckerCountForPlayer(player);
					tempBarFieldMove.setFromIndex(newFromIndex);
					tempBarFieldMove.setToIndex(newToIndex);
					possibleMoves.add(tempBarFieldMove);
				}
			}
			barFieldMoveIsPossible = (possibleMoves.isEmpty() == false);
		}
		
		if (barFieldMoveIsPossible == false) {
		
			// Iteration over all points
			for (int pointIndex = 0; pointIndex < this.numberOfPoints; pointIndex++) {

				// Get all checkers for current point
				// Continue if there are checkers
				Vector<Checker> checkersOfPoint = this.points[pointIndex].getCheckersForPlayer(player);
				if (!checkersOfPoint.isEmpty()) {

					// Get all possible move distances
					// Continue if there are available move distances
					Vector<Integer> possibleMovesForChecker = player.getCurrentDiceResult().getPossibleMoveDistances();
					if (!possibleMovesForChecker.isEmpty()) {

						// Iteration over all available move distances
						for (Integer distance : possibleMovesForChecker) {

							// The calculated index is current index + distance available by dice roll (or combination)
							// - is used if playerID equals 2
							int newIndex = (playerID == 1) ? (pointIndex + distance) : (pointIndex - distance);

							boolean isInField = (0 <= newIndex) && (newIndex < this.numberOfPoints);
							boolean isOut1 = (playerID == 1) && (this.numberOfPoints <= newIndex);
							boolean isOut2 = (playerID == 2) && (newIndex < 0);


							//TODO checkers nur to out wenn alle im eigenen haus -> abfragen!!
							//-> ganz am anfang der methode abfragen und als boolean speichern
							if (isOut1) newIndex = IBackgammonBoard.OUT_PLAYER1_INDEX;
							if (isOut2) newIndex = IBackgammonBoard.OUT_PLAYER2_INDEX;

							// If current new index is greater than max index or lower than 0, continue with next distance
							if (!isInField && !isOut1 && !isOut2)
								continue;		

							// If aim field is not blocked, the move is possible and will be added to the list
							boolean isBlocked = this.points[newIndex].isBlockedForPlayer(player);
							if (!isBlocked) {
								Move newPossibleMove = this.createNewMove(player, playerID, pointIndex, newIndex);
								possibleMoves.add(newPossibleMove);
							}
						}
					}
				}
			}
		}
		
		return possibleMoves;
	}
	
	
	
	
	
	protected boolean checkInnerFieldMove(Player player, Move move) {
		
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		if (this.bar.getCheckerCountForPlayer(player) > 0) {
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
		
		if (this.allCheckersInHouse(player, playerID) == false) {
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
				currentPoint = this.getFieldOnBoard(currentIndex);
				
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
					ICheckerList point = this.getFieldOnBoard(tempIndex);
					if (point != null && point.getClass().equals(Point.class) && !point.isBlockedForPlayer(player)) {
						isLegal = true;
					
					}
				}
			}
		}
		
		return isLegal;
	}
	
	
	
	
	public boolean allCheckersInHouse(Player player, int playerID) {
		
		int beginIndex = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 6) : (0);
		int endIndex = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 1) : (5);
		int count = 0;
		
		for (int currentIndex = beginIndex; currentIndex <= endIndex; currentIndex++) {
			count += this.points[currentIndex].getCheckerCountForPlayer(player);
		}
		
		return (count == this.numberOfCheckersOfOnePlayer);
	}
	
	public Vector<Integer> playerHasBlots(Player player) {
		Vector<Integer> blots = new Vector<Integer>();
		
		for (int currentIndex = 0; currentIndex < IBackgammonBoard.BAR_INDEX; currentIndex++) {
			if (this.points[currentIndex].isBlotOfPlayer(player)) {
				blots.add(new Integer(currentIndex));
			}
		}
		return blots;
	}
}
