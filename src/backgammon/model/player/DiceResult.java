package backgammon.model.player;

import java.util.Vector;

public class DiceResult extends Vector<Integer> {
	private static final long serialVersionUID = 1L;
	
	
	//Handle move request
	public Vector<Integer> makeMove(Integer distance) {
		
		Vector<Integer> removedValues = new Vector<Integer>();
		
		if (this.baseResultContainsDistance(distance)) {
			
			this.remove(distance);
			removedValues.add(distance);
			
		} else if (this.composedResultContainsDistance(distance)) {
			
			removedValues = this.removeComposedDistance(distance);
		}
		
		return removedValues;
	}
	
	
	//All distances
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
	
	
	//Available?
	public boolean baseResultContainsDistance(Integer distance) {
		return this.contains(distance);
	}
	public Integer baseResultContainsDistanceOrGreater(Integer distance) {
		
		for (Integer number : this) {
			if (number >= distance) {
				return number;
			}
		}
		
		return 0;
	}
	public boolean composedResultContainsDistance(Integer distance) {
		return this.getPossibleMoveDistances().contains(distance);
	}
	public Integer composedResultContainsDistanceOrGreater(Integer distance) {
		
		for (Integer number : this.getPossibleMoveDistances()) {
			if (number >= distance) {
				return number;
			}
		}
		
		return 0;
	}
	
	
 	private Vector<Integer> removeComposedDistance(Integer distance) {
		
 		Vector<Integer> numbersToBeRemoved = getNumbersForComposedDistance(distance);
 		for (Integer aNumber : numbersToBeRemoved) {
			this.remove(aNumber);
		}
 		
 		return numbersToBeRemoved;
	}
	private Vector<Integer> getNumbersForComposedDistance(Integer distance) {
		Vector<Integer> theNumbers = new Vector<Integer>();
		Integer baseValue = this.elementAt(0);
		if (baseValue.intValue() == 0)
			return theNumbers;
		int iteratorCounterMax = distance / baseValue;
		
		
		for (int removeCounter = 0; removeCounter < iteratorCounterMax; removeCounter++) {
			theNumbers.add(baseValue);
		}
		
		return theNumbers;
	}
}
