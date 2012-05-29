package backgammon.event;

/**
 * Enthält Informationen zu einem Spieler
 */
public class InfoEvent extends BackgammonEvent {

	/**
	 * Die möglichen Info-Kategorien
	 */
	public static enum infoType {
		WIN
	}
	
	/**
	 * Der aktuelle {@link infoType}
	 */
	private InfoEvent.infoType info;
	/**
	 * Die ID des Spielers, zu dem die Informationen geliefert werden
	 */
	private int playerID;
	/**
	 * Der Name des Spielers
	 */
	private String playerName;
	/**
	 * Die Anzahl Punkte, die der Spieler erreicht hat
	 */
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
