package backgammon.event;

import backgammon.model.player.Player;

public class ActivePlayerInfoEvent extends BackgammonEvent {

	private Player activePlayer;
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
