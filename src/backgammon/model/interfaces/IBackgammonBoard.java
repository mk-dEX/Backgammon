package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.player.Move;
import backgammon.model.player.Player;

public interface IBackgammonBoard {

	public static final int BAR_INDEX = 24;
	public static final int OUT_PLAYER1_INDEX = 25;
	public static final int OUT_PLAYER2_INDEX = 26;
	
	public ICheckerList getPointAtIndex(int index);
	public ICheckerList getBar();
	public ICheckerList getOut(int playerID);
	
	public Vector<Move> getPossiblePlayerMoves(Player player, int playerID);
	public Vector<Move> getMoveResults(Player player, Move originalMove);
	public boolean commitMove(Move m);
	public boolean hasCheckersOnBar(Player player);
	public boolean allCheckersInHouse(Player player, int playerID);
	public Vector<Integer> playerHasBlots(Player player);
}
