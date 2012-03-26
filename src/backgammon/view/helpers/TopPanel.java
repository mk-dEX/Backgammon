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

import backgammon.view.BackgammonViewGUI;

public class TopPanel extends JPanel{

	private Image img;
	private BackgammonViewGUI view;
	
	public TopPanel(String checkerpfad, String name)
	{
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		JComponent seperator = compFactory.createSeparator(name);
		
		//Add Seperator
		this.add(seperator,BorderLayout.NORTH);
		
		//set Dimensions
		Dimension pl_bars = new Dimension();
		pl_bars.height = 70;
		pl_bars.width = 385;
		
		//Set Size
		this.setPreferredSize(pl_bars);
		
		
		//Set Border
		this.setBorder(new EtchedBorder());
		
		//Add Checkerimage
		this.img = new ImageIcon(getClass().getResource("/img/"+checkerpfad)).getImage();
		
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(this.img, 10, 10, null);
	}
}
