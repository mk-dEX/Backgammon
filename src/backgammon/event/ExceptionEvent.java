package backgammon.event;

public class ExceptionEvent {

	public static enum errorType {
		INIT,
		DICE,
		CHECKER_MOVE
	}
	
	private errorType error;
	private Exception exception;
	
	public ExceptionEvent(ExceptionEvent.errorType type) {
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
