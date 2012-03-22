package backgammon.event;

public class CheckerMoveEvent {

	public static enum moveType {
		SET_CHECKER,
		REMOVE_CHECKER
	}
	
	private moveType type;
	private int playerID;
	private int indexPoint;
	private int indexHeight;
	
	public CheckerMoveEvent(moveType type, int playerID, int indexPoint, int indexHeight) {
		this.type = type;
		this.playerID = playerID;
		this.indexPoint = indexPoint;
		this.indexHeight = indexHeight;
	}
	
	public moveType getMoveType() {
		return this.type;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}
	
	public int getIndexPoint() {
		return this.indexPoint;
	}
	
	public int getIndexHeight() {
		return this.indexHeight;
	}
	
}
