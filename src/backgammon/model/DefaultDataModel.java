package backgammon.model;

import backgammon.app.GameSettings;
import backgammon.listener.IModelEventListener;
import backgammon.model.board.DefaultBackgammonBoard;
import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.IDataController;
import backgammon.model.interfaces.IPlayer;
import backgammon.model.player.ComputerPlayer;
import backgammon.model.player.HumanPlayer;

public class DefaultDataModel implements IDataController {
	
	private IModelEventListener listener;
	private GameSettings settings;
	
	private IPlayer player1;
	private IPlayer player2;
	private IBackgammonBoard gameBoard;
	
	public DefaultDataModel(GameSettings currentSettings) {
		this.settings = currentSettings;
		this.gameBoard = new DefaultBackgammonBoard(this);
		initPlayer(player1, this.settings.getNamePlayer1(), this.settings.getSelectedKIModePlayer1());
		initPlayer(player2, this.settings.getNamePlayer2(), this.settings.getSelectedKIModePlayer2());
	}
	
	private void initPlayer(IPlayer player, String name, GameSettings.KIMode mode) {
		if (mode.equals(GameSettings.KIMode.HUMAN)) {
			player = new HumanPlayer(name, this);
		} else {
			player = new ComputerPlayer(name, this, mode);
		}
		player.init();
	}
		
	public void addDataModelListener(IModelEventListener listener) {
		
		this.listener = listener;
	}
	
	public void startGame() {
		
		
	}

	public IPlayer getPlayer(int playerID) {
		if (playerID == 1)
			return this.player1;
		return this.player2;
	}

	public IBackgammonBoard getBackgammonBoard() {
		return this.gameBoard;
	}

}
