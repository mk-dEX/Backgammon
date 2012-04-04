package backgammon.model.board;

import java.util.Vector;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public class DefaultBackgammonBoard implements IBackgammonBoard {
	
	private final int numberOfPoints = IBackgammonBoard.BAR_INDEX - 1;
	
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
	
	//TODO geht deutlich einfacher!!
	protected Move createNewMove(Player player, int playerID, int oldPoint, int newPoint) {
		
		Move newMove;
		
		int fromIndex = this.points[oldPoint].getTopCheckerIndexForPlayer(player);
		
		int toIndex;
		int topToIndex = this.points[newPoint].getTopCheckerIndexForPlayer(player);
		
		//TODO kann evtl reduziert werden, ziehen auf blot wirft gegnerischen checker
		//-> es reicht den obersten chip abzufragen und index + 1 zu rechnen
		
		// There are only checkers of this player or move does kick other checker
		if (topToIndex >= 0) {
			
			toIndex = topToIndex + 1;
			
		// There could be one other checker
		} else {
			
			boolean hasOtherChecker = this.points[newPoint].getCheckers().size() > 0;
			if (hasOtherChecker) {
				toIndex = 1;
			} else {
				toIndex = 0;
			}
		}
		
		newMove = new Move(playerID, oldPoint, fromIndex, newPoint, toIndex);	
		return newMove;
	}
	
	
	//TODO zusammengesetzte distances zwischenstationen beachten!!
	//TODO http://www.backgammoned.net/de/backgammon-artikel/backgammon-regeln.html
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
	
	//TODO public boolean hasCheckersOnBar(Player player)
	//TODO public boolean allCheckersInHouse(Player player)
	//TODO public int[](die indizes) playerHasBlots(Player player)
}
