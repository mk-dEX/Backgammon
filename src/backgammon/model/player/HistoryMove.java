package backgammon.model.player;

public class HistoryMove extends Move {

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
