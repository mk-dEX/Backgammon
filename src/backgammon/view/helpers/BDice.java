package backgammon.view.helpers;

public class BDice {
	
	private int p;
	private int i;
	private int x;
	private int y;
	private int rvalue;
	private int rotation;
	private boolean used = false;
	
	public static enum diceType {
		DICE,
		DOUBLE_DICE
	}

	public BDice(int p, int i) {
		this.p = p;
		this.i = i;
	}
	public int getPlayer()
	{
		return this.p;
	}
	public int getValue()
	{
		return this.i;
	}
	public void setCoords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setRValue(int i)
	{
		this.rvalue = i;
	}
	public int getRValue()
	{
		return this.rvalue;
	}
	public int getRotation() {
		return this.rotation;
	}
	public void setRotation(int i) {
		this.rotation = i;
	}
	public Boolean isUsed()
	{
		return this.used;
	}
	public void setUsed()
	{
		this.used = true;
	}

}
