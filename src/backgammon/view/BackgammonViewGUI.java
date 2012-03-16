package backgammon.view;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import backgammon.controller.IControllerDelegate;
import backgammon.listener.IModelEventListener;

public class BackgammonViewGUI implements IModelEventListener{
	
	/**
	 * Represents the controller
	 */
	private IControllerDelegate controller;
	
	/**
	 * The Panel to draw on
	 */
	private JFrame panel;
	/**
	 * Normal Constructor
	 * 
	 * @param controller The controller instance
	 */
	public BackgammonViewGUI(IControllerDelegate controller) {
		super();
		
		this.controller = controller;
		
		this.panel = this.initiateFrame("Backgammon v0.1");
		
	}
	
	
	protected JFrame initiateFrame(String title)
	{
		JFrame temp = new JFrame(title); 
		temp.setSize(500,300);
		temp.setVisible(true);
		
		return temp;
	}
	
	/**
	 * Draws a Checker in the specific prime
	 * @param prime Represents the prime as integer.
	 * @return Boolean Indicates whether the drawing was successful or not.
	 */
	private boolean drawChecker(int prime)
	{
		return false;
	}
	
	/**
	 * 
	 * This function draws a checker from one prime to another.
	 * The function will NOT check if there are other checkers at the stopping prime (opponents one)
	 * and will take the first checker from the starting prime.
	 * 
	 * @param from The starting prime as integer.
	 * @param to The prime the checker should be moved to.
	 * @return Boolean Indicates whether the move has been drawn successfully or not
	 */
	private boolean moveChecker(int from, int to)
	{
		return false;
	}
	
	/**
	 * This function will render ONLY the Board on the internal Panel
	 * 
	 * @return Boolean whether the Board has been drawn successfully or not
	 */
	private boolean drawBoard()
	{
		//BufferedImage image = ImageIO.read(newFile(this.controller.getSettings().boardImage));
		
		return false;
	}
	
	/**
	 * This function will draw the normal dices on the board.
	 * 
	 * @param first The value of the first dice
	 * @param second The value of the second dice
	 * @param player indicates the player side, if Zero (0) it will draw the first on the one and the second on the other side.
	 * @return Boolean whether the draw was succeeded or not
	 */
	private boolean drawDices(int first, int second, int player)
	{
		return false;
	}
	
	/**
	 * This function will draw the Doubledice on the board
	 * 
	 * @param value The value of the dice
	 * @param player The side the dice should be drawn on, Zero (0) means in the middle
	 * @return Boolean whether the draw was succeeded or not
	 */
	private boolean drawDoubleDice(int value, int player)
	{
		return false;
	}
	
	/**
	 * This function will draw all information needed, such as player names, pipcount for both players and the actual winning points.
	 * @param game Game Object that contains all information needed.
	 * @return Boolean whether the draw was succeeded or not
	 */
	private boolean drawInformation(Object game)
	{
		return false;
	}

	/**
	 * 
	 * Testfunction
	 * 
	 * @return Boolean True or false
	 */
	public boolean makeTestDraw() {
		return false;
	}

}
