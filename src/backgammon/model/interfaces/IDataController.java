package backgammon.model.interfaces;

import backgammon.model.player.Move;

public interface IDataController extends IDataModel {
	public IBackgammonBoard getBackgammonBoard();
	public int registerMove(Move newMove);
}
