package backgammon.model.board;

import java.util.Vector;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public class DefaultBackgammonBoard implements IBackgammonBoard {
	
	private final int numberOfPoints = IBackgammonBoard.BAR_INDEX - 1;
	private final int numberOfCheckersOfOnePlayer = 15;
	
	private ICheckerList[] points = new Point[this.numberOfPoints];
	private ICheckerList bar = new Bar();
	private ICheckerList[] outs = new Out[2];
	
	public DefaultBackgammonBoard() {
		for (int i = 0; i < this.numberOfPoints; i++) {
			this.points[i] = new Point();
		}
		outs[0] = new Out();
		outs[1] = new Out();
	}

	public ICheckerList getPointAtIndex(int index) {
		if (index >= 0 && index < this.numberOfPoints)
			return this.points[index];
		return null;
	}

	public ICheckerList getBar() {
		return this.bar;
	}

	public ICheckerList getOut(int playerID) {
		if (0 < playerID && playerID < 3) {
			return this.outs[playerID];
		}
		return null;
	}
	
	protected ICheckerList getFieldOnBoard(int pointIndex) {
		if (0 <= pointIndex && pointIndex < IBackgammonBoard.BAR_INDEX) {
			return this.points[pointIndex];
		
		} else if (pointIndex == IBackgammonBoard.BAR_INDEX) {
			return this.bar;
			
		} else if (pointIndex == IBackgammonBoard.OUT_PLAYER1_INDEX) {
			return this.outs[0];
			
		} else if (pointIndex == IBackgammonBoard.OUT_PLAYER2_INDEX) {
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
			
			Move legalMove = new Move(originalMove.getID(), originalMove.getFromPoint(), -1, originalMove.getToPoint(), -1);
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
	
	//TODO should return Move object (?)
	//TODO set aim index of Move
	public boolean commitMove(Move m) {
		//TODO fill in index fields by return values
		return true;
	}
	
	//TODO überarbeiten
	public Vector<Move> getPossiblePlayerMoves(Player player, int playerID) {
		
		Vector<Move> possibleMoves = new Vector<Move>();
		
		//Bar zuerst prüfen
		//Wenn Checker auf bar, dann field checker nicht prüfen(!), außer noch züge übrig ohne checker auf bar
		
		Vector<Checker> checkersOfBar = this.bar.getCheckersForPlayer(player);
		if (!checkersOfBar.isEmpty()) {
			
			int fromIndex = IBackgammonBoard.BAR_INDEX;
			//TODO nur ein würfelergebnis kann zum wiedereinsetzen verwendet werden
			//TODO evtl in eigene Methode auslagern?
		}
		
		
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

		
		
		
		return possibleMoves;
	}
	
	
	
	
	
	protected boolean checkInnerFieldMove(Player player, Move move) {
		
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		if (this.hasCheckersOnBar(player)) {
			return false;
		}
		
		boolean isLegalDirection = (playerID == 1) ? (fromPoint < toPoint) : (fromPoint > toPoint);
		
		if (isLegalDirection) {
			
			int distance = (playerID == 1) ? (toPoint - fromPoint) : (fromPoint - toPoint);
			return this.checkDistance(player, distance);
		}
		return false;
	}
	
	protected boolean checkBarFieldMove(Player player, Move move) {
		
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		int startMin = (playerID == 1) ? (0) : (IBackgammonBoard.BAR_INDEX - 6);
		int startMax = (playerID == 1) ? (5) : (IBackgammonBoard.BAR_INDEX - 1);
		boolean toIsAtStart = (startMin <= toPoint) && (toPoint <= startMax);
		boolean isBarFieldMove = (fromPoint == IBackgammonBoard.BAR_INDEX) && toIsAtStart;
		
		if (isBarFieldMove) {
			
			int distance = (playerID == 1) ? (toPoint + 1) : (IBackgammonBoard.BAR_INDEX - toPoint);
			return this.checkDistance(player, distance);
		}
		return false;
	}
	
	//TODO
	protected boolean checkFieldOutMove(Player player, Move move) {
		
		int fromPoint = move.getFromPoint();
		int toPoint = move.getToPoint();
		int playerID = move.getID();
		
		if (this.allCheckersInHouse(player, playerID) == false) {
			return false;
		}
		
		int outReadyMin = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 6) : (0);
		int outReadyMax = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 1) : (5);
		boolean fromIsOutReady = (outReadyMin <= fromPoint) && (fromPoint <= outReadyMax);
		int outIndex = (playerID == 1) ? (IBackgammonBoard.OUT_PLAYER1_INDEX) : (IBackgammonBoard.OUT_PLAYER2_INDEX);
		boolean toIsOut = (toPoint == outIndex);
		boolean isFieldOutMove = fromIsOutReady && toIsOut;
		
		if (isFieldOutMove) {
			
			int minDistance = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - fromPoint) : (fromPoint + 1);
			
			Vector<Integer> possibleMoveDistancesForChecker = player.getCurrentDiceResult().getPossibleMoveDistances();
			for (Integer onePossibleDistance : possibleMoveDistancesForChecker) {
				if (onePossibleDistance.intValue() >= minDistance) {
					return true;
				}
			}
		}
		return false;
	}
	
	//TODO zusammengesetzte distances zwischenstationen beachten!!
	//TODO ort sollte bereits stimmen, muss nicht abgefragt werden
	protected boolean checkDistance(Player player, int distance) {
		Vector<Integer> possibleMoveDistancesForChecker = player.getCurrentDiceResult().getPossibleMoveDistances();
		
		for (Integer onePossibleDistance : possibleMoveDistancesForChecker) {
			if (onePossibleDistance.intValue() == distance) {
				return true;
			}
		}
		return false;
	}


	
	
	public boolean hasCheckersOnBar(Player player) {
		return (this.bar.getCheckersForPlayer(player).isEmpty() == false);
	}
	
	public boolean allCheckersInHouse(Player player, int playerID) {
		
		int beginIndex = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 6) : (0);
		int endIndex = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 1) : (5);
		int count = 0;
		
		for (int currentIndex = beginIndex; currentIndex <= endIndex; currentIndex++) {
			count += this.points[currentIndex].getCheckersForPlayer(player).size();
		}
		
		return (count == this.numberOfCheckersOfOnePlayer) ? (true) : (false);
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
