package backgammon.view.helpers;

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