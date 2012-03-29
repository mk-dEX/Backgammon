package backgammon.model.interfaces;

import java.util.Vector;

import backgammon.model.player.Move;

public interface IDataController extends IDataModel {
	
	public IBackgammonBoard getBackgammonBoard();
	public Move requestMove(IPlayer player);
	public int handleMove(Move registeredMove, Vector<Integer> numbers);
}
