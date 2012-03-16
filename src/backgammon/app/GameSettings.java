package backgammon.app;

import java.awt.Color;

public class GameSettings {

	private enum gameMode {
		STANDARD,
		TRIC_TRAC,
		WURFZABEL
	}
	
	private enum KIMode {
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
	private KIMode selectedMode;
	
	// Spielart
	// Spielmodus (KI / Mensch)
		// KI Modus
}
