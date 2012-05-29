package backgammon.model.player;

/**
 * Ableitung eines Move zur Speicherung in der History
 */
public class HistoryMove extends Move {

	/**
	 * Die ID des {@link Player}, der zur Zeit des {@link Move} an der Reihe war.
	 * Dies ist insbesondere wichtig, wenn während eines Zuges der Spielstein eines Spielers vom Feld geworfen wurde. Wenn der Spieler, der im {@link Move} hinterlegt ist, also nicht an der Reihe war
	 */
	private int historyPlayerID;
	
	public HistoryMove(Move moveToBeStored, int playerIDOfCurrentPlayer) {
		super(	moveToBeStored.getID(),
				moveToBeStored.getFromPoint(),
				moveToBeStored.getFromIndex(),
				moveToBeStored.getToPoint(),
				moveToBeStored.getToIndex());
		
		this.historyPlayerID = playerIDOfCurrentPlayer;
	}
	
	public int getHistoryPlayerID() {
		return this.historyPlayerID;
	}
}
