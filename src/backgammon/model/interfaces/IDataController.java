package backgammon.model.interfaces;

import java.util.Vector;
import backgammon.model.player.Player;
import backgammon.model.player.Move;

public interface IDataController extends IDataModel {
	
	public Vector<Move> getMoveResults(Player player, Move originalMove);
	public Vector<Move> getPossiblePlayerMoves(Player player);
	public IBackgammonBoard getBoard();
}
