package backgammon.event;

public class InfoEvent {

	public static enum infoType {
		ILLEGAL_MOVE,
		TEXT_INFO
	};
	
	private infoType type;
	private String info;
	
	public InfoEvent(infoType type) {
		this.type = type;
	}
	
	public InfoEvent(infoType type, String infoText) {
		this.type = type;
		this.info = infoText;
	}
	
	public infoType getInfoEventType() {
		return this.type;
	}
	
	public String getTextInfo() {
		return this.info;
	}
	
}
