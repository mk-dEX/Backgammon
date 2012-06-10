package backgammon.model.player;

/**
 * Kapselung eines Zuges mit einem Spielstein
 */
public class Move {

	/**
	 * Die ID des Spielers, der zieht
	 */
	protected int id;
	
	/**
	 * Der Point, von dem aus gezogen wurde
	 */
	protected int fromPoint;
	/**
	 * Der Point, zu dem gezogen wird
	 */
	protected int toPoint;
	/**
	 * Die Position des Spielsteins innerhalb des Points, von dem gezogen wurde
	 */
	protected int fromIndex;
	/**
	 * Die Position des Spielsteins innerhalb des Points, zu dem gezogen wird
	 */
	protected int toIndex;
	
	/**
	 * Der Ausgangspunkt soll auf den Zielpunkt gesetzt werden
	 */
	protected boolean equalize = false;
	
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
	
	/**
	 * @return true wenn Zugquelle == Zugziel. Sonst false
	 */
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
		return this.toIndex;
	}
	
	/**
	 * Der Zug wird in die entgegengesetzte Richtung durchgefŸhrt
	 */
	public void invertDirection()
	{
		int tmp = this.fromIndex;
		this.fromIndex = this.toIndex;
		this.toIndex = tmp;
		
		tmp = this.fromPoint;
		this.fromPoint = this.toPoint;
		this.toPoint = tmp;
	}
	
	public void setEqual(boolean enable) {
		this.equalize = enable;
	}
	
	public boolean equalize() {
		return this.equalize;
	}
	
	@Override
	public String toString() {
		return "[" + this.id + "] : (" + this.fromPoint + ":" + this.fromIndex + ") -> (" + this.toPoint + ":" + this.toIndex + ")";
	}
}
