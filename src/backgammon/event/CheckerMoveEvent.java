package backgammon.event;

import backgammon.model.player.Move;

public class CheckerMoveEvent extends BackgammonEvent {

	protected Move move;
	
	public CheckerMoveEvent(Move move) {
		super(BackgammonEvent.type.CHECKER_MOVE);
		this.move = move;
	}
	
	public Move getMove() {
		return this.move;
	}
	
}
