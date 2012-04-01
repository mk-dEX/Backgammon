package backgammon.model.player;

import java.util.Vector;

public class DiceResult extends Vector<Integer> {
	private static final long serialVersionUID = 1L;
	
	public static final int ILLEGAL_NUMBER = -2;
	public static final int ILLEGAL_DISTANCE = -1;
	public static final int STANDARD_DISTANCE = 0;
	public static final int COMPOSED_DISTANCE = 1;
	
	public int makeMove(Integer distance) {
		
		if (this.contains(distance)) {
			
			this.remove(distance);
			return DiceResult.STANDARD_DISTANCE;
			
		} else if (this.getPossibleMoveDistances().contains(distance)) {
			
			try { this.removeComposedDistance(distance); }
			catch (Exception e) { 
				e.printStackTrace();
				return DiceResult.ILLEGAL_NUMBER;
			}
			
			return DiceResult.COMPOSED_DISTANCE;
			
		} else {
			
			return DiceResult.ILLEGAL_DISTANCE;
		}
	}
	
	public Vector<Integer> getPossibleMoveDistances() {
		
		Vector<Integer> numbers = new Vector<Integer>();
		for (Integer number : this) {
			numbers.add(number);
		}
		
		int size = numbers.size();
		
		// doublets
		if (size > 2) {
			
			int baseValue = numbers.elementAt(0);
			numbers.removeAllElements();
			for (int currentMultiplicator = 1; currentMultiplicator <= size; currentMultiplicator++) {
				numbers.add(new Integer(baseValue * currentMultiplicator));
			}
			
		
		// standard
		} else if (size == 2) {
			
			numbers.add(new Integer(numbers.elementAt(0).intValue() + numbers.elementAt(1).intValue()));
		}
		
		return numbers;
	}
	
	private void removeComposedDistance(Integer distance) throws Exception {
		
		Integer baseValue = this.elementAt(0);
		int iteratorCounterMax = distance / baseValue;
		
		for (int removeCounter = 0; removeCounter < iteratorCounterMax; removeCounter++) {
			this.remove(baseValue);
		}
	}
}
