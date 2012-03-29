package backgammon.view.helpers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import backgammon.listener.ImageBoardMouseListener;
import backgammon.model.player.Move;
import backgammon.view.helpers.BChecker;
import backgammon.view.BackgammonViewGUI;

public class ImageBoard extends JPanel {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private BackgammonViewGUI view;
	private ArrayList<BPosition> PositionMatrix;
	private ArrayList<BChecker> checker;
	private MoveAnimationManager animation;

	public ImageBoard(BackgammonViewGUI backgammonViewGUI, String img) {
    
	  this(img);
	  
	  this.view = backgammonViewGUI;
	  this.checker = new ArrayList<BChecker>();
	  this.PositionMatrix = getPoisitionMatrix();
	  this.animation = new MoveAnimationManager(this);
	  
	  this.setStartPosition();
	}
	
	public ImageBoard(String img) {
		
	    this.addMouseListener(new ImageBoardMouseListener(this));
	    this.addMouseMotionListener(new ImageBoardMouseListener(this));
		
	    Image iimg = new ImageIcon(getClass().getResource(img)).getImage();
		
	    this.img = iimg;
	    Dimension size = new Dimension(iimg.getWidth(null), iimg.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
	  }
	  public BChecker addChecker(int player, int point, int index)
	  {
		  BChecker tmp = null;
		  
		  if(point <= 23)
			  tmp = new BChecker(BChecker.Place.BOARD, player, point, index);
		  else if(point == 24)
			  tmp = new BChecker(BChecker.Place.BAR, player, point, index);
		  else
			  tmp = new BChecker(BChecker.Place.OUT, player, point, index);
		  
		  this.checker.add(tmp);
		  return tmp;
	  }
  
  
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
    
    this.drawChecker(g);
  }
  	public ArrayList<BChecker> getChecker()
  	{
  		return this.checker;
  	}
  	private BChecker findChecker(int point, int index)
	{
		for(BChecker checker : this.checker)
		{
			if(checker.getPoint() == point &&
			   checker.getIndex() == index)
				return checker;
		}
		return null;
	}

	private void setStartPosition() 
	{
		for(int i = 0; i < 15; i++)
		{
			//set for each site
			this.addChecker(1, 25, i);
			this.addChecker(2, 25, i);
		}
		
	}
	public void moveChecker(Move move) {
		//BChecker bChecker, int toPoint, int toIndex)
		BChecker tmp = findChecker(move.getFromPoint(), move.getFromIndex());
		
		if(tmp == null)
		{
			tmp = this.addChecker(move.getID(), move.getFromPoint(), move.getFromIndex());
		}
		
		//Reihenfolge herstellen, damit der gerade gezeichnete Checker immer oben ist.
		this.checker.remove(tmp);
		this.checker.add(tmp);
		
		this.animation.addMoveAnimation(tmp,move.getToPoint(), move.getToIndex());
	}

	private void drawChecker(Graphics g) {
		
		for(BChecker checker : this.checker)
		{
			BPosition tmp = null;
			tmp = checker.getCoords();
			g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()-25,null);
		}
		
		
	}
	
	public static ArrayList<BPosition> getPoisitionMatrix()
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
	public static ArrayList<PHitBox> getPointHitBox()
	{
		ArrayList<PHitBox> tmp = new ArrayList<PHitBox>();
		//unten
		tmp.add(new PHitBox(834, 895, 340,578));
		tmp.add(new PHitBox(765, 833, 340,578));
		tmp.add(new PHitBox(704, 764, 340,578));
		tmp.add(new PHitBox(635, 703, 340,578));
		tmp.add(new PHitBox(574, 634, 340,578));
		tmp.add(new PHitBox(505, 573, 340,578));
		
		tmp.add(new PHitBox(400, 465, 340,578));
		tmp.add(new PHitBox(333, 399, 340,578));
		tmp.add(new PHitBox(270, 332, 340,578));
		tmp.add(new PHitBox(200, 269, 340,578));
		tmp.add(new PHitBox(140, 199, 340,578));
		tmp.add(new PHitBox(74, 139, 340,578));
		//oben
		tmp.add(new PHitBox(74, 139, 22,265));
		tmp.add(new PHitBox(140, 199, 22,265));
		tmp.add(new PHitBox(200, 269, 22,265));
		tmp.add(new PHitBox(270, 332, 22,265));
		tmp.add(new PHitBox(333, 399, 22,265));
		tmp.add(new PHitBox(400, 465, 22,265));
		
		tmp.add(new PHitBox(505, 573, 22,265));
		tmp.add(new PHitBox(574, 634, 22,265));
		tmp.add(new PHitBox(635, 703, 22,265));
		tmp.add(new PHitBox(704, 764, 22,265));
		tmp.add(new PHitBox(765, 833, 22,265));
		tmp.add(new PHitBox(834, 895, 22,265));
		//test
		
		//endtest
		return tmp;
	}
	public PHitBox getPlayerBoard(int player)
	{
		if(player == 1)
			return new PHitBox(505, 900, 22, 578);
		else
			return new PHitBox(70, 465, 22, 578);
	}
	public static int getOBIndex(int player, int index)
	{
		if(player == 2)
			return index*15;
		else
			return -(index*15);
	}
	public static int getIndex(int point, int index)
	{
		
		if(point >= 12)
		{
			return index*20;
		}	
		else
		{
			return -(index*20); 
		}
	}
	public static BPosition getBarPosition(int player)
	{
		if(player == 1)
			return new BPosition(485,440);
		else
			return new BPosition(485,160);
	}
	public static BPosition getOutPosition(int player)
	{
		if(player == 1)
			return new BPosition(935,554);
		else
			return new BPosition(935,46);
	}
	public ArrayList<BPosition> getPositionMatrix() {
		
		return PositionMatrix;
	}
	public Integer getHighestIndex(int point)
	{
		int result = -1;
		
		for(BChecker c : this.checker)
		{
			if(c.getPoint() == point && c.getIndex() > result)
				result = c.getIndex();
		}
		return result;	
	}
	public BChecker getHighestChecker(int point)
	{
		BChecker result = null;
		
		for(BChecker c : this.checker)
		{
			if(result == null)
				result = c;
			if(c.getPoint() == point && c.getIndex() >= result.getIndex())
				result = c;
		}
		return result;	
	}
	public void setFocus(BChecker tmp)
	{
		this.checker.remove(tmp);
		this.checker.add(tmp);
	}
	public MoveAnimationManager getAnimation()
	{
		return this.animation;
	}
}
