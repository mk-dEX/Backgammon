package backgammon.model.board;

import java.util.Vector;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.interfaces.IPlayer;

public class Point implements ICheckerList {
	
	protected Vector<Checker> checkers = new Vector<Checker>();
	
	public boolean addChecker(IPlayer player) {
		
		if (this.isBlockedForPlayer(player)) {
			return false;
		}
		
		Checker newChecker = new Checker(player);
		this.checkers.add(newChecker);
		
		return true;
	}

	public boolean removeChecker(IPlayer player) {
		
		int index = this.getTopCheckerIndexForPlayer(player);
		
		if (index < 0) {
			return false;
		}
		
		this.checkers.remove(index);
		return true;
	}

	public Vector<Checker> getCheckers() {
		return this.checkers;
	}

	public Vector<Checker> getCheckersForPlayer(IPlayer player) {
		
		Vector<Checker> playerCheckers = new Vector<Checker>();
		
		for (Checker checker : this.checkers) {
			if (checker.getOwner().equals(player)) {
				playerCheckers.add(checker);
			}
		}
		
		return playerCheckers;
	}
	
	protected int getTopCheckerIndexForPlayer(IPlayer player) {
		
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
	
	public boolean hasCheckersOfPlayer(IPlayer player) {
		
		if (this.checkers.isEmpty()) {
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

	public boolean isBlockedForPlayer(IPlayer player) {
		
		if (this.isEmpty()) {
			return false;
		}
		
		int diff = this.checkers.size() - this.getCheckersForPlayer(player).size();
		return (diff > 1);
	}
	
}
