package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.board.Checker;

public interface ICheckerList {
	
	public boolean addChecker(IPlayer player, IPlayer otherPlayer);
	public boolean removeChecker(IPlayer player);
	public Vector<Checker> getCheckers();
	public Vector<Checker> getCheckersForPlayer(IPlayer player);
	public boolean hasCheckersOfPlayer(IPlayer player);
	public boolean isEmpty();
	public boolean isFull();
	/**
	 * Access is locked when player has more than two checkers in this ICheckerList.
	 * One player has to call the method with the other player as argument to know whether this
	 * player is blocking.
	 * @param player The other player
	 * @return Player blocks the ICheckerList field.
	 */
	public boolean isBlockedByPlayer(IPlayer player);
	
}
