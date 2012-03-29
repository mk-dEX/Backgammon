package backgammon.model.interfaces;

public interface IBackgammonBoard {

	public static final int BAR_INDEX = 24;
	public static final int OUT_PLAYER1_INDEX = 25;
	public static final int OUT_PLAYER2_INDEX = 26;
	
	public ICheckerList getPointAtIndex(int index);
	public ICheckerList getBar();
	public ICheckerList getOut(int playerID);
	
}
