package backgammon.model.board;

import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Player;

public class Out implements ICheckerList {

	private int size = 0;
	
	@Override
	public int addChecker(Player player) {
		return ++this.size;
	}

	@Override
	public int removeChecker(Player player) {
		return ((this.size > 0) ? (--this.size) : (0));
	}

	@Override
	public int getTopCheckerIndexForPlayer(Player player) {
		return (this.size - 1);
	}

	@Override
	public int getCheckerCountForPlayer(Player player) {
		return this.size;
	}

	@Override
	public boolean hasCheckersOfPlayer(Player player) {
		return (this.size > 0);
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
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
