package backgammon.model.interfaces;

import backgammon.event.CheckerMoveEvent;
import backgammon.listener.IModelEventListener;

public interface IDataModel {
	public void addDataModelListener(IModelEventListener listener);
	
	public void initGameCheckers();
	public void initGame();
	
	public void initNextPlayerMove();
	public boolean startMove(int playerID);
	public void endMove(CheckerMoveEvent moveEvent);
	
	public void startDoubleOffer(int playerID);
	public void offerAccepted(boolean didAccept);
	
	public boolean gameStarted();
}
