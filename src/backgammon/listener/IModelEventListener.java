package backgammon.listener;

import backgammon.event.BackgammonEvent;

public interface IModelEventListener {

	public int handleBackgammonEvent(BackgammonEvent event);
}
