package backgammon.model.interfaces;

public interface IDataController extends IDataModel {
	public IPlayer getPlayer(int playerID);
	public IBackgammonBoard getBackgammonBoard();
	
}
