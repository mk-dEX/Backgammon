package backgammon.model.player;

import java.util.Vector;

/**
 * Enthält ein Würfelergebnis sowie alle daraus resultierenden Augenzahlen 
 */
public class DiceResult extends Vector<Integer> {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Entfernt alle Augenzahlen aus dem Ergebnis oder einer Kombination, die für eine Gesamtaugenzahl verwendet werden
	 * @param distance Die Gesamtaugenzahl
	 * @return Die Zahlen, die aus dem Ergebnis entfernt wurden
	 */
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
	
	
	/**
	 * @return Alle durch die Augenzahl oder deren Kombination möglichen Zugdistanzen
	 */
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
	
	
	/**
	 * @param distance Die gesuchte Distanz
	 * @return true wenn das Würfelergebnis die Distanz direkt enthält. Sonst false
	 */
	public boolean baseResultContainsDistance(Integer distance) {
		return this.contains(distance);
	}
	
	/**
	 * @param distance Die gesuchte Distanz
	 * @return Die entsprechende Augenzahl des Würfelergebnisses, wenn es die Distanz enthält oder größer ist. Sonst false
	 */
	public Integer baseResultContainsDistanceOrGreater(Integer distance) {
		
		for (Integer number : this) {
			if (number >= distance) {
				return number;
			}
		}
		
		return 0;
	}
	
	/**
	 * @param distance Die gesuchte Distanz
	 * @return true wenn eine Kombination des Würfelergebnisses die Distanz enthält. Sonst false
	 */
	public boolean composedResultContainsDistance(Integer distance) {
		return this.getPossibleMoveDistances().contains(distance);
	}
	
	/**
	 * @param distance Die gesuchte Distanz
	 * @return Die entsprechende Augenzahl des Würfelergebnisses oder einer Kombination, wenn eine davon die Distanz enthält oder größer ist. Sonst false
	 */
	public Integer composedResultContainsDistanceOrGreater(Integer distance) {
		
		for (Integer number : this.getPossibleMoveDistances()) {
			if (number >= distance) {
				return number;
			}
		}
		
		return 0;
	}
	
	/**
	 * Entfernt alle Augenzahlen aus dem Würfelergebnis, die für eine Distanz verwendet werden
	 * @param distance Die gesuchte Distanz
	 * @return Die Augenzahlen, die entfernt wurden
	 */
 	private Vector<Integer> removeComposedDistance(Integer distance) {
		
 		Vector<Integer> numbersToBeRemoved = getNumbersForComposedDistance(distance);
 		for (Integer aNumber : numbersToBeRemoved) {
			this.remove(aNumber);
		}
 		
 		return numbersToBeRemoved;
	}
 	
 	/**
 	 * @param distance Die gesuchte Distanz
 	 * @return Alle Augenzahlen, die für die Distanz notwendig sind
 	 */
	@SuppressWarnings("unchecked")
	private Vector<Integer> getNumbersForComposedDistance(Integer distance) {
		Vector<Integer> theNumbers = new Vector<Integer>();
		
		if (this.size() == 2) {
			Integer firstPossibleElement = this.elementAt(0);
			Integer secondPossibleElement = this.elementAt(1);
			if (firstPossibleElement.intValue() + secondPossibleElement.intValue() == distance.intValue()) {
				theNumbers = (Vector<Integer>)this.clone();
			}
		}
		
		if (theNumbers.isEmpty()) {
			Integer baseValue = this.elementAt(0);
			if (baseValue.intValue() == 0)
				return theNumbers;
			int iteratorCounterMax = distance / baseValue;


			for (int removeCounter = 0; removeCounter < iteratorCounterMax; removeCounter++) {
				theNumbers.add(baseValue);
			}
		}
		
		return theNumbers;
	}
}
