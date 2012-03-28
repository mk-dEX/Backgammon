package backgammon.event;

public class InfoEvent {

	private String info;
	
	public InfoEvent(String infoText) {
		this.info = infoText;
	}
	
	public String getTextInfo() {
		return this.info;
	}
	
}
