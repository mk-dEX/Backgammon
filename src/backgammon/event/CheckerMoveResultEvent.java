package backgammon.event;

import backgammon.model.player.Move;


public class CheckerMoveResultEvent extends CheckerMoveEvent {

	public static enum moveResult {
		CORRECT_MOVE,
		ILLEGAL_MOVE,
		COMPUTER_DID_FINISH_MOVE
	};
	
	private moveResult result;
	
	public CheckerMoveResultEvent(CheckerMoveResultEvent.moveResult resultOfTheMove, Move move) {
		super(move);
		this.result = resultOfTheMove;
	}
	
	public moveResult getResult() {
		return this.result;
	}
}
