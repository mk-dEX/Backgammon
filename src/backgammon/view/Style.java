package backgammon.view;

import javax.swing.JPanel;

import com.sun.org.apache.xpath.internal.functions.Function;

public interface Style {
	
	/**
	 * Draws a Checker in the specific prime
	 * @param prime Represents the prime as integer.
	 * @return Boolean Indicates whether the drawing was successful or not.
	 */
	public boolean drawChecker(int prime);
	
	/**
	 * 
	 * This function draws a checker from one prime to another.
	 * The function will NOT check if there are other checkers at the stopping prime (opponents one)
	 * and will take the first checker from the starting prime.
	 * 
	 * @param from The starting prime as integer.
	 * @param to The prime the checker should be moved to.
	 * @return Boolean Inidcates whether the move has been drawn successfully or not
	 */
	public boolean moveChecker(int from, int to);
	
	/**
	 * This function will render ONLY the Board on the internal Panel
	 * 
	 * @return Boolean whether the Board has been drawn successfully or not
	 */
	public boolean drawBoard();
	
	/**
	 * This function will draw the normal dices on the board.
	 * 
	 * @param first The value of the first dice
	 * @param second The value of the second dice
	 * @param player indicates the player side, if Zero (0) it will draw the first on the one and the second on the other side.
	 * @return Boolean whether the draw was succeeded or not
	 */
	public boolean drawDices(int first, int second, int player);
	
	/**
	 * This function will draw the Doubledice on the board
	 * 
	 * @param value The value of the dice
	 * @param player The side the dice should be drawn on, Zero (0) means in the middle
	 * @return Boolean whether the draw was succeeded or not
	 */
	public boolean drawDoubleDice(int value, int player);
	
	/**
	 * This function will draw all information needed, such as player names, pipcount for both players and the actual winning points.
	 * @param game Game Object that contains all information needed.
	 * @return Boolean whether the draw was succeeded or not
	 */
	public boolean drawInformation(Object game);

}
