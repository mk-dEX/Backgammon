package backgammon.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import backgammon.controller.IControllerDelegate;
import backgammon.event.CheckerMoveEvent;
import backgammon.event.DiceEvent;
import backgammon.event.InfoEvent;
import backgammon.event.PlayerMoveRequest;
import backgammon.listener.IModelEventListener;
import backgammon.model.player.Move;
import backgammon.view.helpers.ImageBoard;

public class BackgammonViewGUI implements IModelEventListener{
	
	/**
	 * Represents the controller
	 */
	private IControllerDelegate controller;
	
	/**
	 * The JFram to draw on
	 */
	private JFrame board;
	
	/**
	 * The JFrame for the history
	 */
	private JFrame hist;
	
	private Graphics boardGraphic;
	
	private Image pl1_checker;

	private ImageBoard imageBoard;
	/**
	 * Normal Constructor
	 * 
	 * @param controller The controller instance
	 */
	public BackgammonViewGUI(IControllerDelegate controller) {
		super();
		this.controller = controller;
		
		this.loadChecker();
	}
	
	
	private void loadChecker() {
		
		this.pl1_checker = new ImageIcon("img/greenchecker.png").getImage();
		
	}


	public void initGUI(String title)
	{
		//Init MainFrame
		JFrame temp = new JFrame(title);
		this.board = temp;
		
		//Get Board
		this.imageBoard = this.drawBoard();
		
		this.board.getContentPane().setLayout(new BorderLayout());
		this.board.setResizable(false);
		//Add Board
		this.board.add(this.imageBoard,BorderLayout.CENTER);
		
		//Add TOP menu bar
		this.board.add(this.drawTopBar(),BorderLayout.NORTH);
		
		//Make all visible
		this.board.pack();
		this.board.setVisible(true);
		
		JFrame hist = new JFrame("History"); 
		
		hist.setSize(300,600);
		hist.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		hist.setResizable(false);
		hist.setLocationRelativeTo(null); 
		hist.setVisible(false);
		this.hist = hist;
	}
	
	
	/**
	 * Draws a Checker in the specific point
	 * @param prime Represents the point as integer.
	 * @return Boolean Indicates whether the drawing was successful or not.
	 */
	private boolean drawChecker(int point, int index, int player)
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
	 * This function will render ONLY the Board on the internal Frame
	 * 
	 * @return Boolean whether the Board has been drawn successfully or not
	 */
	private ImageBoard drawBoard()
	{
		ImageBoard panel = new ImageBoard(this, "img/bg.png");
		
		return panel;
	}
	
	private JPanel drawTopBar()
	{
		JPanel bar = new JPanel();
		JPanel pl1_bar = this.drawPl1Bar();
		JPanel pl2_bar = this.drawPl2Bar();
		JPanel button_bar  = this.drawButtonBar();
		
		//Set Border
		pl1_bar.setBorder(new EtchedBorder());
		pl2_bar.setBorder(new EtchedBorder());
		button_bar.setBorder(new EtchedBorder());
		
		//Set dimensions
		Dimension pl_bars = new Dimension();
		pl_bars.height = 70;
		pl_bars.width = 385;
		
		Dimension m_bars = new Dimension();
		m_bars.height = 70;
		m_bars.width = 185;
		
		//Add Dimensions
		pl1_bar.setPreferredSize(pl_bars);
		pl2_bar.setPreferredSize(pl_bars);
		button_bar.setPreferredSize(m_bars);
		
		//Set Color
		//pl1_bar.setBackground(Color.BLUE);
		//pl2_bar.setBackground(Color.RED);
		//button_bar.setBackground(Color.YELLOW);
		
		//Add to bar
		bar.add(pl1_bar, BorderLayout.WEST);
		bar.add(pl2_bar,BorderLayout.CENTER);
		bar.add(button_bar, BorderLayout.EAST);
		
		
		return bar;
	}
	
	private JPanel drawPl1Bar()
	{
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		
		JPanel tmp = new JPanel();
		JComponent seperator = compFactory.createSeparator(this.controller.getCurrentGameSettings().getNamePlayer1());
		//Add
		tmp.add(seperator,BorderLayout.NORTH);
		
		return tmp;
	}
	private JPanel drawPl2Bar()
	{
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		
		JPanel tmp = new JPanel();
		JComponent seperator = compFactory.createSeparator(this.controller.getCurrentGameSettings().getNamePlayer2());
		
		//Add
		tmp.add(seperator,BorderLayout.NORTH);
		
		return tmp;
	}
	private JPanel drawButtonBar()
	{
		JPanel tmp = new JPanel();
		
		JButton newGame = new JButton("Spiel");
		tmp.add(newGame,BorderLayout.WEST);
		
		JButton showHistory = new JButton("History");
		tmp.add(showHistory,BorderLayout.EAST);
		
		JButton exitProgram = new JButton("Spiel Beenden");
		tmp.add(exitProgram,BorderLayout.SOUTH);
		
		return tmp;
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
	private boolean drawInformation(Object gamemodel)
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
	
	public Image getPl1_checker() {
		return pl1_checker;
	}
	
	@Override
	public int handleCheckerMoveEvent(CheckerMoveEvent event) {
		
		if(event.getMove().isSetMove())
			this.imageBoard.addChecker(event.getMove().getID(), event.getMove().getIndexPointFrom(), event.getMove().getIndexPointFrom());
		return 0;
	}


	@Override
	public int handleDiceEvent(DiceEvent event) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int handleInfoEvent(InfoEvent event) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Move handlePlayerMoveRequest(PlayerMoveRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
