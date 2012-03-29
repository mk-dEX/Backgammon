package backgammon.model.interfaces;

import java.util.Vector;

import backgammon.model.player.Move;

public interface IDataController extends IDataModel {
	public IBackgammonBoard getBackgammonBoard();
	
	public Move requestMove(IPlayer player);
	public int testMove(Move newMove, Vector<Integer> numbers);
	public void handleMove(Move registeredMove);
}
