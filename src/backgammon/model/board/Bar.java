package backgammon.model.board;

import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Player;

/**
 * Die Bar des Backgammon-Spielfeldes
 */
public class Bar implements ICheckerList {
	
	/**
	 * Spieler 1
	 */
	private Player player1;
	/**
	 * Spieler 2
	 */
	private Player player2;
	/**
	 * Anzahl Spielsteine von Spieler 1
	 */
	private int sizeOfPlayer1Checkers = 0;
	/**
	 * Anzahl Spielsteine von Spieler 2
	 */
	private int sizeOfPlayer2Checkers = 0;
	
	public Bar(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	@Override
	public int addChecker(Player player) {
		
		if (player.equals(this.player1))
			return ++this.sizeOfPlayer1Checkers;
		else if (player.equals(this.player2))
			return ++this.sizeOfPlayer2Checkers;
		return 0;
	}

	@Override
	public int removeChecker(Player player) {
		
		if (player.equals(this.player1) && this.sizeOfPlayer1Checkers > 0)
			return --this.sizeOfPlayer1Checkers;
		else if (player.equals(this.player2) && this.sizeOfPlayer2Checkers > 0)
			return --this.sizeOfPlayer2Checkers;
		return 0;
	}

	public int getTopCheckerIndex() {
		return ((this.sizeOfPlayer1Checkers >= this.sizeOfPlayer2Checkers) ? (this.sizeOfPlayer1Checkers - 1) : (this.sizeOfPlayer2Checkers - 1));
	}
	
	@Override
	public int getTopCheckerIndexForPlayer(Player player) {
		
		if (player.equals(this.player1))
			return this.sizeOfPlayer1Checkers - 1;
		else if (player.equals(this.player2))
			return this.sizeOfPlayer2Checkers - 1;
		return -1;
	}
	
	public int getCheckerCount() {
		return (this.sizeOfPlayer1Checkers + this.sizeOfPlayer2Checkers);
	}
	
	@Override
	public int getCheckerCountForPlayer(Player player) {
		
		if (player.equals(this.player1))
			return this.sizeOfPlayer1Checkers;
		else if (player.equals(this.player2))
			return this.sizeOfPlayer2Checkers;
		return 0;
	}

	@Override
	public boolean hasCheckersOfPlayer(Player player) {
		
		if (player.equals(this.player1))
			return (this.sizeOfPlayer1Checkers > 0);
		else if (player.equals(this.player2))
			return (this.sizeOfPlayer2Checkers > 0);
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (this.sizeOfPlayer1Checkers == 0 && this.sizeOfPlayer2Checkers == 0);
	}

	@Override
	public boolean isBlot() {
		return false;
	}

	@Override
	public boolean isBlotOfPlayer(Player player) {
		return false;
	}

	@Override
	public boolean isBlockedForPlayer(Player player) {
		return false;
	}	
}
