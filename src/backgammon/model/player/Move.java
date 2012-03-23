package backgammon.model.player;

public class Move {

	private int id;
	
	private int fromPoint;
	private int toPoint;
	private int fromIndex;
	private int toIndex;
	
	public Move(int id, int fromX, int fromY ,int toX, int toY) {
		this.id = id;
		this.fromPoint = fromX;
		this.fromIndex = fromY;
		this.toPoint = toX;
		this.toIndex = toY;
	}
	
	public void setFromPoint(int newFromPoint) {
		this.fromPoint = newFromPoint;
	}
	
	public void setToPoint(int newToPoint) {
		this.toPoint = newToPoint;
	}
	
	public boolean isSetMove() {
		return (this.fromPoint == this.toPoint);
	}
	
	public int getID() {
		return this.id;
	}

	public int getId() {
		return id;
	}

	public int getFromPoint() {
		return fromPoint;
	}

	public int getToPoint() {
		return toPoint;
	}

	public int getFromIndex() {
		return fromIndex;
	}

	public int getToIndex() {
		return toIndex;
	}
}
