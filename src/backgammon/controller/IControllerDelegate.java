package backgammon.controller;

import backgammon.app.GameAgent;
import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;

/**
 * Interface von Methoden, mithilfe derer die View Klassen Events an das Datenmodell weitergeben
 */
public interface IControllerDelegate {
	
	/**
	 * Anfrage nach den aktuellen Spieleinstellungen
	 * @return Die Spieleinstellungen
	 */
	public GameSettings getCurrentGameSettings();
	/**
	 * Startet das Spiel. Beide Spieler w�rfeln.
	 * Ist der Gewinner ein menschlicher Spieler, darf er ziehen, sonst zieht der Computer automatisch
	 */
	public void initGame();
	/**
	 * Das Spiel wird beendet. R�cksprung in {@link GameAgent} und erneutes Abfragen der neuen Spieleinstellungen
	 */
	public void exitGame();
	
	/**
	 * �berpr�ft ob der aktuelle Spieler alle m�glichen Z�ge absolviert hat. Wenn das der Fall ist, ist der andere Spieler an der Reihe.
	 * Ist der andere Spieler ein Computer, zieht er automatisch
	 */
	public void initNextPlayerMove();
	
	/**
	 * �berpr�fung, ob der Spieler, der versucht zu ziehen, auch an der Reihe ist
	 * @param playerID ID des Spielers, der versucht zu ziehen
	 * @param fromPoint Ausgangspunkt des Checkers, den der Spieler ziehen will
	 * @return true, wenn der Spieler an der Reihe ist. Sonst false
	 */
	public boolean startMove(int playerID, int fromPoint);
	/**
	 * Ein Zug wurde beendet. Es wird �berpr�ft, ob der Zug g�ltig ist und ob er weitere Z�ge zur Folge hat
	 * @param moveEvent Der in einem {@link CheckerMoveEvent} gekapselte Zug
	 */
	public void endMove(CheckerMoveEvent moveEvent);
	
	/**
	 * Spult zu einem angegebenen Zug zur�ck
	 * @param numberOfHistoryMoveElement Index des ausgew�hlten Elements in der History aller bisher durchgef�hrten Z�ge
	 */
	public void rewindToMove(int numberOfHistoryMoveElement);
	
	/**
	 * Ein Spieler schl�gt eine Verdoppelung ("Double") vor
	 * @param playerID Die ID des Spielers, der die Verdoppelung vorschl�gt
	 * @return true wenn die Verdoppelung m�glich ist. Sonst false
	 */
	public boolean startDoubleOffer(int playerID);
	/**
	 * Gibt an, ob eine Verdoppelung akzeptiert wurde
	 * @param didAccept true wenn die Verdoppelung akzeptiert wurde. Sonst false
	 */
	public void offerAccepted(boolean didAccept);
	
	/**
	 * @return Die ID des Spieler, der gerade an der Reihe ist
	 */
	public int getCurrentPlayerID();
	
	/**
	 * @return true, wenn das Spiel bereits gestartet ist. Sonst false
	 */
	public boolean gameStarted();
}
