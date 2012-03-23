package backgammon.listener;

import backgammon.event.CheckerMoveEvent;
import backgammon.event.DiceEvent;
import backgammon.event.InfoEvent;
import backgammon.event.PlayerMoveRequest;
import backgammon.model.player.Move;

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
	 * An info string is available.
	 * @param event The corresponding {@link InfoEvent}
	 * @return >=0 if the event has been handled correctly
	 */
	public int handleInfoEvent(InfoEvent event);
	
	/**
	 * Checkers of one player have to be moved using the GUI. 
	 * @param request The corresponding {@link PlayerMoveRequest}
	 * @return The {@link Move} object which results from UI events
	 */
	public Move handlePlayerMoveRequest(PlayerMoveRequest request);
	
}
