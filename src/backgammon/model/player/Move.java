package backgammon.model.player;

public class Move {

	private int id;
	private Index from = new Index();
	private Index to = new Index();
	
	public Move(int id, int fromX, int fromY ,int toX, int toY) {
		this.id = id;
		this.from.indexPoint = fromX;
		this.from.indexPosition = fromY;
		this.to.indexPoint = toX;
		this.to.indexPosition = toY;
	}
	
	public boolean isSetMove() {
		return (this.from.indexPoint == this.to.indexPoint);
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getIndexPointFrom() {
		return this.from.indexPoint;
	}
	
	public int getIndexPointTo() {
		return this.from.indexPosition;
	}
	
	public int getIndexPositionFrom() {
		return this.to.indexPoint;
	}
	
	public int getIndexPositionTo() {
		return this.to.indexPosition;
	}
	
	private class Index {
		int indexPoint;
		int indexPosition;
	}
	
}
