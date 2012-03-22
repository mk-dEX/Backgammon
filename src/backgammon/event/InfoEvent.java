package backgammon.event;

public class InfoEvent {

	private String infoText;
	
	public InfoEvent(String info) {
		this.infoText = info;
	}
	
	public String getInfoText() {
		return this.infoText;
	}
	
}
