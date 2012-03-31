package backgammon.event;

public class ActivePlayerInfoEvent {

	private String name;
	private boolean isHuman;
	
	public ActivePlayerInfoEvent(String playerName, boolean isHuman) {
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
