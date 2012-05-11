package backgammon.view.helpers;

import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

import backgammon.model.player.Move;
import backgammon.view.helpers.BChecker.Place;

public class AnimationManager implements Runnable{

	//Checker
	
	private ImageBoard board;
	private BChecker checker;
	private int toIndex;
	private int toPoint;
	private int checkerDuration = 400;
	private final int speed = 5;
	private double currentX;
	private double currentY;
	private int finalX;
	private int finalY;
	private Boolean isAnimating = false;
	
	//DICE

	private AnimationEntry dice;
	private int diceDuration = 1500;
	private final int pause = 20;
	private final int Rpause = 240;
	private Thread thread;
	private long startTime;
	private double deltaMoveX;
	private double deltaMoveY;
	private Vector<AnimationEntry> animationqueue;
	private int runType = 0;
	
	
	public AnimationManager(ImageBoard board) {
		this.board = board;
		this.animationqueue = new Vector<AnimationEntry>();
	}
	public void addCheckerAnimation(BChecker checker2, int i, int j) {
		Move tmp = new Move(checker2.getPlayer(), checker2.getPoint(), checker2.getIndex(), i, j);
		this.addCheckerAnimation(tmp);
		
	}
	public void addCheckerAnimation(Move move) {
		AnimationEntry tmp = new AnimationEntry(move, move.getToPoint(), move.getToIndex());

		this.animationqueue.add(tmp);

		// start animation
		if (!isAnimating)
			this.startAnimation();
	}
	public void addDiceAnimation(BDice dice, int toX, int toY) {
		AnimationEntry tmp = new AnimationEntry(dice, toX, toY);

		this.animationqueue.add(0,tmp);
	
		if(!isAnimating)
			this.startAnimation();

	}
	
	public void startAnimation()
	{
		if(this.animationqueue.isEmpty())
			return;
		
		AnimationEntry tmp = this.animationqueue.get(0);
		
		if(tmp.move == null)
		{
			this.startDiceAnimation();
			this.runType  = 1;
		}
		else
		{
			this.startCheckerAnimation();
			this.runType = 2;
		}
	}
	
	public void startDiceAnimation() {
	
		if(this.animationqueue.isEmpty())
			return;
		
		isAnimating = true;
		
		AnimationEntry tmp = this.animationqueue.get(0);
		
		//System.out.println(Integer.toString(deltaMoveX) + " - " + Integer.toString(deltaMoveY));
		
		this.dice = tmp;
		tmp.dice.setRValue(this.roll(1, 6));
		tmp.dice.setRotation(0);
		
		//Zufallswert holen
		
		
		int tmpX = roll(-1000, 1000);
		int tmpY = roll(-1000, 1000);
		this.currentX = tmpX;
		this.currentY = tmpY;
		
		this.dice.dice.setCoords(tmpX, tmpY);
		//Ende zufallswert
		
		deltaMoveX =   (double) ( (double) (tmp.toX - tmpX) / (double) ( this.diceDuration / this.pause)) ;
		deltaMoveY =   (double) ( (double) (tmp.toY - tmpY) / (double) ( this.diceDuration / this.pause)) ;
		
		this.animationqueue.remove(0);

		this.thread = new Thread(this);

		this.thread.start();
	}
	public void startCheckerAnimation() {
		
		if(this.animationqueue.isEmpty())
			return; 
		
		// is not animating, so get the first item in the list an animate it
		this.isAnimating = true;

		AnimationEntry tmp = this.animationqueue.get(0);

		this.animationqueue.remove(0);

		//Get Checker
		BChecker tmp2 = this.board.findChecker(tmp.move.getFromPoint(), tmp.move.getFromIndex());
		if(tmp2 == null)
		{
			tmp2 = this.board.addChecker(tmp.move.getID(), tmp.move.getFromPoint(), tmp.move.getFromIndex());
		}
		// set all variables
		
		//set focus.
		this.board.getChecker().remove(tmp2);
		this.board.getChecker().add(tmp2);
		
		this.checker = tmp2;
		this.toIndex = tmp.toIndex;
		this.toPoint = tmp.toPoint;

		this.calculatePositions();

		this.thread = new Thread(this);

		this.thread.start();
	}

	private void endCheckerAnimation() {
		if (this.thread != null)
			this.thread = null;

		this.checker.setPointIndex(this.toPoint, this.toIndex);
		this.board.repaint();

		// Animation done, check queue
		if (!this.animationqueue.isEmpty()) {
			// Not empty, so start next animation
			this.startAnimation();
		}
		else
		{
			isAnimating = false;
			this.board.getView().eventFinished();
		}
	}

	private void calculatePositions() {

		BPosition tmp = null;

		this.currentX = this.checker.getCoords().getX();
		this.currentY = this.checker.getCoords().getY();

		this.checker.setCoords( (int) currentX, (int) currentY);

		if (toPoint <= 23) {
			tmp = this.board.getPositionMatrix().get(toPoint);
			finalX = tmp.getX();
			finalY = tmp.getY() + ImageBoard.getIndex(toPoint, toIndex);

			this.checker.setPosition(Place.BOARD);

		} else if (toPoint == 24) {
			tmp = ImageBoard.getBarPosition(this.checker.getPlayer());
			finalX = tmp.getX();
			finalY = tmp.getY()
					+ ImageBoard.getOBIndex(this.checker.getPlayer(), toIndex);

			this.checker.setPosition(Place.BAR);
		} else {
			tmp = ImageBoard.getOutPosition(this.checker.getPlayer());
			finalX = tmp.getX();
			finalY = tmp.getY()
					+ ImageBoard.getOBIndex(this.checker.getPlayer(), toIndex);

			this.checker.setPosition(Place.OUT);
		}

		/*
		 * System.out.println(Integer.toString(finalX));
		 * System.out.println(Integer.toString(finalY));
		 * System.out.println(Integer.toString(currentX));
		 * System.out.println(Integer.toString(currentY));
		 */

	}

	
	public void checkerRun() {
		this.startTime = System.currentTimeMillis();

		if (this.animationqueue.size() >= 6)
			this.checkerDuration = 150;

		while (System.currentTimeMillis() - this.startTime <= this.checkerDuration) {

			int deltaX = (int) (finalX - currentX);
			int deltaY = (int) (finalY - currentY);

			if ((deltaX >= speed || deltaY >= speed)
					|| (-deltaX >= speed || -deltaY >= speed)) {
				deltaX = (int) Math.ceil(deltaX / speed);
				deltaY = (int) Math.ceil(deltaY / speed);
			}

			currentX += deltaX;
			currentY += deltaY;

			this.checker.setCoords((int) currentX, (int) currentY);

			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// notify the view
			this.board.repaint();
		}

		if (this.animationqueue.size() <= 5)
			this.checkerDuration = 400;
		this.endCheckerAnimation();
	}

	// Inner Class - represents one animation entry
	class AnimationEntry {
		private Move move;
		private BDice dice;
		private int toPoint;
		private int toIndex;
		private int toX;
		private int toY;

		public AnimationEntry(Move move, int tP, int tI) {
			this.move = move;
			this.toPoint = tP;
			this.toIndex = tI;
			this.dice = null;
			this.toX = 0;
			this.toY = 0;
		}
		public AnimationEntry(BDice dice, int tP, int tI) {
			this.dice = dice;
			this.toPoint = tP;
			this.toIndex = tI;
			this.move = null;
			this.toX = tP;
			this.toY = tI;
		}
	}

	
	private void endDiceAnimation() {
		if (this.thread != null)
			this.thread = null;

		// System.out.println(Integer.toString(finalX));
		// System.out.println(Integer.toString(finalY));
		//System.out.println(Integer.toString(toPoint));
		//System.out.println(Integer.toString(toIndex));
		
		this.dice.dice.setRValue(this.dice.dice.getValue());
		this.dice.dice.setCoords(this.dice.toX, this.dice.toY);
		this.board.repaint();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		if(!this.animationqueue.isEmpty())
		{
			this.startAnimation();
		}
		else
		{
			isAnimating = false;
			this.board.getView().eventFinished();
		}
		
		
		
	}


	public void diceRun() {
		
		//Würfel zur Liste packen
		this.board.getDices().add(this.dice.dice);
		
		//Für Wuerfelbilder
		Long zwischen = System.currentTimeMillis();
		
		this.startTime = System.currentTimeMillis();

		int rPauseCount = 0;
		while (System.currentTimeMillis() - this.startTime <= this.diceDuration) {
			
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

			
			currentX += deltaMoveX;
			currentY += deltaMoveY;
			
			this.dice.dice.setCoords(Math.round(currentX), Math.round( currentY));
	
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// notify the view
			this.board.repaint();
		}
		this.endDiceAnimation();
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
		return this.isAnimating;
	}

	@Override
	public void run() {

		this.isAnimating = true;
		
		if(this.runType == 1)
		{
			this.diceRun();
		}
		else
		{
			this.checkerRun();
		}
		
	}
}
