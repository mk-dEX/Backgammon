package backgammon.view.helpers;

public class BDice {
	
	private int p;
	private int i;
	private int x;
	private int y;
	private int rvalue;
	private int rotation;

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

}