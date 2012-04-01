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
	
	public void reverseFromPoint(int maxIndex) {
		this.fromPoint = this.getReversedFromPoint(maxIndex);
	}
	
	public void setToPoint(int newToPoint) {
		this.toPoint = newToPoint;
	}
	
	public void reverseToPoint(int maxIndex) {
		this.toPoint = this.getReversedToPoint(maxIndex);
	}
	
	public boolean isSetMove() {
		return (this.fromPoint == this.toPoint);
	}
	
	public int getID() {
		return this.id;
	}

	public int getFromPoint() {
		return this.fromPoint;
	}
	
	public int getReversedFromPoint(int maxIndex) {
		return maxIndex - this.fromPoint;
	}

	public int getToPoint() {
		return this.toPoint;
	}
	
	public int getReversedToPoint(int maxIndex) {
		return maxIndex - this.toPoint;
	}

	public int getFromIndex() {
		return this.fromIndex;
	}

	public int getToIndex() {
		return toIndex;
	}
}
