package backgammon.model.interfaces;

import backgammon.model.player.DiceResult;
import backgammon.model.player.Move;

public interface IPlayer {

	public int rollDice(int min, int max);
	public Move move(DiceResult diceResult);
	
}
