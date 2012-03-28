package backgammon.view.helpers;

import java.util.ArrayList;

import backgammon.view.helpers.BChecker.Place;

public class  MoveAnimationManager implements Runnable {

  private ImageBoard board;
	private BChecker checker;
	private int toIndex;
	private int toPoint;
	private int duration = 500;
	private final int speed = 5;
	private final int pause = 10;
	private int currentX;
	private int currentY;
	private int finalX;
	private int finalY;
	private Thread thread;
	private long startTime;
	private ArrayList<AnimationEntry> animationqueue;
	private Boolean isAnimating = false;
  
	public MoveAnimationManager(ImageBoard board) {
		this.board = board;
		this.animationqueue = new ArrayList<AnimationEntry>();
	}
	
	public void addMoveAnimation(BChecker checker, int toPoint, int toIndex)
	{
		AnimationEntry tmp = new AnimationEntry(checker, toPoint, toIndex);
		
		this.animationqueue.add(tmp);
		
		//start animation
		if(!isAnimating)
			this.startAnimation();
		
	}

	private void startAnimation()
	{
		//is not animating, so get the first item in the list an animate it
		this.isAnimating = true;
		
		AnimationEntry tmp = this.animationqueue.get(0);
		
		this.animationqueue.remove(0);
		
		//set all variables
		this.checker = tmp.checker;
		this.toIndex = tmp.toIndex;
		this.toPoint = tmp.toPoint;
		
		this.calculatePositions();
		
		this.thread = new Thread(this);
		
		this.thread.start();
	}
	
	private void endAnimation()
	{
		if(this.thread != null)
			this.thread = null;
		
		/*System.out.println(Integer.toString(finalX));
		System.out.println(Integer.toString(finalY));
		System.out.println(Integer.toString(currentX));
		System.out.println(Integer.toString(currentY));*/
		
		this.checker.setCoords(finalX, finalY);
		this.checker.setPoint(this.toPoint);
		this.checker.setIndex(this.toIndex);
		
		this.board.repaint();
		
		
		//Animation done, check queue
		if(!this.animationqueue.isEmpty())
		{
			//Not empty, so start next animation
			this.startAnimation();
		}
		this.isAnimating = false;
	}
	private void calculatePositions() {
		
		BPosition tmp = null;
		
		if(this.checker.getPosition() == BChecker.Place.BOARD)
		{
			tmp = this.board.getPositionMatrix().get(this.checker.getPoint());	
			this.currentY = tmp.getY()+this.board.getIndex(this.checker.getPoint(), this.checker.getIndex());
		}
		else if(checker.getPosition() == BChecker.Place.BAR)
		{
			tmp = this.board.getBarPosition(checker.getPlayer());
			this.currentY = tmp.getY()+this.board.getOBIndex(this.checker.getPlayer(), this.checker.getIndex());
		}
		else
		{
			tmp = this.board.getOutPosition(checker.getPlayer());
			this.currentY = tmp.getY()+this.board.getOBIndex(this.checker.getPlayer(), this.checker.getIndex());
		}	
		
		
		this.currentX = tmp.getX();
		
		this.checker.setCoords(currentX, currentY);
		
		if(toPoint <= 23)
		{
			tmp = this.board.getPositionMatrix().get(toPoint);
			finalX = tmp.getX();
			finalY = tmp.getY()+this.board.getIndex(toPoint, toIndex);
			
			this.checker.setPoint(99);
			this.checker.setIndex(99);
			
			this.checker.setPosition(Place.BOARD);
			
		}
		  else if(toPoint == 24)
		  {
			  tmp = this.board.getBarPosition(this.checker.getPlayer());
			  finalX = tmp.getX();
			  finalY = tmp.getY()+this.board.getOBIndex(this.checker.getPlayer(), toIndex);
			 
			  this.checker.setPoint(99);
			  this.checker.setIndex(99);
				
			  this.checker.setPosition(Place.BAR);
		  }
		  else
		  {
			  tmp = this.board.getOutPosition(this.checker.getPlayer());
			  finalX = tmp.getX();
			  finalY = tmp.getY()+this.board.getOBIndex(this.checker.getPlayer(), toIndex);
			  
			  this.checker.setPoint(99);
			  this.checker.setIndex(99);
				
			  this.checker.setPosition(Place.OUT);
		  }
		
/*		System.out.println(Integer.toString(finalX));
		System.out.println(Integer.toString(finalY));
		System.out.println(Integer.toString(currentX));
		System.out.println(Integer.toString(currentY));*/
		
		
		
	}

	@Override
	public void run() 
	{	
		this.startTime = System.currentTimeMillis();
		
		if(this.animationqueue.size() >= 3)
			this.duration = 150;
		
		while(System.currentTimeMillis() - this.startTime <= this.duration){
			
			int deltaX = (finalX - currentX);
			int deltaY = (finalY - currentY);
			
			if(deltaX >= speed || deltaY >= speed || -deltaX >= speed || -deltaY >= speed)
			{
				deltaX = deltaX /speed;
				deltaY = deltaY /speed;
			}
			
			currentX += deltaX;
			currentY += deltaY;
            
			this.checker.setCoords(currentX, currentY);
			
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
          //notify the view
    		this.board.repaint();
		}
		
		if(this.animationqueue.size() <= 1)
			this.duration = 500;
		this.endAnimation();
	}
	
	//Inner Class - represents one animation entry
	class AnimationEntry
	{
		private BChecker checker;
		private int toPoint;
		private int toIndex;
		
		public AnimationEntry(BChecker checker, int tP, int tI) {
			this.checker = checker;
			this.toPoint = tP;
			this.toIndex = tI;
		}
	}
}