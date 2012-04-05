package backgammon.event;

public class PlayerMoveRequest {

	private int playerID;
	
	public PlayerMoveRequest(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}
	
}
