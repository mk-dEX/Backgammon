package backgammon.model;

import java.util.Vector;
import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;
import backgammon.event.DiceEvent;
import backgammon.event.PlayerMoveRequest;
import backgammon.listener.IModelEventListener;
import backgammon.model.board.DefaultBackgammonBoard;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.interfaces.IDataController;
import backgammon.model.interfaces.IPlayer;
import backgammon.model.player.ComputerPlayer;
import backgammon.model.player.DiceResult;
import backgammon.model.player.HumanPlayer;
import backgammon.model.player.Move;

public class DefaultDataModel implements IDataController {

	private IModelEventListener listener;
	private GameSettings settings;

	private IPlayer player1;
	private IPlayer player2;
	private IPlayer nextPlayer;
	private IBackgammonBoard gameBoard;

	
	public DefaultDataModel(GameSettings currentSettings) {
		this.settings = currentSettings;
		this.gameBoard = new DefaultBackgammonBoard();

		GameSettings.KIMode KIModePlayer1 = this.settings.getSelectedKIModePlayer1();
		GameSettings.KIMode KIModePlayer2 = this.settings.getSelectedKIModePlayer2();
		String namePlayer1 = this.settings.getNamePlayer1();
		String namePlayer2 = this.settings.getNamePlayer2();

		if (KIModePlayer1.equals(GameSettings.KIMode.HUMAN)) {
			player1 = new HumanPlayer(namePlayer1, this);
		} else {
			player1 = new ComputerPlayer(namePlayer1, this, KIModePlayer1);
		}

		if (KIModePlayer2.equals(GameSettings.KIMode.HUMAN)) {
			player2 = new HumanPlayer(namePlayer2, this);
		} else {
			player2 = new ComputerPlayer(namePlayer2, this, KIModePlayer2);
		}
	}
	
	
	protected void initCheckersOfPlayer(int playerID) {
		Move tempRegisteredMove;
		int j = 14;

		// IndexPoint 0
		for (int i = 0; i < 2; i++) {

			tempRegisteredMove = new Move(playerID, 25, j, 0, i);
			this.pushCheckerMoveEvent(tempRegisteredMove);
			j--;		
		}

		// IndexPoint 11 & 18
		for (int i = 0; i < 5; i++) {

			tempRegisteredMove = new Move(playerID, 25, j, 11, i);
			this.pushCheckerMoveEvent(tempRegisteredMove);
			j--;
			
			tempRegisteredMove = new Move(playerID, 25, j, 18, i);
			this.pushCheckerMoveEvent(tempRegisteredMove);
			j--;
		}

		// IndexPoint 16
		for (int i = 0; i < 3; i++) {

			tempRegisteredMove = new Move(playerID, 25, j, 16, i);
			this.pushCheckerMoveEvent(tempRegisteredMove);
			j--;	
		}
	}
	public void initGameCheckers() {

		// Player initialization
		// checkers are placed
		this.initCheckersOfPlayer(1);
		this.initCheckersOfPlayer(2);
	}
	
	protected DiceResult getDiceResult(IPlayer currentPlayer, boolean initial) throws Exception {
		
		if (!initial && currentPlayer == null) {
			throw new Exception();
		}
		
		Integer diceResult1;
		Integer diceResult2;
		
		do {
			diceResult1 = (initial) ? (this.player1.rollDice(1, 6)) : (currentPlayer.rollDice(1, 6));
			try {Thread.sleep(5);} catch (Exception e) { e.printStackTrace(); }
			diceResult2 = (initial) ? (this.player2.rollDice(1, 6)) : (currentPlayer.rollDice(1, 6));
			try {Thread.sleep(5);} catch (Exception e) { e.printStackTrace(); }
			
		} while (initial && diceResult1.equals(diceResult2));
		
		int playerID;
		if (initial) { playerID = 0; } 
		else { playerID = (currentPlayer.equals(this.player1)) ? (1) : (2);	}
		
		DiceResult diceResults = new DiceResult();
		int factor = (initial && diceResult1.equals(diceResult2)) ? (1) : (2);
		while (factor > 0) {
			diceResults.add(diceResult1);
			diceResults.add(diceResult2);
			factor--;
		}
		DiceEvent diceEvent = new DiceEvent(DiceEvent.diceType.DICE, playerID, diceResults);
		this.listener.handleDiceEvent(diceEvent);
		
		return diceResults;
	}
	protected Vector<Integer> getNumbersUsed(Move move, DiceResult diceResult) {
		Vector<Integer> numbersUsed = new Vector<Integer>();
		//TODO get all numbers which are used for the move
		return numbersUsed;
	}
	protected void executeMove(IPlayer currentPlayer, Move move) {
		//TODO set chips on board
	}
	protected void controlPlayerMove(IPlayer currentPlayer, DiceResult diceResultsForPlayer) {
		
		Move currentMove;
		while (!diceResultsForPlayer.isEmpty()) {
			
			currentMove = currentPlayer.move(diceResultsForPlayer);
			Vector<Integer> numbersUsed = this.getNumbersUsed(currentMove, diceResultsForPlayer);
			for (Integer number : numbersUsed) {
				diceResultsForPlayer.remove(number);
			}
		}
	}
	public void initNextPlayerMove() {
		
		this.nextPlayer = (this.nextPlayer.equals(this.player1)) ? (this.player2) : (this.player1);
		
		DiceResult nextDiceResult = null;
		try {
			nextDiceResult = this.getDiceResult(this.nextPlayer, false);
		} catch (Exception e) { e.printStackTrace(); System.exit(0); }
		
		this.controlPlayerMove(this.nextPlayer, nextDiceResult);
		
	}
	public void initGame() {
		
		DiceResult diceResult = null;
		try { 
			diceResult = this.getDiceResult(null, true); 
		} catch (Exception e) { e.printStackTrace(); System.exit(0); }
		
		// Player who wins the start roll begins the game with a move
		this.nextPlayer = (diceResult.elementAt(0) > diceResult.elementAt(1)) ? (this.player1) : (this.player2);
		this.controlPlayerMove(this.nextPlayer, diceResult);
		
	}
	
	

	
	public Move requestMove(IPlayer player) {

		int playerID = (player.equals(player1)) ? (1) : (2);
		PlayerMoveRequest request = new PlayerMoveRequest(playerID);

		Move resultingMove = this.listener.handlePlayerMoveRequest(request);

		return resultingMove;
	}
	public int checkMove(Move move, DiceResult diceResult) {
		
		//TODO check move if illegal
		return 0;
	}

	
	protected void pushCheckerMoveEvent(Move move) {
		
		if (move.getID() == 2) {
			
			if (move.getFromPoint() < IBackgammonBoard.BAR_INDEX) {
				move.setFromPoint((IBackgammonBoard.BAR_INDEX - 1) - move.getFromPoint());
			}
			if (move.getToPoint() < IBackgammonBoard.BAR_INDEX) {
				move.setToPoint((IBackgammonBoard.BAR_INDEX - 1) - move.getToPoint());
			}

		}

		this.listener.handleCheckerMoveEvent(new CheckerMoveEvent(move));
	}
	protected ICheckerList getCheckerListForIndex(int index) throws Exception{
		
		if (index < IBackgammonBoard.BAR_INDEX) {
			return this.gameBoard.getPointAtIndex(index);
		
		} else if (index == IBackgammonBoard.BAR_INDEX) {
			return this.gameBoard.getBar();
					
		} else if (index == IBackgammonBoard.OUT_PLAYER1_INDEX) {
			return this.gameBoard.getOut(1);
			
		} else if (index == IBackgammonBoard.OUT_PLAYER2_INDEX) {
			return this.gameBoard.getOut(2);
		
		} else {
			throw new Exception();
			
		}
	}
	
	
	
	public void addDataModelListener(IModelEventListener listener) {

		this.listener = listener;
	}
	public IBackgammonBoard getBackgammonBoard() {
		return this.gameBoard;
	}
}
