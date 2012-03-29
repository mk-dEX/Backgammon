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
	private BChecker checker = null;

	public ImageBoardMouseListener(ImageBoard parent) {
		this.parent = parent;
	}
	@Override
	public void mouseClicked(MouseEvent f) {
		
		//this.setCheckerFromEvent(f);
		
		//do stuff
		
		//this.checker = null;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent f) {
		// gucken, ob neuer Point, ansonsten zurückanimieren.
		if(!this.setCheckerFromEvent(f))
			return;
		
		this.setPointFromEvent(f);
		
		System.out.println("Mouse released");
		this.checker = null;

	}
	public void mouseDragged(MouseEvent e) {
		// Drag aktivieren
		if(!this.setCheckerFromEvent(e))
			return;
		
		this.checker.setCoords(e.getX(), e.getY());
		
		
	    this.parent.repaint();
	}
	private Boolean setCheckerFromEvent(MouseEvent f)
	{
		
		if(this.checker != null)
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
			System.out.println("Point: " + Integer.toString(Point));
			this.checker = this.parent.getHighestChecker(Point);
			return true;
		}
		else
		{
			this.checker = null;
		}
		return false;
	}
	private void setPointFromEvent(MouseEvent f) {
		ArrayList<PHitBox> tmp = ImageBoard.getPointHitBox();
				
		//gucken ob wir auf einem Point landen, falls ja, dann holen wir uns den höchsten Index
		//und platzieren den Checker dort.
		int i = 0;
		for(PHitBox h : tmp)
		{
			if(f.getX() >= h.getX1() && f.getX() <= h.getX2() && f.getY() >= h.getY1() && f.getY() <= h.getY2())
			{
				//Hitbox gefunden, also point und Index setzen.
				int index = this.parent.getHighestIndex(i);
				this.checker.setPointIndex(i, index);
			}
			i++;
		}
		//keine Hitbox getroffen, also zurücksetzen.
		this.checker.setCoordsFromPointAndIndex();
	}

}
