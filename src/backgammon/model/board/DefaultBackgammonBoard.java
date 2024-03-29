package backgammon.model.board;

import java.util.Vector;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.player.Player;

/**
 * Ein Backgammon-Spielfeld
 */
public class DefaultBackgammonBoard implements IBackgammonBoard {
	
	/**
	 * Die 24 {@link Point}s des Spielfeldes
	 */
	private ICheckerList[] points = new Point[IBackgammonBoard.BAR_INDEX];
	/**
	 * Die {@link Bar} des Spielfeldes
	 */
	private ICheckerList bar;
	/**
	 * Die 2 {@link Out}-Felder des Spielfeldes
	 */
	private ICheckerList[] outs = new Out[2];
	
	public DefaultBackgammonBoard(Player player1, Player player2) {
		for (int i = 0; i < IBackgammonBoard.BAR_INDEX; i++) {
			this.points[i] = new Point();
		}
		this.bar = new Bar(player1, player2);
		this.outs[0] = new Out();
		this.outs[1] = new Out();
	}

	@Override
	public ICheckerList getFieldOnBoard(int index) {
		if (0 <= index && index < IBackgammonBoard.BAR_INDEX) {
			return this.points[index];
		
		} else if (index == IBackgammonBoard.BAR_INDEX) {
			return this.bar;
			
		} else if (index == IBackgammonBoard.OUT_PLAYER1_INDEX) {
			return this.outs[0];
			
		} else if (index == IBackgammonBoard.OUT_PLAYER2_INDEX) {
			return this.outs[1];	
		}
		
		return null;
	}
		
	@Override
	public boolean allCheckersInHouse(Player player, int playerID) {
		
		int beginIndex = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 6) : (0);
		int endIndex = (playerID == 1) ? (IBackgammonBoard.BAR_INDEX - 1) : (5);
		int countHouse = 0;
		
		for (int currentIndex = beginIndex; currentIndex <= endIndex; currentIndex++) {
			countHouse += this.points[currentIndex].getCheckerCountForPlayer(player);
		}
		
		int countOut = (playerID == 1) ? (this.outs[0].getCheckerCount()) : (this.outs[1].getCheckerCount());
		
		return ((countHouse + countOut) == IBackgammonBoard.NUMBER_OF_CHECKERS_PER_PLAYER);
	}
	
	@Override
	public Vector<Integer> playerHasBlots(Player player) {
		Vector<Integer> blots = new Vector<Integer>();
		
		for (int currentIndex = 0; currentIndex < IBackgammonBoard.BAR_INDEX; currentIndex++) {
			if (this.points[currentIndex].isBlotOfPlayer(player)) {
				blots.add(new Integer(currentIndex));
			}
		}
		return blots;
	}
}
