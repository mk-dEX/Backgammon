package backgammon.event;

public class InfoEvent extends BackgammonEvent {

	public enum infoType {
		
	}
	
	private InfoEvent.infoType info;
	
	public InfoEvent(InfoEvent.infoType typeOfInformation) {
		super(BackgammonEvent.type.INFO);
		this.info = typeOfInformation;
	}
	
	public InfoEvent.infoType getInfo() {
		return this.info;
	}
}
