package backgammon.model.interfaces;

import backgammon.model.player.Move;

public interface IDataController extends IDataModel {
	public IBackgammonBoard getBackgammonBoard();
	
	public Move requestMove(IPlayer player);
	public int testMove(Move newMove);
	public void handleMove(Move registeredMove);
}
