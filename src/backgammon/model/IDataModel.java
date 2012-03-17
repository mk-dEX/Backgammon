package backgammon.model;

import backgammon.app.GameSettings;
import backgammon.listener.IModelEventListener;

public interface IDataModel {
	public void addDataModelListener(IModelEventListener listener);
	public void startGame();
}
