package backgammon.view.helpers;

import java.util.Vector;
import java.util.Calendar;
import java.util.Random;

public class DiceMoveAnimationManager implements Runnable {

	private AnimationEntry dice;
	private ImageBoard board;
	private int duration = 1500;
	private final int pause = 30;
	private final int Rpause = 240;
	private Thread thread;
	private long startTime;
	private int deltaMoveX;
	private int deltaMoveY;
	private Vector<AnimationEntry> animationqueue;
	private boolean animating = false;

	public DiceMoveAnimationManager(ImageBoard board) {
		this.board = board;
		this.animationqueue = new Vector<AnimationEntry>();
	}

	public void addMoveAnimation(BDice dice, int toX, int toY) {
		AnimationEntry tmp = new AnimationEntry(dice, toX, toY);

		this.animationqueue.add(tmp);
	
		if(!animating)
			this.startAnimation();

	}

	private void startAnimation() {
		
		animating = true;
		
		AnimationEntry tmp = this.animationqueue.get(0);
		
		//System.out.println(Integer.toString(deltaMoveX) + " - " + Integer.toString(deltaMoveY));
		
		this.dice = tmp;
		tmp.dice.setRValue(this.roll(1, 6));
		tmp.dice.setRotation(0);
		
		//Zufallswert holen
		
		PHitBox bP = this.board.getPlayerBoard(tmp.dice.getPlayer());
		
		int tmpX = this.roll(bP.getX1(), bP.getX2());
		int tmpY = this.roll(bP.getY1(), bP.getY2());
		
		this.dice.dice.setCoords(tmpX, tmpY);
		//Ende zufallswert
		
		deltaMoveX = (int) (((tmp.toX+0) - tmp.dice.getX()) / Math.ceil((this.duration / (this.pause-0)))) ;
		deltaMoveY = (int) (((tmp.toY+0) - tmp.dice.getY()) / Math.ceil((this.duration / (this.pause-0)))) ;
		
		this.animationqueue.remove(0);

		this.thread = new Thread(this);

		this.thread.start();
	}

	private void endAnimation() {
		if (this.thread != null)
			this.thread = null;

		// System.out.println(Integer.toString(finalX));
		// System.out.println(Integer.toString(finalY));
		//System.out.println(Integer.toString(toPoint));
		//System.out.println(Integer.toString(toIndex));
		
		this.dice.dice.setRValue(this.dice.dice.getValue());
		//this.dice.dice.setCoords(this.dice.toX, this.dice.toY);
		this.board.repaint();
		if(!this.animationqueue.isEmpty())
		{
			this.startAnimation();
		}
		animating = false;
		
		try
		{
			Thread.sleep(500);
		}
		catch (Exception e) {
			//Do nothing
		}
		
		this.board.getView().eventFinished();
	}

	@Override
	public void run() {
		
		//W�rfel zur Liste packen
		this.board.getDices().add(this.dice.dice);
		
		//F�r Wuerfelbilder
		Long zwischen = System.currentTimeMillis();
		
		this.startTime = System.currentTimeMillis();

		if (this.animationqueue.size() >= 3)
			this.duration = 150;
		
		int rPauseCount = 0;
		while (System.currentTimeMillis() - this.startTime <= this.duration) {
			
			rPauseCount += System.currentTimeMillis() - zwischen;

			zwischen = System.currentTimeMillis();
			
			if(rPauseCount >= this.Rpause)
			{
				//Zufallswert holen
				int tmp = this.roll(1, 6);
				this.dice.dice.setRValue(tmp);
				
				tmp = this.roll(0,360);
				
				this.dice.dice.setRotation(tmp);
				
				rPauseCount = 0;
			}

			

			this.dice.dice.setCoords(this.dice.dice.getX()+deltaMoveX, this.dice.dice.getY()+deltaMoveY);
	
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// notify the view
			this.board.repaint();
		}

		if (this.animationqueue.size() <= 1)
			this.duration = 1500;
		this.endAnimation();
	}

	// Inner Class - represents one animation entry
	class AnimationEntry {
		private BDice dice;
		private int toX;
		private int toY;

		public AnimationEntry(BDice dice, int tP, int tI) {
			this.dice = dice;
			this.toX = tP;
			this.toY = tI;
		}
	}
	//Von Marc geklaut
	public int roll(int min, int max) {
		
		Random generator = new Random(Calendar.getInstance().getTimeInMillis());
		
		if (min < max && min >= 0 && max >= 0) {
			return generator.nextInt(max) + min;
		} else {
			return generator.nextInt(6) + 1;
		}
	}

	public void destroyThread() {
		this.thread = null;
		
	}

	public boolean isAnimating() {
		return this.animating;
	}

}