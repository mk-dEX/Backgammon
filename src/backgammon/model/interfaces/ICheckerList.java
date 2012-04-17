package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.board.Checker;
import backgammon.model.player.Player;

public interface ICheckerList {
	
	public int addChecker(Player player);
	public int removeChecker(Player player);
	public Vector<Checker> getCheckers();
	public Vector<Checker> getCheckersForPlayer(Player player);
	public int getTopCheckerIndexForPlayer(Player player);
	public boolean hasCheckersOfPlayer(Player player);
	public boolean isEmpty();
	public boolean isBlot();
	public boolean isBlotOfPlayer(Player player);
	public boolean isBlockedForPlayer(Player player);
	
}
