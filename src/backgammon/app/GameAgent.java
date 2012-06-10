package backgammon.app;

import backgammon.view.BackgammonViewSettings;
import backgammon.app.GameSettings;
import backgammon.controller.ControllerDelegate;

/**
 * Eine {@link GameAgent} Instanz startet die Eingabe der Spieleinstellungen und anschlie§end das Spiel
 */
public class GameAgent {
	
	/**
	 * Startet den Einstellungsdialog bei Erzeugung eines {@link GameAgent} Objekts
	 */
	public GameAgent() {
		
		startUpdateSettings();
	}
	
	/**
	 * Startet den Einstellungsdialog
	 */
	public void startUpdateSettings() {
		
		new BackgammonViewSettings(this);
	}
	
	/**
	 * Erzeugt einen Controller, der den Ablauf des eigentlich Spiels steuert
	 * @param newSettings Die {@link GameSettings}, die mittels Einstellungsdialog initialisiert wurden
	 */
	public void updateSettings(GameSettings newSettings) {
		
		new ControllerDelegate(this, newSettings);
	}
}
