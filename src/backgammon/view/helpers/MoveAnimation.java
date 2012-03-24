package backgammon.view.helpers;

import backgammon.view.helpers.BChecker.Place;

public class MoveAnimation implements Runnable {

  private ImageBoard board;
	private BChecker checker;
	private int toIndex;
	private int toPoint;
	private final int duration = 1000;
	private int currentX;
	private int currentY;
	private int finalX;
	private int finalY;
	private Thread thread;
	private long startTime;
  
	MoveAnimation(ImageBoard board, BChecker checker, int toPoint, int toIndex)
	{
		this.board = board;
		this.checker = checker;
		this.toPoint = toPoint;
		this.toIndex = toIndex;
		this.thread = new Thread(this);
		
		this.calculatePositions();
		
		this.startTime = System.currentTimeMillis();
		this.start();
		
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
	public void start()
	{
		this.thread.start();
	}

	@Override
	public void run() 
	{	
		
		
		
		while(System.currentTimeMillis() - this.startTime <= this.duration){
			
			int deltaX = (finalX - currentX);
			int deltaY = (finalY - currentY);
			
			currentX += deltaX/5;
			currentY += deltaY/5;
            
			this.checker.setCoords(currentX, currentY);
			
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
          //notify the view
    		this.board.repaint();
		}
		
		this.stop();
	}
	private void stop() {

		if(this.thread != null)
			this.thread = null;
		
		System.out.println(Integer.toString(finalX));
		System.out.println(Integer.toString(finalY));
		System.out.println(Integer.toString(currentX));
		System.out.println(Integer.toString(currentY));
		
		this.checker.setCoords(finalX, finalY);
		this.checker.setPoint(this.toPoint);
		this.checker.setIndex(this.toIndex);
		
		this.board.repaint();
		
	}
}