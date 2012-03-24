package backgammon.view.helpers;

class BChecker
	{
		private int point;
		private int index;
		private int player;
		private Place position;
		private BPosition coords;
		
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
		protected Place getPosition() {
			return position;
		}
		protected void setPosition(Place position) {
			this.position = position;
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
		protected void setCoords(int x, int y) {
			this.coords = new BPosition(x, y);
		}
		protected BPosition getCoords() {
			return this.coords;
		}
	}