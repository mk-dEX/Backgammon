package backgammon.model;

import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;
import backgammon.event.PlayerMoveRequest;
import backgammon.listener.IModelEventListener;
import backgammon.model.board.DefaultBackgammonBoard;
import backgammon.model.interfaces.IBackgammonBoard;
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
		
	public void addDataModelListener(IModelEventListener listener) {
		
		this.listener = listener;
	}
	
	public void startGame() {
		
		player1.init(1);
		player2.init(2);
		
		
	}

	public IBackgammonBoard getBackgammonBoard() {
		return this.gameBoard;
	}
	
	public Move requestMove(IPlayer player) {
		
		int playerID = (player.equals(player1)) ? (1) : (2);
		PlayerMoveRequest request = new PlayerMoveRequest(playerID);
		
		Move resultingMove = this.listener.handlePlayerMoveRequest(request);
		
		return resultingMove;
	}
	
	public int testMove(Move newMove) {
		return 0;
	}
	
	public void handleMove(Move registeredMove) {
		
		if (registeredMove.getId() == 2) {
			if(registeredMove.getFromPoint() <= 23)
			{
				registeredMove.setFromPoint(23 - registeredMove.getFromPoint());
				registeredMove.setToPoint(23 - registeredMove.getToPoint());
			}
			
		}
		
		this.listener.handleCheckerMoveEvent(new CheckerMoveEvent(registeredMove));
		
	}

}
