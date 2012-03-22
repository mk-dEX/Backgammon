package backgammon.model.interfaces;

import backgammon.model.player.Move;

public interface IPlayer {

	public void init();
	public int rollDice(int min, int max);
	public Move move();
	
}
