package backgammon.model.interfaces;

import java.util.Vector;

public interface IPlayer {

	public int rollDice(int min, int max);
	public void move(Vector<Integer> numbers);
	
}
