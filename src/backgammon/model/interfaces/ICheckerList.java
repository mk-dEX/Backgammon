package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.board.Checker;

public interface ICheckerList {
	
	public int addChecker(IPlayer player);
	public int removeChecker(IPlayer player);
	public Vector<Checker> getCheckers();
	public Vector<Checker> getCheckersForPlayer(IPlayer player);
	public boolean hasCheckersOfPlayer(IPlayer player);
	public boolean isEmpty();
	public boolean isBlockedForPlayer(IPlayer player);
	
}
