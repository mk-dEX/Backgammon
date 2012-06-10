package backgammon.model.interfaces;

import backgammon.model.player.Player;

/**
 * Ein Element eines Backgammon-Spielfeldes, das Spielsteine enthalten kann
 */
public interface ICheckerList {
	
	/**
	 * @param player
	 * @return Fügt dem Feld einen Spielstein des angegebenen Spielers hinzu
	 */
	public int addChecker(Player player);
	/**
	 * @param player
	 * @return Entfernt einen Spielstein des angegebenen Spielers vom Feld
	 */
	public int removeChecker(Player player);
	
	/**
	 * @return Index des obersten Spielsteins
	 */
	public int getTopCheckerIndex();
	/**
	 * @param player
	 * @return Index des obersten Spielsteins des angegebenen Spielers
	 */
	public int getTopCheckerIndexForPlayer(Player player);
	/**
	 * @return Anzahl der Spielsteine auf dem Feld
	 */
	public int getCheckerCount();
	/**
	 * @param player
	 * @return Anzahl der Spielsteine des angegebenen Spielers auf dem Feld
	 */
	public int getCheckerCountForPlayer(Player player);
	
	/**
	 * @param player
	 * @return true wenn das Feld Spielsteine des angegebenen Spielers enthält. Sonst false
	 */
	public boolean hasCheckersOfPlayer(Player player);
	/**
	 * @return true wenn das Feld keine Spielsteine enthält. Sonst false
	 */
	public boolean isEmpty();
	/**
	 * @return true wenn das Feld nur einen einzigen Spielstein enthält. Sonst false
	 */
	public boolean isBlot();
	/**
	 * @param player
	 * @return true wenn sich auf dem Feld nur ein einziger Spielstein des angegebenen Spielers befindet. Sonst false
	 */
	public boolean isBlotOfPlayer(Player player);
	/**
	 * @param player
	 * @return true wenn sich bereits mindestens zwei Spielsteine des Gegners des angegeben Spielers auf dem Feld befinden 
	 */
	public boolean isBlockedForPlayer(Player player);
	
}
