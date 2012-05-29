package backgammon.event;

/**
 * Abstraktes Event, das von einer View-Klasse behandelt wird
 */
public abstract class BackgammonEvent {
	
	/**
	 * Die mšglichen Eventtypen
	 */
	public enum type {
		ACTIVE_PLAYER_INFO,
		CHECKER_MOVE,
		CHECKER_MOVE_RESULT,
		DICE,
		EXCEPTION,
		INFO,
		POSSIBLE_MOVES
	}
	
	/**
	 * Der Typ des abgeleiteten Events
	 */
	protected BackgammonEvent.type eventType;
	public BackgammonEvent.type getEventType() {
		return this.eventType;
	}
	
	public BackgammonEvent(BackgammonEvent.type typeOfEvent) {
		this.eventType = typeOfEvent;
	}
}
