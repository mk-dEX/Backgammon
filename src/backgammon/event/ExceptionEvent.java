package backgammon.event;

public class ExceptionEvent {

	private String errorText;
	
	public ExceptionEvent(String text) {
		this.errorText = text;
	}
	
	public String getError() {
		return this.errorText;
	}
}
