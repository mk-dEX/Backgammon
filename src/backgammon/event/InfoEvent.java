package backgammon.event;

public class InfoEvent extends BackgammonEvent {

	public static enum infoType {
		WIN
	}
	
	private InfoEvent.infoType info;
	private int playerID;
	private String playerName;
	private int points;
	
	public InfoEvent(InfoEvent.infoType typeOfInformation, int playerID, String playerName, int points) {
		super(BackgammonEvent.type.INFO);
		this.info = typeOfInformation;
		this.playerID = playerID;
		this.playerName = playerName;
		this.points = points;
	}
	
	public InfoEvent.infoType getInfo() {
		return this.info;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getPoints() {
		return this.points;
	}
}
