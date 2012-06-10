package backgammon.listener;

import backgammon.event.BackgammonEvent;

/**
 * Interface für View-Klassen, die Events aus dem Datenmodell verarbeiten und visualisieren
 */
public interface IModelEventListener {

	/**
	 * Behandelt ein allgemeines {@link BackgammonEvent}
	 * @param event Das {@link BackgammonEvent}
	 */
	public void handleBackgammonEvent(BackgammonEvent event);
}
