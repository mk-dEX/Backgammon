package backgammon.controller;

import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;

public interface IControllerDelegate {
	
	public GameSettings getCurrentGameSettings();
	public void initGame();
	public void exitGame();
	
	/* InfoEvent welcher player(name / human?) ist an der reihe? "ist das der gleiche wie vorher" mit rein nehmen */
	/* IF computer gleich dice roll -> event & move -> MoveEvent */
	/* ComputerDidFinish Event --> GUI initNextPlayerMove */
	public void initNextPlayerMove();
	
	/* ist der angeklickte player an der Reihe? */
	public boolean startMove(int playerID, int fromPoint);
	/*multiple CheckerMoveResultEvent*/
	public void endMove(CheckerMoveEvent moveEvent);
	
	/*DiceEvent*/
	public void startDoubleOffer(int playerID);
	/*DiceEvent ; playerID = 0 == spiel beendet*/
	public void offerAccepted(boolean didAccept);
	
	//getCurrentPlayerId
	public int getCurrentPlayerID();
	
	public boolean gameStarted();
}
