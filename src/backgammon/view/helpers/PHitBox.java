package backgammon.view.helpers;

public class PHitBox
	{
		private int x1;
		private int y1;
		private int x2;
		private int y2;
			
		PHitBox(int x1, int x2,int y1, int y2)
		{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public int getX1() {
			return x1;
		}
		public int getX2() {
			return x2;
		}
		public int getY1() {
			return y1;
		}
		public int getY2() {
			return y2;
		}
	}