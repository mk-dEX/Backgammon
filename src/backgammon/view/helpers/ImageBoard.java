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
import backgammon.view.helpers.ImageBoard.BChecker.Place;

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
	  
	  this.setStartPosition();
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

	  private void setStartPosition() {
		int i = 0;
		for(i = 0; i < 15; i++)
		{
			//set for each site
			this.addChecker(1, 25, i);
			this.addChecker(2, 25, i);
		}
		
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
		  
		  if(point <= 23)
			  this.checker.add(new BChecker(BChecker.Place.BOARD, player, point, index));
		  else if(point == 24)
			  this.checker.add(new BChecker(BChecker.Place.BAR, player, point, index));
		  else
			  this.checker.add(new BChecker(BChecker.Place.OUT, player, point, index));
	  }
  
  
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
    
    this.drawChecker(g);
  }

	private void drawChecker(Graphics g) {
		
		for(BChecker checker : this.checker)
		{
			if(checker.position == BChecker.Place.BOARD)
			{
				BPosition tmp = this.PositionMatrix.get(checker.getPoint());	
				
				g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()+this.getIndex(checker.getPoint(), checker.getIndex(),false)-25,null);
			}
			else if(checker.position == BChecker.Place.BAR)
			{
				BPosition tmp = this.getBarPosition(checker.getPlayer());
				
				g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()+this.getOBIndex(checker.getPoint(), checker.getIndex())-25,null);
			}
			else
			{
				BPosition tmp = this.getOutPosition(checker.getPlayer());
				
				g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()+this.getOBIndex(checker.getPlayer(), checker.getIndex())-25,null);
			}	
		}
		
		
	}
	private ArrayList<BPosition> initPoisitionMatrix()
	{
		ArrayList<BPosition> tmp = new ArrayList<BPosition>();
		//unten
		tmp.add(new BPosition(867,554));
		tmp.add(new BPosition(802,554));
		tmp.add(new BPosition(737,554));
		tmp.add(new BPosition(672,554));
		tmp.add(new BPosition(607,554));
		tmp.add(new BPosition(541,554));
		tmp.add(new BPosition(433,554));
		tmp.add(new BPosition(368,554));
		tmp.add(new BPosition(303,554));
		tmp.add(new BPosition(237,554));
		tmp.add(new BPosition(172,554));
		tmp.add(new BPosition(107,554));
		//oben
		tmp.add(new BPosition(107,46));
		tmp.add(new BPosition(172,46));
		tmp.add(new BPosition(238,46));
		tmp.add(new BPosition(303,46));
		tmp.add(new BPosition(368,46));
		tmp.add(new BPosition(433,46));
		tmp.add(new BPosition(542,46));
		tmp.add(new BPosition(607,46));
		tmp.add(new BPosition(672,46));
		tmp.add(new BPosition(737,46));
		tmp.add(new BPosition(802,46));
		tmp.add(new BPosition(867,46));
		//test
		
		//endtest
		return tmp;
	}
	private int getOBIndex(int player, int index)
	{
		if(player == 2)
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
				return index*43;
		}	
		else
		{
			if(fold)
				return -(index*30);
			else
				return -(index*43); 
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
	static class BChecker
	{
		private int point;
		private int index;
		private int player;
		private Place position;
		
		public static enum Place {
			BOARD,
			BAR,
			OUT
		}
		
		BChecker(Place field, int player, int point, int index)
		{
			this.point = point;
			this.index = index;
			this.player = player;
			this.position = field;
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
