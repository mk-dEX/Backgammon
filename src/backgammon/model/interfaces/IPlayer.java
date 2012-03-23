package backgammon.model.interfaces;

public interface IPlayer {

	public void init(int playerID);
	public int rollDice(int min, int max);
	public void move();
	
}
