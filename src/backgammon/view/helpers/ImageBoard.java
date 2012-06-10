package backgammon.view.helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import backgammon.listener.ImageBoardMouseListener;
import backgammon.model.player.Move;
import backgammon.view.BackgammonViewGUI;

public class ImageBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private BackgammonViewGUI view;
	private Vector<BPosition> PositionMatrix;
	private Vector<BChecker> checker;
	private Vector<BDice> dice;
	private AnimationManager animationManager;
	private Thread thread = null;
	private String info = "";
	private BDice dDice = null; //DoubleDice
	private Vector<Move> possibleMoves = new Vector<Move>();

	public ImageBoard(BackgammonViewGUI backgammonViewGUI, String img) {

		this(img);

		this.view = backgammonViewGUI;
		this.checker = new Vector<BChecker>();
		this.dice = new Vector<BDice>();
		this.PositionMatrix = getPoisitionMatrix();
		this.animationManager = new AnimationManager(this);
		initBoard();
	}

	public ImageBoard(String img) {

		this.addMouseListener(new ImageBoardMouseListener(this));
		this.addMouseMotionListener(new ImageBoardMouseListener(this));

		Image iimg = new ImageIcon(getClass().getResource(img)).getImage();

		this.img = iimg;
		Dimension size = new Dimension(iimg.getWidth(null),
				iimg.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		
	}
	public void destroyThreads()
	{
		this.animationManager.destroyThread();
		
	}
	private void initBoard()
	{
		for(int i = 0; i < 15; i++)
		{
			this.addChecker(1, 25, i);
		}
		for(int i = 0; i < 15; i++)
		{
			this.addChecker(2, 26, i);
		}
	}
	public BChecker addChecker(int player, int point, int index) {
		BChecker tmp = null;

		if (point <= 23)
			tmp = new BChecker(BChecker.Place.BOARD, player, point, index);
		else if (point == 24)
			tmp = new BChecker(BChecker.Place.BAR, player, point, index);
		else
			tmp = new BChecker(BChecker.Place.OUT, player, point, index);

		this.checker.add(tmp);
		
		return tmp;
	}

	public void paintComponent(Graphics g) {

		g.drawImage(img, 0, 0, null);
		
		
		this.drawChecker(g);
		this.drawPossibleMoves(g);
		this.drawDice(g);
		
		
		//Info zeichnen
		if(!this.info.isEmpty())
		{
			this.drawInfo(g);
		}
	}

	private void drawPossibleMoves(Graphics g) {
		
		if(this.possibleMoves.isEmpty())
			return;
		
		try {
			for (Move move : this.possibleMoves) {
			
			//Position bestimmen
			int x = this.PositionMatrix.get(move.getToPoint()).getX();
			int y = this.PositionMatrix.get(move.getToPoint()).getY() + ImageBoard.getIndex(move.getToPoint(), move.getToIndex());
				
				g.drawImage(this.view.getPossibleMove(),
						x - 25, y - 25, null);
			}
		}
		catch (ConcurrentModificationException concEx)
		{
			this.repaint();
		}
	}

	private void drawInfo(Graphics g) {
		
		Image iimg = new ImageIcon(getClass().getResource("/img/info.png")).getImage();
		
		String z1 = "",z2 = "",z3 = "";
		
		if(this.info.length() > 25)
		{	
			int leerInd = (this.info.substring(0, 24).lastIndexOf(" ")+25)%25;
			z1 = this.info.substring(0, leerInd);
			
			if(this.info.length() > 50)
			{
				int leerInd2 = leerInd;
				
				System.out.println(this.info.substring(leerInd2,25+leerInd2));
				
				leerInd = this.info.substring(leerInd2,25+leerInd2).lastIndexOf(" ");
				z2 = this.info.substring(leerInd2+1, leerInd2 + leerInd);
				z3 = this.info.substring(leerInd2 + leerInd+1);
			}
			else
				z2 = this.info.substring(leerInd+1);
		}
		else
			z1 = this.info;
		
		g.drawImage(iimg, 0, 0, null);
		g.setFont(new Font("SansSerif", Font.BOLD, 13));
		g.setColor(Color.BLACK);
		g.drawString(z1, 25, 40);
		g.drawString(z2, 25, 55);
		g.drawString(z3, 25, 70);

	}

	public void clearInfo()
	{
		this.info = "";
	}
	public void showInfo(String info)
	{
		this.info = info;
		if(this.thread != null)
			this.thread.interrupt();
		
		this.thread = new Thread(new InfoThread(this));
	    this.thread.start();
	}
	public Vector<BChecker> getChecker() {
		return this.checker;
	}

	public BChecker findChecker(int point, int index) {
		for (BChecker checker : this.checker) {
			if (checker.getPoint() == point && checker.getIndex() == index)
				return checker;
		}
		return null;
	}

	public void moveChecker(Move move) {

		this.animationManager.addCheckerAnimation(move);
	}

	private void drawChecker(Graphics g) {
		try
		{
			for (BChecker checker : this.checker) {
				BPosition tmp = null;
				tmp = checker.getCoords();
				g.drawImage(this.view.getChecker(checker.getPlayer()),
					tmp.getX() - 25, tmp.getY() - 25, null);
			}
		} catch (ConcurrentModificationException e) {
			
			this.repaint();
		}
	}

	private void drawDice(Graphics g) {
		for (int i = 0; i < this.getDices().size(); i++) {
				
			Image img = this.view.getDice( this.getDices().get(i).getPlayer(), this.getDices().get(i).getRValue());

	        g.drawImage(img, (int) this.getDices().get(i).getX() - 24, (int) this.getDices().get(i).getY() - 24, this);
	        
	        
	        //is used?
	        if(this.getDices().get(i).isUsed())
	        {
	        	Image used =  new ImageIcon(getClass().getResource("/img/diceused.png")).getImage();

		        g.drawImage(used, (int) (this.getDices().get(i).getX()) - 24, (int)(this.getDices().get(i).getY()) - 24, this);
	        }
	        	
		}
		
		//DoubleDice
		if(this.dDice != null)
		{
			Image used =  new ImageIcon(getClass().getResource("/img/double"+this.dDice.getValue()+".png")).getImage();
			this.dDice.setCoords(this.getDoubleDicePosition(this.dDice.getPlayer()).getX(), this.getDoubleDicePosition(this.dDice.getPlayer()).getY());
	        g.drawImage(used, this.getDoubleDicePosition(this.dDice.getPlayer()).getX() - 24, this.getDoubleDicePosition(this.dDice.getPlayer()).getY() - 24, this);
		}
	}


	public static Vector<BPosition> getPoisitionMatrix() {
		Vector<BPosition> tmp = new Vector<BPosition>();
		// unten
		tmp.add(new BPosition(867, 554));
		tmp.add(new BPosition(802, 554));
		tmp.add(new BPosition(737, 554));
		tmp.add(new BPosition(672, 554));
		tmp.add(new BPosition(607, 554));
		tmp.add(new BPosition(541, 554));
		tmp.add(new BPosition(433, 554));
		tmp.add(new BPosition(368, 554));
		tmp.add(new BPosition(303, 554));
		tmp.add(new BPosition(237, 554));
		tmp.add(new BPosition(172, 554));
		tmp.add(new BPosition(107, 554));
		// oben
		tmp.add(new BPosition(107, 46));
		tmp.add(new BPosition(172, 46));
		tmp.add(new BPosition(238, 46));
		tmp.add(new BPosition(303, 46));
		tmp.add(new BPosition(368, 46));
		tmp.add(new BPosition(433, 46));
		tmp.add(new BPosition(542, 46));
		tmp.add(new BPosition(607, 46));
		tmp.add(new BPosition(672, 46));
		tmp.add(new BPosition(737, 46));
		tmp.add(new BPosition(802, 46));
		tmp.add(new BPosition(867, 46));
		// test

		// endtest
		return tmp;
	}

	public static Vector<PHitBox> getPointHitBox() {
		Vector<PHitBox> tmp = new Vector<PHitBox>();
		// unten
		tmp.add(new PHitBox(834, 895, 340, 578));
		tmp.add(new PHitBox(765, 833, 340, 578));
		tmp.add(new PHitBox(704, 764, 340, 578));
		tmp.add(new PHitBox(635, 703, 340, 578));
		tmp.add(new PHitBox(574, 634, 340, 578));
		tmp.add(new PHitBox(505, 573, 340, 578));

		tmp.add(new PHitBox(400, 465, 340, 578));
		tmp.add(new PHitBox(333, 399, 340, 578));
		tmp.add(new PHitBox(270, 332, 340, 578));
		tmp.add(new PHitBox(200, 269, 340, 578));
		tmp.add(new PHitBox(140, 199, 340, 578));
		tmp.add(new PHitBox(74, 139, 340, 578));
		// oben
		tmp.add(new PHitBox(74, 139, 22, 265));
		tmp.add(new PHitBox(140, 199, 22, 265));
		tmp.add(new PHitBox(200, 269, 22, 265));
		tmp.add(new PHitBox(270, 332, 22, 265));
		tmp.add(new PHitBox(333, 399, 22, 265));
		tmp.add(new PHitBox(400, 465, 22, 265));

		tmp.add(new PHitBox(505, 573, 22, 265));
		tmp.add(new PHitBox(574, 634, 22, 265));
		tmp.add(new PHitBox(635, 703, 22, 265));
		tmp.add(new PHitBox(704, 764, 22, 265));
		tmp.add(new PHitBox(765, 833, 22, 265));
		tmp.add(new PHitBox(834, 895, 22, 265));
		// test

		// endtest
		return tmp;
	}
	public static Vector<PHitBox> getOutHitBox() {
		Vector<PHitBox> tmp = new Vector<PHitBox>();
		// unten
		tmp.add(new PHitBox(905, 965, 300, 578));
		// oben
		tmp.add(new PHitBox(905, 965, 22, 299));

		return tmp;
	}
	public static Vector<PHitBox> getBarHitBox() {
		Vector<PHitBox> tmp = new Vector<PHitBox>();

		tmp.add(new PHitBox(467, 505, 22, 578));

		return tmp;
	}

	private BPosition getDoubleDicePosition(int player) {

		if(player == 2)
		{
			return new BPosition(35, 60);
		}
		else if(player == 1)
		{
			return new BPosition(35, 530);
		}
		else
		{
			return new BPosition(35, 280);
		}
	}
	
	public BPosition getDicePosition(int player, int dice) {
		if (player == 1 && dice == 1) {
			return new BPosition(620, 300);
		} else if (player == 1 && dice == 2) {
			return new BPosition(670, 300);
		} else if (player == 1 && dice == 3) {
			return new BPosition(720, 300);
		} else if (player == 1 && dice == 4) {
			return new BPosition(770, 300);
		} else if (player == 2 && dice == 1) {
			return new BPosition(150, 300);
		} else if (player == 2 && dice == 2) {
			return new BPosition(200, 300);
		} else if (player == 2 && dice == 3) {
			return new BPosition(250, 300);
		} else {
			return new BPosition(300, 300);
		}
	}

	public PHitBox getPlayerBoard(int player) {
		if (player == 1)
			return new PHitBox(505, 900, 22, 578);
		else
			return new PHitBox(70, 465, 22, 578);
	}

	public static int getOBIndex(int player, int index) {
		if (player == 2)
			return index * 15;
		else
			return -(index * 15);
	}

	public static int getIndex(int point, int index) {
		
		if (point >= 12) {
			
			if(point == 25)
				return -(index*20);
			if(point == 26)
				return (index*20);
			//Pyramidenmodus
			// 5 unten, dann 4, dann 3 , dann 2, dann 1 
			return ImageBoard.getPyramid(index);
		} else {
			//Pyramidenmodus
			// 5 unten, dann 4, dann 3 , dann 2, dann 1 
			return -(ImageBoard.getPyramid(index));
		}
	}
	private static int getPyramid(int index)
	{
		switch(index)
		{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				return (index*42);
			case 5:
			case 6:
			case 7:
			case 8:
				return ((index%5)*42)+21;
			case 9:
			case 10:
			case 11:
				return ((index-8)*42);
			case 12:
			case 13:
				return ((index-11)*42)+21;
			case 14:
				return ((index-12)*42);
		}
		return 0;
	}
	public static BPosition getBarPosition(int player) {
		if (player == 1)
			return new BPosition(485, 440);
		else
			return new BPosition(485, 160);
	}

	public static BPosition getOutPosition(int player) {
		if (player == 1)
			return new BPosition(935, 554);
		else
			return new BPosition(935, 46);
	}

	public Vector<BPosition> getPositionMatrix() {

		return PositionMatrix;
	}

	public Integer getHighestIndex(int point) {
		int result = -1;

		for (BChecker c : this.checker) {
			if (c.getPoint() == point && c.getIndex() > result)
				result = c.getIndex();
		}
		return result;
	}

	public BChecker getHighestChecker(int point, int player) {
		BChecker result = null;

		for (BChecker c : this.checker) {
			if (result == null)
				result = c;
			if (c.getPoint() == point && c.getIndex() >= result.getIndex() && (c.getPlayer() == player || player == 0))
				result = c;
		}
		return result;
	}

	public void setFocus(BChecker tmp) {
		this.checker.remove(tmp);
		this.checker.add(tmp);
	}

	public void addDice(int player, Integer value, int dice) {

		BDice tmp = new BDice(player, value);
		
		BPosition p = null;
		
		if(dice == 0)
		{
			//Double Dice
			p = this.getDoubleDicePosition(player);
		}
		else
		{
			//Normal Dice
			p = this.getDicePosition(player, dice);
			
		}
		
		this.animationManager.addDiceAnimation(tmp, p.getX(), p.getY());
		
		this.repaint();
	}

	public Vector<BDice> getDices() {
		return this.dice;
	}

	// Von Marc geklaut
	public int roll(int min, int max) {

		Random generator = new Random(Calendar.getInstance().getTimeInMillis());

		if (min < max && min >= 0 && max >= 0) {
			return generator.nextInt(max) + min;
		} else {
			return generator.nextInt(6) + 1;
		}
	}

	public BackgammonViewGUI getView() {
		return view;
	}

	public AnimationManager getAnimation() {
		return this.animationManager;
	}

	public void setDoubleDice(int playerID, Integer i) {
		this.dDice = new BDice(playerID, i);
		
	}
	public BDice getDoubleDice()
	{
		return this.dDice;
	}
	public void setPossibleMoves(Vector<Move> possibleMoves2) {
		
		//Leere Liste, pack alle moves in die Liste
		this.possibleMoves.clear();
		
		this.possibleMoves = new Vector<Move>(possibleMoves2);
		
	}
	public void clearPossibleMoves()
	{
		this.possibleMoves.clear();
	}
}
