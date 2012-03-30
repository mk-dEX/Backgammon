package backgammon.controller;

import backgammon.app.GameSettings;
import backgammon.event.CheckerMoveEvent;

public interface IControllerDelegate {
	
	public GameSettings getCurrentGameSettings();
	public void initGame();
	public void exitGame();
	
	/*boolean*/
	public void initNextPlayerMove();
	
	/*CheckerMoveResultEvent*/
	public void startMove(CheckerMoveEvent moveEvent);
	/*Vector<CheckerMoveResultEvent>*/
	public void endMove(CheckerMoveEvent moveEvent);
	
	/*boolean*/
	public void startDoubleOffer(int playerID);
	public void offerAccepted(boolean didAccept);
}
