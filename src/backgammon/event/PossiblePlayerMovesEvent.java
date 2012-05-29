package backgammon.event;

import java.util.Vector;
import backgammon.model.player.Move;

/**
 * Enthält alle möglichen {@link Move}s des Spielers, der aktuell an der Reihe ist
 */
public class PossiblePlayerMovesEvent extends BackgammonEvent {

	/**
	 * Die möglichen {@link Move}s
	 */
	private Vector<Move> possibleMoves;
	
	public PossiblePlayerMovesEvent(Vector<Move> possibleMoves) {
		super(BackgammonEvent.type.POSSIBLE_MOVES);
		
		this.possibleMoves = possibleMoves;
	}
	
	public Vector<Move> getPossibleMoves() {
		return this.possibleMoves;
	}
}
