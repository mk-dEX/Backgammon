package backgammon.model.interfaces;

import backgammon.listener.IModelEventListener;

public interface IDataModel {
	public void addDataModelListener(IModelEventListener listener);
	public void initGameCheckers();
	public void initGame();

}
