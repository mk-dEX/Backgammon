package backgammon.model.board;

import backgammon.model.interfaces.IBackgammonBoard;
import backgammon.model.interfaces.ICheckerList;
import backgammon.model.interfaces.IDataController;

public class DefaultBackgammonBoard implements IBackgammonBoard {
	
	private final int numberOfPoints = 24;
	
	private ICheckerList[] points = new Point[this.numberOfPoints];
	private ICheckerList bar = new Bar();
	private ICheckerList outPlayer1 = new Out();
	private ICheckerList outPlayer2 = new Out();
	
	public DefaultBackgammonBoard(IDataController rootDataController) {
		for (int i = 0; i < this.numberOfPoints; i++) {
			this.points[i] = new Point();
		}
	}

	public ICheckerList getPointAtIndex(int index) {
		if (index >= 0 && index < this.numberOfPoints)
			return this.points[index];
		return null;
	}

	public ICheckerList getBar() {
		return this.bar;
	}

	public ICheckerList getOut(int playerID) {
		if (playerID == 1)
			return this.outPlayer1;
		return this.outPlayer2;
	}
	
}
