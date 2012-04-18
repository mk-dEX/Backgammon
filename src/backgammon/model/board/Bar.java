package backgammon.model.board;

import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Player;

public class Bar implements ICheckerList {
	
	private Player player1;
	private Player player2;
	private int sizeOfPlayer1Checkers = 0;
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

	@Override
	public int getTopCheckerIndexForPlayer(Player player) {
		
		if (player.equals(this.player1))
			return this.sizeOfPlayer1Checkers - 1;
		else if (player.equals(this.player2))
			return this.sizeOfPlayer2Checkers - 1;
		return -1;
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
