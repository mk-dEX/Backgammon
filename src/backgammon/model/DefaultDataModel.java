package backgammon.model;

import backgammon.app.GameSettings;
import backgammon.listener.IModelEventListener;

public class DefaultDataModel implements IDataModel {
	
	private IModelEventListener listener;
	private GameSettings settings;
	
	public DefaultDataModel(GameSettings currentSettings) {
		this.settings = currentSettings;
		
		
	}
	
	public void addDataModelListener(IModelEventListener listener) {
		
		this.listener = listener;
	}
	
	public void startGame() {
		
		
	}
		
}
