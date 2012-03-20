package backgammon.model.player;

import java.util.Calendar;
import java.util.Random;

public class Dice {
	
	// Standard WŸrfel
	private int min = 1;
	private int max = 6;
	
	public void setRange(int min, int max) {
		if (min < max && min >= 0 && max >= 0) {
			this.min = min;
			this.max = max;
		}
	}
	
	public int roll() {
		Random generator = new Random(Calendar.getInstance().getTimeInMillis());
		return generator.nextInt(this.max) + this.min;
	}
	
}
