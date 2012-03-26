package backgammon.view.helpers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.text.Position;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import backgammon.model.player.Move;
import backgammon.view.helpers.BChecker;
import backgammon.view.helpers.BChecker.Place;
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
	  this.PositionMatrix = initPoisitionMatrix();
	  this.animation = new MoveAnimationManager(this);
	  
	  this.setStartPosition();
	}
	
	public ImageBoard(String img) {
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
			if(checker.getPosition() == BChecker.Place.BOARD)
			{
				BPosition tmp = null;
			
				
				if(checker.getPoint() == 99 && checker.getIndex() == 99)
				{
					tmp = checker.getCoords();
					g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()-25,null);
					continue;
				}
				
				tmp = this.PositionMatrix.get(checker.getPoint());	
				g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()+this.getIndex(checker.getPoint(), checker.getIndex())-25,null);
			}
			else if(checker.getPosition() == BChecker.Place.BAR)
			{
				BPosition tmp = null;
				
				if(checker.getPoint() == 99 && checker.getIndex() == 99)
				{
					tmp = checker.getCoords();
					g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()-25,null);
					continue;
				}
				
				tmp = this.getBarPosition(checker.getPlayer());
				g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()+this.getOBIndex(checker.getPlayer(), checker.getIndex())-25,null);
			}
			else
			{
				BPosition tmp = null;
				
				if(checker.getPoint() == 99 && checker.getIndex() == 99)
				{
					tmp = checker.getCoords();
					g.drawImage(this.view.getChecker(checker.getPlayer()), tmp.getX()-25,tmp.getY()-25,null);
					continue;
				}
				
				tmp = this.getOutPosition(checker.getPlayer());
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
	int getOBIndex(int player, int index)
	{
		if(player == 2)
			return index*15;
		else
			return -(index*15);
	}
	int getIndex(int point, int index)
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
	BPosition getBarPosition(int player)
	{
		if(player == 1)
			return new BPosition(485,440);
		else
			return new BPosition(485,160);
	}
	BPosition getOutPosition(int player)
	{
		if(player == 1)
			return new BPosition(935,554);
		else
			return new BPosition(935,46);
	}
	public ArrayList<BPosition> getPositionMatrix() {
		
		return PositionMatrix;
	}
}
