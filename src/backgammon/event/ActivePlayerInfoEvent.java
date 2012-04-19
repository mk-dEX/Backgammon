package backgammon.event;

public class ActivePlayerInfoEvent extends BackgammonEvent {

	private String name;
	private boolean isHuman;
	
	public ActivePlayerInfoEvent(String playerName, boolean isHuman) {
		super(BackgammonEvent.type.ACTIVE_PLAYER_INFO);
		this.name = playerName;
		this.isHuman = isHuman;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isHuman() {
		return this.isHuman;
	}
	
}
