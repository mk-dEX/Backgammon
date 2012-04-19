package backgammon.event;

public class ExceptionEvent extends BackgammonEvent {

	public static enum errorType {
		DICE_ROLL_DID_FAIL,
		CHECKER_MOVE_DID_FAIL
	}
	
	private errorType error;
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
