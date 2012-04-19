package backgammon.event;

public abstract class BackgammonEvent {
	
	public enum type {
		ACTIVE_PLAYER_INFO,
		CHECKER_MOVE,
		CHECKER_MOVE_RESULT,
		DICE,
		EXCEPTION,
		INFO
	}
	
	protected BackgammonEvent.type eventType;
	public BackgammonEvent.type getEventType() {
		return this.eventType;
	}
	
	public BackgammonEvent(BackgammonEvent.type typeOfEvent) {
		this.eventType = typeOfEvent;
	}
}
