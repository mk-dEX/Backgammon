package backgammon.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import backgammon.controller.IControllerDelegate;
import backgammon.event.CheckerMoveEvent;
import backgammon.event.DiceEvent;
import backgammon.event.InfoEvent;
import backgammon.event.PlayerMoveRequest;
import backgammon.listener.IModelEventListener;
import backgammon.model.player.Move;
import backgammon.view.helpers.ImageBoard;
import backgammon.view.helpers.TopPanel;

public class BackgammonViewGUI implements IModelEventListener, ActionListener {

	/**
	 * Represents the controller
	 */
	protected IControllerDelegate controller;

	/**
	 * The JFram to draw on
	 */
	private JFrame board;

	/**
	 * The JFrame for the history
	 */
	private JFrame hist;

	private Image pl1_checker;
	private Image pl2_checker;

	private ImageBoard imageBoard;

	private JButton newGame;

	private JButton showHistory;

	private JButton startGame;

	/**
	 * Normal Constructor
	 * 
	 * @param controller
	 *            The controller instance
	 */
	public BackgammonViewGUI(IControllerDelegate controller) {
		super();
		this.controller = controller;

		this.loadChecker();
	}

	private void loadChecker() {

		this.pl1_checker = new ImageIcon(getClass().getResource(
				"/img/"
						+ this.controller.getCurrentGameSettings()
								.getPathCheckerPlayer1())).getImage();
		this.pl2_checker = new ImageIcon(getClass().getResource(
				"/img/"
						+ this.controller.getCurrentGameSettings()
								.getPathCheckerPlayer2())).getImage();
	}

	public void initGUI(String title) {
		// Init MainFrame
		JFrame temp = new JFrame(title);
		this.board = temp;

		// Get Board
		this.imageBoard = this.drawBoard();

		this.board.getContentPane().setLayout(new BorderLayout());
		this.board.setResizable(false);
		// Add Board
		this.board.add(this.imageBoard, BorderLayout.CENTER);

		// Add TOP menu bar
		this.board.add(this.drawTopBar(), BorderLayout.NORTH);

		// Set Frame
		// this.board.setLocationRelativeTo(null);

		// Make all visible
		this.board.pack();
		this.board.setVisible(true);

		/*
		 * JFrame hist = new JFrame("History");
		 * 
		 * hist.setSize(300,600);
		 * hist.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		 * hist.setResizable(false); hist.setLocationRelativeTo(null);
		 * hist.setVisible(false); this.hist = hist;
		 */
	}

	/**
	 * 
	 * This function draws a checker from one prime to another. The function
	 * will NOT check if there are other checkers at the stopping prime
	 * (opponents one) and will take the first checker from the starting prime.
	 * 
	 * @param from
	 *            The starting prime as integer.
	 * @param to
	 *            The prime the checker should be moved to.
	 * @return Boolean Indicates whether the move has been drawn successfully or
	 *         not
	 */
	private boolean moveChecker(Move move) {
		this.imageBoard.moveChecker(move);
		return false;
	}

	/**
	 * This function will render ONLY the Board on the internal Frame
	 * 
	 * @return Boolean whether the Board has been drawn successfully or not
	 */
	private ImageBoard drawBoard() {

		ImageBoard panel = new ImageBoard(this, "/img/"
				+ this.controller.getCurrentGameSettings().getPathBoard());

		return panel;
	}

	private JPanel drawTopBar() {
		JPanel bar = new JPanel();
		JPanel pl1_bar = this.drawPl1Bar();
		JPanel pl2_bar = this.drawPl2Bar();
		JPanel button_bar = this.drawButtonBar();

		// Set Border
		button_bar.setBorder(new EtchedBorder());

		// Set dimensions
		Dimension m_bars = new Dimension();
		m_bars.height = 70;
		m_bars.width = 265;

		// Add Dimensions
		button_bar.setPreferredSize(m_bars);

		// Add to bar
		bar.add(pl1_bar, BorderLayout.WEST);
		bar.add(pl2_bar, BorderLayout.CENTER);
		bar.add(button_bar, BorderLayout.EAST);

		return bar;
	}

	private JPanel drawPl1Bar() {
		return new TopPanel(this.controller.getCurrentGameSettings()
				.getPathCheckerPlayer1(), this.controller
				.getCurrentGameSettings().getNamePlayer1());
	}

	private JPanel drawPl2Bar() {
		return new TopPanel(this.controller.getCurrentGameSettings()
				.getPathCheckerPlayer2(), this.controller
				.getCurrentGameSettings().getNamePlayer2());
	}

	private JPanel drawButtonBar() {
		JPanel tmp = new JPanel();

		this.newGame = new JButton("Neues Spiel");
		tmp.add(newGame, BorderLayout.WEST);
		newGame.addActionListener(this);

		this.showHistory = new JButton("History");
		tmp.add(showHistory, BorderLayout.EAST);

		JButton exitProgram = new JButton("Spiel Beenden");
		tmp.add(exitProgram, BorderLayout.SOUTH);
		exitProgram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.startGame = new JButton("Spiel starten");
		tmp.add(startGame, BorderLayout.SOUTH);
		startGame.setVisible(false);

		return tmp;
	}

	/**
	 * This function will draw the normal dices on the board.
	 * 
	 * @param first
	 *            The value of the first dice
	 * @param second
	 *            The value of the second dice
	 * @param player
	 *            indicates the player side, if Zero (0) it will draw the first
	 *            on the one and the second on the other side.
	 * @return Boolean whether the draw was succeeded or not
	 */
	private boolean drawDices(int first, int second, int player) {
		return false;
	}

	/**
	 * This function will draw the Doubledice on the board
	 * 
	 * @param value
	 *            The value of the dice
	 * @param player
	 *            The side the dice should be drawn on, Zero (0) means in the
	 *            middle
	 * @return Boolean whether the draw was succeeded or not
	 */
	private boolean drawDoubleDice(int value, int player) {
		return false;
	}

	/**
	 * This function will draw all information needed, such as player names,
	 * pipcount for both players and the actual winning points.
	 * 
	 * @param game
	 *            Game Object that contains all information needed.
	 * @return Boolean whether the draw was succeeded or not
	 */
	private boolean drawInformation(Object gamemodel) {
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

	public Image getChecker(int pl) {

		if (pl == 1)
			return pl1_checker;
		else
			return pl2_checker;
	}

	public IControllerDelegate getController() {
		return this.controller;
	}

	@Override
	public int handleCheckerMoveEvent(CheckerMoveEvent event) {

		if (event.getMove().isSetMove())
			this.imageBoard.addChecker(event.getMove().getID(), event.getMove()
					.getFromPoint(), event.getMove().getFromIndex());
		else {
			// this.imageBoard.addChecker(event.getMove().getID(),
			// event.getMove().getFromPoint(), event.getMove().getFromIndex());
			this.moveChecker(event.getMove());
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Test1");
		
		if(e.getSource() == this.newGame)
		{
			this.controller.exitGame();
		}
		
	}

}
