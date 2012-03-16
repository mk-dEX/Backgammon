package backgammon.model;

import backgammon.listener.IModelEventListener;

public class DefaultDataModel implements IDataModel {

	IModelEventListener view;

	public void addDataModelListener(IModelEventListener listener) {
		
		this.view = listener;
	}
		
}
