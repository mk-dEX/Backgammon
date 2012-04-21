package backgammon.model.player;

public class Move {

	protected int id;
	
	protected int fromPoint;
	protected int toPoint;
	protected int fromIndex;
	protected int toIndex;
	
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
	
	public void setFromIndex(int newFromIndex) {
		this.fromIndex = newFromIndex;
	}
	
	public void setToIndex(int newToIndex) {
		this.toIndex = newToIndex;
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

	public int getToPoint() {
		return this.toPoint;
	}

	public int getFromIndex() {
		return this.fromIndex;
	}

	public int getToIndex() {
		return toIndex;
	}
	public void invertDirection()
	{
		int tmp = this.fromIndex;
		this.fromIndex = this.toIndex;
		this.toIndex = tmp;
		
		tmp = this.fromPoint;
		this.fromPoint = this.toPoint;
		this.toPoint = tmp;
	}
}
