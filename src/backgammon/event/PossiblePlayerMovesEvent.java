package backgammon.event;

import java.util.Vector;
import backgammon.model.player.Move;

public class PossiblePlayerMovesEvent extends BackgammonEvent {

	private Vector<Move> possibleMoves;
	
	public PossiblePlayerMovesEvent(Vector<Move> possibleMoves) {
		super(BackgammonEvent.type.POSSIBLE_MOVES);
		
		this.possibleMoves = possibleMoves;
	}
	
	public Vector<Move> getPossibleMoves() {
		return this.possibleMoves;
	}
}
