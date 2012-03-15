package backgammon.view;

import backgammon.view.styles.Standard;

public class BackgammonViewGUI {
	
	/**
	 * Represents the actual style of the game
	 */
	private Style style;

	/**
	 * Normal Constructor, if no style is choosed, the default one will be taken.
	 */
	public BackgammonViewGUI() {
		super();
		this.style = new Standard();
	}
	
	/**
	 * Normal Constructor with specific style to use
	 * 
	 * @param style The Style for Board and Checkers
	 */
	public BackgammonViewGUI(Style style) {
		super();
		this.style = style;
	}
	
	/**
	 * 
	 * @return Style actual Board style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * 
	 * @param style the style the board should use from now on.
	 */
	public void setStyle(Style style) {
		this.style = style;
	}
	

}
