package backgammon.model.interfaces;

import java.util.Vector;

import backgammon.model.board.Point;
import backgammon.model.player.Player;

/**
 * Interface für ein Backgammon-Spielfeld
 */
public interface IBackgammonBoard {

	/**
	 * Der (0-basierte) Index der Bar auf dem Spielfeld
	 */
	public static final int BAR_INDEX = 24;
	/**
	 * Der (0-basierte) Index des Out-Bereichs von Spieler 1 auf dem Spielfeld
	 */
	public static final int OUT_PLAYER1_INDEX = 25;
	/**
	 * Der (0-basierte) Index des Out-Bereichs von Spieler 2 auf dem Spielfeld
	 */
	public static final int OUT_PLAYER2_INDEX = 26;
	
	/**
	 * Die Gesamtzahl {@link Point}s auf dem Spielfeld
	 */
	public final int NUMBER_OF_POINTS = IBackgammonBoard.BAR_INDEX;
	/**
	 * Die Anzahl Spielsteine pro Spieler
	 */
	public final int NUMBER_OF_CHECKERS_PER_PLAYER = 15;
	
	/**
	 * @param index Index des Spielfeld-Elements
	 * @return Die gesuchte {@link ICheckerList}
	 */
	public ICheckerList getFieldOnBoard(int index);
	/**
	 * @param player Der Spieler, dessen Spielsteine überprüft werden sollen
	 * @param playerID Die ID des Spielers, dessen Spielsteine überprüft werden sollen
	 * @return true wenn sich alle Spielsteine des Spielers in dem ihm zugewiesenen Out-Bereich befinden. Sonst false
	 */
	public boolean allCheckersInHouse(Player player, int playerID);
	/**
	 * @param player Der Spieler, dessen Spielsteine überprüft werden sollen
	 * @return true wenn der Spieler auf einem der von ihm besetzten {@link Point}s nur einen einzigen Spielstein hat. Sonst false
	 */
	public Vector<Integer> playerHasBlots(Player player);
}
