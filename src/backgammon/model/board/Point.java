package backgammon.model.board;

import java.util.Vector;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Player;

public class Point implements ICheckerList {
	
	protected Vector<Checker> checkers = new Vector<Checker>();
	
	public int addChecker(Player player) {
		
		if (this.isBlockedForPlayer(player)) {
			return -1;
		}
		
		Checker newChecker = new Checker(player);
		this.checkers.add(newChecker);
		
		return this.checkers.size() - 1;
	}

	public int removeChecker(Player player) {
		
		int index = this.getTopCheckerIndexForPlayer(player);
		
		if (index >= 0) {
			this.checkers.remove(index);
		}
		
		return index;
	}

	public Vector<Checker> getCheckers() {
		return this.checkers;
	}

	public Vector<Checker> getCheckersForPlayer(Player player) {
		
		Vector<Checker> playerCheckers = new Vector<Checker>();
		
		for (Checker checker : this.checkers) {
			if (checker.getOwner().equals(player)) {
				playerCheckers.add(checker);
			}
		}
		
		return playerCheckers;
	}
	
	public int getTopCheckerIndexForPlayer(Player player) {
		
		if (this.hasCheckersOfPlayer(player) == false) {
			return -1;
		}
		
		int index = this.checkers.size() - 1;
		for (int i = index; i >= 0; i--) {
			Checker tempChecker = this.checkers.elementAt(i);
			if (tempChecker.getOwner().equals(player)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public boolean hasCheckersOfPlayer(Player player) {
		
		if (this.isEmpty()) {
			return false;
		}
		
		for (Checker checker : this.checkers) {
			if (checker.getOwner().equals(player)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isEmpty() {
		return (this.checkers.isEmpty());
	}

	public boolean isBlot() {
		return (this.checkers.size() == 1);
	}
	
	public boolean isBlotOfPlayer(Player player) {
		return (this.isBlot() && this.hasCheckersOfPlayer(player));
	}
	
	public boolean isBlockedForPlayer(Player player) {
		
		if (this.isEmpty()) {
			return false;
		}
		
		int diff = this.checkers.size() - this.getCheckersForPlayer(player).size();
		return (diff > 1);
	}
	
}
