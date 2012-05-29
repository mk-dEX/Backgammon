package backgammon.event;

import backgammon.model.player.Move;

/**
 * Enth�lt Informationen �ber einen {@link Move}
 */
public class CheckerMoveEvent extends BackgammonEvent {

	/**
	 * Der {@link Move}
	 */
	protected Move move;
	
	public CheckerMoveEvent(Move move) {
		super(BackgammonEvent.type.CHECKER_MOVE);
		this.move = move;
	}
	
	public Move getMove() {
		return this.move;
	}
	
}
