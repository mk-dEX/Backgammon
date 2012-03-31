package backgammon.listener;

import backgammon.event.CheckerMoveEvent;
import backgammon.event.DiceEvent;
import backgammon.event.ActivePlayerInfoEvent;

public interface IModelEventListener {

	/**
	 * A checker has to be added to/removed from the board.
	 * @param event The corresponding {@link CheckerMoveEvent}
	 * @return >=0 if the event has been handled correctly
	 */
	public int handleCheckerMoveEvent(CheckerMoveEvent event);
	
	/**
	 * New dice data is available.
	 * @param event The corresponding {@link DiceEvent}
	 * @return >=0 if the event has been handled correctly
	 */
	public int handleDiceEvent(DiceEvent event);
	
	/**
	 * Possible change of active player.
	 * @param event The corresponding {@link ActivePlayerInfoEvent}
	 * @return >=0 if the event has been handled correctly
	 */
	public int handleActivePlayerInfoEvent(ActivePlayerInfoEvent event);
	
}
