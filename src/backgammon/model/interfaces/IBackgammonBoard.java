package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.player.Player;

public interface IBackgammonBoard {

	public static final int BAR_INDEX = 24;
	public static final int OUT_PLAYER1_INDEX = 25;
	public static final int OUT_PLAYER2_INDEX = 26;
	
	public final int NUMBER_OF_POINTS = IBackgammonBoard.BAR_INDEX;
	public final int NUMBER_OF_CHECKERS_PER_PLAYER = 15;
	
	public ICheckerList getFieldOnBoard(int index);
	public boolean allCheckersInHouse(Player player, int playerID);
	public Vector<Integer> playerHasBlots(Player player);
}
