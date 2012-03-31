package backgammon.event;

import backgammon.model.player.Move;


public class CheckerMoveResultEvent extends CheckerMoveEvent {

	public static enum infoType {
		CORRECT_MOVE,
		ILLEGAL_MOVE,
		COMPUTER_DID_FINISH
	};
	
	private infoType type;
	
	public CheckerMoveResultEvent(infoType type, Move move) {
		super(move);
		this.type = type;
	}
	
	public infoType getInfoEventType() {
		return this.type;
	}
}
