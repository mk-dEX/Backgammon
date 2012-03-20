package backgammon.model.interfaces;

public interface IBackgammonBoard {

	public ICheckerList getPointAtIndex(int index);
	public ICheckerList getBar();
	public ICheckerList getOut(int playerID);
	
}
