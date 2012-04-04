package backgammon.view.helpers;

import java.util.Vector;

public class BChecker {
	private int point;
	private int index;
	private int player;
	private Place position;
	private BPosition coords;

	public static enum Place {
		BOARD, BAR, OUT
	}

	BChecker(Place field, int player, int point, int index) 
	{
		this.point = point;
		this.index = index;
		this.player = player;
		this.position = field;

		this.setCoordsFromPointAndIndex();
	}

	public void setCoordsFromPointAndIndex() {

		Vector<BPosition> tmp = ImageBoard.getPoisitionMatrix();

		int x, y = 0;

		if (this.position == Place.BOARD) {
			x = tmp.get(point).getX();
			y = tmp.get(point).getY() + ImageBoard.getIndex(point, index);
		} else if (this.position == Place.BAR) {
			x = ImageBoard.getBarPosition(player).getX();
			y = ImageBoard.getBarPosition(player).getY()
					+ ImageBoard.getOBIndex(player, index);
		} else {
			x = ImageBoard.getOutPosition(player).getX();
			y = ImageBoard.getOutPosition(player).getY()
					+ ImageBoard.getOBIndex(player, index);
		}

		this.coords = new BPosition(x, y);

	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;

		this.setCoordsFromPointAndIndex();

	}

	protected Place getPosition() {
		return position;
	}

	protected void setPosition(Place position) {
		this.position = position;

		// this.setCoordsFromPointAndIndex();
	}

	public int getIndex() {
		return index;
	}

	public int getPlayer() {
		return player;
	}

	public void setIndex(int index) {
		this.index = index;

		this.setCoordsFromPointAndIndex();
	}

	public void setCoords(int x, int y) {
		this.coords = new BPosition(x, y);
	}

	public BPosition getCoords() {
		return this.coords;
	}

	public void setPointIndex(int point, int index) {
		this.point = point;
		this.index = index;

		this.setCoordsFromPointAndIndex();
	}
}