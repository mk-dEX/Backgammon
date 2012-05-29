package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.player.Player;
import backgammon.model.player.Move;

/**
 * Internes Interface des Datenmodells
 */
public interface IDataController {
	
	/**
	 * @param player Der Spieler, der den Zug macht
	 * @param originalMove Der urspr�ngliche Zug
	 * @param isDebugMove wenn true, dann wird das W�rfelergebnis nicht mit in die Ermittlung miteinbezogen
	 * @return Die aus dem originalMove resultierenden {@link Move}s
	 */
	public Vector<Move> getMoveResults(Player player, Move originalMove, boolean isDebugMove);
	/**
	 * @param player Der Spieler, der am Ziehen ist
	 * @return Alle mit dem aktuellen W�rfelergebnis m�glichen {@link Move}s
	 */
	public Vector<Move> getPossiblePlayerMoves(Player player);
	/**
	 * @return {@link IBackgammonBoard}
	 */
	public IBackgammonBoard getBoard();
}
