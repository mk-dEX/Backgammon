package backgammon.listener;

import backgammon.event.BackgammonEvent;

public interface IModelEventListener {

	public void handleBackgammonEvent(BackgammonEvent event);
}
