package backgammon.app;

/**
 * {@link GameSettings} kapselt die Einstellungsdaten f�r das Spiel
 */
public class GameSettings {

	/**
	 * Der Spielmodus, in dem das Spiel gestartet werden soll
	 */
	public static enum gameMode {
		STANDARD,
		TRIC_TRAC,
		WURFZABEL
	}
	
	/**
	 * Der Modus des gew�hlten Spielers.
	 * HUMAN = Mensch
	 * PASSIVE = Computer spielt passiv
	 * AGGRESSIVE = Computer spielt offensiv
	 */
	public static enum KIMode {
		HUMAN,
		PASSIVE,
		AGGRESSIVE
	}
	
	/**
	 * Der Name des ersten Spielers
	 */
	private String namePlayer1;
	/**
	 * Der Name des zweiten Spielers
	 */
	private String namePlayer2;
	
	/**
	 * Dateipfad zur Grafikdatei des Spielbretts
	 */
	private String pathBoard;
	/**
	 * Dateipfad zur Grafikdatei der Checker von Spieler 1
	 */
	private String pathCheckerPlayer1;
	/**
	 * Dateipfad zur Grafikdatei der Checker von Spieler 2
	 */
	private String pathCheckerPlayer2;
	/**
	 * Dateipfad zur Grafikdatei der W�rfel von Spieler 1
	 */
	private String pathDicePlayer1;
	/**
	 * Dateipfad zur Grafikdatei der W�rfel von Spieler 2
	 */
	private String pathDicePlayer2;
	/**
	 * Dateipfad zur Grafikdatei des Doppelw�rfels
	 */
	private String pathDiceDouble;
	
	/**
	 * Der gew�hlte {@link gameMode}
	 */
	private gameMode selectedGameMode;
	
	/**
	 * Der gew�hlte {@link KIMode} von Spieler 1
	 */
	private KIMode selectedKIModePlayer1;
	/**
	 * Der gew�hlte {@link KIMode} von Spieler 2
	 */
	private KIMode selectedKIModePlayer2;
	
	
	public String getNamePlayer1() {
		return namePlayer1;
	}
	public void setNamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}
	public String getNamePlayer2() {
		return namePlayer2;
	}
	public void setNamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}
	public String getPathBoard() {
		return pathBoard;
	}
	public void setPathBoard(String pathBoard) {
		this.pathBoard = pathBoard;
	}
	public String getPathCheckerPlayer1() {
		return pathCheckerPlayer1;
	}
	public void setPathCheckerPlayer1(String pathCheckerPlayer1) {
		this.pathCheckerPlayer1 = pathCheckerPlayer1;
	}
	public String getPathCheckerPlayer2() {
		return pathCheckerPlayer2;
	}
	public void setPathCheckerPlayer2(String pathCheckerPlayer2) {
		this.pathCheckerPlayer2 = pathCheckerPlayer2;
	}
	public String getPathDicePlayer1() {
		return pathDicePlayer1;
	}
	public void setPathDicePlayer1(String pathDicePlayer1) {
		this.pathDicePlayer1 = pathDicePlayer1;
	}
	public String getPathDicePlayer2() {
		return pathDicePlayer2;
	}
	public void setPathDicePlayer2(String pathDicePlayer2) {
		this.pathDicePlayer2 = pathDicePlayer2;
	}
	public String getPathDiceDouble() {
		return pathDiceDouble;
	}
	public void setPathDiceDouble(String pathDiceDouble) {
		this.pathDiceDouble = pathDiceDouble;
	}
	public gameMode getSelectedGameMode() {
		return selectedGameMode;
	}
	public void setSelectedGameMode(gameMode selectedGameMode) {
		this.selectedGameMode = selectedGameMode;
	}
	public KIMode getSelectedKIModePlayer1() {
		return selectedKIModePlayer1;
	}
	public void setSelectedKIModePlayer1(KIMode selectedMode) {
		this.selectedKIModePlayer1 = selectedMode;
	}
	public KIMode getSelectedKIModePlayer2() {
		return selectedKIModePlayer2;
	}
	public void setSelectedKIModePlayer2(KIMode selectedMode) {
		this.selectedKIModePlayer2 = selectedMode;
	}
}
