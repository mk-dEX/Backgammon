package backgammon.event;

import backgammon.model.player.Player;

/**
 * Enthält Informationen über den Spieler, der gerade an der Reihe ist
 */
public class ActivePlayerInfoEvent extends BackgammonEvent {

	/**
	 * Der Spieler, der aktuell an der Reihe ist
	 */
	private Player activePlayer;
	/**
	 * true wenn der Spieler ein Mensch ist. Sonst false
	 */
	private boolean isHuman;
	
	public ActivePlayerInfoEvent(Player currentPlayer, boolean isHuman) {
		super(BackgammonEvent.type.ACTIVE_PLAYER_INFO);
		this.activePlayer = currentPlayer;
		this.isHuman = isHuman;
	}
	
	public Player getActivePlayer() {
		return this.activePlayer;
	}
	
	public boolean isHuman() {
		return this.isHuman;
	}
	
}
