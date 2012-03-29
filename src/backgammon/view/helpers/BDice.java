package backgammon.view.helpers;

public class BDice {
	
	private int p;
	private int i;

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

}
