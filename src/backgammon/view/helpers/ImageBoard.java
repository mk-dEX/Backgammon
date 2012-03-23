package backgammon.view.helpers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.text.Position;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import backgammon.view.BackgammonViewGUI;

public class ImageBoard extends JPanel {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private BackgammonViewGUI view;
	private ArrayList<BPosition> PositionMatrix;
	private ArrayList<Position> IndexMatrix;
	private ArrayList<BChecker> checker;

	public ImageBoard(BackgammonViewGUI backgammonViewGUI, String img) {
    
	  this(new ImageIcon(img).getImage());
	  
	  this.view = backgammonViewGUI;
	  this.checker = new ArrayList<BChecker>();
	  this.PositionMatrix = initPoisitionMatrix();
	  
	    //DEBUG
	   /* this.addChecker(1, 3, 0);
	    this.addChecker(1, 12, 1);
	    this.addChecker(1, 13, 2);
	    this.addChecker(1, 3, 3);
	    this.addChecker(1, 12, 4);
	    this.addChecker(1, 3, 5);
	    this.addChecker(1, 3, 6);
	    this.addChecker(1, 13, 7);
	    
	    this.addChecker(1, 5, 2);*/
	  
	}

	  public ImageBoard(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }
	  public void addChecker(int player, int point, int index)
	  {
		  this.checker.add(new BChecker(player, point, index));
		  
		  System.out.println(Integer.toString(point) + " "+ Integer.toString(index));
		  
		  this.repaint();
	  }
  
  
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
    
    this.drawChecker(g);
  }

	private void drawChecker(Graphics g) {
		
		for(BChecker lol : this.checker)
		{
			BPosition tmp = this.PositionMatrix.get(lol.getPoint());
			g.drawImage(this.view.getPl1_checker(), tmp.getX()-25,tmp.getY()+this.getIndex(lol.getPoint(), lol.getIndex(),false)-25,null);
		}
		
		
	}
	private ArrayList<BPosition> initPoisitionMatrix()
	{
		ArrayList<BPosition> tmp = new ArrayList<BPosition>();
		//unten
		tmp.add(new BPosition(865,554));
		tmp.add(new BPosition(800,554));
		tmp.add(new BPosition(735,554));
		tmp.add(new BPosition(670,554));
		tmp.add(new BPosition(605,554));
		tmp.add(new BPosition(735,554));
		tmp.add(new BPosition(431,554));
		tmp.add(new BPosition(366,554));
		tmp.add(new BPosition(301,554));
		tmp.add(new BPosition(235,554));
		tmp.add(new BPosition(170,554));
		tmp.add(new BPosition(105,554));
		//oben
		tmp.add(new BPosition(105,46));
		tmp.add(new BPosition(170,46));
		tmp.add(new BPosition(236,46));
		tmp.add(new BPosition(301,46));
		tmp.add(new BPosition(366,46));
		tmp.add(new BPosition(431,46));
		tmp.add(new BPosition(540,46));
		tmp.add(new BPosition(605,46));
		tmp.add(new BPosition(670,46));
		tmp.add(new BPosition(735,46));
		tmp.add(new BPosition(800,46));
		tmp.add(new BPosition(865,46));
		//test
		
		//endtest
		return tmp;
	}
	private int getOBIndex(int point, int index)
	{
		if(point >= 12)
			return index*15;
		else
			return -(index*15);
	}
	private int getIndex(int point, int index, boolean fold)
	{
		
		if(point >= 12)
		{
			if(fold)
				return index*30;
			else
				return index*50;
		}	
		else
		{
			if(fold)
				return -(index*30);
			else
				return -(index*50); 
		}
	}
	private BPosition getBarPosition(int player)
	{
		if(player == 1)
			return new BPosition(485,420);
		else
			return new BPosition(485,180);
	}
	private BPosition getOutPosition(int player)
	{
		if(player == 1)
			return new BPosition(935,554);
		else
			return new BPosition(935,46);
	}
	class BPosition
	{
		private int x;
		private int y;
		
		BPosition(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		protected int getX() {
			return x;
		}
		protected int getY() {
			return y;
		}
	}
	class BChecker
	{
		private int point;
		private int index;
		private int player;
		
		BChecker(int player, int point, int index)
		{
			this.point = point;
			this.index = index;
			this.player = player;
		}
		
		protected int getPoint() {
			return point;
		}
		protected void setPoint(int point) {
			this.point = point;
		}
		protected int getIndex() {
			return index;
		}
		protected int getPlayer() {
			return player;
		}
		protected void setIndex(int index) {
			this.index = index;
		}
	}
}
