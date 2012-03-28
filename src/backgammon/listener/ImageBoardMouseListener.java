package backgammon.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import backgammon.view.helpers.BChecker;
import backgammon.view.helpers.ImageBoard;

public class ImageBoardMouseListener extends MouseMotionAdapter implements MouseListener {

	private ImageBoard parent;
	private BChecker checker = null;

	public ImageBoardMouseListener(ImageBoard parent) {
		this.parent = parent;
	}
	@Override
	public void mouseClicked(MouseEvent f) {
		
		this.setCheckerFromEvent(f);
		
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
	public void mouseReleased(MouseEvent arg0) {
		// gucken, ob neuer Point, ansonsten zurückanimieren.
		this.checker.setPoint(1);
		this.checker.setIndex(0);
		//this.checker = null;

	}
	public void mouseDragged(MouseEvent e) {
		// Drag aktivieren
				if(this.checker == null)
					this.setCheckerFromEvent(e);
				
				this.checker.setCoords(e.getX(), e.getY());
				
				
			    this.parent.repaint();
	}
	private void setCheckerFromEvent(MouseEvent f)
	{
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
			this.checker.setPoint(99);
			this.checker.setIndex(99);
		}
	}

}
