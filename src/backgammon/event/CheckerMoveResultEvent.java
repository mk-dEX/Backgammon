package backgammon.event;

import backgammon.model.player.Move;


public class CheckerMoveResultEvent {

	public static enum infoType {
		CORRECT_MOVE,
		ILLEGAL_MOVE
	};
	
	private Move move;
	private infoType type;
	
	public CheckerMoveResultEvent(infoType type, Move move) {
		this.type = type;
		this.move = move;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public infoType getInfoEventType() {
		return this.type;
	}
}
