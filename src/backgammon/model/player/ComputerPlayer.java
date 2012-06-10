package backgammon.model.player;

import java.util.Vector;
import backgammon.app.GameSettings;
import backgammon.app.GameSettings.KIMode;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.interfaces.IDataController;

/**
 * Erweitert die Klasse {@link HumanPlayer} um automatische Zugermittlung
 */
public class ComputerPlayer extends HumanPlayer {

	/**
	 * Der gewählte {@link KIMode}
	 */
	private GameSettings.KIMode mode;
	
	public ComputerPlayer(String playerName, IDataController rootDataController, GameSettings.KIMode mode) {
		super(playerName, rootDataController);
		this.mode = mode;
	}

	@Override
	public boolean isHuman() {
		return false;
	}
	
	public GameSettings.KIMode getMode() {
		return this.mode;
	}
	
	/**
	 * @param toPoint Zielpunkt des Zuges
	 * @return true wenn ein Spielstein des Gegners geworfen wird. Sonst false
	 */
	private boolean kicksOtherPlayer(int toPoint) {
		ICheckerList toPointItem = this.rootDataController.getBoard().getFieldOnBoard(toPoint);
		return toPointItem.isBlot() && !toPointItem.isBlotOfPlayer(this);
	}
	
	/**
	 * @param toPoint Zielpunkt des Zuges
	 * @return true wenn ein eigener, einzelner Spielstein geschützt wird. Sonst false
	 */
	private boolean protectsBlot(int toPoint) {
		ICheckerList toPointItem = this.rootDataController.getBoard().getFieldOnBoard(toPoint);
		return toPointItem.isBlotOfPlayer(this);
	}
	
	/**
	 * @param fromPoint Ursprung des Zuges
	 * @param toPoint Zielpunkt des Zuges
	 * @return true wenn der Zug einen einzelnen, eigenen Spielstein am Ursprung des Zuges zurücklässt. Sonst false
	 */
	private boolean causesBlot(int fromPoint, int toPoint) {
		ICheckerList fromPointItem = this.rootDataController.getBoard().getFieldOnBoard(fromPoint);
		ICheckerList toPointItem = this.rootDataController.getBoard().getFieldOnBoard(toPoint);
		return fromPointItem.getCheckerCount() == 2 || toPointItem.getCheckerCountForPlayer(this) == 0;
	}
	
	/**
	 * @param toPoint Zielpunkt des Zuges
	 * @param playerID Die ID des Players, dessen House-Bereich ermittelt werden soll
	 * @return true wenn der Zielpunkt des Zuges im House-Bereich des Spielers mit der ID playerID liegt. Sonst false
	 */
	private boolean movesToHouse(int toPoint, int playerID) {
		int minHouse = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 6) : (0);
		int maxHouse = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 1) : (5);
		
		return minHouse <= toPoint && toPoint <= maxHouse;
	}
	
	/**
	 * @param toPoint Zielpunkt des Zuges
	 * @param playerID Die ID des Players, dessen Out-Bereich ermittelt werden soll
	 * @return true wenn der Zielpunkt des Zuges im Out-Bereich des Spielers mit der ID playerID liegt. Sonst false
	 */
	private boolean movesOut(int toPoint, int playerID) {
		return toPoint == ((playerID == 1) ? (IBackgammonBoard.OUT_PLAYER1_INDEX) : (IBackgammonBoard.OUT_PLAYER2_INDEX));
	}
	
	/**
	 * @param possibleMoves Alle möglichen Züge für den Spieler
	 * @return Der beste mögliche Zug des Spielers und die daraus resultierenden Züge
	 */
	public Vector<Move> move(Vector<Move> possibleMoves) {
		
		Move bestMove = null;
		Vector<Move> moveResults = new Vector<Move>();
		
		Vector<Move> aggressiveMoves = new Vector<Move>();
		Vector<Move> passiveMoves = new Vector<Move>();
		
		int playerID = 0;
		//iteriert über alle möglichen Züge
		for (Move possibleMove : possibleMoves) {
			
			if (playerID == 0) playerID = possibleMove.getID();
			int fromPoint = possibleMove.getFromPoint();
			int toPoint = possibleMove.getToPoint();

			if (this.movesOut(toPoint, playerID)) {
				bestMove = possibleMove;
				break;
			}
			
			boolean movesToHouse = this.movesToHouse(toPoint, playerID);
			boolean causesBlot = this.causesBlot(fromPoint, toPoint);
			
			boolean kicksOtherPlayer = this.kicksOtherPlayer(toPoint);
			if (kicksOtherPlayer && movesToHouse) {
				if (this.mode == KIMode.AGGRESSIVE) {
					bestMove = possibleMove;
				}
				aggressiveMoves.add(0, possibleMove);
			}
			else if (kicksOtherPlayer)
				aggressiveMoves.add(possibleMove);
			else if (movesToHouse && !causesBlot)
				aggressiveMoves.add(possibleMove);
			
			boolean protectsBlot = this.protectsBlot(toPoint);
			if (protectsBlot && !causesBlot) {
				if (this.mode == KIMode.PASSIVE) {
					bestMove = possibleMove;
				}
				passiveMoves.add(0, possibleMove);
			}
			else if (protectsBlot || !causesBlot)
				passiveMoves.add(possibleMove);
				
		}
		
		if (bestMove == null) {
			if (this.mode == KIMode.AGGRESSIVE) {
				if (!aggressiveMoves.isEmpty()) {
					bestMove = aggressiveMoves.elementAt(0);
				} else if (!passiveMoves.isEmpty()) {
					bestMove = passiveMoves.elementAt(0);
				} else {
					bestMove = possibleMoves.elementAt(0);
				}
			}
			else if (this.mode == KIMode.PASSIVE) {
				if (!passiveMoves.isEmpty()) {
					bestMove = passiveMoves.elementAt(0);
				} else if (!aggressiveMoves.isEmpty()) {
					bestMove = aggressiveMoves.elementAt(0);
				} else {
					bestMove = possibleMoves.elementAt(0);
				}
			}
		}
		
		System.out.println("Best Move: " + bestMove);
		
		moveResults = this.rootDataController.getMoveResults(this, bestMove, false);
		
		return moveResults;
	}
}
