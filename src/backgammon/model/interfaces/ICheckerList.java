package backgammon.model.interfaces;

import backgammon.model.player.Player;

public interface ICheckerList {
	
	public int addChecker(Player player);
	public int removeChecker(Player player);
	
	public int getTopCheckerIndexForPlayer(Player player);
	public int getCheckerCountForPlayer(Player player);
	
	public boolean hasCheckersOfPlayer(Player player);
	public boolean isEmpty();
	public boolean isBlot();
	public boolean isBlotOfPlayer(Player player);
	public boolean isBlockedForPlayer(Player player);
	
}
