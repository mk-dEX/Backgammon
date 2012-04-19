package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public interface IBackgammonBoard {

	public static final int BAR_INDEX = 24;
	public static final int OUT_PLAYER1_INDEX = 25;
	public static final int OUT_PLAYER2_INDEX = 26;
	
	public ICheckerList getFieldOnBoard(int index);
	
	public Vector<Move> getPossiblePlayerMoves(Player player, int playerID);
	public Vector<Move> getMoveResults(Player player, Move originalMove);
	public Move commitMove(Player player, Move m);
	public boolean hasCheckersOnBar(Player player);
	public boolean allCheckersInHouse(Player player, int playerID);
	public Vector<Integer> playerHasBlots(Player player);
}
