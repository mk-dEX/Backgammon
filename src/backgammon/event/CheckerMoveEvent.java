package backgammon.event;

import backgammon.model.player.Move;

public class CheckerMoveEvent {

	private Move move;
	
	public CheckerMoveEvent(Move move) {
		this.move = move;
	}
	
	public Move getMove() {
		return this.move;
	}
	
}
