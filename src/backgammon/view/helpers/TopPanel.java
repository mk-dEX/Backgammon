package backgammon.view.helpers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class TopPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;


	public TopPanel(String checkerpfad, String name) {
		DefaultComponentFactory compFactory = DefaultComponentFactory
				.getInstance();
		JComponent seperator = compFactory.createSeparator(name);

		// Add Seperator
		this.add(seperator, BorderLayout.SOUTH);

		// set Dimensions
		Dimension pl_bars = new Dimension();
		pl_bars.height = 70;
		pl_bars.width = 345;

		// Set Size
		this.setPreferredSize(pl_bars);

		// Set Border
		this.setBorder(new EtchedBorder());

		// Add Checkerimage
		this.img = new ImageIcon(getClass().getResource("/img/" + checkerpfad))
				.getImage();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(this.img, 10, 10, null);
	}
}
