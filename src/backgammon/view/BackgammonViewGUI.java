package backgammon.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import backgammon.controller.IControllerDelegate;
import backgammon.event.BackgammonEvent;
import backgammon.event.CheckerMoveResultEvent;
import backgammon.event.DiceEvent;
import backgammon.event.DiceEvent.diceType;
import backgammon.event.ActivePlayerInfoEvent;
import backgammon.event.ExceptionEvent;
import backgammon.event.InfoEvent;
import backgammon.event.PossiblePlayerMovesEvent;
import backgammon.listener.IModelEventListener;
import backgammon.model.player.Move;
import backgammon.view.helpers.BDice;
import backgammon.view.helpers.HistoryPanel;
import backgammon.view.helpers.ImageBoard;
import backgammon.view.helpers.TopPanel;

public class BackgammonViewGUI implements IModelEventListener, ActionListener {

	/**
	 * Represents the controller
	 */
	protected IControllerDelegate controller;

	/**
	 * The JFrame to draw on
	 */
	private JFrame board;

	/**
	 * The JFrame for the history
	 */
	private HistoryPanel hist;

	private Image pl1_checker;
	private Image pl2_checker;

	private ImageBoard imageBoard;

	private JButton newGame;

	private JButton showHistory;

	private JButton startGame;

	private JButton exitProgram;

	private Vector<BackgammonEvent> normalEventList = new Vector<BackgammonEvent>();
	private Vector<BackgammonEvent> importantEventList = new Vector<BackgammonEvent>();

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

	public void destroyGUI() {
		this.imageBoard.destroyThreads();
		// Aufräumen
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

		this.hist = new HistoryPanel(this);

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
		this.newGame.setPreferredSize(new Dimension(125, 25));
		tmp.add(newGame, BorderLayout.NORTH);
		newGame.addActionListener(this);

		this.exitProgram = new JButton("Spiel Beenden");
		this.exitProgram.setPreferredSize(new Dimension(125, 25));
		tmp.add(this.exitProgram, BorderLayout.NORTH);
		this.exitProgram.addActionListener(this);

		this.showHistory = new JButton("History");
		this.showHistory.setPreferredSize(new Dimension(250, 25));
		this.showHistory.setVisible(false);
		tmp.add(showHistory, BorderLayout.SOUTH);
		this.showHistory.addActionListener(this);

		this.startGame = new JButton("Spiel starten");
		this.startGame.setPreferredSize(new Dimension(250, 25));
		tmp.add(startGame, BorderLayout.SOUTH);
		this.startGame.addActionListener(this);

		return tmp;
	}

	public Image getChecker(int pl) {

		if (pl == 1)
			return pl1_checker;
		else
			return pl2_checker;
	}

	public Image getDice(int pl, int i) {

		if (pl == 1)
			return new ImageIcon(getClass().getResource(
					"/img/"
							+ this.controller.getCurrentGameSettings()
									.getPathDicePlayer1() + i + ".png"))
					.getImage();
		else
			return new ImageIcon(getClass().getResource(
					"/img/"
							+ this.controller.getCurrentGameSettings()
									.getPathDicePlayer2() + i + ".png"))
					.getImage();
	}

	public IControllerDelegate getController() {
		return this.controller;
	}

	private void handleDiceEvent(DiceEvent event) {

		if (event.getDiceType() == diceType.DICE) {

			// Würfel leeren
			this.imageBoard.getDices().clear();

			// System.out.println("Ich würfel");
			if (event.getPlayerID() == 0) {
				this.imageBoard.addDice(1, event.getDiceResult().get(0), 3);
				this.imageBoard.addDice(2, event.getDiceResult().get(1), 2);
			} else {
				int i = 1;
				for (int result : event.getDiceResult()) {
					this.imageBoard.addDice(event.getPlayerID(), result, i);
					i++;
				}
			}
		} else if (event.getDiceType() == diceType.DOUBLE_DICE) {
			this.imageBoard.setDoubleDice(event.getPlayerID(), event
					.getDiceResult().get(0));

			// Event beenden
			this.eventFinished();
		} else if (event.getDiceType() == diceType.NUMBERS_USED) {

			Vector<Integer> tmpList = new Vector<Integer>(event.getNumersUsed());
			// Den Würfel grau machen oder rot durchkreuzen
			// würfel suchen
			for (int i = 0; i < this.imageBoard.getDices().size(); i++) {

				BDice tmpDice = this.imageBoard.getDices().get(i);

				if (tmpList.size() == 0)
					return;

				if (tmpDice.getValue() == tmpList.get(0)) {
					// gefunden
					if (!tmpDice.isUsed()) {
						tmpDice.setUsed();
						tmpList.remove(0);
					}
				}

			}
			// Event beenden
			this.eventFinished();

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// System.out.println("Test1");

		if (e.getSource() == this.newGame) {
			
			this.hist.getList().clear();
			this.board.dispose();
			this.controller.exitGame();
		}
		if (e.getSource() == this.startGame) {

			if (this.imageBoard.getAnimation().isAnimating())
				return;

			// this.board.dispose();
			this.startGame.setVisible(false);
			this.showHistory.setVisible(true);

			this.controller.initGame();
		}
		if (e.getSource() == this.exitProgram) {

			this.destroyGUI();
			System.exit(0);
		}
		if (e.getSource() == this.showHistory) {

			this.hist.setVisible(true);
		}

	}

	public void handleBackgammonEvent(BackgammonEvent event) {

		System.out.println(event.getEventType().toString());

		// Event untersuchen
		if (event.getEventType() == BackgammonEvent.type.CHECKER_MOVE_RESULT) {
			CheckerMoveResultEvent tmp = (CheckerMoveResultEvent) event;
			if (tmp.getResult() != CheckerMoveResultEvent.moveResult.COMPUTER_DID_FINISH_MOVE)
				this.importantEventList.add(event);
			else
				this.normalEventList.add(event);
		} else if (event.getEventType() == BackgammonEvent.type.DICE) {
			DiceEvent tmp = (DiceEvent) event;
			if (tmp.getDiceType() == DiceEvent.diceType.DICE)
				this.importantEventList.add(event);
			else
				this.normalEventList.add(event);
		} else
			this.normalEventList.add(event);

		this.handleNormalEventList();

	}

	private void handleImportantEventList() {

		if (this.importantEventList.isEmpty())
			return;

		BackgammonEvent event = this.importantEventList.get(0);
		this.importantEventList.remove(0);

		if (event.getEventType() == BackgammonEvent.type.CHECKER_MOVE_RESULT) {
			this.handleCheckerMoveResultEvent((CheckerMoveResultEvent) event);
		} else if (event.getEventType() == BackgammonEvent.type.DICE) {
			this.handleDiceEvent((DiceEvent) event);
		}
		this.board.repaint();

	}

	private void handleNormalEventList() {

		if (!this.importantEventList.isEmpty()) {
			this.handleImportantEventList();
		}

		if (this.normalEventList.isEmpty())
			return;

		// Falls die Liste leer ist, dann nicht weitermachen
		if (this.imageBoard.getAnimation().isAnimating())
			return;

		this.board.repaint();

		BackgammonEvent event = this.normalEventList.firstElement();
		this.normalEventList.remove(0);

		if (event.getEventType() == BackgammonEvent.type.ACTIVE_PLAYER_INFO) {
			this.handleActivePlayerEvent((ActivePlayerInfoEvent) event);
			// Aktiven Spieler anzeigen
		} else if (event.getEventType() == BackgammonEvent.type.EXCEPTION) {
			this.handleException((ExceptionEvent) event);
		} else if (event.getEventType() == BackgammonEvent.type.INFO) {

			this.handleInfoEvent((InfoEvent) event);
		} else if (event.getEventType() == BackgammonEvent.type.DICE) {
			this.handleDiceEvent((DiceEvent) event);
		} else if (event.getEventType() == BackgammonEvent.type.POSSIBLE_MOVES) {
			this.handlePossibleMovesEvent((PossiblePlayerMovesEvent) event);
		} else if (event.getEventType() == BackgammonEvent.type.CHECKER_MOVE_RESULT) {
			this.handleCheckerMoveResultEvent((CheckerMoveResultEvent) event);
		}

		this.board.repaint();

		if (!this.normalEventList.isEmpty())
			this.handleNormalEventList();
	}

	private void handleInfoEvent(InfoEvent event) {

		if (event.getInfo() == InfoEvent.infoType.WIN) {

			// Dialogbox wer gewonnen hat.
			// Neues Spiel machen
			// falls ok, dann anzeigen.
			Object[] options = { "OK" };
			int n = JOptionPane.showOptionDialog(
					this.board,
					event.getPlayerName() + " hat gewonnen mit "
							+ event.getPoints() + " Punkten", "YEAH!",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, // do not use a
							// custom Icon
					options, // the titles of buttons
					options[0]); // default button title

			if (n == 0) {
				// annehmen
				this.hist.getList().clear();
				this.board.dispose();
				this.controller.exitGame();
				this.eventFinished();
			}

		}

	}

	private void handlePossibleMovesEvent(PossiblePlayerMovesEvent event) {

		this.imageBoard.setPossibleMoves(event.getPossibleMoves());

		// Event beenden
		this.eventFinished();
	}

	private void handleCheckerMoveResultEvent(CheckerMoveResultEvent event) {

		// System.out.println(event.getResult().toString());

		// Illegal move
		if (event.getResult() == CheckerMoveResultEvent.moveResult.ILLEGAL_MOVE) {
			// Info
			this.imageBoard.showInfo("Der Zug ist leider nicht gültig.");

			// invert direction
			event.getMove().invertDirection();

			// move
			this.moveChecker(event.getMove());
		} else if (event.getResult() == CheckerMoveResultEvent.moveResult.COMPUTER_DID_FINISH_MOVE) {
			// Computer ist fertig, also nächsten Move anstossen, vorher 1sek
			// warten.
			this.imageBoard.repaint();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.controller.initNextPlayerMove();

			// Event beenden
			this.eventFinished();
		} else if (event.getResult() == CheckerMoveResultEvent.moveResult.INIT) {
			this.moveChecker(event.getMove());
		} else if (event.getResult() == CheckerMoveResultEvent.moveResult.CORRECT_MOVE) {
			if (event.getMove() != null)
				this.moveChecker(event.getMove());
			else
				this.eventFinished();
		} else if (event.getResult() == CheckerMoveResultEvent.moveResult.HISTORY_MOVE) {

			this.hist.addListEntry(event.getMove());
			// Event beenden
			this.eventFinished();
		}

	}

	private void handleActivePlayerEvent(ActivePlayerInfoEvent event) {

		String name = event.getActivePlayer().getName();
		this.imageBoard.showInfo(name + " ist an der Reihe");

		// Event beenden
		this.eventFinished();

	}

	private void handleException(ExceptionEvent event) {
		String msg = "";
		if (event == null) {
			msg = "Es ist ein TestFehler aufgetreten.";
		} else if (event.getError() == ExceptionEvent.errorType.CHECKER_MOVE_DID_FAIL) {
			msg = "Es ist ein Fehler beim Ziehen eines Checkers aufgetreten.";
		} else if (event.getError() == ExceptionEvent.errorType.DICE_ROLL_DID_FAIL) {
			msg = "Es ist ein Fehler Würfeln aufgetreten.";
		} else {
			msg = "Es ist ein allgemeiner Fehler aufgetreten.";
		}

		Object[] options = { "Neues Spiel", "Programm beenden" };
		int n = JOptionPane.showOptionDialog(this.board, msg,
				"Es ist ein Fehler aufgetreten", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, // do not use a custom Icon
				options, // the titles of buttons
				options[0]); // default button title

		if (n == 0) {
			// Spiel neu starten
			// Event beenden
			this.eventFinished();

			this.board.dispose();
			this.controller.exitGame();
		} else {
			// Programm beenden
			// Event beenden
			this.eventFinished();

			this.board.dispose();
			this.destroyGUI();
			System.exit(1);
		}

	}

	public void eventFinished() {

		this.handleNormalEventList();

	}

	public Image getPossibleMove() {
		return new ImageIcon(getClass().getResource("/img/possibleMoves.png"))
				.getImage();
	}
}
