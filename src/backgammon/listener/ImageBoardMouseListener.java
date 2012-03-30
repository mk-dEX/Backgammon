package backgammon.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import backgammon.view.helpers.BChecker;
import backgammon.view.helpers.ImageBoard;
import backgammon.view.helpers.PHitBox;

public class ImageBoardMouseListener extends MouseMotionAdapter implements MouseListener {

	private ImageBoard parent;
	private static BChecker checker = null;
	private static boolean dragging = false;

	public ImageBoardMouseListener(ImageBoard parent) {
		this.parent = parent;
	}
	@Override
	public void mouseClicked(MouseEvent f) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {
		
		//System.out.println("Press");
		if(!this.setCheckerFromEvent(e))
			return;
		
		if(this.parent.getView().getController().startMove(this.checker.getPlayer()))
		{
			//System.out.println("Start dragging");
			ImageBoardMouseListener.dragging = true;
			this.parent.setFocus(ImageBoardMouseListener.checker);
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent f) {
		// gucken, ob neuer Point, ansonsten zurückanimieren.
		ImageBoardMouseListener.dragging = false;
		
		//System.out.println("Mouse released");
		
		if(ImageBoardMouseListener.checker == null)
			return;
		
		this.setPointFromEvent(f);
		
		ImageBoardMouseListener.checker = null;
		

	}
	public void mouseDragged(MouseEvent e) {

		if(!ImageBoardMouseListener.dragging)
		{
			return;
		}
		
		//System.out.println("Drag");
		ImageBoardMouseListener.checker.setCoords(e.getX(), e.getY());
		
	    this.parent.repaint();
	}
	private Boolean setCheckerFromEvent(MouseEvent f)
	{
		
		if(ImageBoardMouseListener.checker != null)
			return true;
		
		Integer Point = 50;
		
		Integer x = f.getPoint().x;// - e.getLocationOnScreen().x;
		Integer y = f.getPoint().y;// - e.getLocationOnScreen().y; 

		Integer i = 1;
		for(BChecker c: this.parent.getChecker())
		{
			if(c.getCoords() != null)
			{
				//System.out.println(Integer.toString(i) + ". Checker: " + Integer.toString(c.getCoords().getX()) + " - " + Integer.toString(c.getCoords().getY()));
				if (c.getCoords().getX()-25 <= x && c.getCoords().getX()+25 >= x
					&& c.getCoords().getY()-25 <= y && c.getCoords().getY()+25 >= y)
				{
					//innerhalb, also Point ausgeben
					//System.out.println("Point: " + Integer.toString(c.getPoint()));
					Point = c.getPoint();
				}
			}
			i++;
		}
		//System.out.println("Mouse: " + Integer.toString(x) + " - " + Integer.toString(y));
		
		if(Point != 50)
		{
			//Kontroller Bescheid sagen.
			//System.out.println("Point: " + Integer.toString(Point));
			ImageBoardMouseListener.checker = this.parent.getHighestChecker(Point);
			
			if(ImageBoardMouseListener.checker != null)
				return true;
			else
				return false;
		}
		else
		{
			//System.out.println("Test1");
			ImageBoardMouseListener.checker = null;
			return false;
		}
	}
	private void setPointFromEvent(MouseEvent f) {
		ArrayList<PHitBox> tmp = ImageBoard.getPointHitBox();
				
		//gucken ob wir auf einem Point landen, falls ja, dann holen wir uns den höchsten Index
		//und platzieren den Checker dort.
		boolean found = false;
		int i = 0;
		for(PHitBox h : tmp)
		{
			if(f.getX() >= h.getX1() && f.getX() <= h.getX2() && f.getY() >= h.getY1() && f.getY() <= h.getY2())
			{
				//Hitbox gefunden, also point und Index setzen.
				found = true;
				//System.out.println("Hitbox gefunden");
				int index = this.parent.getHighestIndex(i);
				if(ImageBoardMouseListener.checker.getPoint() != i)
					//ImageBoardMouseListener.checker.setPointIndex(i, index+1);
					this.parent.getCheckerAnimation().addMoveAnimation(ImageBoardMouseListener.checker, i, index+1);
				
			}
			i++;
		}
		//keine Hitbox getroffen, also zurücksetzen.
		if(!found)
			this.parent.getCheckerAnimation().addMoveAnimation(ImageBoardMouseListener.checker, ImageBoardMouseListener.checker.getPoint(), ImageBoardMouseListener.checker.getIndex());
		//ImageBoardMouseListener.checker.setCoordsFromPointAndIndex();
		this.parent.repaint();
	}

}
