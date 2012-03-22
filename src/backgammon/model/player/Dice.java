package backgammon.model.player;

import java.util.Calendar;
import java.util.Random;

public class Dice {
	
	public int roll(int min, int max) {
		
		Random generator = new Random(Calendar.getInstance().getTimeInMillis());
		
		if (min < max && min >= 0 && max >= 0) {
			return generator.nextInt(max) + min;
		} else {
			return generator.nextInt(6) + 1;
		}
	}
	
}
