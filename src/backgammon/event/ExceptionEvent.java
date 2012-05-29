package backgammon.event;

/**
 * Enthält Informationen über eine Ausnahme, die im Datenmodell aufgetreten ist
 */
public class ExceptionEvent extends BackgammonEvent {

	/**
	 * Die möglichen Fehlerarten
	 */
	public static enum errorType {
		DICE_ROLL_DID_FAIL,
		CHECKER_MOVE_DID_FAIL
	}
	
	/**
	 * Der aktuelle {@link errorType}
	 */
	private errorType error;
	/**
	 * Die Exception, die im Datenmodell aufgetreten ist
	 */
	private Exception exception;
	
	public ExceptionEvent(ExceptionEvent.errorType type) {
		super(BackgammonEvent.type.EXCEPTION);
		this.error = type;
	}
	
	public ExceptionEvent(ExceptionEvent.errorType type, Exception exception) {
		this(type);
		this.exception = exception;
	}
	
	public ExceptionEvent.errorType getError() {
		return this.error;
	}
	
	public Exception getException() {
		return this.exception;
	}
}
