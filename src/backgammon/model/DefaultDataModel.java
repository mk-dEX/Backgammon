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
import backgammon.model.player.HumanPlayer;
import backgammon.model.player.Move;

public class DefaultDataModel implements IDataController {

	private IModelEventListener listener;
	private GameSettings settings;

	private IPlayer player1;
	private IPlayer player2;
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
	
	
	protected void controlPlayerMove(IPlayer currentPlayer, Vector<Integer> diceResultsForPlayer) {
		
		
	}
	public void initGame() {
		
		// Players roll dice
		Integer diceResultPlayer1;
		Integer diceResultPlayer2;
		
		do {
			diceResultPlayer1 = new Integer(player1.rollDice(1, 6));
			diceResultPlayer2 = new Integer(player2.rollDice(1, 6));
			
		} while(diceResultPlayer1.equals(diceResultPlayer2));
		
		Vector<Integer> diceResults = new Vector<Integer>();
		diceResults.add(diceResultPlayer1);
		diceResults.add(diceResultPlayer2);
		
		DiceEvent initialDiceEvent = new DiceEvent(DiceEvent.diceType.DICE, 0, diceResults);
		this.listener.handleDiceEvent(initialDiceEvent);
		
		// Player who wins the start roll begins the game with a move
		IPlayer startPlayer = (diceResultPlayer1.intValue() > diceResultPlayer2.intValue()) ? (this.player1) : (this.player2);
		this.controlPlayerMove(startPlayer, diceResults);
		
	}

	
	public Move requestMove(IPlayer player) {

		int playerID = (player.equals(player1)) ? (1) : (2);
		PlayerMoveRequest request = new PlayerMoveRequest(playerID);

		Move resultingMove = this.listener.handlePlayerMoveRequest(request);

		return resultingMove;
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
	public int handleMove(Move registeredMove, Vector<Integer> numbers) {

		int srcIndex = registeredMove.getFromIndex();
		int dstIndex = registeredMove.getToIndex();
		ICheckerList src;
		ICheckerList dst;
		IPlayer currentPlayer = (registeredMove.getID() == 1) ? (this.player1) : (this.player2);
		
		try {
			src = this.getCheckerListForIndex(srcIndex);
			dst = this.getCheckerListForIndex(dstIndex);
		
		} catch(Exception e) { return -1; }
		
		if (!dst.isBlockedForPlayer(currentPlayer)) {
			return 0;
		}
		
		
		this.pushCheckerMoveEvent(registeredMove);
		return 0;

	}
	
	
	public void addDataModelListener(IModelEventListener listener) {

		this.listener = listener;
	}
	public IBackgammonBoard getBackgammonBoard() {
		return this.gameBoard;
	}
}
