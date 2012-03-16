package backgammon.app;

public class GameSettings {

	public static enum gameMode {
		STANDARD,
		TRIC_TRAC,
		WURFZABEL
	}
	
	public static enum KIMode {
		HUMAN,
		PASSIVE,
		AGGRESSIVE
	}
	
	private String namePlayer1;
	private String namePlayer2;
	
	private String pathBoard;
	private String pathCheckerPlayer1;
	private String pathCheckerPlayer2;
	private String pathDicePlayer1;
	private String pathDicePlayer2;
	private String pathDiceDouble;
	
	private gameMode selectedGameMode;
	private KIMode selectedKIMode;
	
	
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
	public KIMode getSelectedKIMode() {
		return selectedKIMode;
	}
	public void setSelectedKIMode(KIMode selectedMode) {
		this.selectedKIMode = selectedMode;
	}
}
