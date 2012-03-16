package backgammon.model;

import backgammon.listener.IModelEventListener;

public interface IDataModel {
	
	public void addDataModelListener(IModelEventListener listener);
}
