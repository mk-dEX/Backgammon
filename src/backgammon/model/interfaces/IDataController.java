package backgammon.model.interfaces;

import backgammon.model.player.DiceResult;
import backgammon.model.player.Move;

public interface IDataController extends IDataModel {
	
	public IBackgammonBoard getBackgammonBoard();
	public Move requestMove(IPlayer player);
	public int checkMove(Move move, DiceResult diceResult);
}
