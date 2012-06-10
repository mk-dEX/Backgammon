package backgammon.event;

import backgammon.model.player.Move;

/**
 * Enthält Informationen über das Ergebnis eines {@link Move}s
 */
public class CheckerMoveResultEvent extends CheckerMoveEvent {

	/**
	 * Die möglichen Zugarten
	 */
	public static enum moveResult {
		INIT,
		CORRECT_MOVE,
		ILLEGAL_MOVE,
		HISTORY_MOVE,
		COMPUTER_DID_FINISH_MOVE
	};
	
	/**
	 * Der aktuelle {@link moveResult}
	 */
	private moveResult result;
	
	public CheckerMoveResultEvent(CheckerMoveResultEvent.moveResult resultOfTheMove, Move move) {
		super(move);
		this.result = resultOfTheMove;
		this.eventType = BackgammonEvent.type.CHECKER_MOVE_RESULT;
	}
	
	public moveResult getResult() {
		return this.result;
	}
}
